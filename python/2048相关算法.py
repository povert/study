# 翻转，以三角形为对称轴，交换值
def Symmetrical_flip(li):
    for i in range(len(li)):
        for j in range(i+1):
            li[i][j], li[j][i] = li[j][i], li[i][j]
# 左右颠倒
def overturn(li):
    for i in range(len(li)):
        for j in range(len(li[i]) // 2):
            li[i][j], li[i][-j-1] = li[i][-j-1], li[i][j]

# 核心算法
def DemoTip(li):
    i, j = 0, 0
    while j < len(li):
        if li[j] == 0 or i == j:
            j += 1
        else:
            if li[i] == li[j]:
                li[i] *= 2
                li[j] = 0
                i += 1
                j += 1
            elif li[i] == 0:
                li[i], li[j] = li[j], li[i]
                j += 1
            else:
                i += 1

li = [0,2,0,2]
DemoTip(li)
print(li)



def printLi(li):
    for i in li:
        print(i)


