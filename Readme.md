# Bakery App - code challenge readme.

## This app is done in Maven with Java 8 and Spring Boot.

## Running the app:
### 1) Clone following repository:
```sh
git clone https://github.com/SanMateo222/BakeryChallenge
```

### 2) Build & run the app
```sh
cd <git_directory>/BakeryChallenge
```
```sh

mvn install && java -jar target/BakeryApp-1.0-SNAPSHOT.jar
```

### 3) Request
Using Http client (ex. Postman), run POST request for following url:
```sh
http://localhost:8080/generate-order
```
with body in json format, example:
```sh

[
  {
    "productCode": "VS5",
    "productQuantity": "10"
  },
    {
    "productCode": "MB11",
    "productQuantity": "14"
  },
    {
    "productCode": "CF",
    "productQuantity": "13"
  }
]
```
App is using internal json resource file as product database - packages of current products can be modified. 
New products and package sets can also be added.

## Assumptions:
1) If one of order requests holds incorrect product code or quantity that cannot be purchased with current packages, order will be returned with info message. Rest of products will be processed successfully if requested with correct parameters.
```sh

[ {
  "productName" : "Vegemite Scroll",
  "productCode" : "VS5",
  "productQuantity" : 1,
  "message" : "Requested amount of product cannot be purchased as it is smaller than our current smallest package. Please select higher amount of product."
}, {
  "productName" : "Blueberry Muffin",
  "productCode" : "MB11",
  "productQuantity" : 14,
  "totalPrice" : 54.80,
  "packings" : [ {
    "packagePrice" : 9.95,
    "productsInPackage" : 2,
    ......
    
```

2) Request with incorrect data type will not be processed and will throw an exception.
```sh

[
  {
    "productCode": "VS5",
    "productQuantity": "Ups I'm a string..."
  }
]
  ```
  ```sh

  There was an error with the request - The quantity entered, Ups I'm a string... is invalid.
  
 ```


Created by Mateusz Wlodarczyk, Berlin 2019.