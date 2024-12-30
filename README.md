**To Launch:**
1. Please make sure that you have Maven installed by running the following command in the terminal
   `mvn -v`
   If Maven is not installed - please install it for the app run.
2. Download the Repo
3. From the Terminal navigate to downloaded folder and run
   `mvn spring-boot:run`

_Note: app is running on port 8080 so please make sure that this port is free_

**DataBase:**
It uses H2 DB. You can access it by following after the app launch  
**http://localhost:8080/h2-console/**

spring.datasource.url=jdbc:h2:mem:phonebookdb
spring.datasource.driver-class-name=org.h2.Driver


Endpoints:
`GET: /api/phonebook` - returns all records
`GET: /api/phonebook/ID` - returns specific record
`PUT: /api/phonebook/ID` - update record by ID
`DEL: /api/phonebook/ID` - delete a record

JSON body format:
```
{
    "name": "Name",
    "phoneNumber": "987654321"
}
```
