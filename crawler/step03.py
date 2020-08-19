# 데이터 빼보기
# 3. 브라우저 창 띄울 필요없음

from selenium import webdriver

# 43959
def url(no):
    return f'https://www.wevity.com/?c=find&s=1&gbn=viewok&gp=1&ix={no}'


options = webdriver.ChromeOptions()
options.add_argument('headless')
browser = webdriver.Chrome(chrome_options=options)
browser.get(url(43959))

contest_detail = browser.find_element_by_css_selector('.contest-detail')

print(type(contest_detail.text))