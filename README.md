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

The API creates two tables: user and PHONEBOOK.

user
```
id: Long, unique: true
username: String
```
PHONEBOOK
```
id: Long, unique: true
name: String
phoneNumber: String
user_id: links to user.id 

```


**Endpoints:**

_**user:**_

`GET: /api/user` - returns all user without phonebooks

`POST: /api/user` - adds new user and their phonebook (body required, type JSON). Can add one at a time

`GET: /api/user/id` - returns specific record

`PUT: /api/user/id` - update record by id. Supports just update of username. Use plain text for it

`DEL: /api/user/id` - delete a record

**Example SON Body:**

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

`POST: /api/phonebook/addPhoneNumber` - adds new phone number to user phonebook

**Example JSON Body**
```
{
  "name": "Kim",
  "phoneNumber": "9876543210",
  "user": {"id" : 1}
}
```
`GET: /api/phonebook/id` - gets all phone numbers by user id

`GET: /api/phonebook/search?username=` - searches by useranme and its parts 

`GET: /api/phonebook/search?phoneNumber=` - searches by phone number

`GET: /api/phonebook/search?phoneNumber=NUMBER&username=USERNAME` - searches by phone number + username and its parts

`PUT: /api/phonebook/id` - updates phone number/ name by id

`DELETE: /api/phonebook/id` - deletes phone number by id