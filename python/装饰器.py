# '''了解装饰器的基础知识点'''
# '''【总结】基本上@标识的都是装饰器'''
#
# '''函数里可以嵌套函数,函数是从外到里执行，外层返回值调用里层函数，
#     如果外层返回值不调用里层函数，则里层函数就显得没有意义但不会报错。
#     对于装饰器而已外层函数必须返回里层函数名.否则会出现抛出callable异常（返回值不是一个任务）
#     这是因为装饰器要求接受一个callable对象，并返回一个callable对象
#     里面那个函数作用域直在其外面那层函数有效，这叫闭包函数.
#     闭包函数的特性：1.函数内部定义的函数 2.包含对外部作用域而非全局作用域的引用
#     装饰器常工作原理也类是这样的，并且高级装饰器函数常常是多层嵌
#     所以装饰器特性：外部函数传入被装饰函数名，内部函数返回装饰函数名。
# 　　特点：1.不修改被装饰函数的调用方式 2.不修改被装饰函数的源代码'''
#
# ''' 装饰器本质上是一个Python函数，我们可以让一个类重载__call__()方法实现类装饰器
#     它可以让其他函数在不需要做任何代码变动的前提下增加额外功能，
#     装饰器的返回值也是一个函数对象。它经常用于有切面需求的场景，
#     比如：插入日志、性能测试、事务处理、缓存、权限校验等场景。
#     装饰器是解决这类问题的绝佳设计，
#     有了装饰器，我们就可以抽离出大量与函数功能本身无关的雷同代码并继续重用
# '''
#
#
# # 一个装饰器demno
# def debug(func):   #装饰器函数 参数func是被装饰函数形参
#     def wrapper(*args,**kwargs): #这里必须保证被装饰的函数参数与这里参数一样
#         '''*args和**kwargs 是接收不确个数的参数目的就是为了接收不确定个数的参数
#            *args是元祖 **kwargs是字典 args和kwargs其实可以换名称，重要是*和**'''
#         print ("[DEBUG]: enter {}(),接收到函数参数{}".format(func.__name__,args))
#         return func(*args,**kwargs)
#     return wrapper
# @debug   #标识，表示这个函数被debuc装饰器函数装饰
# def say_hello(a):
#     print (a)
#
# # 带参数的装饰器
# def daidebug(lev = "参数"):  #通过这个小demo 可以看出带参数的装饰器就是在做一层装饰
#     def debug(func):
#         print("接收的参数:{}".format(lev))
#         def wrapper(*args,**kwargs):
#             print ("[DEBUG]: enter {}(),接收到函数参数{}".format(func.__name__,args))
#             return func(*args,**kwargs)
#         return wrapper
#     return debug
#
#
# #类装饰器demo
# class logging(object):
#     #因为装饰器要求接受一个callable对象，并返回一个callable对象,因此初始化类必须接收一个callable对象
#     def __init__(self, func):
#         self.func = func
#     def __call__(self, *args, **kwargs):
#         print( "[DEBUG]: enter function {func}()".format(
#             func=self.func.__name__))
#         return self.func(*args, **kwargs)
# #带参数的类装饰器
# class logging_input(object):
#     '''如果需要通过类形式实现带参数的装饰器，
#     那么在构造函数里接受的就不是一个函数，
#     而是传入的参数。通过类把这些参数保存起来。
#     然后在重载__call__方法是就需要接受一个函数并返回一个函数。'''
#     def __init__(self, level='INFO'):
#         self.level = level
#
#     def __call__(self, func): # 接受函数
#         def wrapper(*args, **kwargs):
#             print( "[{level}]: enter function {func}()".format(
#                 level=self.level,
#                 func=func.__name__))
#             func(*args, **kwargs)
#         return wrapper  #返回函数
# @logging
# @logging_input(level='ww') # ww是类接收的参数
# def say(something):
#     print ("say {}!".format(something))
# #如果一个函数被多个装饰器装饰,那么执行顺序是从上到下。
# say('123')
#
# #内置的装饰器
# #内置的装饰器和普通的装饰器原理是一样的，只不过返回的不是函数，而是类对象，所以更难理解一些
# @property
# def x(self):
#     pass
# # 等同于
# def x(self):
#     pass
# x = property(x)
# ''' 与之类似还有@classmethod @staticmethod 这些在类中常用，标识类中方法
#     @classmethod装饰器返回的是staticmethod对象 @classmethod 返回classmethod对象
#     他们都是调用的是各自的__init__()构造函数'''
# #decorator.py 是一个非常简单的装饰器加强包 wrapt是一个功能非常完善的包，用于实现各种你想到或者你没想到的装饰器。
#
#
#
#             # 函数property
# '''特性（property)
# 特性是对类的一个特定属性进行拦截，在操作这个属性时，执行特定的函数，对属性的操作进行拦截。
# 特性使用property类来实现，也可以使用property装饰器实现，二者本质是一样的。
# 即@property 装饰器等同于 参数名 = property(fget=函数名)  @property修饰参数名就是函数名'''
# class r1(object):
#     def __init__(self):
#         self.width = 0
#         self.height = 0
#     def set_size(self,size):
#         self.width,self.height = size #这里size是个元祖，会做自动拆分
#     def get_size(self):
#         return self.width,self.height  #当return跟多个返回值时，会自动变成一个元祖作为返回值
#     size = property(get_size,set_size)  #使用property类,这里必须有获取方法，且获取方法必须在设置值方法前
# class r2(object):
#     def __init__(self):
#         self.width = 0
#         self.height = 0
#     @property
#     #@pproperty 修饰后，这个方法就变成了一个属性，当设置值时需要给参数复值， 即实例化对象.size = (10,15) 来赋值。而实例化对象.set_size(10,15)则不能赋值了
#     def set_size(self,size):
#         self.width,self.height = size #这里size是个元祖，会做自动拆分
#     @property
#     def get_size(self):
#         return self.width,self.height  #当return跟多个返回值时，会自动变成一个元祖作为返回值
# def demo():
#     r_1 = r1()
#     r_2 = r2()
#     print(r_1.size)#结果:(0,0)
#     r_1.size = 10,15 # 这里会自动作为一个元祖赋值
#     print(r_1.size)#结果:(10,15)
#     #为什么，需要了解property的工作原理
#     print(r_2.size)#报错，r_2没有size属性
#     print(r_2.get_size)#结果(0,0)
#     r_2.set_size = 10,15 #报错，不能给这个属性赋值
#     r_2.set_size((10,15))#报错,需要必要一个参数'size'
#     r_2.size=10,15 #结果：成功赋值
#
#

def outer(outer_args):
    def middle(func):
        def wrapper(*args,**kwargs):
            print("before func")
            print("use args: "+outer_args)
            # wrapper_result=func(*args,**kwargs)
            print("after func")
            return func(*args,**kwargs)
        return wrapper
    return middle
@outer('ssss')
def aa(ss):
    print('11'+ss)
aa('aaa')