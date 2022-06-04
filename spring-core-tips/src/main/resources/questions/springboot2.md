a. Difference between @ConfigurationProperties and @Value
   
   @Value is a core container feature and it does not provide the same features as type-safe Configuration Properties.
    The table below summarizes the features that are supported by @ConfigurationProperties and @Value:
   
     Feature     	     @ConfigurationProperties        @Value
     Relaxed binding   	        Yes                     	No
     Meta-data support  	    Yes	                        No
     SpEL evaluation	         No	                        Yes
   If you define a set of configuration keys for your own components, spring boot recommends you to group them in a 
   POJO annotated with @ConfigurationProperties. Please also be aware that since @Value does not support relaxed binding, 
   it isn’t a great candidate if you need to provide the value using environment variables.
   
   Finally, while you can write a SpEL expression in @Value, such expressions are not processed from Application property files.
   
1. Reading Configuration using SpEL and @Value Annotation
   We will add some fields to read the configuration from employee.properties using @Value annotation. 
   The @Value annotation can be used for injecting values into fields in Spring-managed beans and it can be applied 
   at the field, constructor or method parameter level.
   
   @Value ("#{'${employee.names}'.split(',')}")
   private List<String> employeeNames;
   
   Here, we are using Spring expression language to get a list of employee names. We can manipulate the properties to 
   get the list of values. The field employeeNames will give a list: [Petey Cruiser, Anna Sthesia, Paul Molive, Buck Kinnear].
   Suppose we want to get only first entry from a list of employee names, we can write the expression as follows:
   
   @Value ("#{'${employee.names}'.split(',')[0]}")
    private String firstEmployeeName;
    
   This will get the first name from the list: Petey Cruiser.
   
   Let us now look at how to use @Value with Maps. We have the following property defined.
   employee.age={one:'26', two : '34', three : '32', four: '25'}
   We can inject the above the property value as a Map as follows:
   
   @Value ("#{${employee.age}}")
    private Map<String, Integer> employeeAge;
   Suppose, we want to get the value using certain key, we can get the value as follows:
   @Value ("#{${employee.age}.two}")
   private String employeeAgeTwo;
   
   If we are not sure if a certain key exists and we want to get a default value, then we could write the expression as follows:
   @Value ("#{${employee.age}['five'] ?: 30}")
    private Integer ageWithDefaultValue;
    
2. Reading System Properties using @Value Annotation
   We can also inject system properties using SpEL. For example, Java home can be injected as follows:
   @Value ("#{systemProperties['java.home']}")
   private String javaHome;
   Similarly user directory can be injected as below:
   @Value ("#{systemProperties['user.dir']}")
   private String userDir;

3. It seems that @ConfigurationProperties doesn't evaluate Spring Expression Language, but @Value does. 
    That means in cases where configuration may contain expressions, @Value can't transparently be replaced with 
    @ConfigurationProperties. This was a bit of a surprise and took me a while to diagnose. For example:
   
   application.properties:   test.expression=#{1+1}
   TestValue.java:
   
   @Component
   public class TestValue {
       private @Value("${test.expression}") String expression;
   
       @PostConstruct
       public void print() {
           System.out.println("Test Value: " + expression);
       }
   }
   TestConfigurationProperties.java
   
   @Component
   @ConfigurationProperties("test")
   @Getter @Setter
   public class TestConfigurationProperties {
       private String expression;
   
       @PostConstruct
       public void print() {
           System.out.println("Test ConfigurationProperties: " + expression);
       }
   }
   prints:
   
   Test ConfigurationProperties: #{1+1}
   Test Value: 2
   
   It seems like it would be reasonably straightforward to perform this evaluation, or to add an argument to @ConfigurationProperties. 