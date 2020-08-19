# 셀레니움 실행하기
from selenium import webdriver

URL = 'https://www.wevity.com/?c=find&s=1&gbn=viewok&gp=1&ix=43959'

browser = webdriver.Chrome()
browser.get(URL)