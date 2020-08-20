# 데이터 빼보기
# 3. 브라우저 창 띄울 필요없음
# 4. 제목
# 5. DTO만들기

from selenium import webdriver
from dto import DTO

# 43959
def url(no):
    return f'https://www.wevity.com/?c=find&s=1&gbn=viewok&gp=1&ix={no}'

dto = DTO()

options = webdriver.ChromeOptions()
options.add_argument('headless')
browser = webdriver.Chrome(options=options)
browser.get(url(43959))

dto.title = browser.find_element_by_css_selector('.tit').text

print(dto.__dict__)