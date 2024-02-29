'''for 循环的工作原理'''
'''关于可迭代对象的定义是这样的：
        定义了可以返回一个迭代器的__iter__方法，
        或者定义了可以支持下标索引的__getitem__方法，
        那么它就是一个可迭代对象。'''

class Classmate(object):
    def __init__(self):
        self.name = list()
    def add(self,name):
        self.name.append(name)

#一个对象要想能够被迭代，他就需要具有迭代器，具备__iter__ 方法
    def __iter__(self):
        '''因为迭代器迭代一次以后就空了，
        那么如果list，dict也是一个迭代器，迭代一次就不能再继续被迭代了，这显然是反人类的；
        所以通过__iter__每次返回一个独立的迭代器，就可以保证不同的迭代过程不会互相影响。
        '''
        #如果想要一个对象变成可迭代对象需要__iter__方法 或 __getitem__
        #__iter__方法需要返回一个迭代器
        return ClassIterator(self.name)



#一个迭代器 必须具有 __next__方法 和 __iter__方法(可以没有)
class ClassIterator(object):
    def __init__(self,name):
        self.name = name
        self.index = -1

    def __iter__(self):
        pass

    def __next__(self):
        if self.index < len(self.name)-1:
            self.index+=1
            return self.name[self.index]
        else:
            raise StopIteration

#通过__getitem__来实现for循环
class NoIterable(object):
    def __init__(self, data):
        self.data = data
    def __getitem__(self, item):
        '''如果对象没有__iter__(优先)，但是实现了__getitem__，会改用下标迭代的方式。这是一种旧的迭代方法'''
        return self.data[item]

no_iter = NoIterable('abcde')
'''当for发现没有__iter__但是有__getitem__的时候，会从0开始依次读取相应的下标，直到发生IndexError为止'''
for item in no_iter:
    print(item)

classmethod = Classmate()
classmethod.add("大老王")
classmethod.add("二老王")
classmethod.add("三老王")
classmethod.add("四老王")
classmethod.add("五老王")
for i in range(2):
    for name in classmethod:
        print(name)

print('==============')