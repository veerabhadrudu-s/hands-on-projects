# Introduction about this application 

* This is a Sample web application with security enabled using Servlet Specification 4.0.
* This application security configuration is defined using Annotations.This is also called as declarative security using Annotations.
* In this example authentication is of BASIC type.
* This application has two servlets (GreetingsServlet,AdminServlet), each servlet has it's own security constraint defined using @ServletSecurity Annotation.
* This application also redirects http traffic to https , this is due to TransportGuarantee.CONFIDENTIAL configuration in @ServletSecurity.

## Prerequisites before deploying this application.
* Make sure to open http and https ports on the server.
* To make configuration simple define users and assign group names to users same as roles defined in @ServletSecurity of Servlets. As the roles and group names are same, this makes most of the  application servers to automatically map user-groups defined in servers-realm (In Wildfly, it is  ApplicationRealm ) to roles defined in application ( i.e defined in @ServletSecurity of servlet's ) during deployment.
* Make sure to add atleast 2 users in servers default realm (In Wildfly, it is ApplicationRealm and add users under it using bin/add-user.sh ) with one user having app-admin,app-user-role as groups and another user having only app-user-role as group. This makes to clearly observe the Authorization behaviour in JAVAEE environment.

## Deploying and Testing the application.

* Build the application using ***mvn clean package*** command.
* Deploy the application into the server and start testing the application using below 2 url's using both users created in Prerequisites step

http://IP_ADDRESS:PORT/javaee-web-security-basic-auth-using-annotations/GreetingsServlet  
http://IP_ADDRESS:PORT/javaee-web-security-basic-auth-using-annotations/AdminServlet

## Observation's you can make out during testing of the application.
* HTTP url will be redirected to https.
* If you test above 2 url's with both users , you can observe the authorization enforced by application.
