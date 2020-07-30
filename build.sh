# (in project root) ./build.sh

echo 'STEP1) FRONT BUILD JOB...'
cd front
npm install
npm run build
echo 'STEP2) COPY BUILD FILE TO SPRINGBOOT...'
mkdir ../Back-End/static
cp -r ./dist/* ../Back-End/static
echo 'STEP3) RUN SPRINGBOOT SERVER...'
cd ../Back-End
if [ $(expr length "$(sudo netstat -tnlp | grep 8181 | awk '{print $7}' | sed 's/\/java//g')") -ne 0 ];then sudo kill $(sudo netstat -tnlp | grep 8181 | awk '{print $7}' | sed "s/\/java//g") ;fi
# 이미 8181포트가 사용중일 경우 종료시키는 명령
sudo chmod 755 ./mvnw
nohup ./mvnw spring-boot:run &
# nohup: appending output to 'nohup.out' 이 메세지 확인 후 엔터치면 스크립트는 정상종료되고 sudo netstat -tnlp로 확인가능
