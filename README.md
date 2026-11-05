# Lab 4: Spring Core with Annotation-Based Configuration

-  Converts the Lab 3 XML configuration to annotations using @Configuration, @ComponentScan and @Component
- Injects values and dependencies with @Value and @Autowired (constructor injection for Address, setter injection for the Subject list)
- Defines the Maths 101 and Java EE Subject beans with @Bean methods in AppConfig
- Uses prototype scope for Student and singleton scope for Address, then verifies both in App
- Requires JDK 17+, Maven, Spring Core and Lombok