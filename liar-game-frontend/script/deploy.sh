#!/bin/bash
export PATH=$PATH
echo "react배포전테스트" >>  /work/docker/debug.txt
cd /home/ec2-user/apps/liar-game/liar-game-frontend

# module install
nohup /root/.nvm/versions/node/v17.9.0/bin/npm install 

# build
nohup /root/.nvm/versions/node/v17.9.0/bin/npm run build 

# find pid application
NODE_PID=`ps -ef | grep start.js | grep -v grep | awk '{print $2}'`
echo $NODE_PID >>  /work/docker/debug.txt
echo "배포테스트" >>  /work/docker/debug.txt
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

# react 배포 
nohup /root/.nvm/versions/node/v17.9.0/bin/npm start > /dev/null 2> /dev/null < /dev/null &
echo "react배포완료테스트" >>  /work/docker/debug.txt
