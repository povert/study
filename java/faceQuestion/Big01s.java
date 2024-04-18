import java.util.Scanner;

/*
 * 输入一个01序列的串，求出最大01交错字串
 * 如果一个01串任意两个相邻位置的字符都是不一样的,我们就叫这个01串为交错01串。
 * 例如: "1","10101","0101010"都是交错01串。
 */
//字符串从头遍历到尾，如果不同，长度加一，相同，长度重置为1；
//定义一个数字，始终记录下当前最长的字串长度
public class Big01s {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char[] cs = s.toCharArray();
		int len = 1, maxlen = 1;
		for(int i = 1;i<cs.length;i++) {
			if(cs[i-1]!=cs[i]) {
				len++;
				if(maxlen<len)
					maxlen = len;
			}else
				len = 1;
		}
		System.out.println(maxlen);
	}
}
