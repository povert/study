package TreeNode;
//������ʵ��ڵ���
public class TreeNode {
	private int data; //�ڵ�
	private TreeNode left;//��ڵ�
	private TreeNode right;//�ҽڵ�
	public TreeNode(int data) {
		this.data = data;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
}
