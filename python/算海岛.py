'''
给你一个二维数组，由1和0构成，1代表陆地，0代表海
算出哪些是岛屿，（相连的上下左右相连的1算是一个岛屿)
'''

def infect(li,x,y): ##递归感染
      li[x][y]=2
      if x>0:
            if li[x-1][y] == 1:
                  infect(li,x-1,y)
      if x<len(li)-1:
            if li[x+1][y]==1:
                  infect(li,x+1,y)
      if y>0:
            if li[x][y-1] == 1:
                  infect(li,x,y-1)
      if y<len(li[x])-1:
            if li[x][y-1] == 1:
                  infect(li,x,y+1)
      
def sun(li):
      if len(li)==0:
            return 0
      elif len(li)==1:
            return li[0]
      sum=0
      for x in range(len(li)):
            for y in range(len(li[x])):
                  if li[x][y]==1:
                        infect(li,x,y)
                        sum+=1
      return sum
      
                           
            
      
