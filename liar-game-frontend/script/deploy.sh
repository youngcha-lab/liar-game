cd /home/ec2-user/apps/liar-game/liar-game-frontend

sleep 300

# module install
nohup npm install 

sleep 10

mkdir /work/test0601

# build
nohup npm run build

sleep 10

nohup npm start
