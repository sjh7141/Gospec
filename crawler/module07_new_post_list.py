# 신규게시물의 리스트 뽑기
from selenium import webdriver

new_url = 'https://www.wevity.com/?c=find&s=1&gub=1&cidx=&sp=&sw=&gbn=list&mode=new'

options = webdriver.ChromeOptions()
options.add_argument('headless')
browser = webdriver.Chrome(options=options)
browser.get(new_url)

# .tit > a로 검색시 신규페이지 항상 20개씩
elmt_list = browser.find_elements_by_css_selector('.tit > a')

for elmt in elmt_list:
    link = elmt.get_attribute('href')
    print(link)