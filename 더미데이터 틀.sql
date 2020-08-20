SELECT * FROM ssafy.contest_info;

update contest_info set content = '■ 제 목 : 보고싶다 정선아 단편 영화제

■ 주 최 : 정선군 

■ 주 관 : 오마이필름

■ 후 원 : 유쾌한생각

■ 내 용 :‘정선’이라는 이름을 가진 인물 또는 케릭터가 등장하는 영상
- 러닝타임 : 3분 미만
- 영상포맷 : FHD 1920×1080 / 24p or 30p / mp4(권장), mov, mpeg, wmv
- 참가작품은 홈페이지를 통해 시청할 수 있음
- 필수삽입영상 : 영상 끝에 정선군 로고 삽입
- 주제 : 보고싶다 정선아
- 참가팀 : 30팀 선정
- 참가지원금 : 각 팀당 20만원 (총 600 만원)
* 선정된 30팀에게 제작지원금 지급

■ 행사기간 
- 참가자 모집기간 : 2020년 7월 27일(월) ~ 2020년 8월 12일(수)
- 참가자발표 : 8월 14일(금)
- 제작기간 : 2020년 8월 17일(월) ~ 2020년 9월 6일(일)
- 심사기간 - 9/7(월) ~ 9/10(목)
- 결과발표 및 시상식 - 9/11(금)

■ 참가자격 : 18세 이상 누구나

■ 참가방법 : 공식 홈페이지를 통해 신청
※ 공식 홈페이지 추후 오픈 : http://jeongseonmovie.com

■ 문의전화 : 02-578-5281

■ 심사내역
- 주제연관성 : 20점
- 연출부분 : 20점
- 촬영부분 : 20점
- 아이디어 : 20점
- 참여도 : 20점

■ 시상내역 : 총상금(7,500,000원)
- 1등 : 300만원 / 1명 대상
- 2등 : 100만원 / 1명 최우수상
- 3등 : 50만원 / 4명 우수상
- 4등 : 30만원 / 5명 장려상
- 5등 : 지윤스무스4 / 5명 특별상
※ 수상 및 경품은 대한민국 정부의 규정에 근거하여 국내 수상자의 경우세금 공제 후 지급

■ 주의사항 
ㄱ. 시상 일정 및 경품 지급은 수상자에게 개별 공지됩니다.
ㄴ. 수상자의 저작권은 제작자에게 귀속 됩니다.
ㄷ. 최종 선정 후에도 국내외에서 이미 사용한 영상일 경우 수상 취소
ㄹ. 초상권 및 저작권 문제가 발생할 경우 모든 책임은 해당 제작자에게 발생
ㅁ. 타 영화제의 수상작, 타인의 저작물 도용 등의 경우 심사제외 및 시상내역(시상금)은 환수합니다.
ㅂ. 수상 및 경품은 대한민국 정부의 규정에 근거하여 국내 수상자의 경우 4.4%의 세금 공제 후 지급
ㅅ. 해외 수상자의 경우 한국 법과 규정에 따라 세금 보고를 위해 이메일을 사용 개인정보를 수집할 것입니다. 세금(세금 및 제세공과금)은 오마이필름이 부담
ㅇ. 수상작품에 대하여 저작권은 제작자, 정선군, 오마이필름과 공유 된다
ㅈ. 출품작은 제 3자의 저작권(음원, 영상, 사진, 폰트 등) 초상권,상표권등 일체의 법적문제가 없어야 합니다.
ㅊ. 페러디물 제작시 제작자의 해석이나 비평, 풍자가 첨부되어야 합니다. 
'

where contest_no = 49;

SELECT * FROM ssafy.contest_field;

insert into contest_field values(49, '광고-마케팅');
insert into contest_field values(45, '디자인-캐릭터-웹툰');
insert into contest_field values(35, '건축-건설-인테리어');
insert into contest_field values(49, '영상-UCC-사진');
insert into contest_field values(49, '웹-모바일-플래시');
insert into contest_field values(47, '게임-소프트웨어');
insert into contest_field values(49, '예체능-미술-음악');
insert into contest_field values(47, '대외활동-서포터즈');
insert into contest_field values(29, '네이밍-슬로건');
insert into contest_field values(21, '봉사활동');
insert into contest_field values(47, '기타');
insert into contest_field values(43, '기획-아이디어');
insert into contest_field values(43, '논문-리포트');
insert into contest_field values(41, '문학-글-시나리오');
insert into contest_field values(17, '해외');
insert into contest_field values(47, '과학-공학');
insert into contest_field values(17, '취업-창업');