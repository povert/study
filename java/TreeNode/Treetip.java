package TreeNode;

import java.util.Scanner;

public class Treetip {
	/*
	 * �������Ļ���֪ʶ
	 * 	�������ķ��ࣺ
	 * 	�򵥵ģ�������������ȫ������ һ��������������ȫ��������������洢������Ͳ���ʵ�幹��
	 * 	ƽ����������������������������������ĸ߶Ȳ�ľ���ֵ������1������������������Ҳ����ƽ������
	 * 	�������������������߶����������нڵ���������ӽڵ�󣬱��������ӽڵ�С��
	 *      �������һ�ּ���ƽ��������������ж��������������ʵ�����������Ϊ�ص�ѧϰ��
	 *      �ο�ѧϰ:https://www.cnblogs.com/yinbiao/p/10732600.html
	 */
	public static void main(String[] args) {
		RBTree root = new RBTree(20, true);
//		root.Left = new RBTree(2, false);
//		root.Right = new RBTree(14, true);
//		root.Right.Right = new RBTree(15, false);
//		root.Left.Left = new RBTree(1, true);
//		root.Left.Right = new RBTree(7, true);
//		root.Left.Right.Left = new RBTree(5, false);
//		root.Left.Right.Right = new RBTree(8, false);
		Scanner sc = new Scanner(System.in);
		int data = 1;
		int i = 0;
		int[] a = {10,40,5,30,50,0};
		while(a[i] != 0) {
//			System.out.println("��������ֵ");
//			data = sc.nextInt();
			data = a[i++];
			root = root.RB_Insert(root,new RBTree(data, false));
		}
		H_PrintTree(root);
		while(data!=0) {
			System.out.println("����ɾ����ֵ");
			data = sc.nextInt();
			RBTree z = root.find(root, data);
			if(z!=null) {
				root = root.RB_Delete(root, z);
				H_PrintTree(root);
			}
		}
	}
	//�������������ӡ�������Ա������ò鿴���
	public static void H_PrintTree(RBTree root)
	{
		if(root == null) return;
		H_PrintTree(root, 1);
	}
	private static void H_PrintTree(RBTree root, int nLayer) 
	{
			if(root == null) return;
			H_PrintTree(root.Right, nLayer+1);
			for(int i = 0; i < nLayer; i++)
				System.out.print("              ");
			System.out.println(root.data+""+ root.color);
			H_PrintTree(root.Left, nLayer+1);
	}
}
/*
 * �����������:
(1) ÿ���ڵ�����Ǻ�ɫ�������Ǻ�ɫ��
(2) ���ڵ��Ǻ�ɫ��
(3) ÿ��Ҷ�ӽڵ��Ǻ�ɫ�� [ע�⣺����Ҷ�ӽڵ㣬��ָΪ�յ�Ҷ�ӽڵ㣡��Ϊ�յ�Ҷ�ӽڵ�]
(4) ���һ���ڵ��Ǻ�ɫ�ģ��������ӽڵ�����Ǻ�ɫ�ġ�
(5) ��һ���ڵ㵽�ýڵ������ڵ������·���ϰ�����ͬ��Ŀ�ĺڽڵ㡣
 */
