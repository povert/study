import math
''' 如果一个数是质数，并且每一位都是质数，那么我们称这个数是完美质数'''
#判断质数
def iszhi(n):
    if n == 1 or n == 0:
        return False
    for i in range(2,int(math.sqrt(n))+1):
        if n%i == 0:
            return False
    return True
#方法1求两位数的完美质数
def fangfa1():
    for i in range(10,99):
        if iszhi(i):
            if(iszhi(i%10)):
                if(iszhi(i//10%10)):
                        print(i,end=',')
#方法2求两位数的完美质数
def fangfa2():
    arr = (3,7,2,5) #因为每一位必须是这四个质数才能是完美质数，所以每一位可能性就这四种
    brr = (7,3) #因为最后一位为2，5 必定不是质数，所以它只有3，7两种可能 所以arr 排列将3，7 放前面，2，5放后面做特殊处理
    for i in range(4):
        for j in range(2):  #因为和本身组合能被11整除
            a = arr[i]*10+brr[j]
            if(iszhi(a)):
                print(a,end=',')

fangfa1()
print()
fangfa2()
