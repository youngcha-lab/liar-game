#!/bin/bash

cd /home/ec2-user/apps/liar-game/liar-game-frontend

echo " frontend deploy 시작" >> front_debug.txt 
# module install
nohup npm install &
echo " npm install 완료" >> front_debug.txt
# build
nohup npm run build &
echo " npm build 완료" >> front_debug.txt


nohup /root/.nvm/versions/node/v17.4.0/bin/npx serve -s -n build &
