Q1. What is Spring Boot and What Are Its Main Features?
    Spring Boot is essentially a framework for rapid application development built on top of the Spring Framework. With
     its auto-configuration and embedded application server support, combined with the extensive documentation and 
     community support it enjoys, Spring Boot is one of the most popular technologies in the Java ecosystem as of date.
    
    Here are a few salient features:
    
    Starters – a set of dependency descriptors to include relevant dependencies at a go
    Auto-configuration – a way to automatically configure an application based on the dependencies present on the classpath
    Actuator – to get production-ready features such as monitoring
    Security
    Logging
    
Q2. How to Disable a Specific Auto-Configuration?
    If we want to disable a specific auto-configuration, we can indicate it using the exclude attribute of the 
    EnableAutoConfiguration annotation. For instance, this code snippet neutralizes DataSourceAutoConfiguration:
    
    // other annotations
    @EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
    public class MyConfiguration { }
    If we enabled auto-configuration with the @SpringBootApplication annotation — which has @EnableAutoConfiguration as 
    a meta-annotation — we could disable auto-configuration with an attribute of the same name:
    
    // other annotations
    @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
    public class MyConfiguration { }
    
   We can also disable an auto-configuration with the spring.autoconfigure.exclude environment property. This setting in
     the application.properties file does the same thing as before:
    
    spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
    
Q3. How to Register a Custom Auto-Configuration?
    To register an auto-configuration class, we must have its fully-qualified name listed under the EnableAutoConfiguration
     key in the META-INF/spring.factories file:
    
   org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.baeldung.autoconfigure.CustomAutoConfiguration
    If we build a project with Maven, that file should be placed in the resources/META-INF directory, which will end up 
    in the mentioned location during the package phase.

Q4. How to Tell an Auto-Configuration to Back Away When a Bean Exists?
   To instruct an auto-configuration class to back off when a bean is already existent, we can use the 
   @ConditionalOnMissingBean annotation. The most noticeable attributes of this annotation are:
    
    value: The types of beans to be checked
    name: The names of beans to be checked
    When placed on a method adorned with @Bean, the target type defaults to the method's return type:
    
    @Configuration
    public class CustomConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public CustomService service() { ... }
    }
    
Q5. How to Deploy Spring Boot Web Applications as Jar and War Files?
    Traditionally, we package a web application as a WAR file, then deploy it into an external server. Doing this allows 
   us to arrange multiple applications on the same server. During the time that CPU and memory were scarce, this was a 
   great way to save resources.
    
    However, things have changed. Computer hardware is fairly cheap now, and the attention has turned to server configuration. 
  A small mistake in configuring the server during deployment may lead to catastrophic consequences.
    
  Spring tackles this problem by providing a plugin, namely spring-boot-maven-plugin, to package a web application as 
  an executable JAR. To include this plugin, just add a plugin element to pom.xml:
    
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
    With this plugin in place, we'll get a fat JAR after executing the package phase. This JAR contains all the necessary 
    dependencies, including an embedded server. Thus, we no longer need to worry about configuring an external server.
    
    We can then run the application just like we would an ordinary executable JAR.
    
    Notice that the packaging element in the pom.xml file must be set to jar to build a JAR file:
    
    <packaging>jar</packaging>
    If we don't include this element, it also defaults to jar.
    
    In case we want to build a WAR file, change the packaging element to war:
    
    <packaging>war</packaging>
    And leave the container dependency off the packaged file:
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>
    After executing the Maven package phase, we'll have a deployable WAR file.

Q6. How to Use Spring Boot for Command Line Applications?
   Just like any other Java program, a Spring Boot command line application must have a main method.
    This method serves as an entry point, which invokes the SpringApplication#run method to bootstrap the application
    
Q7. What Are Possible Sources of External Configuration?
    Spring Boot provides support for external configuration, allowing us to run the same application in various environments.
    We can use properties files, YAML files, environment variables, system properties, and command-line option arguments to 
    specify configuration properties.
    
   We can then gain access to those properties using the @Value annotation, a bound object via the @ConfigurationProperties 
    annotation, or the Environment abstraction.
    
Q8: What Does it Mean that Spring Boot Supports Relaxed Binding?
    Relaxed binding in Spring Boot is applicable to the type-safe binding of configuration properties.
    With relaxed binding, the key of a property doesn't need to be an exact match of a property name. Such an environment p
    roperty can be written in camelCase, kebab-case, snake_case, or in uppercase with words separated by underscores.
    For example, if a property in a bean class with the @ConfigurationProperties annotation is named myProp, it can be 
    bound to any of these environment properties: myProp, my-prop, my_prop, or MY_PROP.
    
Q9: How to Write Integration Tests?
    When running integration tests for a Spring application, we must have an ApplicationContext.
    
   To make our life easier, Spring Boot provides a special annotation for testing – @SpringBootTest. This annotation
     creates an ApplicationContext from configuration classes indicated by its classes attribute.
    
   In case the classes attribute isn't set, Spring Boot searches for the primary configuration class. The search starts 
   from the package containing the test up until it finds a class annotated with @SpringBootApplication or @SpringBootConfiguration.
 
Q10: What Are the Basic Annotations that Spring Boot Offers?
     The primary annotations that Spring Boot offers reside in its org.springframework.boot.autoconfigure and its sub-packages. 
     Here are a couple of basic ones: 
     @EnableAutoConfiguration – to make Spring Boot look for auto-configuration beans on its classpath and automatically apply them.
     @SpringBootApplication – used to denote the main class of a Boot Application. This annotation combines @Configuration, 
     @EnableAutoConfiguration, and @ComponentScan annotations with their default attributes.
     
