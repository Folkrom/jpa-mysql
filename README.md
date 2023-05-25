# JPA with MySQL Sample Project

This is a simple project that demonstrates the usage of Java Persistence API (JPA) with MySQL. It provides a basic setup for connecting to a MySQL database using JPA and performing CRUD operations.

## Prerequisites

Before running this project, make sure you have the following installed:

- Java Development Kit (JDK) - version 8 or higher
- MySQL Database Server

## Setup

1. Clone the repository to your local machine:

```bash
git clone https://github.com/Folkrom/jpa-mysql.git
```

2. Open the project in your favorite Java IDE.
3. Modify the persistence.xml file located in the src/main/resources/META-INF directory. Update the following properties to match your MySQL database configuration:
```xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/mydatabase"/>
<property name="javax.persistence.jdbc.user" value="your-username"/>
<property name="javax.persistence.jdbc.password" value="your-password"/>
```

4. Build the project to download the required dependencies. You can use Maven or Gradle, depending on the build system you prefer.
5. Run the project and verify that the application starts successfully without any errors.
