package faceQuestion;
/*
 * �����������ǻ��ĵģ���12321
 * ���i*i�Ľ����һ����������ӡ����
 * ��ӡi��256��Χ�ڵĻ�����
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
