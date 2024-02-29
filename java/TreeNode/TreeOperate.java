package TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/*
 * ˵��������Զ��������ң��������ȷ����ǻ��ڶ��������ֵ���ظ��������
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
	 * �������ı���
	 * ���������õݹ���ʵ�ֵģ�����Ͳ�����
	 */
	public void preOrder(TreeNode root) //�������  ������
	{
		if(root==null) {
			return;//�������������ز���ϵͳ
			/*
			 * return �������ã�
			 * a������һ��ֵ����������������
			 * b��ʹ������������ز���ϵͳ��
			 * java��������û�з���ֵ�����Դ�һ��return��䡣
			 */
		}
		System.out.print(root.getData()+",");
		preOrder(root.getLeft());
		preOrder(root.getRight());
	}
	public void inOrder(TreeNode root) //������� �����
	{
		if(root==null) {
			return;
		}
		inOrder(root.getLeft());
		System.out.println(root.getData()+",");
		inOrder(root.getRight());
	}
	public void postOrder(TreeNode root) //�������  ���Ҹ�
	{
		if(root==null) {
			return;
		}
		postOrder(root.getLeft());
		postOrder(root.getRight());
		System.out.print(root.getData()+",");
	}
	/*
	 * �������ı������ǵݹ�ʵ�֣����õ�ջ�����ݽṹ
	 * ���ȣ�����������仰
	 * 		1.��ͷ�ڵ��⣬���еĽ�㶼��ĳ�����Ҷ�ӽ��
	 * 		2.���еĽڵ㶼���Ǹ��ڵ㡣
	 * ���������Ǿ�Ҫ����仰ȥ����ջ���Ѵ�ӡ˳��������
	 */
	public void preOrder_stack(TreeNode root) //������� ������
	{
		/*
		 * ��������ķǵݹ��д��,
		 * �Ͳ㼶����������Ϊ���Ƕ��ǴӸ��ڵ����·��ʣ�����Ҫ��ӡ��Щ���ڵ�
		 * ������ѹ����ڵ㣬Ȼ��ѭ����ÿ�ε���һ���ڵ㣬����ڵ�������Ҫ��ӡ�ĸ��ڵ�
		 *  �����õ���仰 ��ͷ�ڵ��⣬���еĽ�㶼��ĳ�����Ҷ�ӽ��
		 *  ���԰������Ƿ���˳�����δ�ӡ�������ڵ㣬
		 *  ������Ҫ��ӡ��ڵ㣬��ô���ǰ�������ҽڵ���ѹ��ջ�У�Ȼ��ѹ����ڵ㡣
		 *  ������֤����ڵ��ӡ�����ٿ�ʼ��ӡ�ҽڵ�
		 */
		Stack<TreeNode> stack = new Stack<>(); //����һ��ջ
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
	public void inOrder_stack(TreeNode root) //������� �����
	{
		/*��������ķǵݹ��д����
		 * �������ȴ�ӡ����ô������һ·���󣬰���ڵ�ѹ��ջ��
		 * ���û����ڵ������Ǿ͵�������ڵ㣬��ʱ����ڵ��ǵ�ǰ����˵Ľڵ㣬���Ǵ�ӡ����
		 * ��ʱ�������˵Ľڵ������п��ܣ�һ��������������һ��������Ҷ�ӽڵ�
		 * ��������ǽڵ����ң�Ȼ�������ٿ����ܲ�����һ·����ѹ��ջ��
		 * ���û���ҽڵ㣬�����ٵ���һ�����������жϴ�ӡ
		 */
		 Stack<TreeNode> stack = new Stack<>();
	        while (!stack.isEmpty() || root != null) {
	            if (root != null) {
	                stack.add(root);
	                root = root.getLeft(); //��һ·����ѹ��ջ��
	            } else {
	            	root = stack.pop();
	                System.out.print(root.getData() + ",");
	                root = root.getRight();
	            }
	        }
	}
	public void postOrder_stack(TreeNode root) //������������Ҹ�
	{  
		/* ��������ǵݹ�
			    ��ǰ�����һ����ֻ������ʹ��������ջ
			    ��ǰ�������ʱ�� �� �� �� �ڵ�ѹջ
			    ��ԭ���Ǵ�ӡ�ĵط�����ӡ�������ô�ӡ�Ľڵ�ѹ���ڶ���ջ��
			    �����ڶ���ջ�ĳ�ջ˳�����  �� �� ����
		 */
		Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
        	root = stack.pop();
            //�����ﲻ��ӡ���������Ƿŵ��ڶ���ջ��ȥ
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
	 * ��α������Ӹ��ڵ㿪ʼ��ÿ��������ң��������±���ÿһ���ڵ�
	 * ʵ�ַ�����ʹ��һ�����У�
	 * ��ȡ��һ���ڵ㣬������ӣ�
	 * �������в�Ϊ�գ�ȡ�����е�ͷ��Ԫ�ز����Ӵ�ӡ������Ԫ�����Ӳ�Ϊ�գ�������ӣ��Һ��Ӳ�Ϊ�գ��Һ�����ӣ�
	 * ���ظ��ڣ�
	 */
	public void BTreeLevelOrder(TreeNode root) //��α������õ�����
	{
		/*
		 * Queue˵��
		 * ��java5����������java.util.Queue�ӿڣ�����֧�ֶ��еĳ�������
		 * Queue�ӿ���List��Setͬһ���𣬶��Ǽ̳���Collection�ӿڡ�
		 * Queueʹ��ʱҪ��������Collection��add()��remove()������
		 * ����Ҫʹ��offer()������Ԫ�أ�ʹ��poll()����ȡ���Ƴ�Ԫ�ء�
		 * ���ǵ��ŵ���ͨ������ֵ�����жϳɹ����add()��remove()������ʧ�ܵ�ʱ����׳��쳣�� 
		 * LinkedList��ʵ����Queue�ӿڣ�������ǿ��԰�LinkedList����Queue���á�
		 */
		//��������
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
	 * �ж�һ���������Ƿ�Ϊ��ȫ�������������ڲ�α������ö���
	 * ������ӽڵ�����һ������Ͳ�����������
	 * ������Ϊ�գ���������Ϊ��
	 * ��������Ϊ�գ�������Ϊ���ҵ�ǰ���в�Ϊ�գ���������������
	 * ����������������Ϊ���ҵ�ǰ���в�Ϊ�գ���������������	
	 */
	public boolean isCompleteTree(TreeNode root)  //�ж��Ƿ�Ϊ��ȫ������
	{
		boolean b = true;//�м��Ǳ���
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
	 * ���ݶ������������򣬺����򹹽�һ��������
	 * ����Ҫ��ȷ������һ���������У���һ��һ�����ڵ㣬���������һ���Ǹ��ڵ�
	 * ������ݽڵ�λ�ÿ��Էֳ�����������
	 * ���ݴ˿��Խ������ڻ���λһ������ͣ�һ����������
	 * ��ˣ�Ҫ���õݹ�ȥʵ�֡�
	 */
	public TreeNode buildTreeBypre(int[] pre,int[] in) //������������򹹽�������
	{
		if(pre.length==0||in.length==0) {
			return null;
		}
		//���ÿ�ʼ�����±��ʾ�µ�����
		TreeNode tree = buildTreeBypre(pre,0,pre.length-1, in,0,in.length-1);
		return tree;
	}
	private TreeNode buildTreeBypre(int[] pre, int stapre, int endpre, int[] in, int stain, int endin) 
	{
		if(stapre>endpre||stain>endin) {
			return null;
		}
		//�õ�һ�����ڵ�
		TreeNode root = new TreeNode(pre[stapre]);
		for(int i=stain;i<=endin;i++) {
			/*
			 * stapre                        endpre
			 *   ��                             ��
			 *   1  , 2 , 4 ,  7   ,3,5,8, 9 , 6
			 *                 ��          
			 *           i-stain+stapre 
			 *       
			 * stain          i           endin
			 *   ��            ��            ��
			 *   4   , 7 , 2 ,1, 8 ,5,9,3, 6
			 *             ��     ��
			 *            i-1   i+1
			 */
			if(in[i]==pre[stapre]) {
				root.setLeft(buildTreeBypre(pre,stapre+1,i-stain+stapre,in,stain,i-1));
				root.setRight(buildTreeBypre(pre, i-stain+stapre+1, endpre, in, i+1, endin));
				break;//��ֹѭ�����൱��һ���Ż�
			}
		}
		return root;
	}
	public TreeNode buildTreeBypost(int[] post,int[] in) //���ݺ�������򹹽�������
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
			 *   ��            ��            ��
			 *   4   , 7 , 2 ,1, 8 ,5,9,3, 6
			 *             ��     ��
			 *            i-1   i+1
			 * 
			 * stapost                    endpost       
			 *   ��                           ��
			 *   7  , 4 , 2 , 8 ,9,5,6 , 3 , 1
			 *            ��              ��
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
	public int getHeight(TreeNode root) //��ö������ĸ߶� �ݹ鷨
	{
		if(root==null) {
			return 0;
		}
		int left = getHeight(root.getLeft());
		int right = getHeight(root.getRight());
		int height = left>right?left:right;
		return height+1;	
	}
	public int getHeight_byQueue(TreeNode root) //��ö������ĸ߶� �ǵݹ鷨
	{
		/*
		 * ���ò㼶���������������ߣ���Ҫ���㼶�����Ĺ���ԭ��
		 * �㼶������һ��һ������±�������������ܹ�֪��ʲôʱ���������һ�㣬��ô�Ϳ����ٱ��������еõ�����
		 * ��������Ҫ���㼶�������㼶������ÿ����һ���ڵ�������ڵ�������ֽڵ�������
		 * һ��ʼ�����нڵ���ֻ��һ�������Ǹ��ڵ㣬������ڵ㵯������ô��ô���ڵ���һ��ڵ���һ����ȫ�����뵽������
		 * ����queue.size() �����ܹ��õ����д�ʱ�Ľڵ�����������һ��ڵ����Ŀ
		 * ͬ����һ��Ľڵ���ȫ������������һ��ڵ���Ҳ��ȫ��������У��������ơ�
		 */
		if(root==null) return 0;
		int treeHigh=0;//	���� 
		int nextCount=1;//��δ���б�������һ��Ľڵ���
		int count=0; //��ǰ�����Ѿ������Ľڵ���  
		LinkedList<TreeNode> queue=new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty())
		{
			count++; //ÿ��ѭ�����ᵯ��һ���ڵ㣬�������Ľڵ������һ���Ľڵ��Ǳ�ʶ�Ѿ�������һ��
			TreeNode node=queue.poll();
			if(node.getLeft()!=null)
				queue.add(node.getLeft());
			if(node.getRight()!=null)
				queue.add(node.getRight());
			//��ǰ�����Ľڵ����͵�ǰ��Ľڵ������ʱ
			//��һ��Ľڵ�������
			if(count==nextCount)
			{
				nextCount=queue.size();//��ʱ�����нڵ�������Ϊ��һ���еĽڵ�����
				count=0;//����һ�����Ѿ������Ľڵ�����Ϊ0
				treeHigh++;//������һ�� ���߼�1
			}
		}
		return treeHigh;
	}
	public int getLeaf(TreeNode root) //�����Ҷ����
	{
		/*
		 * ���Ҷ�û�нڵ���ܳ�Ϊ��Ҷ
		 * ������õݹ����������
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
	 * �������һЩ�򵥵Ķ���������
	 * ��һ�´�������������
	 */
	public TreeNode findNode(TreeNode root,int x) //��ֵ������Ѱ��ĳ���ڵ�
	{
		if(root!=null) {
			if(root.getData()==x)
				return root;
			else {
				TreeNode Node;
				if(root.getLeft()!=null) {
					Node = findNode(root.getLeft(),x);
					if(Node!=null)   //ֻҪ��Ϊ�գ���˵�����ҵ���ֵ����������һ�αȽϡ�
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
	public int getNodeKNum(TreeNode root,int k) //���K��ڵ�����
	{
		/*
		 * ��������Ҫ��ȷ���ݹ�ʱ�临�ӶȾ���һ���������õĴ����������ķֲ档
		 * ����
		 */
		if(k==1) {
			if(root == null)
				return 0;
			else
				return 1;
		}
		return getNodeKNum(root.getLeft(),k-1)+getNodeKNum(root.getRight(),k-1);
	}
	//����״��ӡ������ �����ӡ
	public void V_PrintTree(TreeNode root) 
	{
		/*
		 * �����ӡ�������Ծ��ǲ㼶������
		 * ����Ҫ�ܹ���ʾ����״�����ǻ���Ҫ��������ߣ�����������������ո�
		 * Ŀǰ��û��������
		 */
		if(root == null) return;
		
	}
	//����״��ӡ������ �����ӡ
	public void H_PrintTree(TreeNode root)
	{
		/*
		 * �����ӡ����������������������ң��������ӡ
		 * ����㼶���Ա��ڿո�ֳ�����
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
