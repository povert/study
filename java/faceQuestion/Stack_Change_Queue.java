package faceQuestion;
/*
 * ������ջ��ʵ��һ�����У���ɶ��е�Push��Pop������
 *  �����е�Ԫ��Ϊint����
 */
import java.util.Stack;
public class Stack_Change_Queue {
	Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    /*
     * ջ�Ͷ��г���˳�����෴�ģ�
     * ���ǿ�����������Ӿͷ���һ��ջ��
     * ��ջʱ������ӵ�ջ��Ԫ�س�ջ����һ��ջ�У���ʱ�����ջ�г�ջ˳��ͺͶ��г�ջ˳����ͬ
     * ���ٴ����ʱ������Ȼ���ڵ�һ��ջ�У�
     * ��Ҫ��ջ������һ��ջԪ��ȫѹ�뵼��һ��ջ����һ��ջ�ڳ�ջ�ڳ�ջ��
     */
    public void push(int node) {
    	stack1.push(node);
    }
    public int pop() {
    	//�������ջ��Ϊ�գ������Ϊ��
    	if(stack1.empty()&&stack2.empty()){
    		throw new RuntimeException("Queue is empty!");
    		}
    	//�����Ǵӵڶ���ջ�������ڶ���ջΪ�գ�����Ҫ�ѵ�һ��ջ��Ԫ��ȫѹ�뵽�ڶ���ջ�
    	if(stack2.empty()){
    		while(!stack1.empty()){
    			stack2.push(stack1.pop());
    			}
    		}
    	return stack2.pop();
    	}
}
