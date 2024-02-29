package faceQuestion;

import java.util.Scanner;

/*
 * 给你一组无序数组，有正有负，也有零，求数组中三个数的最大乘积
 * 最大乘积有两种情况：
 * 		三个最大数的乘积
 * 		两个最小数与一个最大的数的乘积
 * 因此找到最大的三个数和最小的两个数
 * 然后比较它们的乘积
 */

public class ThreeChen_BigNum {
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int x = sc.nextInt();
//		int[] a = new int[x];
//		for(int i=0;i<x;i++) {
//			a[i]=sc.nextInt();
//		}
		int[] a = {-1,-4,-3,-5,-2,-10,-9};
		System.out.println(my(a));
		System.out.println(getLargestMul(a));
	}
	//我第一次认为的方法
	public static int my(int[] a) {
		int max1,max2,max3,min1,min2;
		if(a[0]>a[1]) {
			max1=a[0];
			max2=a[1];
			min1=a[1];
			min2=a[0];
		}else {
			max1=a[1];
			max2=a[0];
			min1=a[0];
			min2=a[1];
		}
		if(a[2]>max1) {
			max3=max2;
			max2=max1;
			max1=a[2];
		}else if(a[2]>max2) {
			max3=max2;
			max2=a[2];
			min2=a[2];
		}else {
			max3=a[2];
			min2=min1;
			min1=a[2];
		}
		for(int i=3;i<a.length;i++) {
			if(a[i]>max1) {
				max3=max2;
				max2=max1;
				max1=a[i];
			}else if(a[i]>max2) {
				max3=max2;
				max2=a[i];
			}else if(a[i]>max3) {
				max3=a[i];
			}
			if(a[i]<min1) {
				min2=min1;
				min1=a[i];
			}else if(a[i]<min2) {
				min2=a[i];
			}
		}
		return max1*max2*max3>min1*min2*max1?max1*max2*max3:min1*min2*max1;
	}
	//第二个方法，有些优化的方法
	//思考为什么他没有单独列车前三个数(特殊情况，全为负数为错)
	static int getLargestMul(int[] num){
        long max1=0,max2=0,max3=0, min1=0,min2=0;
        for (int i = 0; i <num.length; i++) {
            if(num[i]!=0){
                if(num[i]>max1){
                    max3 = max2;
                    max2 = max1;
                    max1 = num[i];
                }else if(num[i]>max2){
                    max3 = max2;
                    max2 = num[i];
                }else if(num[i]>max3){
                    max3 = num[i];
                }else if(num[i]<min1 ){
                    min2 = min1;
                    min1 = num[i];
                }else if(num[i]>min1 && num[i]<min2){
                    min2 = num[i];
                }
            }else continue;

        }
        int max = (int) Math.max(max1*max2*max3,max1*min1*min2);
        return max;
    }
}
