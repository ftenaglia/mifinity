# Solution
Built with Springboot (Mvc, Security, and Data) and Thymeleaf, using Maven.

### Assumptions
I assumed that when a user tries to add a card that is already in the system (in the edit flux), the system should verify if the current user attempting that is the actual owner of the card.

### Admin
Default administrator credentials: admin/admin.

### How to run
To run from a jar file, execute `mvn clean install` and you will have a jar file named `mifinity-0.0.1-SNAPSHOT.jar` placed in the `target` directory.

Otherwise, you could simply execute `mvn spring-boot:run`. 

The application will be available at http://localhost:8080/