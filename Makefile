include .env
export

install:
	mvn clean install

sam-build:
	sam build

sam-local: install sam-build
	sam local start-api

services:
	docker compose up -d

migrate:
	mvn clean flyway:migrate -f core/pom.xml