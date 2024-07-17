# 2. Record architecture decisions

Date: 2024-07-14

## Status

Accepted

## Context

1.  RollDice logic into constructor?
2. When to rolldices() before or after creating a game (contructor with dices to 0 or not)
3. Random random parameter in DiceGameServiceImpl needed
4. Recomended Random parameter injected in DiceGameServiceImpl constructor

## Decision

1. No. Better to call a method like rollDices()
2. Both are ok but better before creating the game entity.
3. Not needed but better practice
4. Dependency injection + bean

## Consequences
1. Is better practice to separate the logic from the entity construction. Scalability, add other games.
2. For inmutability of the game object, for clarity of code,easier to understand and clarify
3. Better for reusability, predictability in testing, single instance avoiding a use resource-intensive
4. For better testability, Random Bean improves flexibility and testability