# Movie Recommender API 

### Introduction
It can take sometimes take longer to chose a movie than to watch it!
<p>This Movie API allows users to retrieve movie recommendations based on a search criteria - only 3 movie recommendations are returned based on the iMDB ratings.

### Movie API Specification
This API recommends provides the following end points:
- No filter, e.g. /movies
- Year, e.g. movies/year/2019 or year/startYear=1990&endYear=1995
- Genre, e.g. movies/genre/family
- Person, e.g. movies/person/Harrison Ford
- Name of movie, e.g. movies/name/Star Wars

and returns a result in JSON format.

An optional emailAddress parameter can be used to send the recommendations via email through integration with Twilio's SendGrid platform.

The full specification can be seen at: http://api.movi3.me.uk/swagger-ui/index.html

### Pre-Requisites
- Java SE Development Kit 11
- Maven

### Technologies
- Java
- Spring Boot
- MySQL
- Lombok
- H2 Database
- Swagger
- AWS
- Docker
- SendGrid


### Running the Unit Tests
- You can run the unit tests in IntelliJ, or you can go to your terminal and inside the root of this directory, run:

`mvn test`

### Setup and Installation

1. Download or clone the repository from GitHub	
git clone https://github.com/sairaawan/movie-api.git
<br><br>
2. Database set-up
<br>The data used for this project is from iMDb. 
<br><br>A subset of the data available is described at https://www.imdb.com/interfaces is used, which can be downloaded from https://datasets.imdbws.com.
<br><br>Instructions are available here - [DB Setup instructions](https://github.com/sairaawan/movie-api/blob/master/src/main/resources/db_scripts/DatabaseSetup.md)


3. Configuration of application properties and environment variables
<br>The application requires configuration of the following environment variables to run: 
<table>
<tr><td>prod_movieDB</td><td>e.g. yourdomain:3306/imdb</td></tr>
<tr><td>prod_db_user</td><td>prod db user</td></tr>
<tr><td>prod_db_pwd</td><td>password for prod db user</td></tr>
<tr><td>test_movieDB</td><td>e.g. localhost:3306/imdb</td></tr>
<tr><td>test_db_user</td><td>test db user/imdb</td></tr>
<tr><td>test_db_pwd</td><td>password for test db user</td></tr>
<tr><td>sendgrid_api_key</td><td>your SendGrid API key</td></tr>
<tr><td>test_email</td><td>email address to use for testing</td></tr>
</table>


4. Running the application
<br>`mvn clean install`
<br>`java -jar target/api-movies-data-0.0.1`

The application default port is 8080 and hence all endpoints can be accessed starting from http://localhost:8080


### Deployment
The project is deployed as Docker image on AWS Elastic beanstalk. Visit http://api.movi3.me.uk/movies to view the API.

The Main Entry Point for the application is: [MovieApiApplication.java](src/main/java/com/ssp/movie/api/MovieApiApplication.java)



