build:
	mvn clean install
	sam build

local: build
	sam local start-api --warm-containers lazy