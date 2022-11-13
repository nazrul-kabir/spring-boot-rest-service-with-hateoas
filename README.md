# Spring Boot - HATEOAS for RESTful Services

HATEOAS stands for “Hypermedia as the engine of application state”.
This repository contains a simple implement of HATEOAS for a REST API with Spring Boot.

What's the benefit of HATEOAS?
For example, when requesting information about a product via REST API, a REST service can return the following

* Product details
* Link to order this product
* Link to get his product's current stock


## Run Locally

Clone the project

```bash
  git clone https://github.com/nazrul-kabir/ordering-micro-service
```

Go to product-service sub-directory

```bash
  cd ordering-micro-service/spring-boot-rest-service-with-hateoas
```

Build and run the service

```bash
  ./gradlew bootRun
```
# REST API

The REST API to the example app is described below.

## Get list of Products

### Request

`GET /api/v1/product/`

### Response

    [
        {
            "id": 1,
            "name": "Dell Precision 5550",
            "currentStock": 99,
            "description": "The world's smallest and thinnest 15 inch mobile workstation.",
            "type": "Laptop",
            "dateCreated": "2022-10-23T08:33:10.658+00:00",
            "lastModified": "2022-10-23T08:33:10.658+00:00"
        }
    ]


## Create a new Product

### Request

`POST /api/v1/product/`

### Request Body

    {
        "name": "HP Pavilion 14",
        "currentStock": 9,
        "description": "Great buy",
        "type": "Laptop"
    }


### Response

    {
        "name": "HP Pavilion 14",
        "currentStock": 9,
        "description": "Great buy",
        "type": "Laptop"
    }


## Get a specific Product

### Request

`GET api/v1/product/id`


### Response

    {
    "id": 1,
    "name": "Dell Precision 5550",
    "currentStock": 99,
    "description": "The world's smallest and thinnest 15 inch mobile workstation.",
    "type": "Laptop",
    "dateCreated": "2022-10-23T08:33:10.658+00:00",
    "lastModified": "2022-10-23T08:33:10.658+00:00",
    "_links": {
        "all-products": {
            "href": "http://localhost:3001/api/v1/product"
        }
    }
}

## Update a Product
Out of scope as of now

## Delete a Product

### Request

`DELETE /api/v1/product/id`
