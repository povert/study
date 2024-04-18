#因为列表的pop方法默认弹出最后一个，append方法默认将一个附叫到列表末尾，所以可以将列表看作哟个栈。
li = list()
path = list()

#待优化
def migong(mg,p,e):  #mg 迷宫列表，p 起点坐标 e 终点坐标
    li.append(p)
    x_max = len(mg)
    y_max = len(mg[0])
    x_end,y_end = e;
    while(len(li)!=0):
        x,y=li.pop()
        mg[x][y]=2 #将弹出的点设为2 这样就不会在重复压入栈了
        path_pan(x,y)
        if(x==x_end and y==y_end):break
        if(x>0 and mg[x-1][y]==0):
            li.append((x-1,y))
        if(x<x_max-1 and  mg[x+1][y]==0):
            li.append((x+1,y))
        if(y>0 and mg[x][y-1]==0):
            li.append((x,y-1))
        if(y<y_max-1 and mg[x][y+1]==0):
            li.append((x,y+1))

def path_pan(x,y):
    while True:
        lenth = len(path)
        if(lenth==0):
            path.append((x,y))
            break
        else:
            i,j = path[lenth-1]
            if((x==i and (y==j+1 or y==j-1)) or (y==j and (x==i-1 or x==i+1))):
                path.append((x,y))
                break
            else:
                path.pop()
def main():
    mg = [
        [0,0,1,0,1,0,0],
        [1,0,1,0,0,0,1],
        [0,0,0,0,1,0,1],
        [0,1,0,0,0,0,0],
        [0,0,1,0,1,0,1],
    ]
    p = (0,0)
    e = (4,5)
    migong(mg,p,e)
    print(path)

if __name__ == '__main__':
    main()