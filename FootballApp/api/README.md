# Football Team and Player Management API

This project is a REST API application for managing football players, teams, matches and stats. It receives input through HTTP Requests/Csv files and produces output in the form of HTTP Responses. The general flow of the program is achieved through usage of `Controller`, `Service` & `Repository` classes and interfaces. The application contains logic and operations that allow:

## Managing entities such as:
- ### Player
    - Represents a football player, including fields `id`, `teamNumber`, `position`, `fullName`, `team`
    - has a `ManyToOne` relationship with a `Team` entity (referencing `teamId`)

- ### Team
    - Represents a football team, including fields `id`, `name`, `manager`, and `tournamentGroup`

- ### Match
    - Represents a football match between two teams with fields `id`, `aTeam`, `bTeam`, `date`, `score`
    - has a `ManyToOne` relationship with a `Team` entity for both team fields (referencing `aTeamId` & `bTeamId`)

- ### Record
    - Tracks a player's participation in a match, including fields `id`, `player`, `match`, and minutes played (`fromMinutes` & `toMinutes`)
    - has a `ManyToOne` relationship with a `Player` entity (referencing `playerId`)
    - has a `ManyToOne` relationship with a `Match` entity (referencing `matchId`)

- ### Pair
    - Represents a pair of `Player` entities and tracks their shared minutes and matches into two separate collections - `ArrayList<Integer> minutesShared` & `HashSet<Long> matchesSharedById`
    - also has `totalMinutesShared` field which is used to determine which pair has shared the most minutes together

- ### DTOs for each of the mentioned entities


## (Work in progress) CRUD operations for entities
- `Player`, `Team`, `Match`

## Reading data from CSV files for each entity
- Validations with the help of custom classes (e.g. `ScoreValidator`, `StringValidator`, `PlayerValidatorCsv`) for each row from the file depending on which entity it relates to
- Add each row to an `ArrayList<String[]>` object only if each of the rows has passed the validation checks
- Persist the data in the database

## Data persistence by utilizing:
- Spring JpaRepository interface
- PostgresSQL database
- pgAdmin4

## Algorithm for identifying the pair(s) of players that have shared the most minutes together 
- FIRST PART OF THE ALGORITHM - the method `public List<Pair> getPairsWithSharedMinutes()` in class `SharedMinutesService`
- a new empty `List<Pair>` is initialized (this is what will be returned by `getPairsWithSharedMinutes()`
- The algorithm loads all `Player` entities from the database into a List
- Then for each `PlayerA` finds (by `playerId`) all `records` in which the player is present and adds them to a list
- After that for each `Record` in the list the algorithm gets (by `matchId`) all `records` within the same `Match`
- Then for each of those `records` from the same `Match`, `getPairsWithSharedMinutes()` starts calling other methods provided by the `SharedMinutesUtil` class
- Next the algorithm compares the `PlayerA` (from the outermost loop) and compares its ID and `Team` to the ones of `PlayerB` from the current `Record` 
- `PlayerB` and `PlayerA` should not be the same player and also they should be from the same `Team`, so that there is no possibility to make a `Pair` like "*Toni Kroos* & *Toni Kroos*" or "*Phil Foden* (**England**) & *Alvaro Morata* (**Spain**)" 
- Then if the minutes ranges of both players overlap, their shared minutes in the match get calculated with this code: `int shared = Math.min(aEnd, bEnd) - Math.max(aStart, bStart);` and a new `Pair` is made with the two players
- After that if there is already such a `Pair` in the `List<Pair>`, the algorithm adds the shared minutes and matches to the existing `Pair` in the `List<Pair>` (avoids duplication of pairs so that we don't end up with one `Pair` (PlayerA, PlayerB) and another `Pair` (PlayerB, PlayerA);
- If there is no such `Pair` in the `List<Pair>`, the `Pair` gets added to it
- At the end `getPairsWithSharedMinutes()` returns the populated `List<Pair>` containing all possible pairs of players that shared minutes together
<!-- Blank line -->
- SECOND PART
- the method `public List<Pair> getPairsWithMostMinutesShared()` in the `SharedMinutesService` class takes the return value of `getPairsWithSharedMinutes()` in class `SharedMinutesService` and finds the most shared minutes among all pairs
- Then returns a `List<Pair>` containing every `Pair` that has the most shared minutes
- This is needed because there might be more than one `Pair` with the most shared minutes (this is the case with the whole **England** team according to the csv data)
- Finally, the method `public List<PairDto> pairDTOsWithMostMinutesShared()` transforms the `List<Pair>` into `List<PairDto>` and returns the result





## Technologies used
- Java 21
- Maven
- Spring Boot
- Spring Data JPA
- PostgresSQL
- pgAdmin4
- Lombok
- Postman 