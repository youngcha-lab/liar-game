```mermaid
sequenceDiagram
actor user as User
participant fe as Frontend
participant server as Server

user ->> fe: Click 'end game' button
fe ->> server: Request to quit the game (DELETE /api/game)
server ->> server: Delete game
server ->> server: Find liar
server ->> fe: Response liar nickname
fe ->> user: Show who is liar
```
