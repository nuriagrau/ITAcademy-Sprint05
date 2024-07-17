# 1. Record architecture decisions

Date: 2024-07-14

## Status

Accepted

## Context

Considering that winRate will be a parameter of player entity that will be calculated everytime the player plays, the requirements of ranking requests of the project and the possibility of a view where the player can see its position in the ranking, is a better option to create a ranking table or calculate ranking on-the-fly?

## Decision
Advantages of creating a ranking table:

- Performance: retrieving the player's position in the ranking will be fast and efficient.

- Realtime updates: the ranking information will be up-to-date and accurate.

- Simplified queries

- Scalability: As the number of players grows, the performance impact of calculating rankings on-the-fly increases. A ranking table can handle large datasets more efficiently, especially if you index the table properly.

- User Experience: Consistently fast retrieval times for ranking positions contribute to a better user experience, which is crucial for web applications.

Expecting High Frequency of Play and Ranking Requests & a possible future web application development, the best option is to make a ranking table.


## Consequences
To optimize and ensure the data accuracy the process will be the following:
1. Player plays a game and winRate is recalculated
2. Player winRate is updated in the database
3. Ranking table is updated to reflect the new winRate and players position is recalculated
4. Retrieve ranking from the ranking table when the webpage requests the player ranking position

