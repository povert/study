def sort(li=[]):
    for i in range(len(li)):
        for j in range(i+1,len(li)):
            if li[i]<li[j]:
                temp = li[i]
                li[i] = li[j]
                li[j] = temp
    return li
def summ(li=[]):
    if len(li)==0:
        return 0
    elif len(li)==1:
        return li[0]
    elif len(li)==2:
        if li[0]>li[1]:
            return li[0]
        else:
            return li[1]
    elif len(li)==3:
        return li[0]+li[1]+li[2]
    else:
        k = li[0]+li[-1]+li[-2]*2
        return k+summ(li[2:])
l=[]
n = int(input('请输入人数：'))
for i in range(n):
    l.append(int(input('第'+str(i+1)+'个人所需时间：')))
sort(l)
sum1 = l[-1]*(n-2)
for i in range(0,n-1):
    sum1=sum1 +l[i]
sum2 = summ(l)
print(min(sum1,sum2))


