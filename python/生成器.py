
'''generator(生成器类名）和函数的执行流程，函数是顺序执行的，遇到return语句或者最后一行函数语句就返回。
而变成generator的函数，在每次调用next()的时候执行，遇到yield语句返回，
再次被next（）调用时候从上次的返回yield语句处急需执行，也就是用多少，取多少，不占内存。'''
'''注：生成器函数 本质类型还是函数 '''

'''生成器的工作流程是一次调用在yield处接着执行'''
c = 1
def a():
    global c
    c += 1
    yield c
for i in range(10):
    print(next(a()))


def fib(n):
    a, b = 0, 1
    while b <= n:
        '''方法一旦出现yeild 就不再是方法，而是一个生成器即generator的函数'''
        yield b
        a, b = b, a+b

f = fib(10)
for item in f:
    print(item)
'''迭代器与生成器不属于同一个类，也没有子父类关系，它们关系其实不大，但生成器可以具备迭代器的功能'''
print(isinstance(f,type(iter([1,2])))) #判断是否是生成器是否是迭代器那个类 结果False
print(issubclass(type(f),type(iter([1,2])))) #判断是否是生成器那个类与迭代器那个类是否存在子父类关系 结果False

'''生成器函数最大的特点是可以接受外部传入的一个变量，并根据变量内容计算结果后返回。这是生成器函数最难理解的地方，
也是最重要的地方，协程的实现就全靠它了。'''
def cat():
    print('我是一只hello kitty')
    while True:
        food = yield msg
        if food == '鱼肉':
            msg = '好开心'
        else:
            msg = '不开心，人家要吃鱼肉啦'

'''函数变成generator的函数 就如同类的实例化一样，但此时生成器还未启动，
在一个生成器函数未启动之前，是不能传递数值进去。必须先传递一个None进去或者调用一次next(g)方法，才能进行传值操作。
send唤醒必须要传参，可以传None'''
miao = cat()
miao.__next__()
#通过send 也可以唤醒generator的函数继续执行，并传人一个值
#方法close 用于关闭生成器，当生成器被关闭时，在调用next 就会引发StopIteration异常也可能是其他异常。
print(miao.send('鱼肉'))
'''问题就来，既然在给yield传值过程中，会调用next()方法，那么是不是在调用一次函数的时候，
是不是每次都要给它传递一个空值？有没有什么简便方法来解决這个问题呢？答案，装饰器！！看下面代码:'''
def deco(func):  # 装饰器:用来开启协程
    def wrapper():
        res = func()
        next(res)
        return res # 返回一个已经执行了next()方法的函数对象
    return wrapper
@deco   #装饰器标识
def foo():
    food_list = []
    while True:
        food = yield food_list  #返回添加food的列表
        food_list.append(food)
        print("elements in foodlist are:",food)
g = foo()
print(g.send('苹果'))
print(g.send('香蕉'))
print(g.send('菠萝'))


#列表生成式对比简单生成器推导式
li = [(i+1) for i in range(10)]#结果，生成1个 1-10 的列表
#简单生成器推导式 ()里写代码的东东 没有关键字yeild 是一个简单生成器
g = ((i+1) for i in range(10))  #它不会立即产生值，通过next调用，一个个生成
g.__next__() #结果是1
#等同于g.__next__ 因为它就是调用g.__next__
next(g)   #结果是2

