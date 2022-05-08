#!/bin/bash
cd /home/ec2-user/apps/liar-game/liar-game-frontend
export PATH=$PATH

# module install
#/usr/bin/nohup 
/root/.nvm/versions/node/v17.9.0/bin/npm >> /work/0508.txt
sleep 10

mkdir /work/testa

# build
/usr/bin/nohup /root/.nvm/versions/node/v17.9.0/bin/npm run build
sleep 10

/usr/bin/nohup /root/.nvm/versions/node/v17.9.0/bin/npm start &
