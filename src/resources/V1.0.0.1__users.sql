pom.xml
mvnw
mvnw.cmd
src
    |-- main
        |-- java
            |-- com.springbook.application
                |-- Application
        |-- resources
            |-- application.properties
            |-- db
                |-- migration
                    |-- postgresql
                        |-- V1.0.0.1__users.sql
    |-- test
        |-- java
            |-- com.springbook.application
                |-- ApplicationTests

CREATE TABLE copsboot_user
(
    id uuid NOT NULL PRIMARY KEY,
    auth_server_id uuid,
    email VARCHAR(255),
    mobile_token VARCHAR(255)
);