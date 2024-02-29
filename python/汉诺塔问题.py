'''
    汉诺塔问题，将n个盘中从a移动到c，每次只能移动一个，且大盘子必须在小盘子下面
        思路：
            n == 1  ： 直接将盘中移动到c上面
            n > 1:
                1-> 将n-1个从a移动到b  #又是一个汉诺塔问题
                2-> 将第n个盘子移动到c
                3-> 将b上面n-1个盘子移动到c上面  # 同样是一个汉诺塔问题
'''
# 递归版
def hanio(n,a='A',b='B',c='C'):
    if n == 1:
        print(a + '-'+'1'+'->' + c)
    else:
        hanio(n-1,a,c,b)
        print(a + '-'+str(n)+'->' + c)
        hanio(n-1,b,a,c)
# 非递归版
# 为汉诺塔问题定义一个专门的栈
class stack:
    def __init__(self):
        self.li = []
        self.top = -1
    def push(self,val):
        if self.top == -1:
            self.li.append(val)
        else:
            if self.li[self.top] < val:
                raise ValueError('压入栈值错误')
            self.li.append(val)
        self.top += 1
    def pop(self):
        if self.top == -1:
            raise IndexError('栈为空')
        self.top -=1
        return self.li.pop()
    def isEmpty(self):
        if self.top == -1:
            return False
        else:
            return True
    def __iter__(self):
        return ClassIterator(self.li)

class ClassIterator(object):
    def __init__(self,it):
        self.it = it
        self.index = len(it)-1

    def __iter__(self):
        pass

    def __next__(self):
        if self.index >= 0:
            data = self.it[self.index]
            self.index-=1
            return data
        else:
            raise StopIteration

def hanoi(n,a,b,c):
    if( n == 1 ):
        c.push(a.pop())
    else:
        hanoi(n-1,a,c,b)
        c.push(a.pop())
        hanoi(n-1,b,a,c)
if __name__=='__main__':
    a = stack()
    a.push(5)
    a.push(4)
    a.push(3)
    a.push(2)
    a.push(1)

    b = stack()
    c = stack()
    for i in a:
        print(i,end=',')
    hanoi(5,a,b,c)
    for i in c:
        print(i,end=',')

