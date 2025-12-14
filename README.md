#AI-Generated Integration Builder
#Project Overview

CloudEagle wants to simplify integrations with multiple SaaS applications.
This Spring Boot project allows fetching user data dynamically from any API without changing code.

Key Features:

Dynamic API configuration stored in the database (ApiConfig).

Generic service to fetch users from any API.

Token-based authentication with support for multiple auth types (Bearer, OAuth2, etc.).

Temporary storage of fetched users in the database (TempUser).

Easily extensible to support multiple APIs (Calendly implemented).

#Requirements

Java 17+

Spring Boot 3+

Maven / Gradle

MySQL or any relational database
