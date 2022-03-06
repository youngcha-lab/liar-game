mkdir /home/ec2-user/apps/liar-game/liar-game-app/script/debug

cd /home/ec2-user/apps/liar-game/liar-game-app/script
chmod 755 deploy.sh
./deploy.sh &
echo "app deploy 실행완료" 
sleep 10
cd /home/ec2-user/apps/liar-game/liar-game-frontend/script
chmod 755 deploy.sh
./deploy.sh &
echo "front deploy 실행완료" 
