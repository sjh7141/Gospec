# 파이썬에서 mysql 데이터베이스에 자료 삽입하기
# http://pythonstudy.xyz/python/article/203-MySQL-DML
# pip install pymysql

import pymysql

# DB정보는 git으로 추적하지 않도록 신경써야함!!
class CONST:
    URL = None
    PORT = None
    ID = None
    PW = None
    SCHIMA = None

conn = pymysql.connect(host=CONST.URL, port=CONST.PORT, user=CONST.ID, password=CONST.PW, db=CONST.SCHIMA, charset='utf8')

try:
    with conn.cursor() as cursor:
        sql = "INSERT INTO `user` (`uid`,`email`, `password`) VALUES (%s, %s, %s)"
        cursor.execute(sql, ('asdf', 'webmaster@python.org', 'very-secret'))
    conn.commit()
finally:
    conn.close()