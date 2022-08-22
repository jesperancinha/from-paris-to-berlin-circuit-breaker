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
	docker-compose -f docker-compose.yml -f docker-compose.builder.yml build gui-builder
	docker-compose -f docker-compose.yml -f docker-compose.builder.yml up --exit-code-from gui-builder gui-builder
build-npm-cypress-docker:
	cd e2e && [ -d node_modules ] || mkdir node_modules
	cd e2e && chmod 777 node_modules
	touch e2e/yarn.lock
	chmod 777 e2e
	chmod 777 e2e/yarn.lock
	docker-compose -f docker-compose.yml -f docker-compose.builder.yml build cypress-builder
	docker-compose -f docker-compose.yml -f docker-compose.builder.yml up --exit-code-from cypress-builder cypress-builder
test:
	mvn test
test-maven:
	mvn test
local: no-test
	mkdir -p bin
no-test:
	mvn clean install -DskipTests
docker-clean:
	docker-compose rm -svf
docker:
	rm -rf out
	docker-compose up -d --build --remove-orphans
docker-local:
	cd docker/local
	docker-compose up -d --build --remove-orphans
docker-clean-build-start: docker-clean b docker
docker-clean-start: docker-clean docker
docker-delete: stop
	docker ps -a --format '{{.ID}}' -q --filter="name=from_paris_to_berlin"| xargs -I {}  docker stop {}
	docker ps -a --format '{{.ID}}' -q --filter="name=from_paris_to_berlin"| xargs -I {}  docker rm {}
docker-action: build-npm-docker
	docker-compose -f docker-compose.yml -f docker-compose.builder.yml up -d from_paris_to_berlin_service from_paris_to_berlin_fe
docker-clean-network:
	docker network prune
stop:
	docker-compose down --remove-orphans
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
audit:
	cd from-paris-to-berlin-web && npm audit fix && yarn
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
update:
	npm install -g npm-check-updates
	cd from-paris-to-berlin-web && npx browserslist --update-db && ncu -u && yarn
install-update: update
	npm i -g snyk
audit:
	cd from-paris-to-berlin-web && npm audit fix && yarn
fptb-wait:
	bash fptb_wait.sh
dcd:
	docker-compose down --remove-orphans
dcp:
	docker-compose stop
dcup: dcd docker-clean docker fptb-wait
dcup-full-action: dcd docker-clean no-test build-npm docker fptb-wait
dcup-action: dcp docker-action fptb-wait
