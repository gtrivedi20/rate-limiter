# Rate Limiter 

A brief description of your microservice.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)

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


