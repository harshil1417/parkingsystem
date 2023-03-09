# README #

This is a simple parking Lot management system developed on top of Spring Boot and Java. It has 2 major REST end points for entry and exit flows.
We have used an in memory H2 database for storing vehicle entry / exit and other details.
Parking System Service has the crux of logic currently.
spring.jpa.hibernate.ddl-auto=create is used to auto create some tables required for the flow.

# Assumptions #

1. We are assuming for now it is a single instance application.
2. Simple parking lot with no floors or category of slots.
3. Simple DB design without gates/ slot types etc.
4. For now MAX_SLOTS is hard coded to 5 in the service.

# Future Enhancements #

1. Parking lot can be enhanced for Multiple floors and gates.
2. Amount calculation strategies can be added to support hourly/ daily and other options.
3. Error handling in REST controller can be implemented using the controller advice for good user experience.
4. Logging can be implemented and more appropriate usage of Lombok
5. Contract based testing can be used if integration required with other services or UI.
6. Multiple instance support. (More than 1 pod)
7. Karate tests for integration testing.
8. Swagger and OPEN API configurations


#Sample API calls#

POST http://localhost:8080/parking-system/vehicle-entry-flow
BODY
{
  "number" : "RY18 LNM",
  "type" : 0
}


POST http://localhost:8080/parking-system/vehicle-exit-flow

{
  "id" : 1
}
