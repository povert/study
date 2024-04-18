

import java.util.Arrays;
import java.util.Scanner;

/*
 * N个人过桥问题
 * 首先要对数组排序
 * 有两种情况
 * 1.用时间最少的人带手电筒来回过桥，直到剩下3个人或者2个人
 * 2.用时最少的两个人一个来回带两个人过桥，直到剩下3个人或者2个人
 * 此时我们只需判断那个用时间最少
 * 当剩下3个人时，最少用时是3个人之和
 * 当剩下2个人时，最少用时是2个人中用时最大的那个
 */
public class CrossBridge {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for(int i=0;i<n;i++)
			a[i]=sc.nextInt();
		sort(a);
		System.out.println(jiegou(a));
	}
	private static int jiegou(int[] a) {
		if(a.length<1)
			return -1;
		else if(a.length==1) 
			return a[0];
		else if(a.length==2)
			return a[0]>a[1]?a[0]:a[1];
		else if(a.length==3)
			return a[0]+a[1]+a[2];
		else {
			//排序后保证a[0]是最大值
			int k1=a[0]+a[1]+a[a.length-1]*2;
			int k2=a[0]+a[a.length-1]+a[a.length-2]*2;
			int k = k1<k2?k1:k2;
			int[] newData = Arrays.copyOfRange(a, 2, a.length);
			return k+jiegou(newData);
		}			
	}
	//除了基本数据类型，其余都是引用数据类型，传参是引用数据类型，传递的地址，会影响实参
	//基本数据类型：int float Double String byte char
	private static void sort(int [] a) {
		for(int i=0;i<a.length;i++)
			for(int j=i+1;j<a.length;j++) {
				if(a[j]>a[i]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}			
			}
	}
}
