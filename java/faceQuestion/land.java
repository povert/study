package faceQuestion;
/*
 * �󺣵���
 * ����һ����ά���飬����0��ʾ����1��ʾ½��
 * ���ŵ�1��������������һ����1�������һ�����һ�����ķ�Χ����״����С
 * �磺
 *��0��1��0��
 *��1��0��1��
 *��0��1��1��
 *����3����
 */
//�Զ�ά������б��������Ϊ1���ݹ�����������Ҷ�Ϊ1�ı�Ϊ2
public class land {
	public static void main(String[] args) {
		int [][] a= {{0,1,0,1,0},{1,0,1,1,0},{0,1,1,0,1}};
		System.out.println(sum(a));
	}
	//��Ⱦ������Ŀ�Ľ�Ϊ1�������ݹ������������Ϊ1 �Ķ���Ϊ2��
	public static void infect(int[][]a,int x,int y) {
		a[x][y]=2;
		//��������������Ϊ1
		if(x>0&&a[x-1][y]==1)
			infect(a,x-1,y);
		//��������������Ϊ1
		if(x<a.length-1&&a[x+1][y]==1)
			infect(a,x+1,y);
		//��������������Ϊ1
		if(y>0&&a[x][y-1]==1)
			infect(a,x,y-1);
		//��������������Ϊ1
		if(y>a[x].length&&a[x][y+1]==1)
			infect(a,x,y+1);
	}
	public static int sum(int [][] a) {
		if(a.length<1)
			return 0;
		int sum=0;
		//���������Ϊ1�͸�Ⱦ
		for(int x=0;x<a.length;x++)
			for(int y=0;y<a[x].length;y++)
				if(a[x][y]==1) {
					infect(a,x,y);
					sum+=1;
				}
		return sum;
	}
}
