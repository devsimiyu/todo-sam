include .env
export

install:
	mvn clean install

build:
	sam build

local: services install build
	sam local start-api

services:
	docker compose up -d

migrate:
	mvn clean flyway:migrate -f core/pom.xml