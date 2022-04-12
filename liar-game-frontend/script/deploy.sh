#!/bin/bash
export PATH=$PATH
echo "react배포전테스트" >>  /work/docker/debug.txt
cd /home/ec2-user/apps/liar-game/liar-game-frontend

# module install
nohup /root/.nvm/versions/node/v17.9.0/bin/npm install 

sleep 200

# build
nohup /root/.nvm/versions/node/v17.9.0/bin/npm run build 

sleep 200

# find pid application
NODE_PID=`ps -ef | grep start.js | grep -v grep | awk '{print $2}'`
echo $NODE_PID >>  /work/docker/debug.txt
echo "배포테스트" >>  /work/docker/debug.txt
# if application process is already runned, kill the process
if [ -z $NODE_PID ]
then
        echo "Not process Running"  >>  /work/docker/debug.txt
else
        echo "kill current process"  >>  /work/docker/debug.txt
        kill -9 $NODE_PID
        sleep 5
fi

echo "new application process start"   >>  /work/docker/debug.txt

# react 배포 
nohup /root/.nvm/versions/node/v17.9.0/bin/npm start >>  /work/docker/debug.txt &
echo "react배포완료테스트" >>  /work/docker/debug.txt
