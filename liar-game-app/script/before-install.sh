#!/bin/bash
echo "before 실행" >> before_debug.txt 
if [ -d /home/ec2-user/apps/liar-game ]; then
    rm -rf /home/ec2-user/apps/liar-game
fi
mkdir -vp /home/ec2-user/apps/liar-game
cp -pr /home/ec2-user/apps/liar-game/liar-game-app/build/libs/liar-game-app-0.0.1-SNAPSHOT.jar /home/ec2-user/apps/liar-game/liar-game-app/script/
cp -pr /work/docker/backend/OpenJDK11U-jdk_x64_linux_hotspot_11.0.14.1_1.tar.gz /home/ec2-user/apps/liar-game/liar-game-app/script/
