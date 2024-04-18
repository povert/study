
/*
 * 回文数数字是回文的，如12321
 * 如果i*i的结果是一个回文数打印出来
 * 打印i在256范围内的回文数
 */
public class huiwenNum {
	public static void main(String[] args) {
		int sum = 256;
		for(int i=4;i<sum;i++) {
			int s = i*i;
			int y = 0;
			while(s>0) {
				y=s%10+y*10;
				s=s/10;
			}
			if(y==i*i) {
				System.out.println(i);
			}
		}
	}

}
