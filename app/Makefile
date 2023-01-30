# Makefile
FILEPATH1=src/test/resources/json1.json
FILEPATH2=src/test/resources/json2.json

run-dist: update-dist run # Build and run app

run: # Run app
	./build/install/app/bin/app $(FILEPATH1) $(FILEPATH2)

update-dist: # Build app
	./gradlew clean
	./gradlew installDist