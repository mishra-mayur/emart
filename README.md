# e-Mart

This project contains almost all the e-commerce functionality.

## Getting Started

Clone the project in your local machine and run it as a maven project.

### Prerequisites

You need an IDE(preferably IntelliJ), database MySql, setup the database using the file scripts.sql that is present in the project.
Enable Lombok extension in your IDE to use its annotations.

```
JDK setup done, with database connected and running on 3306 port
```

### Installing

IDE is required and MySQL server

###All the API endpoints: 

There are two type of resources in eMart ;-
* Public endpoints like product search and user signUp 
* Private endpoints like booking, card functionality that requires authorization.

####Public Endpoints 

```
 SignUp 
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method POST
URL :- http://localhost:9999/emart/public/user/signUp
Request Body :- {
                	"userEmail": "xyz@mail.com",
                	"password": "pass"
                }

After SignUp click the otp url provided on the mail to activate user.

 Authenticate
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method POST
URL :- http://localhost:9999/emart/public/user/authenticate
Request Body :- {
                	"userEmail": "xyz@mail.com",
                	"password": "pass"
                }

 Product Search with Category
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method GET
URL :- http://localhost:9999/emart/public/products/category/CLOTHING

 Product Search with Name
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method GET
URL :- http://localhost:9999/emart/public/products/name/shirt

 Product Search Partial Search
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method GET
URL :- http://localhost:9999/emart/public/products/expression/shi

 Deactivate Account 
''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method POST
URL :- http://localhost:9999/emart/public/user/deactivate
Request Body :- {
                	"userEmail": "xyz@mail.com",
                	"password": "pass"
                }

```

####Private Endpoints
For all these endpoints add an extra header
```
[{"key":"Authorization","value":"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXl1ci5taXNocmFAY292aWFtLmNvbSIsImV4cCI6MTU4OTA5ODc3MSwiaWF0IjoxNTg5MDk1MTcxfQ.pNqADt3jznQM2ub8SQ2hD0atokI4nDAGlS2cuFUSkZ0","description":"","type":"text","enabled":true}]
```
value contains Bearer space auth token. 

```
 Get Cart Details
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method :- POST
URL :- http://localhost:9999/emart/cart/getCartDetails
Request Body :- xyz@mail.com

 Add To Cart 
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method :- POST
URL :- http://localhost:9999/emart/cart/addToCart
Request Body :- {
                	"userEmail" : "xyz@mail.com",
                	"productId" : 1
                }

 Remove Forom Cart
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method :- POST
URL :- http://localhost:9999/emart/cart/removeFromCart
Request Body :- {
                	"userEmail" : "xyz@mail.com",
                	"productId" : 1
                }

 Get All Orders
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method :- POST
URL :- http://localhost:9999/emart/booking/getAllOrders
Request Body :- xyz@mail.com

 Plcae Order
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method :- POST
URL :- http://localhost:9999/emart/booking/placeOrder
Request Body :- {
                	"userEmail": "xyz@mail.com",
                	"productQuantityMap": {
                		"Shirt" : 10
                	}
                }

Cancel Order
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
Request Method :- GET
URL :- http://localhost:9999/emart/booking/cancelOrder/5
here 5 is order ID

```

## Deployment

Run the JAR in the local machine to build and deploy the application.
Or use the command :-
```$xslt
./mvnw spring-boot:run
```

## Dependencies

* Spring-Web 
* JPA
* MySQL
* Lombok
* Spring Security
* JWT
* Mail

## Versioning

0.0.1-SNAPSHOT

## Authors

* **Mayur Mishra** - *Coviam Technologies* 

## Acknowledgments

* Google
