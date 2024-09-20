# Project Title

_S05_T02_DiceGame_

## Brief
Advanced Spring Framework
Final project: Game of dice

This is your final project, an API 100% designed by you where you will apply everything you have learned so far to create a complete application, from the database to security. Apply everything you know and even what is not asked.

The dice game is played with two dice. If the result of the sum of the two dice is 7, the game is won, otherwise, it is lost. A player can see a list of all the rolls he has made and the percentage of success. To be able to play the game and make a roll, a user must register with a non-repeated number. When it is created, it is assigned a unique numerical identifier and a registration date. If the user wishes, he can not add any number and it will be called "ANONYMOUS". There can be more than one "ANONYMOUS" player.

Each player can see a list of all the rolls he has made, with the value of each die and whether or not he has won the game. In addition, you can see your percentage of success for all the runs you have made.

You cannot delete a specific game, but you can delete the entire list of rolls of a player.

The software should be able to list all the players in the system, the success percentage of each player and the average success percentage of all the players in the system.

The software must respect the main design patterns. You must take into account the following construction details (URL's):

* POST /players: Create a player
* PUT /players: Change the player number
* POST /players/{id}/games: A specific player makes a dice roll
* DELETE /players/{id}/games: deletes the player's rolls
* GET /players/: returns the list of all players in the system with their percentage of success
* GET /players/{id}/games: returns the list of games played by a player
* GET /players/ranking: returns the percentage of the average success of all players in the system
* GET /players/ranking/loser: returns the player with the worst success rate
* GET /players/ranking/winner: returns the player with the best percentage of success

Adds security: includes authentication by JWT in all accesses to the URLs of the microservice.

Design the project diversifying persistence so that it uses two database schemes at the same time: MySQL and MongoDB.

## Starting...

_CRUD maintenance of DiceGame_

Register request

    curl --location 'http://localhost:8082/diceGame/auth/register' 
    --data-raw '{
    "userName": "nuria",
    "email": "nuria@nuria.com",
    "password": "nuria",
    "role": "USER"
    }'

SignIn request and authentication token generation

    curl --location 'http://localhost:8082/diceGame/auth/logIn'
    --data-raw '{
    "email": "nuria@nuria.com",
    "password": "nuria"
    }'

Request to get all players ordred by winRate descending
    
    curl --location 'http://localhost:8082/players/'

Request the global winRate (average of all players)

    curl --location 'http://localhost:8082/players/ranking'
 
Request to get the winner player

    curl --location 'http://localhost:8082/players/ranking/winner'

Request to get the loser player

    curl --location 'http://localhost:8082/players/ranking/loser'

Request to create a new player( SignIn & required )

    curl --location 'http://localhost:8082/players'
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib29AYm9vLmNvbSIsImlhdCI6MTcyMTgyMTg0OCwiZXhwIjoxNzIxOTA4MjQ4fQ.DDcrHm3qflIniXE3GXPtq1N1A25A7zdfDd4iWfBbi4g'
    --data '{
    "playerName": "{{playerName}}"
    }'

Request to add games to a player

    curl --location --globoff --request POST 'http://localhost:8082/players/{{playerId}}/games/' 
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib29AYm9vLmNvbSIsImlhdCI6MTcyMTgyMTg0OCwiZXhwIjoxNzIxOTA4MjQ4fQ.DDcrHm3qflIniXE3GXPtq1N1A25A7zdfDd4iWfBbi4g'

Request to get all games of a player

    curl --location --globoff 'http://localhost:8082/players/{{playerId}}/games' 

Request to delete all games of a player

    curl --location --globoff --request DELETE 'http://localhost:8082/players/{{playerId}}/games'
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib29AYm9vLmNvbSIsImlhdCI6MTcyMTgyMTg0OCwiZXhwIjoxNzIxOTA4MjQ4fQ.DDcrHm3qflIniXE3GXPtq1N1A25A7zdfDd4iWfBbi4g'

Request to update a player

    curl --location --request PUT 'http://localhost:8082/players'
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib29AYm9vLmNvbSIsImlhdCI6MTcyMTgyMTg0OCwiZXhwIjoxNzIxOTA4MjQ4fQ.DDcrHm3qflIniXE3GXPtq1N1A25A7zdfDd4iWfBbi4g'
    --data '{
    "playerId": "{{playerId}}",
    "playerName": "{{playerName}}"
    }'

Request to delete a player

    curl --location --globoff --request DELETE 'http://localhost:8082/players/delete/{{playerId}}'
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib29AYm9vLmNvbSIsImlhdCI6MTcyMTgyMTg0OCwiZXhwIjoxNzIxOTA4MjQ4fQ.DDcrHm3qflIniXE3GXPtq1N1A25A7zdfDd4iWfBbi4g'
    --data ''

### Analyze end-to-end testing 🔩

The tests are responsible for validating the following cases:

- Check all players ordered by winRate descendent
- Check all games of a player
- Check winRate average of all players
- Check winner player
- Check loser player
- Create a player
- Create a game for a player.
- Update a player.
- Delete a player and its games.
- Delete all gameas of a player

## Build with 🛠️

* [Maven](https://maven.apache.org/) -Dependency management
* [Spring Security](https://spring.io/projects/spring-security/) - To automatize access control (authentication and authorization) and session of an application
* [mySql] (https://www.mysql.com/) - Relational database
* [MongoDb] (https://www.mongodb.com/) - Non relational database
* [Junit] (https://junit.org/junit5/) - For integration and unit test of the application
* [Swagger] (https://swagger.io/) To document and use RESTful web services
* [Postman] (https://swagger.io/) To document and use RESTful web services


## Authors ✒️

* **Núria Grau** - *IT Academy Back End Java student* - [nuriagrau](https://github.com/nuriagrau)

---
