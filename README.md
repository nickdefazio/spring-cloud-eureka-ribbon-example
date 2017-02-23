# spring-cloud-eureka-ribbon-example

## Description
This project is meant to create an example of how ribbon and eureka work together to allow integration with other services in the Spring Cloud Netflix. It is composed of pieces from the following Spring Cloud tutorials:
* https://spring.io/guides/gs/client-side-load-balancing/ (say-hello and user services)
* https://spring.io/blog/2015/01/20/microservice-registration-and-discovery-with-spring-cloud-and-netflix-s-eureka (eureka-service)

## How to run
**Start eureka-service**
```
//From ${project root}/eureka-service
gradle clean build
java -jar build/libs/eureka-service-0.0.1-SNAPSHOT.jar
```

* Verify that euerka is running by going to `http://localhost:8761`


**Start the say-hello service**
```
//From ${project root}/say-hello
gradle clean build
java -jar -Dserver.port=8081 build/libs/say-hello-0.0.1-SNAPSHOT.jar
```

* Verify that the say hello service has registered with eureka using the eureka dashboard on `http://localhost:8761`
* Verify that `http://localhost:8081/greeting` produces a greeting

**Start the user service**
```
//From ${project root}/user
gradle clean build
java -jar -Dserver.port=8090 build/libs/user-0.0.1-SNAPSHOT.jar
```
* Verify that the user service has registered with eureka using the eureka dashboard on `http://localhost:8761`
* Verify that `http://localhost:8090/user` produces a greeting with a name

**Bring up multiple say-hello services**
```
//From ${project root}/say-hello
java -jar -Dserver.port=8082 build/libs/say-hello-0.0.1-SNAPSHOT.jar
java -jar -Dserver.port=8083 build/libs/say-hello-0.0.1-SNAPSHOT.jar

```
* Verify that eureka is showing multiple instances of the say-hello service are registered