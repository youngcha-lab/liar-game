#app 배포 테스트
cd /home/ec2-user/apps/liar-game/liar-game-app/script
chmod 755 deploy.sh
./deploy.sh &
sleep 10

# frontend 배포
cd /home/ec2-user/apps/liar-game/liar-game-frontend/script
chmod 755 deploy.sh
./deploy.sh >>/work/docker/alldebug.txt &
