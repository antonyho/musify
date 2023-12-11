Music Artist Directory (Musify)
====

## Challenges

### High Traffic
The service will have tens of thousands of requests per second at peak times. It is gathering information from different sources through API calls. These API calls have significant response time.

#### Solutions
The best approach should be using a cache service to cache all the results from 3rd parties.
Some famous artists are usually consuming over 90% of the traffic, by theory, according to the distribution researches and studies on Statistic. We can adjust a short expiry time to clean up non-frequent query results.
However, dedicated cache service for example Cassandra, Redis, or ElasticSearch consume a lot of system resources. Hence, are not always available.
A short lifetime built-in library cache for JVM ecosystem could be introduced as a simple solution. For example:
- [Spring Caching](https://spring.io/guides/gs/caching/)
- [Guava](https://github.com/google/guava)
And to shorten overall time on API calls to 3rd parties, we shall make asynchronous parallel API calls.

## Notes

### Work in Progress
Due to the amount of time, I am still working on the unit tests and end-to-end tests.

### Consideration

#### Gradle over Maven
Gradle provides better features and flexibility to modern JVM related project.

#### MVC Model
MVC package structure is not applied in this project. Even though, the business logic is split into Controller and Service. But this RESTful API service is only serving a single GET endpoint. The complexity of such a SOLID design microservice does not need a complicated package structure.

#### AWS Lambda
This project was started from building a Spring Boot service with `spring-cloud-starter-function-web` and `spring-cloud-function-adapter-aws` modules.
But I considered that using serverless function, i.e. AWS Lambda, on Spring Boot is not a good idea. Even though, I will have the benefit on running this same service as a standalone service locally and also capable to run it on AWS Lambda. In production environment, we don't mix them both. Using Spring Boot has a way more costly cold start than using plain `com.amazonaws.services.lambda.runtime.RequestHandler`. Especially when we apply SOLID principles.

#### Wikidata Official Toolkit
I took use of the [official toolkit](https://wikidata.github.io/Wikidata-Toolkit/) from Wikidata, even though I have implemented the simple RestClient call already.
Using official library is my preferred approach unless it is not fulfilling my requirement. I am keeping my own implementation of the API call as a track for your reference.
But the best approach is to remove the unused code from the project to reduce the confusion among the team and also with better readability.
Teammate would be confused if we keep staled code. They might think the staled code is still being in use.

#### CoverArtArchive API Library
Same reason as above, as MusicBrainz has endorsed a [Java library](https://github.com/lastfm/coverartarchive-api) provided by last.fm.
I am keeping my own RestClient implementation for your reference again. With the same philosophy, I would remove my unused RestClient implementation if this is not a coding challenge.

#### Usage of RestClient
This project is using `org.springframework.web.client.RestClient` instead of the well known `RestTemplate` for MusicBrainz API integration.
It is a modern REST client than `RestTemplate`. But it is still very new and not as flexible as `RestTemplate`. For example, it cannot handle redirected HTTP request.
The project is using `RestTemplate` in the Wikipedia API client, due to the HTTP redirect issue. The usage of `RestClient` shall be discarded and to adopt the usage of `RestTemplate` with better reliability.
But with concern on completion time, this small change is not done.


## Usage Instructions

### Pre-requites
The usage instructions here suppose that your environment has **Docker** installed and ready to use.
The make script is written for **POSIX** Shell environment with build tools installed.
These instructions work on **Linux** and **macOS**. Not on Windows environment.

### Get the Project
```shell
git clone https://github.com/antonyho/musify
cd musify
```

### Build
```shell
make
```

### Quick Run on localhost
```shell
make run
```

### On Windows
If you are using Windows environment, you may use the bundled Gradle wrapper to build and run the project.

#### Build
```
./gradlew.bat clean
./gradlew.bat build
```

#### Quick Run on localhost
```
./gradlew.bat bootRun
```


### Usage
If the service is already running on local environment, use `curl`, Postman, or browser to access the API endpoint at **port 8080**.
Endpoint: `http://localhost:8080/musify/music-artist/details/{MusicBrainzID}`

For example:
```
curl -X GET "http://localhost:8080/musify/music-artist/details/f27ec8db-af05-4f36-916e-3d57f91ecf5e"
```