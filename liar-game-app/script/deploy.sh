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
docker build -t backendimage .

sleep 5

# docker application container start 
docker run -d -p 8080:8080 --name spring backendimage
