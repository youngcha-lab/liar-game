mkdir /home/ec2-user/apps/liar-game/liar-game-app/script/debug
cd /home/ec2-user/apps/liar-game/liar-game-app/script
sh -x before-install.sh >> debug.txt
echo "before 실행완료" >> debug.txt
sleep 10
cd /home/ec2-user/apps/liar-game/liar-game-app/script
sh -x deploy.sh >> debug.txt
echo "app deploy 실행완료" >> debug.txt
sleep 10
cd /home/ec2-user/apps/liar-game/liar-game-frontend/script
sh -x deploy.sh >> debug.txt
echo "front deploy 실행완료" >> debug.txt
