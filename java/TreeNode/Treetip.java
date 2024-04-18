

import java.util.Scanner;

public class Treetip {
	/*
	 * 二叉树的基本知识
	 * 	二叉树的分类：
	 * 	简单的，满二叉树，完全二叉树 一般满二叉树和完全二叉树是用数组存储，这里就不作实体构造
	 * 	平衡二叉树：空树或者它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树也都是平衡树。
	 * 	二叉搜索树：空树或者二叉树的所有节点比他的左子节点大，比他的右子节点小。
	 *      红黑树：一种既有平衡二叉树性质又有二叉搜索树的性质的特殊树，作为重点学习。
	 *      参考学习:https://www.cnblogs.com/yinbiao/p/10732600.html
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
//			System.out.println("输入插入的值");
//			data = sc.nextInt();
			data = a[i++];
			root = root.RB_Insert(root,new RBTree(data, false));
		}
		H_PrintTree(root);
		while(data!=0) {
			System.out.println("输入删除的值");
			data = sc.nextInt();
			RBTree z = root.find(root, data);
			if(z!=null) {
				root = root.RB_Delete(root, z);
				H_PrintTree(root);
			}
		}
	}
	//将二叉树横向打印抄过来以便于利用查看结果
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
 * 红黑树的特性:
(1) 每个节点或者是黑色，或者是红色。
(2) 根节点是黑色。
(3) 每个叶子节点是黑色。 [注意：这里叶子节点，是指为空的叶子节点！不为空的叶子节点]
(4) 如果一个节点是红色的，则它的子节点必须是黑色的。
(5) 从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
 */
class RBTree{
	boolean color; //规定红色为false，黑色为true
	int data;
	RBTree parent = null; //多加一个父节点，便于我们以后维护找到父节点
	RBTree Left = null;
	RBTree Right = null;
	public RBTree(int data,boolean color) {
		this.data= data;
		this.color = color;
	}
	/* 红黑树的旋转
	 * 左旋右节点升
	 * 对红黑树的节点(x)进行左旋转
	 * 左旋示意图(对节点x进行左旋)：
	 *      px                              px
	 *     /                               /
	 *    x                               y                
	 *   /  \      --(左旋)-.           / \                #
	 *  lx   y                          x  ry     
	 *     /   \                       /  \
	 *    ly   ry                     lx  ly  
	 *
	 * 对红黑树的节点(y)进行右旋转
	 * 右旋示意图(对节点y进行左旋)：
	 *            py                               py
	 *           /                                /
	 *          y                                x                  
	 *         /  \      --(右旋)-.            /  \                     #
	 *        x   ry                           lx   y  
	 *       / \                                   / \                   #
	 *      lx  rx                                rx  ry
	 *
	 *
	 *通过图可以发现，左旋就是将节点的右支往左拉，右子节点变成父节点，并把晋升之后多余的左子节点出让给降级节点的右子节点；
　　而右旋就是反过来，将节点的左支往右拉，左子节点变成了父节点，并把晋升之后多余的右子节点出让给降级节点的左子节点。
　　即左旋就是往左变换，右旋就是往右变换。不管是左旋还是右旋，旋转的目的都是将节点多的一支出让节点给另一个节点少的一支。
	 */
	//红黑树的旋转，左旋与右旋方法其实相似
	public RBTree leftRotate(RBTree root,RBTree x) {
		//root为红黑树的根节点
		RBTree y = x.Right; //设置x的右节点为y
		  // 将 “y的左孩子” 设为 “x的右孩子”；
	    // 如果y的左孩子非空，将 “x” 设为 “y的左孩子的父亲”
		x.Right = y.Left;
		if(y.Left!=null)
			y.Left.parent = x;
		 // 将 “x的父亲” 设为 “y的父亲”
		y.parent = x.parent;
		if(x.parent==null)
			root = y; // 如果 “x的父亲” 是空节点，则将y设为根节点
		else if(x.parent.Left == x)
			x.parent.Left = y;  // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
		else x.parent.Right = y; // 如果 x是它父节点的右孩子，则将y设为“x的父节点的右孩子”
		y.Left = x; // 将 “x” 设为 “y的左孩子”
		x.parent = y;  // 将 “x的父节点” 设为 “y”
		return root;
	}
	//红黑树的右旋
	public RBTree rightRotate(RBTree root,RBTree y) {
		RBTree x = y.Left  ; //设置x
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
	//红黑树的插入  
	/*
	 * 插入是搜索二叉树插入的方法，插入的位置一定不是根节点
	 * 插入一个节点是，我们默认节点为红色：
	 * 	不违背红黑树的性质5：从任意一个结点出发到空叶子结点，经过的黑色结点个数相同等
	 *      简单来说就是能够违背红黑树的性质少
	 * 插入可能会破坏红黑树的平衡，和红黑树的一些性质，所以需要维护，
	 * Insert_Fixup方法就是维护二叉树的平衡和颜色。具体讲解再方法内
	 */
	public RBTree RB_Insert(RBTree root,RBTree z) {
		if(root == null)
			{z.color = true; root = z;return root;}
		//找到要插入的节点 从根节点开始往下找
		RBTree x = root;
		RBTree y = null;
		while(x!=null) {
			y = x;
			if(x.data > z.data) x = x.Left; //小于根节点，插入到左边的
			else x = x.Right;  //否则插入根节点右边
		}
		//找到插入位置，就开始插入
		z.parent = y;
		if(y.data > z.data) y.Left = z;
		else y.Right = z;
		root = Insert_Fixup(root,z); //开始维护
		return root;
	}
	/*红黑树插入情况分析：
	1.树为空，插入的结点为根结点
		直接将插入的结点变成黑色，这里面在插入就做了处理，所以不需要考虑
	2.父亲结点为黑色结点
		不需要任何操作
	3.父亲结点为红色结点的情况下：
    3.1 叔叔结点也为红色结点
    	将叔叔和父亲结点改为黑色，爷爷结点改为红色，未完，然后又将爷爷结点当作插入结点看待，一直进行上
    	面的操作，直到当前结点为根结点，然后将根结点变成黑色
    3.2 叔叔结点为黑色结点的情况下：
        3.2.1 （父亲结点为左孩子，插入结点也为左孩子）||（父亲结点为右孩子，插入结点也为右孩子）
        	将父亲结点和爷爷结点的颜色互换，然后针对爷爷结点进行一次左 右旋
        3.2.2 （父亲结点为左孩子，插入结点为右孩子）||（父亲结点为右孩子，插入结点为左孩子）
        	针对父结点进行左 右旋(由插入节点的位置确定取相反)，插入点为父节点
        	此时左旋后的情况必定是3.2.1的情况，然后按照3.2.1的情况处理
	 其他说明：
	 	红黑树中，根节点为红色，其左子叶和右子叶一定是黑色（这里的子叶可能是空节点)
	 */
	private RBTree Insert_Fixup(RBTree root, RBTree z) {
		//这里我们执行考虑插入的节点的父节点是红色还是黑色。如果是黑色就不需要操作了
		while(z.parent!=null&&!z.parent.color) //用循环，因为上面都是递归一步步向上判断
		{ 
			//如果一个节点父节点为红色，那么它爷爷节点一定存在且为黑色，所以这里不会抛出空指针异常
			if(z.parent.parent.Left == z.parent) //如果父节点是左节点
			{
				RBTree y = z.parent.parent.Right; //叔叔节点为右节点
				if(y!=null&&!y.color) //如果叔叔节点不为空,空节点必定是黑色的，且叔叔节点为红是
				{
					//将叔叔和父亲结点改为黑色，爷爷结点改为红色
					y.color = false;
					z.parent.color = false;
					z.parent.parent.color = true;
					//然后又将爷爷结点当作插入结点看待
					z = z.parent.parent;
				}
				else {
					if(z.parent.Right == z) //如果插入节点是右节点，情况3.1.2 左旋后变成3.1.1
					{
					z = z.parent;
					root = leftRotate(root, z); //左旋
					}
				//将父亲结点和爷爷结点的颜色互换 因为已经确定了父节点为红色，爷爷节点为黑色
				z.parent.color = true;
				z.parent.parent.color = false;
				root = rightRotate(root, z.parent.parent); //左旋
				}
			}
			else //如果父节点为右节点，处理类似于父节点为左节点，左右调换
			{
				RBTree y = z.parent.parent.Left; //叔叔节点为左节点
				if(y!=null&&!y.color) //如果叔叔节点不为空,空节点必定是黑色的，且叔叔节点红
				{
					//将叔叔和父亲结点改为黑色 true，爷爷结点改为红色 false
					y.color = true;
					z.parent.color = true;
					z.parent.parent.color = false;
					//然后又将爷爷结点当作插入结点看待
					z = z.parent.parent;
				}
				else { 
					if(z.parent.Left == z) //如果插入节点是左节点，情况3.1.2 右旋后变成3.1.1
				{
					z = z.parent;
					root = rightRotate(root, z); //右旋
				}
				//将父亲结点和爷爷结点的颜色互换 因为已经确定了父节点为红色，爷爷节点为黑色
				z.parent.color = true;z.parent.parent.color = false;
				root = leftRotate(root, z.parent.parent); //右旋
				}
			}
		}
		/* 重置根节点为黑色
		 * 预防这种特殊情况
		 * 		  1黑
		 *	  2红            3红
		 *插入4，结果会变成
		 *		 1红
		 *   2黑           3黑
		 *                4红
		 */
		root.color = true;
		return root;
	}
	//红黑树的删除  和二叉树一样。定义一个移植交换方法来删除这个节点
	private RBTree RBTransplant(RBTree root, RBTree u, RBTree v) {
		//u 要删除的节点，v用于替代删除的节点
		if(u.parent == null) //删除的是根节点
			root = v;    
		else if(u == u.parent.Left)  //删除点是左节点
			u.parent.Left = v;
		else u.parent.Right = v;
		if(v!=null) //如果替代的点不是空节点
			v.parent = u.parent;
		return root;
	}
	/* 红黑树的删除，重点理解 理解可以在参考https://www.cnblogs.com/qingergege/p/7351659.html
	 * 删除分为三种情况： 但第三中情况可以转换为第二种情况
	 * 		1.删除点没有子节点
	 * 			1）删除点红色，直接删
	 * 			2）黑色删除后 分情况在维护，具体在维护将
	 * 		2.删除点有一个子节点
	 * 			只有一种情况，删除点为黑色且其子节点为红色，此时将子节点涂黑放到父节点位置即可		
	 * 		3.删除点有两个字节点，这是让删除的点和其右子节点中最左端即最小值交换，这样就变成了情况1或2 
	 */
	public RBTree RB_Delete(RBTree root, RBTree z) {
		/* 设置y 用于当删除节点为两个时，y记录x的后继节点，所以y始终是在被删除的位置
		 * 	设置color记录下删除节点最开始的颜色
		 *    设置x x是用于替代删除节点的位置，因为x可能为空，维护时需要x的父节点来向上遍历，
		 *    	所以，设置child 与 parent 来替代x
		 *   每一次的交换都会违法二叉树的性质，所以维护是从交换处的位置开始维护即x
		 */
		RBTree y = z;
		boolean color = z.color;
		RBTree child,parent;
		//这里我们将没有节点的处理放在维护里面
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
			/* 如果两个节点都存在
			 *     找到删除节点的右节点最左端，交换值然后删除
			 *       此时情况只有两种：1.有一个节点，这个节点一定是右节点，2，没有节点 
			 */
			y = Tree_min(z.Right);//找到后继节点
			color = y.color;
			child = y.Right;  //x是用于替代记录删除节点的位置，所以必定是y
			parent = y.parent;
			//交换值 将节点值交换是不影响红黑树的红黑性质，只会影响二叉搜索树性质，但最后会把这个值删除
			//所以，维护后是不影响红黑树性质
			int temp = y.data; y.data = z.data; z.data = temp;
			//将y作为删除节点删除 此时替代的节点必定是y.right
			root =RBTransplant(root, y, y.Right);
		}
		if(color) Delete_fixup(root,child,parent);
		return root;
	}
	/* 删除节点维护 分析删除情况需要我们维护的有以下情况：
	 * 首先由删除过程我们可以知道，传入的child节点由两种情况，1为空，2不为空且为颜色红
	 * 对于第二种情况，我们只需将其颜色改为黑就行了
	 * 对于第一种情况需要分为以下情况(总体分为4种) ，child我设为x(必定为黑) 其兄弟节点设为w且必定存在,默认存在节点为空的子节点
	 *   1.w为红，必定有两个黑子节点  因为我删除的节点是黑色，其兄弟为红时节点必须有两个黑子节点才能满足性质5
		     针对情况1可以转换为情况2，3，4
		     将兄弟结点和父亲结点的颜色互换
			被删除的元素为左子树：对父亲结点左旋
			被删除的元素为右子树：对父亲结点右旋  
		     然后就变成情况2，3，4了
	 *   2.w为黑，且存在两个黑色子节点或两个空子节点
	 *     这种情况比较好理解，首先一开始，x是空节点，w为黑的话，违背了二叉树性质5，所以将其涂成红色即可
	 *   3.w为黑，且有一个节点并且该节点和w在同一边（同为左子树或者同为右子树）：
	 *    首先不管是括号中那种情况，先交换兄弟结点和父亲结点的颜色，并且把父亲结点和兄弟结点的子结点涂成黑色
		 如果兄弟结点和兄弟结点的儿子都在右子树的话：对父亲结点进行左旋
		 如果兄弟结点和兄弟结点的儿子都在左子树的话：对父亲结点进行右旋
	 *   4.w为黑，且有一个孩子不空，并且该孩子和w不在同一边（右左或者左右的情况）：
	 *   首先，先将兄弟结点和兄弟结点的儿子结点颜色互换
	 如果兄弟结点是左子树，兄弟结点的儿子结点是右子树：对兄弟结点进行左旋
	 如果兄弟结点是右子树，兄弟结点的儿子结点是左子树：对兄弟结点进行右旋
	 这样就将情况4变成了情况3
	     5.w为黑，且两个孩子节点都存在且为红，这时候按情况3同侧处理。
	 */
	private void Delete_fixup(RBTree root, RBTree child, RBTree parent) {
		//总结来说，就是这句话，删除看兄弟，兄弟为红变成黑，然后，两黑，兄弟节点涂红，一红分同侧不同测，两红默认同侧(同侧按所在位置旋转)
		while((child==null)||(child!=root&&child.color)) //x节点开始为空颜色为黑，或者非根
		{
			if(child == parent.Left) //如果x节点是左节点时
			{
				RBTree w = parent.Right;//兄弟节点w为右节点
				//情况1 w为红 必定有两个黑节点
				if(!w.color) {
					//将兄弟结点和父亲结点的颜色互换 
					w.color = true; w.parent.color = false; //因为兄弟为红，父亲必定为黑
					//被删除的元素为左子树,对父亲结点左旋反正则反
					root = leftRotate(root, parent);
					w = parent.Right; //左旋有升高
				}
				//情况2 w为黑，且存在两个黑色子节点或两个空子节点
				if((w.Left==null&&w.Right==null)||(w.Left.color&&w.Right.color)){
					//为什么要向上循环呢，是因为再父亲这一分支上减了一层黑，与父亲对于的一支就比父亲这一支多一层黑
					w.color = false;child = parent; parent = parent.parent;
				}
				else {
					//情况4 w为黑,有一个节点，红节点异侧 变情况3
					if(w.Right == null||w.Right.color) {
					//先将兄弟结点和兄弟结点的儿子结点颜色互换
					w.Left.color = true; //兄弟节点为黑色，兄弟儿子节点一定为红色
					w.color = false;
					//如果兄弟结点是右子树，兄弟结点的儿子结点是左子树：对兄弟结点进行右旋
					root = rightRotate(root, w);
					w = parent.Right;
					}
					//情况3 w为黑，且有一个节点并且该节点和w在同一边
					//先交换兄弟结点和父亲结点的颜色，并且把父亲结点和兄弟结点的子结点涂成黑色
					w.color = parent.color; //此时父亲节点颜色不确定
					parent.color = true; //兄弟节点为黑
					w.Right.color = true;
					//按位置相反方向旋转
					root = leftRotate(root, parent);
					child = root; //当执行到情况4的时候，循环就应该终止了。
					}
			}
			else { //孩子节点为右节点 情况类似于左节点
				RBTree w = parent.Left;
				if(!w.color) {
					w.color = true; w.parent.color = false; //因为兄弟为红，父亲必定为黑
					root = rightRotate(root, parent);
					w = parent.Left; //左旋有升高
				}
				//情况2 w为黑，且存在两个黑色子节点或两个空子节点
				if((w.Left==null&&w.Right==null)||(w.Left.color&&w.Right.color)){
					//为什么要向上循环呢，是因为再父亲这一分支上减了一层黑，与父亲对于的一支就比父亲这一支多一层黑
					w.color = false;child = parent; parent = parent.parent;
				}
				else {
					//情况4 w为黑,有一个节点，红节点异侧 变情况3
					if(w.Left == null||w.Left.color) {
					//先将兄弟结点和兄弟结点的儿子结点颜色互换
					w.Right.color = true; //兄弟节点为黑色，兄弟儿子节点一定为红色
					w.color = false;
					//如果兄弟结点是右子树，兄弟结点的儿子结点是左子树：对兄弟结点进行右旋
					root = leftRotate(root, w);
					w = parent.Left;
					}
					//情况3 w为黑，且有一个节点并且该节点和w在同一边
					//先交换兄弟结点和父亲结点的颜色，并且把父亲结点和兄弟结点的子结点涂成黑色
					w.color = parent.color; //此时父亲节点颜色不确定
					parent.color = true; //兄弟节点为黑
					w.Left.color = true;
					//按位置相反方向旋转
					root = rightRotate(root, parent);
					child = root; //当执行到情况4的时候，循环就应该终止了
					}
			}
		}
		//这里是删除分析情况2，节点有一个子节点，这个节点必定为红，要改成黑替
		//第二是情况2和一直向上的话可能将根节点变成了红色情况4进行一次旋转，根节点变成红色，所以再变成黑
		child.color = true;
	}
	// 找到当前节点最小值
	private RBTree Tree_min(RBTree root) {
		while(root.Left != null) 
			root = root.Left;
		return root;
	}
	// 找到值
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
	//将二叉树横向打印抄过来以便于利用查看结果
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
