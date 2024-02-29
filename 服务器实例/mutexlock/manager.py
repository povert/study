# -*- coding: utf-8 -*-
import  threading

class CMutexLockManager(object):
    def __init__(self):
        self.m_dLockItem = {}

    def Create(self, sName):
        if sName in self.m_dLockItem:
            return
        oLock = threading.Lock()
        self.m_dLockItem[sName] = oLock

    def Acquire(self, sName):
        if not sName in self.m_dLockItem:
            self.Create(sName)
        oLock = self.m_dLockItem[sName]
        oLock.acquire()

    def Release(self, sName):
        if not sName in self.m_dLockItem:
            return
        oLock = self.m_dLockItem[sName]
        oLock.release()


dic = {

}

dic[1001] = {
    'name': '李四',
    'sex': "男",
    'age': 23
}

dic[1002] = {
    'name': '张三',
    'sex': "男",
    'age': 23
}

dic[1003] = {
    'name': '王五',
    'sex': "男",
    'age': 23
}

def GetByName(sname):
    for _index, data in dic.items():
        if data['name'] == sname:
            return data
    return None

print(GetByName("张三"))
print(GetByName("李四"))
print(GetByName("王五"))



