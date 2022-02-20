cd /home/ec2-user/apps/liar-game/liar-game-app/build/libs

# find pid liar application
APP_PID=`ps -ef | grep liar-game-app-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
echo "debug APP_PID : $APP_PID" >> debug.txt
# if application process is already runned, kill the process
if [ -z $APP_PID ]
then
        echo "Not process Running"
        echo "프로세스 실행중 아님" >> debug.txt
else
        echo "kill current process"
        kill -9 $APP_PID
        echo "프로세스 종료" >> debug.txt
        sleep 5
fi

echo "new liar application process start"
echo "프로세스 시작전" >> debug.txt
# application process start
cd /home/ec2-user/apps/liar-game/liar-game-app/build/libs
nohup java -jar liar-game-app-0.0.1-SNAPSHOT.jar 
echo "프로세스 시작" >> debug.txt
