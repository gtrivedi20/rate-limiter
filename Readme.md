# Rate Limiter 

A brief description of your microservice.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#apiendpoints)

## Overview

The Rate Limiter Service control the rate of traffic sent by client or a service

## Features

The Rate Limiter service limits the numbers of client requests allowed to be sent over a specific period. If the API request count exceeds the threshold defined by the rate limiter, all the excess calls are blocked.

Here are a few examples: 

- A user can write no more than 2 posts per second.s
- You can create a maximum of 10 accounts 5 seconds from the same IP address. 
- You can claim rewards no more than 5 times 50 seconds from the same device.

## Technologies

- Gradle 3.7.5
- JDK 17
- Spring Boot 3.2.5

## Installation

Follow below steps to install Rate Limier in local

- clone https://github.com/gtrivedi20/rate-limiter.git in local
- go to project root folder and run below commands 
  - ./gradlew dependencies - this command pull dependencies 
  - ./gradlew build - this command will build jar file

## Usage

- ./gradlew bootRun - run rate limiter service in local
- ./gradlew clean test - execute unit and integration test suit for rate limiter service in local

## API Endpoints

  - Endpoint : /ratelimit/configure
  - Method: POST
  - Description: allows the caller to change the interval of rate limiting (in seconds)
    as well as the number of calls allowed per interval. This is a global configuration change that will
    affect the API calls to the second API.
  - Request:
    {
    "limit":7,
    "seconds":20
    }
  - Response: return status code 200 after set configuration successfully 


  - Endpoint : ratelimit/isratelimited/{unique_token}
  - Method: GET
  - Description: returns the status of rate limiting on the unique token
  - Response: return status code 200 to the client when request sent in a within time interval. return status code 429 to the client when too many requests in a given amount of time