#!/bin/bash
export PATH=$PATH

cd /home/ec2-user/apps/liar-game/liar-game-frontend

# module install
nohup /root/.nvm/versions/node/v17.4.0/bin/npm install & 

# build
nohup /root/.nvm/versions/node/v17.4.0/bin/npm run build & 

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

# react 배포
nohup npm start & 
