def SingleObject(cls):
    _instance = {}
    def _singleton(*args,**kwargs):
        # print(_instance)
        if cls not in _instance:
            _instance[cls] = cls(*args, **kwargs)
        return _instance[cls]
    return _singleton
@SingleObject
class A(object):
    def __init__(self,name):
        self.name = name
    pass
@SingleObject
class B(object):
    def __init__(self,name):
        self.name = name
    pass

if __name__=='__main__':
    a1 = A('a1');
    a2 = A('a2');
    b1 = B('b1');
    b2 = B('b2');
    print(a1.name)
    print(a2.name);
    print(b1.name);
    print(b2.name);