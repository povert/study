package faceQuestion;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
/*
  С����һ������Ϊn����������,a_1,...,a_n��Ȼ������һ��������b�Ͻ���n�����²���:
   1����a_i����b���е�ĩβ
   2������b����
   С����Ҫ������������n��֮���b���С�
 */
public class OperateNums{
	public static void main(String[] args) {
		my();
		guilv();
	
	}
	//��һ��ʼ���뷨,ģ��Ҫ��ȥ��
    public static void my(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> li = new ArrayList<Integer>();
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0;i<n;i++)
            a[i] = sc.nextInt();
        for(int i = 0;i<n;i++){
            li.add(a[i]);
            Collections.reverse(li);
        }
        int k =0;
        for(Integer i:li){
            if(k==0) 
                System.out.print(i);
            else
                System.out.print(" "+i);
            k=1;
        }
    }
    //��������������Ǹ�������
    	public static void guilv() {
    		Scanner in = new Scanner(System.in);
    		//while (in.hasNextInt()) { //in.hasNextInt �ж������Ƿ�Ϊһ��Int���͵���
    			int n = in.nextInt();
    			int[] nums = new int[n];
    			for (int i = 0; i < n; i++) {
    				nums[i] = in.nextInt();
    				}
    			for (int i = n - 1; i >= 0; i -= 2) {
    				// ǰһ������һ������ʼ��2Ϊ�����ݼ� 
    				System.out.print(nums[i] + " ");
    				}
    			for (int i = n % 2; i < n - 2; i += 2) {
    				// ��һ�����������������ż���ֱ�ӵڶ������һ������ʼ��2Ϊ��������
    				System.out.print(nums[i] + " ");
    				}
    			System.out.print(nums[n - 2]);  // ���һ����
    	//}
    }
}