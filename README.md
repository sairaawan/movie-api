# About movie-api

This API allows users to search for Movies and gather information about Movies.

Each movie entry has a way of displaying movie details.Data Format: The API uses HTTP methods and can return data in JSON format



# Technologies

Java

Spring Boot

MYSQL

Spring 

Data JPA

Lombok

H2 Database

Spring Web

Swagger

AWS

Docker

# Setup and Installation

1.	Download or clone the repository from GitHub
	
git clone https://github.com/sairaawan/movie-api.git

2.	Install required programs

In order to follow the user needs to have MySQL and Postman. Below are the short terminal lines for easy installation:

sudo apt update

sudo snap install postman

sudo apt install MySQL

3.	Setup database project

In the root application directory (API-movies-data), SQL script file (project_setup.sql)is present for creating a database.

Run the script using psql client:

cd Documents/api-movies-data

mysql -u root -p

--file project_setup.sql

4.Update database configurations in application properties

If you have changed default user for creating database with some different username and password,update the src/main/resources/application.properties file accordingly:

spring.jpa.hibernate.ddl-auto=create 

spring.datasource.url=jdbc:mysql://localhost:3306/MovieAPI

spring.datasource.username=root

spring.jpa.hibernate.ddl-auto=update

5. Run the spring boot application

If you download /clone repo elsewhere ,change the path update accordingly

cd Documents/Movie API

mvn clean install


java -jar target/api-movies-data-0.0.1

this runs at port 8080and hence all  endpoints can be accessed starting from http://localhost:8080

6. Create database objects (if you want some prerecorded  values in local database)

In the root application directory (Movie API),SQL script file(db.script)is present for populating database with some records

Run the script using psql client

cd Documents/Movie API

psql -U MySql --file project_db.sql
