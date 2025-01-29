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

**Architecture**

The API creates two tables: USERS and PHONEBOOK.

USERS
```
id: Long, unique: true
username: String
```
PHONEBOOK
```
id: Long, unique: true
name: String
phoneNumber: String
user_id: links to users.id 

```


**Endpoints:**

_**Users:**_

`GET: /api/users` - returns all users without phonebooks

`GET: /api/all` - returns all users and their phonebooks

`POST: /api/users` - adds new user and their phonebook (body required, type JSON). Can add one at a time

`GET: /api/users/id` - returns specific record

`PUT: /api/users/id` - update record by id. Supports just update of username. Use plain text for it

`DEL: /api/users/id` - delete a record

**JSON body format:**

```
{
  "username": "bub",
  "phonebookEntries": [
    { "name": "sue", "phoneNumber": "1234" },
    { "name": "kim", "phoneNumber": "12435" }
  ]
}
```

_**Phonebook**_

`POST: /api/phonebook/addPhoneNumber` - adds new phone number to users phonebook

**JSON Body**
```
{
  "name": "Kim",
  "phoneNumber": "9876543210",
  "userid": 1
}
```
`GET: /api/phonebook/id` - gets all phone numbers by user id

`GET: /api/phonebook/searchByUsername?username=` - seraches by useranme and its parts 

`GET: /api/phonebook/search?phoneNumber=` - seraches by phone number and its parts 

`PUT: /api/phonebook/id` - updates phone number/ name by id

`DELETE: /api/phonebook/id` - deletes phone number by id