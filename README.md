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

### 1. Push Main Branch

### 2. Run Github Action(Auto)
   ```
   - ./gradlew clean build
   - Make zip file
   - upload zip to aws S3
   - run code deploy
   ```
### 3. Run Aws Code-deployment(Auto)
   ```
   - send zip to aws S2 from aws S3
   - run appspec.yml
   ```
      
### 4. Run appspec.yml(Auto)
   ```
   - run server deploy.sh 
   - run frontend deploy.sh
   ```  
### 5. Run Server deploy.sh(Auto)
   ```
   - stop and remove server docker process ( if server docker is running)
   - remove server docker image
   - rebuild server docker image
   - run server docker image
   ```  
### 6. Run Frontend deploy.sh(Auto)
   ```
   - stop and remove frontend docker process ( if frontend docker is running)
   - remove frontend docker image
   - rebuild frontend docker image
   - run frontend docker image
   ```    
