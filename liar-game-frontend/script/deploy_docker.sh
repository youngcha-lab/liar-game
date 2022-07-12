#test
mkdir /app
cd /app
nohup npm run clean && npm install
nohup npm run build
nohup npm start
