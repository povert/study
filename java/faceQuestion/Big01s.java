package faceQuestion;

import java.util.Scanner;

/*
 * ����һ��01���еĴ���������01�����ִ�
 * ���һ��01��������������λ�õ��ַ����ǲ�һ����,���Ǿͽ����01��Ϊ����01����
 * ����: "1","10101","0101010"���ǽ���01����
 */
//�ַ�����ͷ������β�������ͬ�����ȼ�һ����ͬ����������Ϊ1��
//����һ�����֣�ʼ�ռ�¼�µ�ǰ����ִ�����
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
