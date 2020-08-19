# 데이터 빼보기
# 3. 브라우저 창 띄울 필요없음
# 4. 제목
# 5. DTO만들기
# 6. 조회수
# 7. 분야, 응모대상, 주최/주관, 후원/협찬, 접수기간, 총 상금, 1등 상금, 홈페이지

from selenium import webdriver
from dto import DTO

# 43959
def url(no):
    return f'https://www.wevity.com/?c=find&s=1&gbn=viewok&gp=1&ix={no}'

dto = DTO()

options = webdriver.ChromeOptions()
options.add_argument('headless')
browser = webdriver.Chrome(options=options)
browser.get(url(39603))

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

print(dto.__dict__)