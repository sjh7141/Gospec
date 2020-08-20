# 이미지 다운로드
# https://intellipaat.com/community/13151/download-image-with-selenium-python
# pip install urllib >> 안내된 내용이 레거시코드라 안되는 것 같아서 다른자료 참조
# https://stackoverflow.com/questions/15035123/what-command-to-use-instead-of-urllib-request-urlretrieve
# pip install requests

from selenium import webdriver
import urllib
import requests

# 43959
def url(no):
    return f'https://www.wevity.com/?c=find&s=1&gbn=viewok&gp=1&ix={no}'

options = webdriver.ChromeOptions()
options.add_argument('headless')

browser = webdriver.Chrome(options=options)
browser.get(url(43959))

img_element = browser.find_element_by_css_selector('.thumb > img:first-child')
src = img_element.get_attribute('src')
r = requests.get(src)

with open('asdf.jpg','wb') as f:
  f.write(r.content)