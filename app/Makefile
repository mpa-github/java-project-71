# Makefile
FILEPATH1=src/test/resources/json/json1.json
FILEPATH2=src/test/resources/json/json2.json
FILEPATH5=src/test/resources/yaml/yaml1.yml
FILEPATH6=src/test/resources/yaml/yaml2.yml
FORMAT1?=stylish
FORMAT2?=plain
FORMAT3?=json

.PHONY: build

run-dist: build test lint test-report run # Build, check and run app

run: # Run app
	./build/install/app/bin/app -f $(FORMAT3) $(FILEPATH3) $(FILEPATH4)

build: # Build app
	./gradlew clean
	./gradlew installDist

test: # Run tests
	./gradlew test

lint: # Check code style
	./gradlew checkstyleMain checkstyleTest

test-report: # Check code coverage
	./gradlew jacocoTestReport
