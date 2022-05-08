tar -zxvf /usr/OpenJDK11U-jdk_x64_linux_hotspot_11.0.14.1_1.tar.gz
/jdk-11.0.14.1+1/bin/java -jar -Dspring.profiles.active=prod liar-game-app-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null
