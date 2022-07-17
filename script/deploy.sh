#app 배포
cd /home/ec2-user/apps/liar-game/liar-game-app/script
chmod 755 deploy.sh
./deploy.sh &  >> /work/docker_error.txt
sleep 10

# frontend 배포
cd /home/ec2-user/apps/liar-game/liar-game-frontend/script
chmod 755 deploy.sh
./deploy.sh & 
