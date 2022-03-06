mkdir /home/ec2-user/apps/liar-game/liar-game-app/script/debug
cd /home/ec2-user/apps/liar-game/liar-game-app/script
chmod 755 before-install.sh
./before-install.sh 
echo "before 실행완료" >> debug.txt
sleep 10
cd /home/ec2-user/apps/liar-game/liar-game-app/script
chmod 755 deploy.sh
./deploy.sh >> debug.txt
echo "app deploy 실행완료" 
sleep 10
cd /home/ec2-user/apps/liar-game/liar-game-frontend/script
chmod 755 deploy.sh
./deploy.sh >> debug.txt
echo "front deploy 실행완료" 
