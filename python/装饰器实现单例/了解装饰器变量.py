# 自定义一个容器，类，看容器会实例化几次
class Hodect():
    def __init__(self,name):
        self.li = list()
        self.name = name
        print(self.name+'容器实例化')
    def __del__(self):
        print(self.name+"容器删除")

def S(func):
    h = Hodect('傅雨')
    def wrap(*args,**kwargs):
        if func not in h.li:
            h.li.append(func)
        print('%s装饰完毕'%(func.__name__))
        return func(*args,**kwargs)
    return wrap
@S
def func1():
    print('func1')
@S
def func2():
    print('func2')
@S
def func3():
    print('func3')
@S
def func4():
    print('func4')
def f():
    print("ff")
if __name__=='__main__':
    # 装饰器注释几次实例化几次,而且不论调用与否
    func1()
    func1()
    func2()
    func2()
    func3()
    func4()
    S(f)
