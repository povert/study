package faceQuestion;
/*
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。
 *  队列中的元素为int类型
 */
import java.util.Stack;
public class Stack_Change_Queue {
	Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    /*
     * 栈和队列出的顺序是相反的，
     * 于是可以这样，入队就放在一个栈中
     * 出栈时，让入队的栈中元素出栈到另一个栈中，此时，这个栈中出栈顺序就和队列出栈顺序相同
     * 当再次入队时，就仍然放在第一个栈中，
     * 需要出栈，将第一个栈元素全压入导另一个栈，另一个栈在出栈在出栈。
     */
    public void push(int node) {
    	stack1.push(node);
    }
    public int pop() {
    	//如果两个栈都为空，则队列为空
    	if(stack1.empty()&&stack2.empty()){
    		throw new RuntimeException("Queue is empty!");
    		}
    	//出队是从第二个栈弹出，第二个栈为空，就需要把第一个栈里元素全压入到第二个栈里。
    	if(stack2.empty()){
    		while(!stack1.empty()){
    			stack2.push(stack1.pop());
    			}
    		}
    	return stack2.pop();
    	}
}
