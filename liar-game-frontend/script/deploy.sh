cd /home/ec2-user/apps/liar-game/liar-game-frontend

echo " frontend deploy 시작" >> front_debug.txt 
# module install
npm install  
echo " npm install 완료" >> front_debug.txt
# build
npm run build 
echo " npm build 완료" >> front_debug.txt
# find pid application
NODE_PID=`ps -ef | grep '/bin/serve -s -n build' | grep -v grep | awk '{print $2}'`

# if application process is already runned, kill the process
if [ -z $NODE_PID ]
then
        echo "Not process Running" >> front_debug.txt
else
        echo "kill current process" >> front_debug.txt
        kill -9 $NODE_PID
        sleep 5
fi

echo "new application process start" >> front_debug.txt

# application process start
npx serve -s -n build &
echo "new application process start 실행완료" >> front_debug.txt 
