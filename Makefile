b: build
build: build-npm build-maven
build-npm:
	cd from-paris-to-berlin-web && yarn install && npm run build
build-maven:
	mvn clean install -DskipTests
build-test:
	mvn clean install
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
stop:
	docker-compose down --remove-orphans
prune-all: docker-delete
	docker network prune
	docker system prune --all
	docker builder prune
	docker system prune --all --volumes
case:
	cd from-paris-to-berlin-demo && ./make-demo.sh
update:
	npm install -g npm-check-updates
	cd from-paris-to-berlin-web && ncu -u && yarn
install-update: update
	npm i -g snyk
audit:
	cd from-paris-to-berlin-web && npm audit fix && yarn
