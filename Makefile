install:
	mvn clean install

build:
	sam build

local: install build
	sam local start-api