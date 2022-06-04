package io.ryspring.tips.springtips;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringTipsApplication {
//  How Spring boot internally works or Explain the run() method in Spring boot?
//  The below 10 steps show the internal working of the run() method:
//
//  x Spring boot application execution will start from the main() method
//  x The main() method internally call SpringApplication.run() method
//  x SpringApplication.run() method performs bootstrapping for our spring boot application
//  x Starts StopWatch to identify the time taken to bootstrap the spring boot application
//  x Prepares environment to run our spring boot application (dev, prod, qa, uat)
//  x Print banner ( Spring Boot Logo prints on console)
//  x Start the IOC container ( ApplicationContext) based on the classpath ( default, Web servlet/ Reactive)
//  x Refresh context
//  x Trigger Runners (ApplicationRunner or CommandLineRunner)
//  x Return ApplicationContext reference ( Spring IOC)
  private static Logger logger = LoggerFactory.getLogger(SpringTipsApplication.class);

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContect =
        new SpringApplicationBuilder(SpringTipsApplication.class).properties("spring.config.name:springTips").build()
            .run(args);
    //SpringApplication.run(SpringTipsApplication.class, args);

  }

}
