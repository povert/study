#菜单
def mean():
    print('{:-^20}'.format('通讯录'))
    print('》{:<10}'.format('1.增加联系人'))
    print('》{:<10}'.format('2.显示全部'))
    print('》{:<10}'.format('3.查找联系人'))
    print('》{:<10}'.format('4.删除联系人'))
    print('》{:<10}'.format('5.修改联系人'))
    print('》{:<10}'.format('0.退出通信系统'))
#增加内容
def add(name,age=None,phone=None,li=[]):
    if(len(name)==0):
        print('姓名为空，添加失败')
        return
    try:
        zi={'name':name,'age':age,'phone':phone}
        li.append(zi)
        print('添加成功')
    except:
        print('添加失败')
#查找
def seek(name,li=[]):
    for i in li:
        if name == i['name']:
            return i
    return None
#新输入函数
def new_input(data):
    ch = input(data+'修改为:(如果不修改直接回车)')
    if(len(ch)==0):
        return data
    return ch
#显示全部
def show(li=[]):
    for i in li:
        print('name:{:<10}age:{:<4}phone:{:<11}'.format(i['name'],i['age'],i['phone']))
#修改
def change(name,li=[]):
    dd = seek(name,li)
    if(dd!=None):
        dd['name']=new_input(dd['name'])
        dd['age']=new_input( dd['age'])
        dd['phone']=new_input( dd['phone'])
    else:
        print('没有该对象')
#主函数
txl=[]
mean()
while True:
    try:
        c=int(input("请输入你的选择："))
    except:
        print('输入不规范哦')
        mean()
    else:
            if c==1:
                name = input("请输入姓名：")
                age = input("请输入年龄：")
                phone= input("请输入电话号码：")
                mean()
                add(name,age,phone,txl)
            elif c==2:
                mean()
                show(txl)
            elif c==3:
                name = input("请输入姓名：")
                mean()
                i = seek(name,txl)
                if i!=None:
                    print('name:{:<10}age:{:<4}phone:{:<11}'.format(i['name'],i['age'],i['phone']))
                else:
                    print('没有该对象')
            elif c==4:
                name = input('输入姓名')
                mean()
                i = seek(name,txl)
                if(i!=None):
                    txl.append(i)
                    print('删除成功')
                else:
                    print('没有该对象')
            elif c==5:
                name = input("请输入姓名：")
                mean()
                change(name,txl)
            elif c==0:
                print('已退出系统')
                break
            else:
                print('没有该选项')