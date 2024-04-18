
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

/* 哈夫曼树，又称最优树，是一类带权路径长度最短的树
 * 	路径：从一个节点到另一个节点的分支序列
 *      路径长度：经过的分支数目	
 *      结点的权：更具实际应用，常常会为每一个结点都赋值一个实数作为结点的权
 *      树的路径长度：从树根到每一个结点的路径长度之和，记作PL
 *      结点的带权路径长度为从该结点到树根之间的路径长度与结点上权的乘积。
 *      树的带权路径长度为树中所有叶子结点的带权路径长度之和，通常记作WPL。
 *     例如：
 *     	WPL = 7*2+5*2+2*2+4*2=36
 *               18
 *             /   \
 *            12    6
 *           / \   / \
 *          7   5 2   4
 *  简单来说就是，哈夫曼树是叶子节点具有赋予权值，节点的权重为其左子树于右子树权值的和，并且是带权路径是最短的
 *   	 研究哈夫曼树的目的在于求最优解
 *  哈夫曼编码：
 *  	我们约定左分支表示字符'0'，右分支表示字符'1'，
 *  	在哈夫曼树中从根结点开始，到叶子结点的路径上分支字符组成的字符串为该叶子结点的哈夫曼编码。
 *  	上面代码所创建的哈夫曼树如下所示：
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
 *       左边3的编码00，右边3的编码01，4的编码10，5的编码11；
 * 哈夫曼编码是前缀编码
 *  	如果在一个系统中任意编码，都不是其他任何编码的前缀，则称该编码系统中的编码是前缀编码
 *  若是前缀编码电文中各字符对应的编码之间不需要分隔符 若不是前缀编码，则必须使用分割符，否则会产生二异性


 */ 
public class Huffman {
	public static void main(String[] args) {
		int[] a = {2,3,5,7,8,1,4,6,9,0};
		HuffmanTree root = BuildHafferTree(a);
		setCode(root);
		Map<Integer,String> map = new HashMap<>();
		map = getCode(root, map);
		System.out.println("哈夫曼树");
		H_PrintTree(root);
		String b = bianma(map,"23788");
		System.out.println("编码："+b);
		System.out.println("解码："+jiema(map, b));
	}
	
	//给定一个数组作为权重建立哈夫曼树
	public static HuffmanTree BuildHafferTree(int[] a) {
		if(a.length==0) return null;
		if(a.length==1) return new HuffmanTree(a[0]);

		//针对数组构建一个二叉树集合
		List<HuffmanTree> node = new ArrayList<>();
		for(int i = 0; i < a.length; i++) 
			node.add(new HuffmanTree(a[i]));
		while(node.size()>1) //当此时集合里有少于两个数组
		{
			//对集合里的元素进行排序 用插入排序
			for(int i = 1; i < node.size(); i++) {
				HuffmanTree cur = node.remove(i); //先取出这个元素
				int index = i;
				while(index>0&&node.get(index-1).val>=cur.val) 
					index--;
				node.add(index, cur); //再把这个元素查到这个位置
			}
			//获取权值最小的两个节点
			HuffmanTree left = node.remove(0);
			HuffmanTree right = node.remove(0);
			//将它们构成一个二叉树
			HuffmanTree parent = new HuffmanTree(left.val+right.val, null, left, right);
			//把这个树加入到集合里
			node.add(parent);
		}
		return node.get(0);//然后返回集合里第一个元素
	}
	//哈夫曼编码 左零右1
	public static void setCode(HuffmanTree root) {
		if(root.left!=null)
			{root.left.code =root.code+"0"; setCode(root.left);}
		if(root.right!=null)
			{root.right.code =root.code+"1"; setCode(root.right);}
	}
	//设置编码方法2 得到编码字典 只放置叶子节点的编码
	public static Map<Integer,String> getCode(HuffmanTree root,Map<Integer,String> map) {
		if(root.left != null)
			map = getCode(root.left,map);
		if(root.right != null)
			map = getCode(root.right, map);
		if(root.right==null&&root.left==null)
			map.put(root.val, root.code);
		return map;
	}
	//根据编码字典对一串数字进行编码
	public static String bianma(Map<Integer,String> map, String str) {
		String s = "";
		for(int i = 0; i<str.length(); i++) {
			int key = str.charAt(i) - 48;
			s += map.get(key);
		}
		return s;
	}
	//根据编码来解码
	public static String jiema(Map<Integer,String> map, String m) {
		String s = "";
		String code = ""; //用于缓存读取的非完整的编码
		Set<Integer> keys = map.keySet();//将所有的键放在一个set集合里，以便于我们遍历寻找
		for(int i = 0; i < m.length(); i++) {
			code += m.substring(i, i+1);
			if(map.containsValue(code))  //如果存在此code映射，就开始找其键 
			{
				for(int key : keys) { 
					if(map.get(key).equals(code))
						{s += key; code = ""; break;}
				}
			}
		}
		return s;
	}
	//树状打印树
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
	public int val; //权值
	public String code=""; //设置编码;
	public HuffmanTree parent; //父节点
	public HuffmanTree left; //左节点
	public HuffmanTree right; //右节点
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
