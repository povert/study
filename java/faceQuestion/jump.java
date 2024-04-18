

import java.util.Scanner;

/*
 * 青蛙跳台阶，青蛙可以一次跳一阶，也可以一次跳两阶，
 * 问，青蛙跳到n阶台阶有多少种跳法。
 * 输入台阶数，返回结果
 */

public class jump {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		//我的解法
		tiao1(n);
		//斐波那切数列法
		fib_tiao(n);
	}
	//我的解法
	private static void tiao1(int n) {
		/*
		 * 跳到n阶台阶，青蛙可以跳0-n%2个2阶台阶，剩下跳1阶
		 * 然后是x个1和y个2排列组合有多少种情况
		 * 我们在考虑x个1和y个2排列组合情况
		 * 其实很简单，x个1 和y个2 排列组合形成长为x+y的数列
		 * 我们把x个1，放进长为x+y数列中，就有C[(x+y)~x]中情况，然后剩下座位就只能放二了。
		 * 简单理解就是m+n个座位，n个学生坐，有多少种做法
		 */
		if(n==0) {
			System.out.println(0);
			return;
		}
		if(n==1) {
			System.out.println(1);
			return;
		}
		int sum = 0;  //定义值作为结果
		//n/2+1,是因为i可以取到n/2
		for(int i=0;i<n/2+1;i++) {
			int y = n-i*2;  //y是1的个数，i是2的个数
			sum+=C(i,y);  //c就是排列算法
		}
		System.out.println(sum);
	}
	//排列算法
	//C[m~n]= A[m~n]/A[n~n]=(m)!/((m-n)!*n!)
	//A[m~n]=m!/(m-n)!
	private static int C(int x, int y) {
		//全为1或全为2时，只有一种排法
		if(x==0||y==0)
			return 1;
		//求x,y,x+y的阶乘
		int jx = 1;
		int jy = 1;
		int jx_y = 1;
		for(int i=1;i<x+y+1;i++) {
			if(i<x+1)
				jx*=i;
			if(i<y+1)
				jy*=i;
			jx_y*=i;
		}
		return jx_y/(jx*jy);
	}

	private static void fib_tiao(int n) {
/*
 如果n=1，只有一种跳法，那就是1    
 如果n=2，那么有两种跳法，2，[1,1]    
 如果n=3，那么有三种跳法，[1,1,1],,[1,2],[2,1]    
 如果n=4，那么有五种跳法，[1,1,1,1],[1,1,2],[1,2,1],[2,1,1],[2,2]    
 如果n=5，那么有八种跳法，[1,1,1,1,1],[1,1,1,2],[1,1,2,1],[1,2,1,1],[2,1,1,1],[2,2,1],[2,1,2],[1,2,2]
结果为1，2，3，5，8  这不特么是斐波那切数列嘛
所以，跳到第几阶就是数列的第几项，接下来详述：
如果台阶级数大于2，设为n的话，这时我们把n级台阶时的跳法看成n的函数，记为f(n)
第一次跳的时候有2种不同的选择：一是第一次跳一级，此时跳法的数目等于后面剩下的n-1级台阶的跳法数目，即为f(n-1)
二是第一次跳二级，此时跳法的数目等于后面剩下的n-2级台阶的跳法数目，即为f(n-2)
因此n级台阶的不同跳法的总数为f(n)=f(n-1)+f(n-2)，不难看出就是斐波那契数列
 */

		//为了降低运算不用递归
		if(n==0) {
			System.out.println(0);
			return;
		}
		if(n==1) {
			System.out.println(1);
			return;
		}
		if(n==2) {
			System.out.println(2);
			return;
		}
		int n1=1,n2=2;
		int i = 2;
		while(i++<n) {
			 int tmp=n1;
	            n1=n2;
	            n2=tmp+n2;
		}
		System.out.println(n2);
	}
}
/*理解为什么是fib序列，那么接着考虑
 * 知识扩展：
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *则有：   f(n)=f(n-1)+f(n-2)+f(n-3)+...+f(1)
 *		f(n-1)=f(n-2)+f(n-3)+...+f(1)
 *		f(n)=2*f(n-1)
 *		f(n-1)=2*f(n-2)
 *so:	f(n)=2^(n-1)
 *
 *一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个m级的台阶总共有多少种跳法。
 *当n>=m时，就等同于上面一样
 *则有：	f(m)=2^m-1
 *当n<m时，
 *则有：	f(m)=f(m-1)+f(m-1)+...+f(m-n)
 *		f(m-1)=f(m-2)+...+f(m-n-1)
 *		f(m)=2f(m-1)-f(n-m-1)
 *		f(m-1)=2f(m-2)-f(n-m-2)
 *因为n<m,且f里面不能为零或负，因此不能再化简，如同斐波那契数列只能列两个函数运算。即，如下：
 *	   {2^(m-1) n>=m;						     {1 n=0，1
 *f(n)={                      斐波那契数列 fib(n)= {
 *	   {2f(m-1)-f(n-m-1)						 {f(n-1)+f(n-2)
 *
 *
 */
