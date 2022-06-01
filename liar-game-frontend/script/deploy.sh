# find pid liar react application 
APP_PID=`docker ps -a | grep react | grep Up`

# if application process is already runned, kill the process
if [ -z $APP_PID ]
then
        echo "Not process Running" 
else
        echo "kill current process" 
        docker stop react
        sleep 5
        docker rm react
        sleep 5
fi

echo "new liar react application process start"

# docker application container build
docker image rm frontendimage
docker build -t frontendimage .

sleep 5

# docker react application container start 
docker run -p 3000:3000 -v /home/ec2-user/apps/liar-game/liar-game-frontend:/app -d --name react frontendimage
