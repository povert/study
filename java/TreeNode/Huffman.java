package TreeNode;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

/* �����������ֳ�����������һ���Ȩ·��������̵���
 * 	·������һ���ڵ㵽��һ���ڵ�ķ�֧����
 *      ·�����ȣ������ķ�֧��Ŀ	
 *      ����Ȩ������ʵ��Ӧ�ã�������Ϊÿһ����㶼��ֵһ��ʵ����Ϊ����Ȩ
 *      ����·�����ȣ���������ÿһ������·������֮�ͣ�����PL
 *      ���Ĵ�Ȩ·������Ϊ�Ӹý�㵽����֮���·������������Ȩ�ĳ˻���
 *      ���Ĵ�Ȩ·������Ϊ��������Ҷ�ӽ��Ĵ�Ȩ·������֮�ͣ�ͨ������WPL��
 *     ���磺
 *     	WPL = 7*2+5*2+2*2+4*2=36
 *               18
 *             /   \
 *            12    6
 *           / \   / \
 *          7   5 2   4
 *  ����˵���ǣ�����������Ҷ�ӽڵ���и���Ȩֵ���ڵ��Ȩ��Ϊ����������������Ȩֵ�ĺͣ������Ǵ�Ȩ·������̵�
 *   	 �о�����������Ŀ�����������Ž�
 *  ���������룺
 *  	����Լ�����֧��ʾ�ַ�'0'���ҷ�֧��ʾ�ַ�'1'��
 *  	�ڹ��������дӸ���㿪ʼ����Ҷ�ӽ���·���Ϸ�֧�ַ���ɵ��ַ���Ϊ��Ҷ�ӽ��Ĺ��������롣
 *  	��������������Ĺ�������������ʾ��
 *  				   15
 *                   /    \
 *                 (0)    (1)
 *                 /        \
 *                6          9
 *               / \        / \
 *             (0) (1)    (0) (1)
 *             /     \    /     \
 *            3       3  4       5
 *           00      01  10      11
 *       ���3�ı���00���ұ�3�ı���01��4�ı���10��5�ı���11��
 * ������������ǰ׺����
 *  	�����һ��ϵͳ��������룬�����������κα����ǰ׺����Ƹñ���ϵͳ�еı�����ǰ׺����
 *  ����ǰ׺��������и��ַ���Ӧ�ı���֮�䲻��Ҫ�ָ��� ������ǰ׺���룬�����ʹ�÷ָ������������������


 */ 
public class Huffman {
	public static void main(String[] args) {
		int[] a = {2,3,5,7,8,1,4,6,9,0};
		HuffmanTree root = BuildHafferTree(a);
		setCode(root);
		Map<Integer,String> map = new HashMap<>();
		map = getCode(root, map);
		System.out.println("��������");
		H_PrintTree(root);
		String b = bianma(map,"23788");
		System.out.println("���룺"+b);
		System.out.println("���룺"+jiema(map, b));
		
	}
	
	//����һ��������ΪȨ�ؽ�����������
	public static HuffmanTree BuildHafferTree(int[] a) {
		if(a.length==0) return null;
		if(a.length==1) return new HuffmanTree(a[0]);

		//������鹹��һ������������
		List<HuffmanTree> node = new ArrayList<>();
		for(int i = 0; i < a.length; i++) 
			node.add(new HuffmanTree(a[i]));
		while(node.size()>1) //����ʱ��������������������
		{
			//�Լ������Ԫ�ؽ������� �ò�������
			for(int i = 1; i < node.size(); i++) {
				HuffmanTree cur = node.remove(i); //��ȡ�����Ԫ��
				int index = i;
				while(index>0&&node.get(index-1).val>=cur.val) 
					index--;
				node.add(index, cur); //�ٰ����Ԫ�ز鵽���λ��
			}
			//��ȡȨֵ��С�������ڵ�
			HuffmanTree left = node.remove(0);
			HuffmanTree right = node.remove(0);
			//�����ǹ���һ��������
			HuffmanTree parent = new HuffmanTree(left.val+right.val, null, left, right);
			//����������뵽������
			node.add(parent);
		}
		return node.get(0);//Ȼ�󷵻ؼ������һ��Ԫ��
	}
	//���������� ������1
	public static void setCode(HuffmanTree root) {
		if(root.left!=null)
			{root.left.code =root.code+"0"; setCode(root.left);}
		if(root.right!=null)
			{root.right.code =root.code+"1"; setCode(root.right);}
	}
	//���ñ��뷽��2 �õ������ֵ� ֻ����Ҷ�ӽڵ�ı���
	public static Map<Integer,String> getCode(HuffmanTree root,Map<Integer,String> map) {
		if(root.left != null)
			map = getCode(root.left,map);
		if(root.right != null)
			map = getCode(root.right, map);
		if(root.right==null&&root.left==null)
			map.put(root.val, root.code);
		return map;
	}
	//���ݱ����ֵ��һ�����ֽ��б���
	public static String bianma(Map<Integer,String> map, String str) {
		String s = "";
		for(int i = 0; i<str.length(); i++) {
			int key = str.charAt(i) - 48;
			s += map.get(key);
		}
		return s;
	}
	//���ݱ���������
	public static String jiema(Map<Integer,String> map, String m) {
		String s = "";
		String code = ""; //���ڻ����ȡ�ķ������ı���
		Set<Integer> keys = map.keySet();//�����еļ�����һ��set������Ա������Ǳ���Ѱ��
		for(int i = 0; i < m.length(); i++) {
			code += m.substring(i, i+1);
			if(map.containsValue(code))  //������ڴ�codeӳ�䣬�Ϳ�ʼ����� 
			{
				for(int key : keys) { 
					if(map.get(key).equals(code))
						{s += key; code = ""; break;}
				}
			}
		}
		return s;
	}
	//��״��ӡ��
	public static void H_PrintTree(HuffmanTree root)
	{
		if(root == null) return;
		H_PrintTree(root, 1);
	}
	private static void H_PrintTree(HuffmanTree root, int nLayer) 
	{
			if(root == null) return;
			H_PrintTree(root.right, nLayer+1);
			for(int i = 0; i < nLayer; i++)
				System.out.print("              ");
			System.out.println(root.val+"->"+root.code);
			H_PrintTree(root.left, nLayer+1);
	}

}
class HuffmanTree{
	public int val; //Ȩֵ
	public String code=""; //���ñ���;
	public HuffmanTree parent; //���ڵ�
	public HuffmanTree left; //��ڵ�
	public HuffmanTree right; //�ҽڵ�
	public HuffmanTree(int val, HuffmanTree parent, HuffmanTree left, HuffmanTree right) {
		super();
		this.val = val;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	public HuffmanTree(int val) {
		super();
		this.val = val;
	}

}
