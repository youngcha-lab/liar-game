# move repository home
cd /home/ec2-user/apps/liar-game

# update git pull list
git fetch --all

# reset git to update branch
git reset --hard origin/main

# start build project
./liar-game-app/gradlew build

# find pid liar application
APP_PID=`pgrep liar.jar`

if [ -z $APP_PID]
then
        echo "Not process Running"
else
        echo "kill current process"
        kill -9 $APP_PID
        sleep 5
fi

echo "new liar application process start"

nohup java -jar liar.jar &
