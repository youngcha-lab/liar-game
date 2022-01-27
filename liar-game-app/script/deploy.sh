# move repository home
cd /home/ec2-user/apps/liar-game

# update git pull list
git fetch --all

# reset git to update branch
git reset --hard origin/main

# start build project
cd liar-game-app
./gradlew build

# find pid liar application
APP_PID=`ps -ef | grep liar-game-app-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`

# if application process is already runned, kill the process
if [ -z $APP_PID ]
then
        echo "Not process Running"
else
        echo "kill current process"
        kill -9 $APP_PID
        sleep 5
fi

echo "new liar application process start"

# application process start
cd build/libs
nohup java -jar liar-game-app-0.0.1-SNAPSHOT.jar &
