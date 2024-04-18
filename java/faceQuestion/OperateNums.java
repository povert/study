
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
/*
  小易有一个长度为n的整数序列,a_1,...,a_n。然后考虑在一个空序列b上进行n次以下操作:
   1、将a_i放入b序列的末尾
   2、逆置b序列
   小易需要你计算输出操作n次之后的b序列。
 */
public class OperateNums{
	public static void main(String[] args) {
		my();
		guilv();
	
	}
	//我一开始的想法,模仿要求去做
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
    //其他方法，这就是给规律题
	public static void guilv() {
		Scanner in = new Scanner(System.in);
		//while (in.hasNextInt()) { //in.hasNextInt 判断输入是否为一个Int类型的数
			int n = in.nextInt();
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = in.nextInt();
				}
			for (int i = n - 1; i >= 0; i -= 2) {
				// 前一半从最后一个数开始以2为步长递减 
				System.out.print(nums[i] + " ");
				}
			for (int i = n % 2; i < n - 2; i += 2) {
				// 后一半根据整数个数的奇偶，分别从第二个或第一个数开始以2为步长递增
				System.out.print(nums[i] + " ");
				}
			System.out.print(nums[n - 2]);  // 最后一个数
	//}
	}
}