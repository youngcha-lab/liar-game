# find pid liar application 
APP_PID=`docker ps -a | grep spring | grep Up`

# if application process is already runned, kill the process
if [ -z $APP_PID ]
then
        echo "Not process Running" 
else
        echo "kill current process" 
        docker stop spring
        sleep 5
        docker rm spring
        sleep 5
fi

echo "new liar application process start"

# docker application container build
docker image rm backendimage
cp -pr /home/ec2-user/apps/liar-game/liar-game-app/build/libs/liar-game-app-0.0.1-SNAPSHOT.jar /home/ec2-user/apps/liar-game/liar-game-app/script/
cp -pr /work/docker/backend/OpenJDK11U-jdk_x64_linux_hotspot_11.0.14.1_1.tar.gz /home/ec2-user/apps/liar-game/liar-game-app/script/
docker build -t backendimage .

sleep 5

# docker application container start 
docker run -d -p 8080:8080 --name spring backendimage
