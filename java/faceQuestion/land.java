package faceQuestion;
/*
 * 求海岛数
 * 给你一个二维数组，其中0表示海，1表示陆地
 * 连着的1（即上下左右有一个是1）组合在一起就是一个岛的范围，形状，大小
 * 如：
 *【0，1，0】
 *【1，0，1】
 *【0，1，1】
 *就有3个岛
 */
//对二维数组进行遍历，如果为1，递归把它上下左右都为1的变为2
public class land {
	public static void main(String[] args) {
		int [][] a= {{0,1,0,1,0},{1,0,1,1,0},{0,1,1,0,1}};
		System.out.println(sum(a));
	}
	//感染函数，目的将为1的数，递归把他上下左右为1 的都变为2，
	public static void infect(int[][]a,int x,int y) {
		a[x][y]=2;
		//如果它下面存在且为1
		if(x>0&&a[x-1][y]==1)
			infect(a,x-1,y);
		//如果它上面存在且为1
		if(x<a.length-1&&a[x+1][y]==1)
			infect(a,x+1,y);
		//如果它左面存在且为1
		if(y>0&&a[x][y-1]==1)
			infect(a,x,y-1);
		//如果它右面存在且为1
		if(y>a[x].length&&a[x][y+1]==1)
			infect(a,x,y+1);
	}
	public static int sum(int [][] a) {
		if(a.length<1)
			return 0;
		int sum=0;
		//遍历，如果为1就感染
		for(int x=0;x<a.length;x++)
			for(int y=0;y<a[x].length;y++)
				if(a[x][y]==1) {
					infect(a,x,y);
					sum+=1;
				}
		return sum;
	}
}
