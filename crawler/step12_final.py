# 데이터 빼보기 steps
# 3. 브라우저 창 띄울 필요없음
# 4. 제목
# 5. DTO만들기
# 6. 조회수
# 7. 분야, 응모대상, 주최/주관, 후원/협찬, 접수기간, 총 상금, 1등 상금, 홈페이지
# 8. 상세내용
# 9. try-catch블럭
# 10. contest_no추출해서 이미지파일명, sql insert, 이미지파일 다운로드 일단 여기서 1차 완성됨!
# 11. 크롤링 패턴: 신규게시물 페이지 접속해서 게시물 리스트 뽑기 >> 공모전 순회하면서 중복체크하고, 중복아닐시에 데이터 저장 + 파일저장경로 옵션 추가
# 12. 1차 완성본. 정상동작여부 테스트 완료

from selenium import webdriver
from dto import DTO
import pymysql
import requests
from time import sleep
import os.path

new_url = 'https://www.wevity.com/?c=find&s=1&gub=1&cidx=&sp=&sw=&gbn=list&mode=new'

class SQL:
    duplicate_check = "SELECT count(*) FROM contest_info WHERE title = %s"
    get_ctst_no = "SELECT max(contest_no) FROM contest_info"
    insert1 = 'INSERT INTO `contest_info` (`contest_no`,`title`, `view_count`, `target`, `host`, `sponsor`, `start_date`, `end_date`, `total_reward`, `first_reward`, `homepage`, `content`,`img_src`) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'
    insert2 = 'INSERT INTO `contest_field` (`contest_no`, `field`) VALUES (%s, %s)'

# DB정보는 git으로 추적하지 않도록 신경써야함!!
class CONST:
    URL = None
    PORT = None
    ID = None
    PW = None
    SCHIMA = None

class PATH:
    RELATIVE = './img'
    ABSOLUTE = 'C:/Users/multicampus/Desktop/image'
    @classmethod
    def build(self, fileName):
        return os.path.join(self.RELATIVE, fileName)    # 여기서 절대경로 또는 상대경로 지정

COMMIT_COUNT = 0

options = webdriver.ChromeOptions()
options.add_argument('headless')
browser = webdriver.Chrome(options=options)
# browser = webdriver.Chrome()  # 브라우저 켠채로 실행하는 코드
browser.get(new_url)

# .tit > a로 검색시 신규페이지, 갯수는 유동적인듯 16~20개
link_list = []
elmt_list = browser.find_elements_by_css_selector('.tit > a')
for elmt in elmt_list:
    link_list.append(elmt.get_attribute('href'))

for idx, link in enumerate(link_list):
    print(f'{idx+1}번째 크롤링 시작: {link.split("/")[-1]}')
    browser.get(link)

    dto = DTO()
    dto.title = browser.find_element_by_css_selector('.tit').text
    dto.view_count = browser.find_element_by_css_selector('.read').text.split(':')[-1].strip().replace(',','')

    raw_list = browser.find_element_by_css_selector('.cd-info-list').text.split('\n')

    dto.contest_field = list(map(lambda x: x.strip(), raw_list[raw_list.index('분야') + 1].replace('/', '-').split(',')))
    dto.target = raw_list[raw_list.index('응모대상') + 1]
    dto.host = raw_list[raw_list.index('주최/주관') + 1]
    dto.sponsor = None if raw_list[raw_list.index('후원/협찬') + 1] == '접수기간' else raw_list[raw_list.index('후원/협찬') + 1]
    dto.start_date = raw_list[raw_list.index('접수기간') + 1].split(' ')[0]
    dto.end_date = raw_list[raw_list.index('접수기간') + 1].split(' ')[2]
    dto.total_reward = raw_list[raw_list.index('총 상금') + 1]
    dto.first_reward = None if raw_list[raw_list.index('1등 상금') + 1] == '홈페이지' else raw_list[raw_list.index('1등 상금') + 1]
    dto.homepage = raw_list[raw_list.index('홈페이지') + 1]
    dto.content = browser.find_element_by_css_selector('#viewContents').text

    # db접속해서 conetst_no 얻고, 중복체트, 데이터 insert
    conn = pymysql.connect(host=CONST.URL, port=CONST.PORT, user=CONST.ID, password=CONST.PW, db=CONST.SCHIMA, charset='utf8')
    try:
        with conn.cursor() as cursor:

            # 중복검사로직
            cursor.execute(SQL.duplicate_check, (dto.title))
            if cursor.fetchone()[0] == 1:
                print(f'{idx+1}번째 크롤링 데이터 중복으로 건너뛰기')
                continue
            
            # PK contest_no 체크 로직
            cursor.execute(SQL.get_ctst_no)
            dto.set_contest_no(cursor.fetchone()[0])

            # 데이터 db에 넣는 로직
        #     cursor.execute(SQL.insert1, (dto.contest_no, dto.title, dto.view_count, dto.target, dto.host, dto.sponsor, dto.start_date, dto.end_date, dto.total_reward, dto.first_reward, dto.homepage, dto.content, dto.img_src))
        #     for field in dto.contest_field:
        #         cursor.execute(SQL.insert2, (dto.contest_no, field))
        # conn.commit()

        COMMIT_COUNT += 1
        print(f'{idx+1}번째 크롤링 성공, {COMMIT_COUNT}번째 작업')
    finally:
        conn.close()

    # 이미지파일 저장
    src = browser.find_element_by_css_selector('.thumb > img:first-child').get_attribute('src')
    with open(PATH.build(dto.img_src),'wb') as f:
        f.write(requests.get(src).content)

print(f'== 작업종료: 총 입력한 자료: {COMMIT_COUNT} ==')