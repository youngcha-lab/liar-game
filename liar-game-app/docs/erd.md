```mermaid
erDiagram
room {
   String room_code
}
user {
    String room_code
    String user_code
    String nickname
}
game {
    String room_code
    String keyword
    String category
}

room ||--o{ user : ""
room ||--o{ game : ""
```