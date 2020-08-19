# 데이터 빼보기
from selenium import webdriver

# 43959
def url(no):
    return f'https://www.wevity.com/?c=find&s=1&gbn=viewok&gp=1&ix={no}'

browser = webdriver.Chrome()
browser.get(url(43959))

contest_detail = browser.find_element_by_css_selector('.contest-detail')
print(contest_detail.text)