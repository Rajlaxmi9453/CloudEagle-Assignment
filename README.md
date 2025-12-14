# AI-Generated Integration Builder

## Overview

CloudEagle wants to simplify integrations with multiple SaaS applications.
This Spring Boot project allows fetching user data dynamically from any API without changing code.

## Key Features:

Dynamic API configuration stored in the database (ApiConfig).

Generic service to fetch users from any API.

Token-based authentication with support for multiple auth types (Bearer, OAuth2, etc.).

Temporary storage of fetched users in the database (TempUser).

Easily extensible to support multiple APIs (Calendly implemented).

## Requirements

Java 17+

Spring Boot 3+

Maven / Gradle

MySQL or any relational database

## Architecture


| ApiConfig Table| ---> | GenericApiService | ---> | TempUser Table  |
   
           ^
           |
    UserController
      (REST API)


## Flow:

Controller receives request to fetch users for a specific API.

Service loads API configuration from ApiConfig table.

WebClient calls external API using authType + token.

Response is parsed into DTOs (UserDto).

Users are saved in TempUser table.

## API Endpoints
### Method	 Endpoint	Description
GET	/api/users/{apiName}	Fetch users for given API and store in DB
POST	/api/users/{apiName}/update-token	Update token dynamically for

## Technologies Used

Java 17

Spring Boot 3

Spring Data JPA

WebClient (Spring Reactive)

H2-database

Lombok
