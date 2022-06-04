Question: 1 What are the possible mechanisms provided by Spring Security to store user details?

Question: 2 What is the easiest method to write a unit test?

Correct Answer:  @RequestMapping("/displayAccount") 
String displayAccount(@RequestParam("accountId") int id, Model model)

Description: This method is not dependent of the servlet API. Id of the account to display may be directly 
passed through the call stack. Thus test methods are simplified.

Question: 3 How could you secure MVC controller with Spring Security? Select a unique answer.

Question: 4 What could not return a Spring MVC controller? Select a single answer.

Description: Spring does not allow to return an absolute path to the view

Question: 5 What is true about Spring security configuration and the security namespace?

Correct Answer:  The patterns declared into the intercept-url tag are analyzed from up to bottom. Winning is the first that matches.

Description: If more than one intercept-url matches, the top one is used.

Question: 6 What is the method that is not provided by the JmsTemplate Spring class?
Description: The onMessage method does not exist.

Question: 7 Where do you cannot declare Spring MVC controller? Select correct answer.

Description: In the web.xml, you may declarer and a ContextLoaderListener and a DispatcherServlet that 
are in charge to load XML Spring configuration files. But you cannot declare controllers 
directly in these file.

Question: 8 What is the purpose of the @ManageResource annotation?
Description: @ManageResources identify a Spring bean as a JMX MBean

Question: 9 What is exact about the HttpInvokerServiceExporter?
Correct Answer:  Has to run into a HTPP server as Jetty

Description: HttpInvokerServiceExporter requires a HTTP web server to process incoming http request. 
Tomcat or Jetty is possible candidates. Spring also supports the Oracle/Sun´s JRE 1.6 HTTP 
server. 

Question: 10 What do you have to do even if you are using the RMI Spring Remoting support? 

Description: Object that are transferred via RMI are serializabled/unserializabled. So they have to 
implement the Serializable interface.

Q11: how to disable spring banner mode
 3 ways:
 -- spring.main.banner-mode=off in application.properties
 -- by console: $java -Dspring.main.banner-mode=off -java myApp.jar
 -- code:
 @SpringBootApplication
 public class SpringBootWebApplication {
  
     private static Logger logger = LoggerFactory.getLogger(SpringBootWebApplication.class);
  
     public static void main(String[] args) throws Exception {
  
         SpringApplication springApp = new SpringApplication(SpringBootWebApplication.class);
         springApp.setBannerMode(Banner.Mode.OFF);
         springApp.run(args);
     }
 }
 
 Q12: Difference between @ConfigurationProperties and @Value
      
      @Value is a core container feature and it does not provide the same features as 
      type-safe Configuration Properties. The table below summarizes the features that 
      are supported by @ConfigurationProperties and @Value:
      
      Feature     	     @ConfigurationProperties     	@Value
      Relaxed binding   	Yes	                         No
      Meta-data support  	Yes	                         No
      SpEL evaluation	    No	                         Yes
      
      If you define a set of configuration keys for your own components, spring boot recommends
       you to group them in a POJO annotated with @ConfigurationProperties. Please also be aware 
       that since @Value does not support relaxed binding, it isn’t a great candidate if you need
       to provide the value using environment variables.
      
      Finally, while you can write a SpEL expression in @Value, such expressions are not processed
       from Application property files.
     
Q13:
How to rename application.properties file in Spring Boot application?
It is a standard practice that during our production deployments, our application loads property files from external locations. This helps us to change our configurations without changing our code. Sometimes we also change default file names according to our needs in production environment. In this page, we will come to know how to load external property file which is renamed into Spring Boot application.
By default, Spring Boot look for externalized default property file application.properties into given below predetermined locations:
 -- In the classpath root.
 -- In the package "/config" in classpath.
 -- In the current directory.
 -- In the "/config" directory of current folder.

Now lets say, you have renamed your default application.properties file like myapp.properties. There are 3 ways of loading it to your Spring Boot application.
--Command line arguments
Use command line argument "--spring.config.name" to pass the renamed file name to the Spring Boot application as shown below:

java -jar myAppBuild.jar --spring.config.name=myapp
Environment variables
In the second approach, you can configure your externalized configuration details into environment variables and your Spring Boot application will read it from your environment as shown below:

--Terminal

set SPRING_CONFIG_NAME=myapp
 
java -jar myAppBuild.jar

Programatically loding configurations

SpringBootWebApplication

package com.java2novice.springboot;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
 
@SpringBootApplication
public class SpringBootWebApplication {
 
    private static Logger logger = LoggerFactory.getLogger(SpringBootWebApplication.class);
 
    public static void main(String[] args) throws Exception {
 
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBootWebApplication.class)
                .properties("spring.config.name:myapp")
                .build()
                .run(args);
    }
}

Q15:What is @SpringBootApplication annotation in spring boot?
    
  Many Spring Boot developers always have their main class annotated with @Configuration, @EnableAutoConfiguration and 
  @ComponentScan. Since these annotations are so frequently used together (especially if you follow the best practices 
  above), Spring Boot provides a convenient @SpringBootApplication alternative.
     
   The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration and
    @ComponentScan with their default attribute.
    
    The following are the parameters accepted in the @SpringBootApplication annotation:
    
   exclude: Exclude the list of classes from the auto configuration.
    
   excludeNames: Exclude the list of fully qualified class names from the auto configuration. This parameter added since spring boot 1.3.0.
    
   ScanBasePackageClasses: Provide the list of classes that has to be applied for the @ComponentScan.
    
   scanBasePackages Provide the list of packages that has to be applied for the @ComponentScan. This parameter added since spring boot 1.3.0.

Q16:
How to load external property files into Spring Boot application?

It is a standard practice that during our production deployments, our application loads property files from external locations. 
This helps us to change our configurations without changing our code. In this page, we will come to know how to load 
external property files into Spring Boot application.

By default, Spring Boot look for externalized default property file application.properties into given below predetermined locations:

 -- In the classpath root.

 -- In the package "/config" in classpath.

 -- In the current directory.

 -- In the "/config" directory of current folder.

Now lets say, your application requires externalized properties like application.properties and another property file myapp.properties.
The both properties can be in the same folder or they can be in different folder. There are 3 ways of doing it.

 1)Command line arguments
In the first approach, all you need to do is pass folder names and property names as part of command line arguments as shown below:

Terminal: java -jar myapp.jar --spring.config.name=application,myapp
--spring.config.location=classpath:/data/myapp/config,classpath:/data/myapp/external/config
In the above command, we are passing property file name as part of "--spring.config.name" variable and folder location as part of "--spring.config.location" variable.

2) Environment variables
In the second approach, you can configure your externalized configuration details into environment variables and your Spring Boot application will read it from your environment as shown below:

> set SPRING_CONFIG_NAME=application,myapp
 
> set SPRING_CONFIG_LOCATION=classpath:/data/myapp/config,classpath:/data/myapp/external/config
   java -jar myapp.jar

3) Programatically loding configurations

SpringBootWebApplication
package com.java2novice.springboot;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
 
@SpringBootApplication
public class SpringBootWebApplication {
 
    private static Logger logger = LoggerFactory.getLogger(SpringBootWebApplication.class);
 
    public static void main(String[] args) throws Exception {
 
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBootWebApplication.class)
                .properties("spring.config.name:application,myapp",
                        "spring.config.location:classpath:/data/myapp/config,classpath:/data/myapp/external/config")
                .build().run(args);
 
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
 
        logger.info(environment.getProperty("cmdb.resource-url"));
    }
}