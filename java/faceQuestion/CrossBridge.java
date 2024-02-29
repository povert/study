package faceQuestion;

import java.util.Arrays;
import java.util.Scanner;

/*
 * N���˹�������
 * ����Ҫ����������
 * ���������
 * 1.��ʱ�����ٵ��˴��ֵ�Ͳ���ع��ţ�ֱ��ʣ��3���˻���2����
 * 2.��ʱ���ٵ�������һ�����ش������˹��ţ�ֱ��ʣ��3���˻���2����
 * ��ʱ����ֻ���ж��Ǹ���ʱ������
 * ��ʣ��3����ʱ��������ʱ��3����֮��
 * ��ʣ��2����ʱ��������ʱ��2��������ʱ�����Ǹ�
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
			//�����֤a[0]�����ֵ
			int k1=a[0]+a[1]+a[a.length-1]*2;
			int k2=a[0]+a[a.length-1]+a[a.length-2]*2;
			int k = k1<k2?k1:k2;
			int[] newData = Arrays.copyOfRange(a, 2, a.length);
			return k+jiegou(newData);
		}			
	}
	//���˻����������ͣ����඼�������������ͣ������������������ͣ����ݵĵ�ַ����Ӱ��ʵ��
	//�����������ͣ�int float Double String byte char
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
