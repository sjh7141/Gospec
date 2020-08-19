# 데이터 빼보기
# 3. 브라우저 창 띄울 필요없음
# 4. 제목
# 5. DTO만들기
# 6. 조회수
# 7. 분야, 응모대상, 주최/주관, 후원/협찬, 접수기간, 총 상금, 1등 상금, 홈페이지
# 8. 상세내용
# 9. try-catch블럭
# 10. contest_no추출해서 이미지파일명, sql insert, 이미지파일 다운로드 일단 여기서 1차 완성됨!

from selenium import webdriver
from dto import DTO
import pymysql
import requests

# 43959
def url(no):
    return f'https://www.wevity.com/?c=find&s=1&gbn=viewok&gp=1&ix={no}'

# DB정보는 git으로 추적하지 않도록 신경써야함!!
class CONST:
    URL = None
    PORT = None
    ID = None
    PW = None
    SCHIMA = None

dto = DTO()

options = webdriver.ChromeOptions()
options.add_argument('headless')
browser = webdriver.Chrome(options=options)
browser.get(url(43959))

# try:
#     # 한번 찾아보되, 게시물이 없으면 에러가 날 것임
#     browser.find_element_by_css_selector('.contest-detail')
# except:
#     continue

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

#이미지 파일명
conn = pymysql.connect(host=CONST.URL, port=CONST.PORT, user=CONST.ID, password=CONST.PW, db=CONST.SCHIMA, charset='utf8')
get_sql = "SELECT max(contest_no) FROM contest_info"
insert_sql_1 = 'INSERT INTO `contest_info` (`contest_no`,`title`, `view_count`, `target`, `host`, `sponsor`, `start_date`, `end_date`, `total_reward`, `first_reward`, `homepage`, `content`,`img_src`) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'
insert_sql_2 = 'INSERT INTO `contest_field` (`contest_no`, `field`) VALUES (%s, %s)'
try:
    with conn.cursor() as cursor:
        cursor.execute(get_sql)
        dto.set_contest_no(cursor.fetchone()[0])
        print(dto.__dict__)
        cursor.execute(insert_sql_1, (dto.contest_no, dto.title, dto.view_count, dto.target, dto.host, dto.sponsor, dto.start_date, dto.end_date, dto.total_reward, dto.first_reward, dto.homepage, dto.content, dto.img_src))
        for field in dto.contest_field:
            cursor.execute(insert_sql_2, (dto.contest_no, field))
    # conn.commit()
finally:
    conn.close()


# 이미지파일 저장
src = browser.find_element_by_css_selector('.thumb > img:first-child').get_attribute('src')
with open(dto.img_src,'wb') as f:
  f.write(requests.get(src).content)