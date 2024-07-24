# Project Title

_S05_T02_DiceGame_

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