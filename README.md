# Liar game

Web based liar game.

## Projects

```
├── liar-game-app           # Backend
├── liar-game-frontend      # Frontend
└── script                  # Deploy shell script
```

## Environment

- Kotlin 1.5 (with Java 11)
- Gradle 7.2
- Spring Boot 2.6
- React 17

## How to start project

### 1. Run Server

1. open terminal where the liar-game-app dir is located
2. Enter the command below
   ```
   ./gradlew bootJar
   ./gradlew clean bootRun
   ```

### 2. Run front

1. open terminal where the liar-game-frontend dir is located
2. Enter the command below
   1. When you first build the project
      ```
       npm install
       npm start
      ```
   2. After
      ```
      npm start
      ```
