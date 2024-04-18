

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/*
 * 说明，这里对二叉树查找，构建，等方法是基于二叉树里的值不重复的情况下
 */
public class TreeOperate {
	public static void main(String[] args) {
		TreeOperate tree = new TreeOperate();
		int[] pre = {1,2,4,5,3,6,7};
//		int[] pre = {'A', 'B', 'D', 'H', 'E', 'I', 'C', 'F', 'J', 'K', 'G'};
		int[] in = {4,2,5,1,3,7,6};
//		int[] in =  {'D', 'H', 'B', 'E', 'I', 'A', 'J', 'F', 'K', 'C' ,'G'};
		int[] post = {4,5,2,7,6,3,1};
//		int[] post ={'H', 'D', 'I', 'E', 'B', 'J', 'K', 'F', 'G', 'C', 'A'};
//		TreeNode root = tree.buildTreeBypre(pre, in);
		TreeNode root = tree.buildTreeBypost(post, in);
		int h1 = tree.getHeight(root);
		int h2 = tree.getHeight_byQueue(root);
		System.out.println(h1+" "+h2);
//		tree.postOrder(root);
		tree.H_PrintTree(root);
		tree.V_PrintTree(root);
	}
	/*
	 * 二叉树的遍历
	 * 很明显是用递归来实现的，具体就不讲了
	 */
	public void preOrder(TreeNode root) //先序遍历  根左右
	{
		if(root==null) {
			return;//函数结束，返回操作系统
			/*
			 * return 语句的作用：
			 * a、返回一个值，可以是任意类型
			 * b、使程序结束，返回操作系统。
			 * java里无论有没有返回值都会自带一个return语句。
			 */
		}
		System.out.print(root.getData()+",");
		preOrder(root.getLeft());
		preOrder(root.getRight());
	}
	public void inOrder(TreeNode root) //中序遍历 左根右
	{
		if(root==null) {
			return;
		}
		inOrder(root.getLeft());
		System.out.println(root.getData()+",");
		inOrder(root.getRight());
	}
	public void postOrder(TreeNode root) //后序遍历  左右根
	{
		if(root==null) {
			return;
		}
		postOrder(root.getLeft());
		postOrder(root.getRight());
		System.out.print(root.getData()+",");
	}
	/*
	 * 二叉树的遍历，非递归实现，即用到栈的数据结构
	 * 首先，先理解这两句话
	 * 		1.除头节点外，所有的结点都是某个结点叶子结点
	 * 		2.所有的节点都能是根节点。
	 * 接下来我们就要用这句话去利用栈，把打印顺序做出了
	 */
	public void preOrder_stack(TreeNode root) //先序遍历 根左右
	{
		/*
		 * 先序遍历的非递归的写法,
		 * 和层级遍历很像，因为我们都是从根节点向下访问，而且要打印这些根节点
		 * 首先先压入根节点，然后循环中每次弹出一个节点，这个节点是我们要打印的根节点
		 *  下面用到这句话 除头节点外，所有的结点都是某个结点叶子结点
		 *  所以按照我们访问顺序依次打印出来根节点，
		 *  接来下要打印左节点，那么我们把里面的右节点先压入栈中，然后压入左节点。
		 *  这样保证，左节点打印完了再开始打印右节点
		 */
		Stack<TreeNode> stack = new Stack<>(); //创建一个栈
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode pop = stack.pop();
			System.out.print(pop.getData()+",");
			if(pop.getRight()!=null)
				stack.push(pop.getRight());
			if(pop.getLeft()!=null)
				stack.push(pop.getLeft());
		}
	}
	public void inOrder_stack(TreeNode root) //中序遍历 左根右
	{
		/*中序遍历的非递归的写法，
		 * 中序是先打印左，那么我们先一路向左，把左节点压入栈中
		 * 如果没有左节点了我们就弹出这个节点，此时这个节点是当前最左端的节点，我们打印出来
		 * 此时这个最左端的节点有两中可能，一种它有右子树，一种是它是叶子节点
		 * 如果有我们节点向右，然后我们再看看能不能再一路向左压入栈中
		 * 如果没有右节点，我们再弹出一个继续这样判断打印
		 */
		 Stack<TreeNode> stack = new Stack<>();
	        while (!stack.isEmpty() || root != null) {
	            if (root != null) {
	                stack.add(root);
	                root = root.getLeft(); //先一路向左压入栈中
	            } else {
	            	root = stack.pop();
	                System.out.print(root.getData() + ",");
	                root = root.getRight();
	            }
	        }
	}
	public void postOrder_stack(TreeNode root) //后序遍历，左右根
	{  
		/* 后序遍历非递归
			    和前序遍历一样的只不过是使用了两个栈
			    在前序遍历的时候将 中 右 左 节点压栈
			    在原来是打印的地方不打印，将本该打印的节点压到第二个栈中
			    这样第二个栈的出栈顺序就是  左 右 中了
		 */
		Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
        	root = stack.pop();
            //在这里不打印，这里我们放到第二个栈中去
            stack2.add(root);
            if (root.getLeft() != null) {
                stack.add(root.getLeft());
            }
            if (root.getRight() != null) {
                stack.add(root.getRight());
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().getData() + ",");
        }
	}
	/*
	 * 层次遍历：从根节点开始，每层从左至右，从上至下遍历每一个节点
	 * 实现方法，使用一个队列：
	 * ①取出一个节点，将他入队，
	 * ②若队列不为空，取出队列的头部元素并出队打印，若该元素左孩子不为空，左孩子入队；右孩子不为空，右孩子入队；
	 * ③重复②；
	 */
	public void BTreeLevelOrder(TreeNode root) //层次遍历，用到队列
	{
		/*
		 * Queue说明
		 * 在java5中新增加了java.util.Queue接口，用以支持队列的常见操作
		 * Queue接口与List、Set同一级别，都是继承了Collection接口。
		 * Queue使用时要尽量避免Collection的add()和remove()方法，
		 * 而是要使用offer()来加入元素，使用poll()来获取并移出元素。
		 * 它们的优点是通过返回值可以判断成功与否，add()和remove()方法在失败的时候会抛出异常。 
		 * LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用。
		 */
		//创建队列
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode pre = queue.poll();
			System.out.print(pre.getData()+",");
			if(pre.getLeft()!=null)
				queue.offer(pre.getLeft());
			if(pre.getRight()!=null)
				queue.offer(pre.getRight());
		}
	}
	/*
	 * 判断一个二叉树是否为完全二叉树，类似于层次遍历利用队列
	 * 如果出队节点满足一下情况就不是满二叉树
	 * 左子树为空，右子树不为空
	 * 左子树不为空，右子树为空且当前队列不为空，后续再有入队情况
	 * 左子树和右子树都为空且当前队列不为空，后续再有入队情况	
	 */
	public boolean isCompleteTree(TreeNode root)  //判断是否为完全二叉树
	{
		boolean b = true;//中间标记变量
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode tree = queue.poll();
			if(tree.getLeft()==null&&tree.getRight()!=null) {
				return false;
			}
			if(!b&&tree.getLeft()!=null) {
				return false;
			}
			if(tree.getLeft()!=null&&tree.getRight()==null) {
				if(!queue.isEmpty()){
					b=false;
				}
				queue.offer(tree.getLeft());
			}
			else if(tree.getLeft()==null&&tree.getRight()==null) {
				if(!queue.isEmpty()) {
					b=false;
				}
			}
			else {
				queue.offer(tree.getLeft());
				queue.offer(tree.getRight());
			}
		}
		return true;
	}
	/*
	 * 根据二叉树先序或后序，和中序构建一个二叉树
	 * 首先要明确，对于一个先序序列，第一个一个根节点，后序则最后一个是根节点
	 * 中序根据节点位置可以分出左右子树。
	 * 根据此可以将序列在划分位一个中序和，一个先序或后序
	 * 因此，要利用递归去实现。
	 */
	public TreeNode buildTreeBypre(int[] pre,int[] in) //根据先序和中序构建二叉树
	{
		if(pre.length==0||in.length==0) {
			return null;
		}
		//利用开始结束下标表示新的序列
		TreeNode tree = buildTreeBypre(pre,0,pre.length-1, in,0,in.length-1);
		return tree;
	}
	private TreeNode buildTreeBypre(int[] pre, int stapre, int endpre, int[] in, int stain, int endin) 
	{
		if(stapre>endpre||stain>endin) {
			return null;
		}
		//得到一个根节点
		TreeNode root = new TreeNode(pre[stapre]);
		for(int i=stain;i<=endin;i++) {
			/*
			 * stapre                        endpre
			 *   ↓                             ↓
			 *   1  , 2 , 4 ,  7   ,3,5,8, 9 , 6
			 *                 ↑          
			 *           i-stain+stapre 
			 *       
			 * stain          i           endin
			 *   ↓            ↓            ↓
			 *   4   , 7 , 2 ,1, 8 ,5,9,3, 6
			 *             ↑     ↑
			 *            i-1   i+1
			 */
			if(in[i]==pre[stapre]) {
				root.setLeft(buildTreeBypre(pre,stapre+1,i-stain+stapre,in,stain,i-1));
				root.setRight(buildTreeBypre(pre, i-stain+stapre+1, endpre, in, i+1, endin));
				break;//终止循环，相当于一个优化
			}
		}
		return root;
	}
	public TreeNode buildTreeBypost(int[] post,int[] in) //根据后序和中序构建二叉树
	{
		if(post.length==0||in.length==0) {
			return null;
		}
		TreeNode tree = buildTreeBypost(post,0,post.length-1, in,0,in.length-1);
		return tree;
	}
	private TreeNode buildTreeBypost(int[] post, int stapost, int endpost, int[] in, int stain, int endin) 
	{
		if(stapost>endpost||stain>endin) {
			return null;
		}
		TreeNode root = new TreeNode(post[endpost]);
		for(int i=stain;i<=endin;i++) {
			/*
			 * stain          i           endin
			 *   ↓            ↓            ↓
			 *   4   , 7 , 2 ,1, 8 ,5,9,3, 6
			 *             ↑     ↑
			 *            i-1   i+1
			 * 
			 * stapost                    endpost       
			 *   ↓                           ↓
			 *   7  , 4 , 2 , 8 ,9,5,6 , 3 , 1
			 *            ↑              ↑
			 *      i-stain+stapost-1    endpost-1
			 *   
			 */
			if(in[i]==post[endpost]) {
				root.setLeft(buildTreeBypost(post,stapost,i-stain+stapost-1,in,stain,i-1));
				root.setRight(buildTreeBypost(post,i-stain+stapost,endpost-1,in,i+1,endin));
				break;
			}
		}
		return root;
	}
	public int getHeight(TreeNode root) //获得二叉树的高度 递归法
	{
		if(root==null) {
			return 0;
		}
		int left = getHeight(root.getLeft());
		int right = getHeight(root.getRight());
		int height = left>right?left:right;
		return height+1;	
	}
	public int getHeight_byQueue(TreeNode root) //获得二叉树的高度 非递归法
	{
		/*
		 * 利用层级遍历，来计算树高，需要理解层级遍历的工作原理
		 * 层级遍历是一层一层的往下遍历，如果我们能够知道什么时候遍历完了一层，那么就可以再遍历过程中得到树高
		 * 接下来需要理解层级遍历，层级遍历是每弹出一个节点会把这个节点的左右字节点加入队列
		 * 一开始，队列节点数只有一个，就是根节点，如果根节点弹出，那么那么根节点下一层节点数一定会全部加入到队列中
		 * 利用queue.size() 方法能够得到队列此时的节点数，就是下一层节点的数目
		 * 同理，当一层的节点数全部弹出，下下一层节点数也就全部加入队列，依此类推。
		 */
		if(root==null) return 0;
		int treeHigh=0;//	树高 
		int nextCount=1;//还未进行遍历的下一层的节点数
		int count=0; //当前层中已经遍历的节点数  
		LinkedList<TreeNode> queue=new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty())
		{
			count++; //每次循环都会弹出一个节点，但弹出的节点等于这一场的节点是标识已经遍历的一层
			TreeNode node=queue.poll();
			if(node.getLeft()!=null)
				queue.add(node.getLeft());
			if(node.getRight()!=null)
				queue.add(node.getRight());
			//当前遍历的节点数和当前层的节点数相等时
			//即一层的节点遍历完成
			if(count==nextCount)
			{
				nextCount=queue.size();//此时队列中节点数量即为下一层中的节点数量
				count=0;//置下一层中已经遍历的节点数量为0
				treeHigh++;//遍历完一层 树高加1
			}
		}
		return treeHigh;
	}
	public int getLeaf(TreeNode root) //获得子叶个数
	{
		/*
		 * 左右都没有节点才能称为子叶
		 * 因此利用递归可以这样做
		 */
		if(root==null) {
			return 0;
		}
		if(root.getLeft()==null&&root.getRight()==null) {
			return 1;
		}
		return getLeaf(root.getLeft())+getLeaf(root.getRight());
	}
	/*
	 * 下面就是一些简单的二叉树操作
	 * 看一下代码可以轻松理解
	 */
	public TreeNode findNode(TreeNode root,int x) //按值二叉树寻找某个节点
	{
		if(root!=null) {
			if(root.getData()==x)
				return root;
			else {
				TreeNode Node;
				if(root.getLeft()!=null) {
					Node = findNode(root.getLeft(),x);
					if(Node!=null)   //只要不为空，就说明他找到了值，不必再做一次比较。
						return Node;
				}
				if(root.getRight()!=null) {
					Node = findNode(root.getRight(),x);
					if(Node!=null) 
						return Node;
				}
			}
		}
		return null;
	}
	public int getNodeKNum(TreeNode root,int k) //获得K层节点总数
	{
		/*
		 * 理解这个需要明确，递归时间复杂度就是一个树，调用的次数就是他的分叉。
		 * 所以
		 */
		if(k==1) {
			if(root == null)
				return 0;
			else
				return 1;
		}
		return getNodeKNum(root.getLeft(),k-1)+getNodeKNum(root.getRight(),k-1);
	}
	//按树状打印二叉树 竖向打印
	public void V_PrintTree(TreeNode root) 
	{
		/*
		 * 竖向打印，很明显就是层级遍历，
		 * 但是要能够显示成树状，我们还需要计算出树高，根据树高来计算出空格
		 * 目前还没有做出了
		 */
		if(root == null) return;
		
	}
	//桉树状打印二叉树 横向打印
	public void H_PrintTree(TreeNode root)
	{
		/*
		 * 横向打印是逆中序遍历二叉树，即右，根，左打印
		 * 传入层级，以便于空格分出左右
		 */
		if(root == null) return;
		H_PrintTree(root, 1);
	}
	private void H_PrintTree(TreeNode root, int nLayer) 
	{
			if(root == null) return;
			H_PrintTree(root.getRight(), nLayer+1);
			for(int i = 0; i < nLayer; i++)
				System.out.print("  ");
			System.out.println(root.getData());
			H_PrintTree(root.getLeft(), nLayer+1);
	}
	
}