class RBTree{
	boolean color; //�涨��ɫΪfalse����ɫΪtrue
	int data;
	RBTree parent = null; //���һ�����ڵ㣬���������Ժ�ά���ҵ����ڵ�
	RBTree Left = null;
	RBTree Right = null;
	public RBTree(int data,boolean color) {
		this.data= data;
		this.color = color;
	}
	/* ���������ת
	 * �����ҽڵ���
	 * �Ժ�����Ľڵ�(x)��������ת
	 * ����ʾ��ͼ(�Խڵ�x��������)��
	 *      px                              px
	 *     /                               /
	 *    x                               y                
	 *   /  \      --(����)-.           / \                #
	 *  lx   y                          x  ry     
	 *     /   \                       /  \
	 *    ly   ry                     lx  ly  
	 *
	 * �Ժ�����Ľڵ�(y)��������ת
	 * ����ʾ��ͼ(�Խڵ�y��������)��
	 *            py                               py
	 *           /                                /
	 *          y                                x                  
	 *         /  \      --(����)-.            /  \                     #
	 *        x   ry                           lx   y  
	 *       / \                                   / \                   #
	 *      lx  rx                                rx  ry
	 *
	 *
	 *ͨ��ͼ���Է��֣��������ǽ��ڵ����֧�����������ӽڵ��ɸ��ڵ㣬���ѽ���֮���������ӽڵ���ø������ڵ�����ӽڵ㣻
�������������Ƿ����������ڵ����֧�����������ӽڵ����˸��ڵ㣬���ѽ���֮���������ӽڵ���ø������ڵ�����ӽڵ㡣
������������������任�������������ұ任������������������������ת��Ŀ�Ķ��ǽ��ڵ���һ֧���ýڵ����һ���ڵ��ٵ�һ֧��
	 */
	//���������ת������������������ʵ����
	public RBTree leftRotate(RBTree root,RBTree x) {
		//rootΪ������ĸ��ڵ�
		RBTree y = x.Right; //����x���ҽڵ�Ϊy
		  // �� ��y�����ӡ� ��Ϊ ��x���Һ��ӡ���
	    // ���y�����ӷǿգ��� ��x�� ��Ϊ ��y�����ӵĸ��ס�
		x.Right = y.Left;
		if(y.Left!=null)
			y.Left.parent = x;
		 // �� ��x�ĸ��ס� ��Ϊ ��y�ĸ��ס�
		y.parent = x.parent;
		if(x.parent==null)
			root = y; // ��� ��x�ĸ��ס� �ǿսڵ㣬��y��Ϊ���ڵ�
		else if(x.parent.Left == x)
			x.parent.Left = y;  // ��� x�������ڵ�����ӣ���y��Ϊ��x�ĸ��ڵ�����ӡ�
		else x.parent.Right = y; // ��� x�������ڵ���Һ��ӣ���y��Ϊ��x�ĸ��ڵ���Һ��ӡ�
		y.Left = x; // �� ��x�� ��Ϊ ��y�����ӡ�
		x.parent = y;  // �� ��x�ĸ��ڵ㡱 ��Ϊ ��y��
		return root;
	}
	//�����������
	public RBTree rightRotate(RBTree root,RBTree y) {
		RBTree x = y.Left  ; //����x
		y.Left = x.Right;
		if(x.Right!=null)
			x.Right.parent = y;
		x.parent = y.parent;
		if(y.parent==null)
			root = x;
		else if(y.parent.Left == y)
			y.parent.Left = x;
		else y.parent.Right = x;
		y.parent = x;
		x.Right = y;
		return root;
	}
	//������Ĳ���  
	/*
	 * ��������������������ķ����������λ��һ�����Ǹ��ڵ�
	 * ����һ���ڵ��ǣ�����Ĭ�Ͻڵ�Ϊ��ɫ��
	 * 	��Υ�������������5��������һ������������Ҷ�ӽ�㣬�����ĺ�ɫ��������ͬ��
	 *      ����˵�����ܹ�Υ���������������
	 * ������ܻ��ƻ��������ƽ�⣬�ͺ������һЩ���ʣ�������Ҫά����
	 * Insert_Fixup��������ά����������ƽ�����ɫ�����彲���ٷ�����
	 */
	public RBTree RB_Insert(RBTree root,RBTree z) {
		if(root == null)
			{z.color = true; root = z;return root;}
		//�ҵ�Ҫ����Ľڵ� �Ӹ��ڵ㿪ʼ������
		RBTree x = root;
		RBTree y = null;
		while(x!=null) {
			y = x;
			if(x.data > z.data) x = x.Left; //С�ڸ��ڵ㣬���뵽��ߵ�
			else x = x.Right;  //���������ڵ��ұ�
		}
		//�ҵ�����λ�ã��Ϳ�ʼ����
		z.parent = y;
		if(y.data > z.data) y.Left = z;
		else y.Right = z;
		root = Insert_Fixup(root,z); //��ʼά��
		return root;
	}
	/*������������������
	1.��Ϊ�գ�����Ľ��Ϊ�����
		ֱ�ӽ�����Ľ���ɺ�ɫ���������ڲ�������˴������Բ���Ҫ����
	2.���׽��Ϊ��ɫ���
		����Ҫ�κβ���
	3.���׽��Ϊ��ɫ��������£�
    3.1 ������ҲΪ��ɫ���
    	������͸��׽���Ϊ��ɫ��үү����Ϊ��ɫ��δ�꣬Ȼ���ֽ�үү��㵱�������㿴����һֱ������
    	��Ĳ�����ֱ����ǰ���Ϊ����㣬Ȼ�󽫸�����ɺ�ɫ
    3.2 ������Ϊ��ɫ��������£�
        3.2.1 �����׽��Ϊ���ӣ�������ҲΪ���ӣ�||�����׽��Ϊ�Һ��ӣ�������ҲΪ�Һ��ӣ�
        	�����׽���үү������ɫ������Ȼ�����үү������һ���� ����
        3.2.2 �����׽��Ϊ���ӣ�������Ϊ�Һ��ӣ�||�����׽��Ϊ�Һ��ӣ�������Ϊ���ӣ�
        	��Ը��������� ����(�ɲ���ڵ��λ��ȷ��ȡ�෴)�������Ϊ���ڵ�
        	��ʱ�����������ض���3.2.1�������Ȼ����3.2.1���������
	 ����˵����
	 	������У����ڵ�Ϊ��ɫ��������Ҷ������Ҷһ���Ǻ�ɫ���������Ҷ�����ǿսڵ�)
	 */
	private RBTree Insert_Fixup(RBTree root, RBTree z) {
		//��������ִ�п��ǲ���Ľڵ�ĸ��ڵ��Ǻ�ɫ���Ǻ�ɫ������Ǻ�ɫ�Ͳ���Ҫ������
		while(z.parent!=null&&!z.parent.color) //��ѭ������Ϊ���涼�ǵݹ�һ���������ж�
		{ 
			//���һ���ڵ㸸�ڵ�Ϊ��ɫ����ô��үү�ڵ�һ��������Ϊ��ɫ���������ﲻ���׳���ָ���쳣
			if(z.parent.parent.Left == z.parent) //������ڵ�����ڵ�
			{
				RBTree y = z.parent.parent.Right; //����ڵ�Ϊ�ҽڵ�
				if(y!=null&&!y.color) //�������ڵ㲻Ϊ��,�սڵ�ض��Ǻ�ɫ�ģ�������ڵ�Ϊ����
				{
					//������͸��׽���Ϊ��ɫ��үү����Ϊ��ɫ
					y.color = false;
					z.parent.color = false;
					z.parent.parent.color = true;
					//Ȼ���ֽ�үү��㵱�������㿴��
					z = z.parent.parent;
				}
				else {
					if(z.parent.Right == z) //�������ڵ����ҽڵ㣬���3.1.2 ��������3.1.1
					{
					z = z.parent;
					root = leftRotate(root, z); //����
					}
				//�����׽���үү������ɫ���� ��Ϊ�Ѿ�ȷ���˸��ڵ�Ϊ��ɫ��үү�ڵ�Ϊ��ɫ
				z.parent.color = true;
				z.parent.parent.color = false;
				root = rightRotate(root, z.parent.parent); //����
				}
			}
			else //������ڵ�Ϊ�ҽڵ㣬���������ڸ��ڵ�Ϊ��ڵ㣬���ҵ���
			{
				RBTree y = z.parent.parent.Left; //����ڵ�Ϊ��ڵ�
				if(y!=null&&!y.color) //�������ڵ㲻Ϊ��,�սڵ�ض��Ǻ�ɫ�ģ�������ڵ��
				{
					//������͸��׽���Ϊ��ɫ true��үү����Ϊ��ɫ false
					y.color = true;
					z.parent.color = true;
					z.parent.parent.color = false;
					//Ȼ���ֽ�үү��㵱�������㿴��
					z = z.parent.parent;
				}
				else { 
					if(z.parent.Left == z) //�������ڵ�����ڵ㣬���3.1.2 ��������3.1.1
				{
					z = z.parent;
					root = rightRotate(root, z); //����
				}
				//�����׽���үү������ɫ���� ��Ϊ�Ѿ�ȷ���˸��ڵ�Ϊ��ɫ��үү�ڵ�Ϊ��ɫ
				z.parent.color = true;z.parent.parent.color = false;
				root = leftRotate(root, z.parent.parent); //����
				}
			}
		}
		/* ���ø��ڵ�Ϊ��ɫ
		 * Ԥ�������������
		 * 		  1��
		 *	  2��            3��
		 *����4���������
		 *		 1��
		 *   2��           3��
		 *                4��
		 */
		root.color = true;
		return root;
	}
	//�������ɾ��  �Ͷ�����һ��������һ����ֲ����������ɾ������ڵ�
	private RBTree RBTransplant(RBTree root, RBTree u, RBTree v) {
		//u Ҫɾ���Ľڵ㣬v�������ɾ���Ľڵ�
		if(u.parent == null) //ɾ�����Ǹ��ڵ�
			root = v;    
		else if(u == u.parent.Left)  //ɾ��������ڵ�
			u.parent.Left = v;
		else u.parent.Right = v;
		if(v!=null) //�������ĵ㲻�ǿսڵ�
			v.parent = u.parent;
		return root;
	}
	/* �������ɾ�����ص���� �������ڲο�https://www.cnblogs.com/qingergege/p/7351659.html
	 * ɾ����Ϊ��������� ���������������ת��Ϊ�ڶ������
	 * 		1.ɾ����û���ӽڵ�
	 * 			1��ɾ�����ɫ��ֱ��ɾ
	 * 			2����ɫɾ���� �������ά����������ά����
	 * 		2.ɾ������һ���ӽڵ�
	 * 			ֻ��һ�������ɾ����Ϊ��ɫ�����ӽڵ�Ϊ��ɫ����ʱ���ӽڵ�Ϳ�ڷŵ����ڵ�λ�ü���		
	 * 		3.ɾ�����������ֽڵ㣬������ɾ���ĵ�������ӽڵ�������˼���Сֵ�����������ͱ�������1��2 
	 */
	public RBTree RB_Delete(RBTree root, RBTree z) {
		/* ����y ���ڵ�ɾ���ڵ�Ϊ����ʱ��y��¼x�ĺ�̽ڵ㣬����yʼ�����ڱ�ɾ����λ��
		 * 	����color��¼��ɾ���ڵ��ʼ����ɫ
		 *    ����x x���������ɾ���ڵ��λ�ã���Ϊx����Ϊ�գ�ά��ʱ��Ҫx�ĸ��ڵ������ϱ�����
		 *    	���ԣ�����child �� parent �����x
		 *   ÿһ�εĽ�������Υ�������������ʣ�����ά���Ǵӽ�������λ�ÿ�ʼά����x
		 */
		RBTree y = z;
		boolean color = z.color;
		RBTree child,parent;
		//�������ǽ�û�нڵ�Ĵ������ά������
		if(z.Left == null) {
			child = z.Right;
			parent = z.parent;
			root = RBTransplant(root, z, z.Right);
		}
		else if(z.Right == null) {
			child = z.Left;
			parent = z.parent;
			root = RBTransplant(root, z, z.Left);
		}else {  
			/* ��������ڵ㶼����
			 *     �ҵ�ɾ���ڵ���ҽڵ�����ˣ�����ֵȻ��ɾ��
			 *       ��ʱ���ֻ�����֣�1.��һ���ڵ㣬����ڵ�һ�����ҽڵ㣬2��û�нڵ� 
			 */
			y = Tree_min(z.Right);//�ҵ���̽ڵ�
			color = y.color;
			child = y.Right;  //x�����������¼ɾ���ڵ��λ�ã����Աض���y
			parent = y.parent;
			//����ֵ ���ڵ�ֵ�����ǲ�Ӱ�������ĺ�����ʣ�ֻ��Ӱ��������������ʣ�����������ֵɾ��
			//���ԣ�ά�����ǲ�Ӱ����������
			int temp = y.data; y.data = z.data; z.data = temp;
			//��y��Ϊɾ���ڵ�ɾ�� ��ʱ����Ľڵ�ض���y.right
			root =RBTransplant(root, y, y.Right);
		}
		if(color) Delete_fixup(root,child,parent);
		return root;
	}
	/* ɾ���ڵ�ά�� ����ɾ�������Ҫ����ά���������������
	 * ������ɾ���������ǿ���֪���������child�ڵ������������1Ϊ�գ�2��Ϊ����Ϊ��ɫ��
	 * ���ڵڶ������������ֻ�轫����ɫ��Ϊ�ھ�����
	 * ���ڵ�һ�������Ҫ��Ϊ�������(�����Ϊ4��) ��child����Ϊx(�ض�Ϊ��) ���ֵܽڵ���Ϊw�ұض�����,Ĭ�ϴ��ڽڵ�Ϊ�յ��ӽڵ�
	 *   1.wΪ�죬�ض����������ӽڵ�  ��Ϊ��ɾ���Ľڵ��Ǻ�ɫ�����ֵ�Ϊ��ʱ�ڵ�������������ӽڵ������������5
		     ������1����ת��Ϊ���2��3��4
		     ���ֵܽ��͸��׽�����ɫ����
			��ɾ����Ԫ��Ϊ���������Ը��׽������
			��ɾ����Ԫ��Ϊ���������Ը��׽������  
		     Ȼ��ͱ�����2��3��4��
	 *   2.wΪ�ڣ��Ҵ���������ɫ�ӽڵ���������ӽڵ�
	 *     ��������ȽϺ���⣬����һ��ʼ��x�ǿսڵ㣬wΪ�ڵĻ���Υ���˶���������5�����Խ���Ϳ�ɺ�ɫ����
	 *   3.wΪ�ڣ�����һ���ڵ㲢�Ҹýڵ��w��ͬһ�ߣ�ͬΪ����������ͬΪ����������
	 *    ���Ȳ���������������������Ƚ����ֵܽ��͸��׽�����ɫ�����ҰѸ��׽����ֵܽ����ӽ��Ϳ�ɺ�ɫ
		 ����ֵܽ����ֵܽ��Ķ��Ӷ����������Ļ����Ը��׽���������
		 ����ֵܽ����ֵܽ��Ķ��Ӷ����������Ļ����Ը��׽���������
	 *   4.wΪ�ڣ�����һ�����Ӳ��գ����Ҹú��Ӻ�w����ͬһ�ߣ�����������ҵ��������
	 *   ���ȣ��Ƚ��ֵܽ����ֵܽ��Ķ��ӽ����ɫ����
	 ����ֵܽ�������������ֵܽ��Ķ��ӽ���������������ֵܽ���������
	 ����ֵܽ�������������ֵܽ��Ķ��ӽ���������������ֵܽ���������
	 �����ͽ����4��������3
	     5.wΪ�ڣ����������ӽڵ㶼������Ϊ�죬��ʱ�����3ͬ�ദ��
	 */
	private void Delete_fixup(RBTree root, RBTree child, RBTree parent) {
		//�ܽ���˵��������仰��ɾ�����ֵܣ��ֵ�Ϊ���ɺڣ�Ȼ�����ڣ��ֵܽڵ�Ϳ�죬һ���ͬ�಻ͬ�⣬����Ĭ��ͬ��(ͬ�ఴ����λ����ת)
		while((child==null)||(child!=root&&child.color)) //x�ڵ㿪ʼΪ����ɫΪ�ڣ����߷Ǹ�
		{
			if(child == parent.Left) //���x�ڵ�����ڵ�ʱ
			{
				RBTree w = parent.Right;//�ֵܽڵ�wΪ�ҽڵ�
				//���1 wΪ�� �ض��������ڽڵ�
				if(!w.color) {
					//���ֵܽ��͸��׽�����ɫ���� 
					w.color = true; w.parent.color = false; //��Ϊ�ֵ�Ϊ�죬���ױض�Ϊ��
					//��ɾ����Ԫ��Ϊ������,�Ը��׽������������
					root = leftRotate(root, parent);
					w = parent.Right; //����������
				}
				//���2 wΪ�ڣ��Ҵ���������ɫ�ӽڵ���������ӽڵ�
				if((w.Left==null&&w.Right==null)||(w.Left.color&&w.Right.color)){
					//ΪʲôҪ����ѭ���أ�����Ϊ�ٸ�����һ��֧�ϼ���һ��ڣ��븸�׶��ڵ�һ֧�ͱȸ�����һ֧��һ���
					w.color = false;child = parent; parent = parent.parent;
				}
				else {
					//���4 wΪ��,��һ���ڵ㣬��ڵ���� �����3
					if(w.Right == null||w.Right.color) {
					//�Ƚ��ֵܽ����ֵܽ��Ķ��ӽ����ɫ����
					w.Left.color = true; //�ֵܽڵ�Ϊ��ɫ���ֵܶ��ӽڵ�һ��Ϊ��ɫ
					w.color = false;
					//����ֵܽ�������������ֵܽ��Ķ��ӽ���������������ֵܽ���������
					root = rightRotate(root, w);
					w = parent.Right;
					}
					//���3 wΪ�ڣ�����һ���ڵ㲢�Ҹýڵ��w��ͬһ��
					//�Ƚ����ֵܽ��͸��׽�����ɫ�����ҰѸ��׽����ֵܽ����ӽ��Ϳ�ɺ�ɫ
					w.color = parent.color; //��ʱ���׽ڵ���ɫ��ȷ��
					parent.color = true; //�ֵܽڵ�Ϊ��
					w.Right.color = true;
					//��λ���෴������ת
					root = leftRotate(root, parent);
					child = root; //��ִ�е����4��ʱ��ѭ����Ӧ����ֹ�ˡ�
					}
			}
			else { //���ӽڵ�Ϊ�ҽڵ� �����������ڵ�
				RBTree w = parent.Left;
				if(!w.color) {
					w.color = true; w.parent.color = false; //��Ϊ�ֵ�Ϊ�죬���ױض�Ϊ��
					root = rightRotate(root, parent);
					w = parent.Left; //����������
				}
				//���2 wΪ�ڣ��Ҵ���������ɫ�ӽڵ���������ӽڵ�
				if((w.Left==null&&w.Right==null)||(w.Left.color&&w.Right.color)){
					//ΪʲôҪ����ѭ���أ�����Ϊ�ٸ�����һ��֧�ϼ���һ��ڣ��븸�׶��ڵ�һ֧�ͱȸ�����һ֧��һ���
					w.color = false;child = parent; parent = parent.parent;
				}
				else {
					//���4 wΪ��,��һ���ڵ㣬��ڵ���� �����3
					if(w.Left == null||w.Left.color) {
					//�Ƚ��ֵܽ����ֵܽ��Ķ��ӽ����ɫ����
					w.Right.color = true; //�ֵܽڵ�Ϊ��ɫ���ֵܶ��ӽڵ�һ��Ϊ��ɫ
					w.color = false;
					//����ֵܽ�������������ֵܽ��Ķ��ӽ���������������ֵܽ���������
					root = leftRotate(root, w);
					w = parent.Left;
					}
					//���3 wΪ�ڣ�����һ���ڵ㲢�Ҹýڵ��w��ͬһ��
					//�Ƚ����ֵܽ��͸��׽�����ɫ�����ҰѸ��׽����ֵܽ����ӽ��Ϳ�ɺ�ɫ
					w.color = parent.color; //��ʱ���׽ڵ���ɫ��ȷ��
					parent.color = true; //�ֵܽڵ�Ϊ��
					w.Left.color = true;
					//��λ���෴������ת
					root = rightRotate(root, parent);
					child = root; //��ִ�е����4��ʱ��ѭ����Ӧ����ֹ��
					}
			}
		}
		//������ɾ���������2���ڵ���һ���ӽڵ㣬����ڵ�ض�Ϊ�죬Ҫ�ĳɺ���
		//�ڶ������2��һֱ���ϵĻ����ܽ����ڵ����˺�ɫ���4����һ����ת�����ڵ��ɺ�ɫ�������ٱ�ɺ�
		child.color = true;
	}
	// �ҵ���ǰ�ڵ���Сֵ
	private RBTree Tree_min(RBTree root) {
		while(root.Left != null) 
			root = root.Left;
		return root;
	}
	// �ҵ�ֵ
	public RBTree find(RBTree root, int data) {
		while(root!=null) {
			if(data == root.data)
				return root;
			else if(data > root.data)
				root = root.Right;
			else root = root.Left;
		}
		return root;	
	}
	//�������������ӡ�������Ա������ò鿴���
		public  void H_PrintTree(RBTree root)
		{
			if(root == null) return;
			H_PrintTree(root, 1);
		}
		private  void H_PrintTree(RBTree root, int nLayer) 
		{
				if(root == null) return;
				H_PrintTree(root.Right, nLayer+1);
				for(int i = 0; i < nLayer; i++)
					System.out.print("              ");
				System.out.println(root.data+""+ root.color);
				H_PrintTree(root.Left, nLayer+1);
		}
}
