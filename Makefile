PWD=$(shell pwd)
GRADLE_VERSION=8.5-jdk17

.PHONY: all gradle clean test run

all: clean build

gradle: # Only needed for project initialisation
	docker run --rm -u gradle -v ${PWD}:/home/gradle/project -w /home/gradle/project gradle:${GRADLE_VERSION} gradle wrapper

clean:
	docker run --rm -u gradle -v ${PWD}:/home/gradle/project -w /home/gradle/project gradle:${GRADLE_VERSION} gradle clean

test:
	docker run --rm -u gradle -v ${PWD}:/home/gradle/project -w /home/gradle/project gradle:${GRADLE_VERSION} gradle clean test --info

build: # Build using Gradle official Docker image
	# Supposing user ID on the host system is 1000 which matches the `gradle` user in the Docker image
	docker run --rm -u gradle -v ${PWD}:/home/gradle/project -w /home/gradle/project gradle:${GRADLE_VERSION} gradle clean build --stacktrace

run: # Run a local Spring Boot server with debug log level
	docker run --rm -u gradle -v ${PWD}:/home/gradle/project -w /home/gradle/project -p 8080:8080 gradle:${GRADLE_VERSION} gradle bootRun -Pargs=--logging.level.root=DEBUG,--logging.level.net.antonyho.musify=DEBUG