/*
배포하기
1) npm install
    빌드위해서 빠진 패키지 있으면 설치
2) npm run build
    빌드하여 산출물을 dist디렉토리에 저장
3-1) node spa_server.js
    node환경에서 정적파일 제공 목적의 서버를 express사용하여 구현
3-2) nohup node spa_server.js &
    이건 백그라운드에서 실행하도록 하는 명령어
    콘솔을 닫아도 계속 돌아간다
*/

const express = require('express');
const history = require('connect-history-api-fallback');
const PORT = 80;

const server = express();

// 뷰라우터에서 history모드 사용시 나타나는 문제 해결을 위한 솔루션 중 하나
// 아래 코드와의 위치(순서)가 정상동작 여부에 영향을 미침. 이유는 아직모름. 
// 자세한 내용은 다음 참고.https://router.vuejs.org/guide/essentials/history-mode.html#example-server-configurations
server.use(history());

// 정적파일요청을 dist 디렉토리에서 찾는다
server.use(express.static('dist'));

server.listen(PORT, () => console.log(`SPA SERVER IS RUNNING AT http://localhost:${PORT}`));
