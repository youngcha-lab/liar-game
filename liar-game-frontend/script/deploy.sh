#!/bin/bash
cd /home/ec2-user/apps/liar-game/liar-game-frontend
export PATH=$PATH

# module install
#/usr/bin/nohup
/root/.nvm/versions/node/v17.9.0/bin/npm install >> /work/0508.txt

sleep 10

mkdir /work/testa

# build
/root/.nvm/versions/node/v17.9.0/bin/npm run build >> /work/0508.txt
sleep 10

/root/.nvm/versions/node/v17.9.0/bin/npm start & >> /work/0508.txt
