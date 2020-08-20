# 별도의 윈도우 창 없이 셀레니움 실행하기
# https://intoli.com/blog/running-selenium-with-headless-chrome/
from selenium import webdriver

FIRST_WEB = 'http://info.cern.ch/hypertext/WWW/TheProject.html'

options = webdriver.ChromeOptions()
options.add_argument('headless')
browser = webdriver.Chrome(chrome_options=options)

browser.get(FIRST_WEB)

print(browser)