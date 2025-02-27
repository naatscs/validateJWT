
# validateJWT

This Java project validates various aspects of a JWT (JSON Web Token). It contains utility methods to check the integrity, structure, and claims of a JWT, such as verifying if a claim is a valid prime number, checking if the `Role` claim contains only specific attributes, and ensuring the `Name` claim doesn't exceed 256 characters.

## Features
- Validate JWT structure.
- Check if a claim's value is prime.
- Ensure a claim contains specific values (e.g., `Role` can only contain Admin, Member, or External).
- Validate the length of claims like `Name`.

## Installation
Clone this repository:

```bash
git clone https://github.com/naatscs/validateJWT.git
```

Navigate to the project directory:

```bash
cd validateJWT
```

Use Maven to build the project:

```bash
mvn clean install
```

## Running the Project
After building the project, you can run the application by executing:

```bash
mvn spring-boot:run
```

This will start the Spring Boot application.

