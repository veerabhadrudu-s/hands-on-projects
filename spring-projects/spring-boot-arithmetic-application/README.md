# Spring boot Arithmetic Application.

This is a spring boot REST application. It performs arithmetic operations over rest interface and it was built using following external modules/components.

* Spring boot + Spring web runs on undertow webserver.
* Spring Actuator Module to moniter statistics.
* Log4j2 over Slf4j.
* Junit5 Module.
* Integrated with Spring Rest Doc Module to generate documentation
  * Spring Doc module can be configured in Junits using **@ExtendWith(RestDocumentationExtension.class)** annotation
  * At present Spring rest doc generation configuration is generic in setup method for all test cases. This can be configured seperately for each test case in unit test method annotated with **@Test**. For more information on this refer **Spring Rest Doc** module reference documentation.
  * Spring Doc Module generated files (**.adoc**) will be consumed by ASCII Doctor Maven plugin during build phase. This can be viewd in project **pom.xml** configuration under **build** section.
  * ASCII Doctor configuration file is in path **src/main/asciidoc/api-guide.adoc**

