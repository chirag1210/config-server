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


**Step 01** - Setting up Limits Microservice

On Spring Initializr, choose:

Group Id: com.in28minutes.microservices
Artifact Id: limits-service
Dependencies
Web
DevTools
Actuator
Config Client

**Step 2** Create Configuration class
Configuration.java

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//ConfigurationProperties i.e limits-service name should be matched with github file i.e limit-service-dev.properties  
@Component
@ConfigurationProperties("limits-service") 
public class Configuration {
 //declare variable and setter and getter

}

**Step 3: Access this configuration file in controller
@RestController
public class LimitsController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(configuration.getMinimum(), 
				configuration.getMaximum());
//		return new Limits(1,1000);
	}
}

**Step 4** Create limit-service-dev.properties in github file
limit-service.min=5
limit-service.max=995

** Step 5** Setting up Spring Cloud Config Server

On Spring Initializr, choose:

Group Id: com.in28minutes.microservices
Artifact Id: spring-cloud-config-server
Dependencies
DevTools
Config Server

** Step 6** application.properties
spring.application.name=spring-cloud-config-server
server.port=8888
spring.cloud.config.server.git.uri=https://github.com/chirag1210/springcloudconfiguration


** Step 7** aaplication class 
 SpringCloudConfigServerApplication.java

import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer

** Step 8**  Connect Limits Service to Spring Cloud Config Server

URLS

http://localhost:8888/limits-service/default

** Step 9** Configuring Profiles for Limits Service

/limits-service/src/main/resources/application.properties Modified

spring.profiles.active=qa
spring.cloud.config.profile=qa
#spring.cloud.config.name=

spring.application.name=limits-service
spring.config.import=optional:configserver:http://localhost:8888

----------------------------------------------------------------------------------
**Reference** 

https://github.com/in28minutes/spring-microservices-v2/tree/main/03.microservices















