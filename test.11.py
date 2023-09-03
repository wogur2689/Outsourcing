from selenium import webdriver 
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By

import time

#############################
# 받을 데이터 목록
title = [] # 제목(없을시 none)
url = [] #게시글 url
redirect = [] # 도달하는 url
#############################

# 크롬 드라이버 받아오기 (현재 크롬116버전의 크롬드라이버가 없음. 그래서 크롬드라이버를 다운로드 받아서 실행.)
# chrome_driver = ChromeDriverManager().install()

# service객체 생성
service = Service(executable_path='./chromedriver.exe')

# USB: usb_device_handle_win.cc:1046 Failed to read descriptor from node connection: 시스템에 부착된 장치가 작동하지 않습니다. (0x1F) 에러 방지
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])

# 드라이버에 서비스 및 옵션 추가
driver = webdriver.Chrome(service = service, options=options)

# 핀터레스트 사이트 접속
URL = "https://www.pinterest.co.kr/LOLrefun/_saved/"
driver.get(URL)

# selenium은 페이지를 직접 이동하면서 조작함. 
# 데이터를 받아야되는데 크롬이 로딩중 이면 발생하는 에러를 방지하기 위한 지연시간.
time.sleep(5)

#크롬창을 터미널에서 사용자 입력으로 닫는 코드
while True:
    value = int(input("종료하시려면 0을 입력하세요 >>> "))
    if value == 0:
        print("3초후에 닫힙니다.")
        break

    # selenium의 by에서 검색방법(CSS Selector) 사용하여 모든 요소의 값을 찾아옴.
    button = driver.find_element(By.CSS_SELECTOR, "a.Wk9.xQ4.CCY.S9z.DUt.iyn.kVc.Tbt.L4E.e8F.BG7")
    # 해당 태그를 클릭
    button.click()
    # 화면 대비 소요시간
    time.sleep(1)


# 실행했던 크롬창 종료.
time.sleep(3)
driver.close()

