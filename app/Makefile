# Makefile
FILEPATH1=src/test/resources/json1.json
FILEPATH2=src/test/resources/json2.json
FILEPATH3=src/test/resources/json3.json
FILEPATH4=src/test/resources/json4.json
FILEPATH5=src/test/resources/yaml1.yml
FILEPATH6=src/test/resources/yaml2.yml

.PHONY: build

run-dist: build test lint test-report run # Build, check and run app

run: # Run app
	./build/install/app/bin/app $(FILEPATH3) $(FILEPATH4)

build: # Build app
	./gradlew clean
	./gradlew installDist

test: # Run tests
	./gradlew test

lint: # Check code style
	./gradlew checkstyleMain checkstyleTest

test-report: # Check code coverage
	./gradlew jacocoTestReport
