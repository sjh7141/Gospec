# 클래스의 멤머메서드 사용법 익히기

class FOO:
    x = 'asdf'

    @classmethod
    def jjoin(self, _string):
        return '#'.join([self.x, _string])

print(FOO.jjoin('bar'))