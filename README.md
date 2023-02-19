Example for config server

Default port: **8888**

In a Spring Boot microservice architecture, a Config Server can be used to store and manage configuration properties for all services in a central location, making it easier to manage the configuration of multiple services. Here are the steps to create an app using a Config Server in Spring Boot microservice:

1. Add the spring-cloud-starter-config dependency to your pom.xml or build.gradle file to include the Config Server in your application.

2. Create a Spring Boot application for the Config Server, which will store the configuration files. You can use the @EnableConfigServer annotation to enable the Config Server.

3. Create a Git repository to store your configuration files. You can store multiple configuration files for different profiles, such as application.properties or application-dev.properties.

4. Configure the Config Server to connect to the Git repository. You can specify the repository URL, username, and password in the bootstrap.properties file.

5. In your microservices, add the spring-cloud-starter-config dependency and configure the Config Client to connect to the Config Server. You can do this by specifying the Config Server URL and the name of the configuration file for your service.

6. Use the @RefreshScope annotation in your microservices to enable the dynamic refreshing of configuration properties. This allows you to change the configuration properties in the Git repository and have the changes reflected in your microservices without having to restart them.

By following these steps, you can create a Spring Boot microservice architecture that is easily configurable and scalable using a Config Server.
