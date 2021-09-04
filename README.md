# Task for Job interview - Use Github API to get user details



Details:
Create a simple rest service which one gonna return the following details:

ID
Login
Name
Type
Avatar Url
Create Date
Calculations *

*calculations =  6 / followersAmount * (2 + publicReposAmount).



Service should get date from https://api.github.com/users/{login}

for example:. https://api.github.com/users/rmaduzia



Service should save amount of requests per user in database.
Database should contain only two columns: 'LOGIN' AND 'REQUEST_COUNT'
Every request value of column 'REQUEST_COUNT' should be increased by one



### Project created with:

* Spring Boot Starter Web v2.5.4
* Spring Boot Starter Test v2.5.4
* Spring Boot Starter Data JDBC v2.5.4
* Lombok v1.18.20
* Javax Persistence API v2.2
* Test Containers v1.16.0
* PostgreSQL

### How to build container with PostgreSQL database:

1. Go to folder named docker
2. Type command: docker-compose up -d
