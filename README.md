# FitnessMonolithApplication

A robust monolithic backend for a full-featured fitness platform. This application is designed to track workouts, manage nutrition, and monitor health goals by providing a seamless, unified architecture for user progress, activity logging, and real-time health analytics.

## Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Database:** MySQL
* **Persistence & ORM:** Hibernate, JDBC
* **Web/API:** REST APIs, Servlets, JSP
* **Version Control:** Git

## Features (Proposed)
* User Authentication and Management
* Workout and Activity Logging
* Nutrition and Diet Tracking
* Goal Setting and Progress Analytics

---

## Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.6/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.6/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent. 
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
