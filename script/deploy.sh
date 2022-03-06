mkdir /home/ec2-user/apps/liar-game/liar-game-app/script/debug
cd /home/ec2-user/apps/liar-game/liar-game-app/script
./before-install.sh
echo "before 실행완료" >> debug.txt
sleep 10
cd /home/ec2-user/apps/liar-game/liar-game-app/script
./deploy.sh
echo "app deploy 실행완료" >> debug.txt
sleep 10
cd /home/ec2-user/apps/liar-game/liar-game-frontend/script
./deploy.sh
echo "front deploy 실행완료" >> debug.txt
