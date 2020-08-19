# 파일 저장시에 특정 경로로 저장하기
# https://stackoverflow.com/questions/8024248/telling-python-to-save-a-txt-file-to-a-certain-directory-on-windows-and-mac

from selenium import webdriver
import requests
import os.path

class PATH:
    RELATIVE = './img'
    ABSOLUTE = 'C:/Users/multicampus/Desktop/image'

    def abs_build(self, fileName):
        return os.path.join(self.ABSOLUTE, fileName)
    def rel_build(self, fileName):
        return os.path.join(self.RELATIVE, fileName)

print(PATH.abs_build('touch.png'))

def url(no):
    return f'https://www.wevity.com/?c=find&s=1&gbn=viewok&gp=1&ix={no}'

options = webdriver.ChromeOptions()
options.add_argument('headless')
browser = webdriver.Chrome(options=options)
browser.get(url(44190))

src = browser.find_element_by_css_selector('.thumb > img:first-child').get_attribute('src')

with open(PATH.rel_build('qwer.jpg'), 'wb') as f:
    f.write(requests.get(src))