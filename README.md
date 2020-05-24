# subscription-microservices
## Creating a group of microservices with Ribbon, Feign and Slueth features
Demonstrate to create a group of microservices, provide a gateway for access, with service discovery, externalized configuration, and client-side load balancing capabilities, retrieve data from [Astronomy Picture of the Day API](https://api.nasa.gov) and [World Time API](https://worldtimeapi.org)

1. Clone this repository to local project parent path
    ```
    mkdir spring-cloud-demo
   
    cd spring-cloud-demo
    
    git clone https://github.com/Han-Wei/subscription-microservices.git
    ```
2. This demonstration use local file repository to store properties file to serve config-service, please initial git-config-service-repo folder as a git repository.
    ```
    cd git-config-service-repo
    
    git init
    
    git add retriever-service-dev.properties
    
    git commit -m "initial local git repository for config-service"
    ```
3. Change the property value: parent_path of application.properties in config-service, to match local project parent path
    ```
    parent_path=project_parent_path
    spring.cloud.config.server.git.uri=file://${parent_path}/subscription-microservices/git-config-service-repo
    ```
    
4. Execute mvn clean package -DskipTests for each service
    ```
    mvn clean package -DskipTests
    ```
5. Start each microservice with follow sequence
    
    `(1) eureka-service`
    ```
    cd ${project_parent}/subscription-microservices/eureka-service/target
    java -jar eureka-service-0.0.1-SNAPSHOT.jar
    ```
    `(2) config-service`
    ```
    cd ${project_parent}/subscription-microservices/config-service/target
    java -jar config-service-0.0.1-SNAPSHOT.jar    
    ```
    `(3) retriever-service`
    ```
    cd ${project_parent}/subscription-microservices/retriever-service/target
    java -jar -Dserver.port=8101 retriever-service-0.0.1-SNAPSHOT.jar
    java -jar -Dserver.port=8102 retriever-service-0.0.1-SNAPSHOT.jar
    java -jar -Dserver.port=8103 retriever-service-0.0.1-SNAPSHOT.jar
    ```
    `(4) subscription-service`
    ```
    cd ${project_parent}/subscription-microservices/subscription-service/target
    java -jar subscription-service-0.0.1-SNAPSHOT.jar    
    ```    
    `(5) zuul-service`
    ```
    cd ${project_parent}/subscription-microservices/zuul-service/target
    java -jar zuul-service-0.0.1-SNAPSHOT.jar    
    ```    
    
6. Access `eureka-service` by access `localhost:8761`, we should see `RETRIEVER-SERVICE`, `SUBSCRIPTION-SERVICE` and `ZUUL-SERVICE` registered. Especially we can see there are 3 instances of registered `RETRIEVER-SERVICE` in Eureka.
    ```
    http://localhost:8761
    ```
6. Access zuul gateway entry-point to GET subscription 101
    ```
    curl http://localhost:8080/subscription-service/subscriptions/101
    ```
    Should get below result (example):
    ```json
    {
    "date": "yyyy-mm-dd",
    "explanation": "xxx",
    "url": "xxx",
    "title": "xxxx",
    "responseBy": "8101"
    }
    ```
    ```
    curl http://localhost:8080/subscription-service/subscriptions/102
    ```
    Should get below result (example):
    ```json
    {
    "datetime": "2020-05-24T23:00:07.574746+08:00",
    "utc_offset": "+08:00",
    "timezone": "Asia/Taipei",
    "responseBy": "8102"
    }
    ```    
    `ResponseBy` property will tell us the port number of `retriever-service` instance(we activated 3 retriever-serivces in port `8101`, `8102` and `8103`) we accessed, if we execute above GET rquest several times, we shoud get `ResponseBy` property in `8101`, `8102` or `8103`