Q11: How Can You Change the Default Port in Spring Boot?
     We can change the default port of a server embedded in Spring Boot using one of these ways:
   -using a properties file – we can define this in an application.properties (or application.yml) file using the property
        server.port
   -programmatically – in our main @SpringBootApplication class, we can set the server.port on the SpringApplication instance
   -using the command line – when running the application as a jar file, we can set the server.port as a java command argument:
     java -jar -Dserver.port=8081 myspringproject.jar

Q12:  Why Do We Need Spring Profiles?
     When developing applications for the enterprise, we typically deal with multiple environments such as Dev, QA, and Prod.
     The configuration properties for these environments are different.
     For example, we might be using an embedded H2 database for Dev, but Prod could have the proprietary Oracle or DB2.
     Even if the DBMS is the same across environments, the URLs would definitely be different.
     To make this easy and clean, Spring has the provision of profiles, to help separate the configuration for each environment. 
     So that instead of maintaining this programmatically, the properties can be kept in separate files such as
      application-dev.properties and application-prod.properties.
       The default application.properties points to the currently active profile using spring.profiles.active 
       so that the correct configuration is picked up.
       
       So far, we've looked at multiple ways of activating profiles. Let's now see which one has priority over the other 
       and what happens if we use more than one, from highest to lowest priority:
       
       Context parameter in web.xml
       WebApplicationInitializer
       JVM System parameter
       Environment variable
       Maven profile
       
   1. @Component
       @Profile("!dev")
       public class DevDatasourceConfig
   2. Profiles can also be configured in XML. The <beans> tag has a profiles attribute, 
      which takes comma-separated values of the applicable profiles:.
    <beans profile="dev">
          <bean id="devDatasourceConfig" 
            class="org.baeldung.profiles.DevDatasourceConfig" />
      </beans>
      
   3. In web applications, WebApplicationInitializer can be used to configure the ServletContext programmatically.
       
       It's also a very handy location to set our active profiles programmatically:
       
       @Configuration
       public class MyWebApplicationInitializer implements WebApplicationInitializer {
        
           @Override
           public void onStartup(ServletContext servletContext) throws ServletException {
        
               servletContext.setInitParameter(
                 "spring.profiles.active", "dev");
           }
       }
        or
    4.  1)Programmatically via ConfigurableEnvironment
       We can also set profiles directly on the environment:
       
       @Autowired
       private ConfigurableEnvironment env;
       ...
       env.setActiveProfiles("someProfile");

       2) Context Parameter in web.xml
        Similarly, we can define the active profiles in the web.xml file of the web application, using a context parameter:
        
        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/app-config.xml</param-value>
        </context-param>
        <context-param>
            <param-name>spring.profiles.active</param-name>
            <param-value>dev</param-value>
        </context-param>
      3) JVM System Parameter
        The profile names can also be passed in via a JVM system parameter. These profiles will be activated during 
        application startup:
        -Dspring.profiles.active=dev
      4) Environment Variable
        In a Unix environment, profiles can also be activated via the environment variable:
        
        export spring_profiles_active=dev
        
      5). Maven Profile
        Spring profiles can also be activated via Maven profiles, by specifying the spring.profiles.active configuration property.
        
        In every Maven profile, we can set a spring.profiles.active property:
        
        <profiles>
            <profile>
                <id>dev</id>
                <activation>
                    <activeByDefault>true</activeByDefault>
                </activation>
                <properties>
                    <spring.profiles.active>dev</spring.profiles.active>
                </properties>
            </profile>
            <profile>
                <id>prod</id>
                <properties>
                    <spring.profiles.active>prod</spring.profiles.active>
                </properties>
            </profile>
        </profiles>
        Its value will be used to replace the @spring.profiles.active@ placeholder in application.properties:
        
        spring.profiles.active=@spring.profiles.active@
        Now we need to enable resource filtering in pom.xml:
        
        <build>
            <resources>
                <resource>
                    <directory>src/main/resources</directory>
                    <filtering>true</filtering>
                </resource>
            </resources>
            ...
        </build>
        and append a -P parameter to switch which Maven profile will be applied:
        
        mvn clean package -Pprod
        This command will package the application for prod profile. It also applies the spring.profiles.active value 
        prod for this application when it is running.
        
       7). @ActiveProfile in Tests
        Tests make it very easy to specify what profiles are active using the @ActiveProfile annotation to enable 
        specific profiles:
        
   6. Get Active Profiles
   Spring's active profiles drive the behavior of the @Profile annotation for enabling/disabling beans. However, we may 
   also wish to access the list of active profiles programmatically.
   
   We have two ways to do it, using Environment or spring.active.profile.
   
   6.1. Using Environment
   We can access the active profiles from the Environment object by injecting it:
   
   public class ProfileManager {
       @Autowired
       private Environment environment;
    
       public void getActiveProfiles() {
           for (String profileName : environment.getActiveProfiles()) {
               System.out.println("Currently active profile - " + profileName);
           }  
       }
   }
   6.2. Using spring.active.profile
   Alternatively, we could access the profiles by injecting the property spring.profiles.active:
   
   @Value("${spring.profiles.active}")
   private String activeProfile;
   Here, our activeProfile variable will contain the name of the profile that is currently active, and if there are 
   several, it'll contain their names separated by a comma.