cd /home/ec2-user/apps/liar-game/liar-game-frontend

# find pid application
NODE_PID=`ps -ef | grep '/bin/serve -s -n build' | grep -v grep | awk '{print $2}'`

# if application process is already runned, kill the process
if [ -z $NODE_PID ]
then
        echo "Not process Running"
else
        echo "kill current process"
        kill -9 $NODE_PID
        sleep 5
fi

echo "new application process start"

# application process start
nohup npx serve -s -n > /dev/null 2> /dev/null < /dev/null &

