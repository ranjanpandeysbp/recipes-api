# recipes-api
Application provides API endpoints to carry out CRUD operations on recipe with additional capability of user registration and login.

Hosted version can be found at below url
-------------------------------------------
https://recipeapi2021.herokuapp.com/api/v1/auth/health

Technologies used:
------------------
1. Java 8 - Provides nice features to write compact and robust code
2. Springboot - Proven framework for building API's that would run in a microservice environment
3. Spring Data JPA - Provides ORM capability for easy interaction with Database
4. JWT Authentication with spring security - Helps in achieving the best level token based security in API based environment 
5. Junit and Mockito for writing test - Provides capability to Mock dependency with proxy objects to easily write unit test
6. Swagger for API documentation - API documentation from the code
7. Spring profiles - Enables to use the same code base with different environment configuration
8. H2 in memory DB for Dev profile - Development RDS Database
9. MySQL DB for Production profile - Production RDS Database
10. Maven - Makes project building and dependency management simple and easy
11. Docker for containerization - Enables environment neutral application deployment by bundling the libraries, installer and application together.
12. Logback & SLF4J - Helps in logging
13. Git and Github - Source code storage and collaboration server
14. Pagination - If the number of recipes increases more than 10, it will be served in another page
                 default page size is 10, it can be changed in application.properties file.
15. Sorting - The recipes are by default sorted based on creationDateTime with DESC order
              Sorting can be done on any field and any order by passing proper api parameters.

How to Run the application:
---------------------------

There are 3 ways to run this application:

Option 1 - As standalone java application
---------
1. Git clone and change to master branch by : git checkout master
2. Import into Intellij or Eclipse
3. Right click on RecipeApiApplication and Run as Java Application

Option 2 - As standalone jar
---------
1. Git clone and change to master branch by : git checkout master
2. Run mvn clean install from project root
3. cd to target folder and run command : java -jar recipe-api-0.0.1-SNAPSHOT.jar

Option 3 -  As Docker container
---------
1. Git clone and change to master branch by : git checkout master
2. Run mvn clean install from project root
3. Build the docker file from root directory with below command to generate the image
     docker build -t recipe-api .
4. Run the docker image with below command
     docker run -p 8080:8080 recipe-api
5. To stop running container, first get the container id with below command
    docker ps -a
    Than run below command
    docker kill <Container Id>

Important Note: 
---------------
1. By default the application will run in Dev profile with in memory DB.
2. To run the application in Prod profile:
   a.) Provide your DB details in application-prod.properties
   b.) In application.properties replace this line spring.profiles.active=prod
   
How to access the application/api
----------------------------------
After the application has successfully started, you can access the application healthcheck and swagger ddocs at below urls:
1. Health check - http://localhost:8080/api/v1/auth/health
2. Swagger docs - http://localhost:8080/v2/api-docs/
Note: No authentication is required to access above urls

##### Note: If you don't want to run the application in local than you can access the heroku hosted instance by following details mentioned below.

Examples API request for various endpoints
-------------------------------------------
The project at root level contains 2 files
1. sample-request.txt - Contains details of how to access various endpoints with example on local server
2. heroku-sample-request.txt - Contains details of how to access various endpoints with example on hosted heroku server

Project Development Timeline
-----------------------------
The project was developed from scratch and hence you will see different branches in github 
pertaining to the specific feature developed in the specific branch.

project-guide.txt file at root of the project has steps of each and every functionality that was build in a particular branch.

