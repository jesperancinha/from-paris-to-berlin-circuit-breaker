GITHUB_RUN_ID ?=123

b: build
build: build-npm build-maven
build-npm:
	cd from-paris-to-berlin-web && yarn install && npm run build
build-maven:
	mvn clean install -DskipTests
build-test:
	mvn clean install
build-npm-docker:
	cd from-paris-to-berlin-web && [ -d node_modules ] || mkdir node_modules
	cd from-paris-to-berlin-web && chmod 777 node_modules
	touch from-paris-to-berlin-web/yarn.lock
	chmod 777 from-paris-to-berlin-web
	chmod 777 from-paris-to-berlin-web/yarn.lock
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.builder.yml build gui-builder
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.builder.yml up --exit-code-from gui-builder gui-builder
build-npm-cypress-docker:
	cd e2e && [ -d node_modules ] || mkdir node_modules
	cd e2e && chmod 777 node_modules
	touch e2e/yarn.lock
	chmod 777 e2e
	chmod 777 e2e/yarn.lock
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.builder.yml build cypress-builder
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.builder.yml up --exit-code-from cypress-builder cypress-builder
test:
	mvn test
test-maven:
	mvn test
local: no-test
	mkdir -p bin
no-test:
	mvn clean install -DskipTests
docker-clean:
	docker-compose -p ${GITHUB_RUN_ID} rm -svf
docker:
	rm -rf out
	docker-compose -p ${GITHUB_RUN_ID} up -d --build --remove-orphans
docker-local:
	cd docker/local
	docker-compose -p ${GITHUB_RUN_ID} up -d --build --remove-orphans
delete-all:
	docker ps -a --format '{{.ID}}' | xargs -I {}  docker stop {}
	docker ps -a --format '{{.ID}}' | xargs -I {}  docker rm {}
docker-clean-build-start: docker-clean b docker
docker-clean-start: docker-clean docker
docker-delete: stop
	docker ps -a --format '{{.ID}}' -q --filter="name=from_paris_to_berlin"| xargs -I {}  docker stop {}
	docker ps -a --format '{{.ID}}' -q --filter="name=from_paris_to_berlin"| xargs -I {}  docker rm {}
docker-action: build-npm-docker
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.builder.yml up -d from-paris-to-berlin-service from-paris-to-berlin-fe
docker-clean-network:
	docker network prune
stop:
	docker-compose -p ${GITHUB_RUN_ID} down --remove-orphans
prune-all: docker-delete
	docker network prune
	docker system prune --all
	docker builder prune
	docker system prune --all --volumes
case:
	cd from-paris-to-berlin-demo && ./make-demo.sh
demo: case
demo-direct:
	cd from-paris-to-berlin-demo && ./make-demo-direct.sh
demo-docker:
	cd from-paris-to-berlin-demo && ./make-demo-docker.sh
cypress-install:
	cd e2e && make build
cypress-open:
	cd e2e && make cypress-open
cypress-open-docker:
	cd e2e && make cypress-open-docker
cypress-electron:
	cd e2e && make cypress-electron
cypress-chrome:
	cd e2e && make cypress-chrome
cypress-firefox:
	cd e2e && make cypress-firefox
cypress-firefox-full:
	cd e2e && make cypress-firefox-full
cypress-edge:
	cd e2e && make cypress-edge
remove-lock-files:
	find . -name "package-lock.json" | xargs -I {} rm {}; \
	find . -name "yarn.lock" | xargs -I {} rm {};
update: remove-lock-files
	npm install -g npm-check-updates
	git pull
	npm install caniuse-lite
	npm install -g npm-check-updates
	cd from-paris-to-berlin-web; \
 		yarn; \
 		npx browserslist --update-db; \
 		ncu -u; \
 		yarn; \
 		cd ..
	cd from-paris-to-berlin-web; \
 		yarn; \
 		npx browserslist --update-db; \
 		ncu -u; \
 		yarn
install-update: update
	npm i -g snyk
audit:
	cd from-paris-to-berlin-web && npm audit fix && yarn
fptb-wait:
	bash fptb_wait.sh
dcd:
	docker-compose -p ${GITHUB_RUN_ID} down --remove-orphans
dcd-all: dcd
	docker-compose down --remove-orphans
dcp:
	docker-compose -p ${GITHUB_RUN_ID} stop
dcup: dcd docker-clean docker fptb-wait
dcup-full-action: dcd docker-clean no-test build-npm docker fptb-wait
dcup-action: dcp docker-action fptb-wait
build-fe-nginx: build-npm build-nginx
build-nginx:
	docker-compose -p ${GITHUB_RUN_ID} stop from-paris-to-berlin-fe
	docker-compose -p ${GITHUB_RUN_ID} rm from-paris-to-berlin-fe
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache from-paris-to-berlin-fe
	docker-compose -p ${GITHUB_RUN_ID} up -d
build-ws:
	cd from-paris-to-berlin-ws-service && mvn clean install -DskipTests
	docker-compose -p ${GITHUB_RUN_ID} stop from-paris-to-berlin-ws-service
	docker-compose -p ${GITHUB_RUN_ID} rm from-paris-to-berlin-ws-service
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache from-paris-to-berlin-ws-service
	docker-compose -p ${GITHUB_RUN_ID} up -d from-paris-to-berlin-ws-service
build-aop:
	cd from-paris-to-berlin-resilience4j-aop-spring-app && mvn clean install -DskipTests
	docker-compose -p ${GITHUB_RUN_ID} stop from-paris-to-berlin-service
	docker-compose -p ${GITHUB_RUN_ID} rm from-paris-to-berlin-service
	docker-compose -p ${GITHUB_RUN_ID} build --no-cache from-paris-to-berlin-service
	docker-compose -p ${GITHUB_RUN_ID} up -d from-paris-to-berlin-service
node-update:
	curl https://raw.githubusercontent.com/creationix/nvm/master/install.sh | bash
	source ~/.nvm/nvm.sh
	nvm install --lts
	nvm use --lts
deps-update: update
revert-deps-cypress-update:
	if [ -f  e2e/docker-composetmp.yml ]; then rm e2e/docker-composetmp.yml; fi
	if [ -f  e2e/packagetmp.json ]; then rm e2e/packagetmp.json; fi
	git checkout e2e/docker-compose.yml
	git checkout e2e/package.json
deps-cypress-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/cypressUpdateOne.sh | bash
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash
deps-java-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/javaUpdatesOne.sh | bash
deps-quick-update: deps-cypress-update deps-plugins-update deps-java-update
