cd /home/ec2-user/apps/liar-game/liar-game-frontend

echo "debug test" >> /work/mydebug.txt

# module install
/usr/bin/nohup /root/.nvm/versions/node/v17.9.0/bin/npm install 
sleep 10

# build
/usr/bin/nohup /root/.nvm/versions/node/v17.9.0/bin/npm run build
sleep 10

/usr/bin/nohup /root/.nvm/versions/node/v17.9.0/bin/npm start &
