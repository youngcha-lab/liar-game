#test2
mkdir /app
cd /app
npm cache clean --force 
nohup npm install
nohup npm run build
nohup npm start
