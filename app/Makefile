# Makefile
FILEPATH1=src/test/resources/json1.json
FILEPATH2=src/test/resources/json2.json

run-dist: update-dist test lint run # Build, check and run app

run: # Run app
	./build/install/app/bin/app $(FILEPATH1) $(FILEPATH2)

update-dist: # Build app
	./gradlew clean
	./gradlew installDist

test: # Run tests
	./gradlew test

lint: # Check code style
	./gradlew checkstyleMain checkstyleTest