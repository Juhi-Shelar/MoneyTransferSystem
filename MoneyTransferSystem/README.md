# Money Transfer REST API

RESTful API for money transfers between accounts.

### Build
To build application use command:
```sh
mvn clean install
```
### 

### Start
To start application run the spring boot Application class: MoneyTransferSystemApplication.

Application starts a tomcat server on localhost port 8080 (by default). An H2 in-memory database initialized with account data.

### Swagger

Swagger URL:

- http://localhost:8080/swagger-ui/index.html



### Available Services

| HTTP METHOD | PATH                    | USAGE |
| -----------|-------------------------| ------ |
| GET | /account/{accountId}    | get account by accountId |
| POST | /account/transferAmount | perform transfer between 2 accounts |


### Http Status
- 200 OK: The request has succeeded
- 400 Bad Request: The request has invalid input parameter
- 500 Internal Server Error: The server encountered an unexpected condition 
