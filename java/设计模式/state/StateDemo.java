package state;
/*״̬ģʽ��https://blog.csdn.net/weixin_39397471/article/details/82843404
 * ��ͼ������һ�����������ڲ�״̬�ı�ʱ�ı�������Ϊ�� ���������ƺ��޸��������ࡣ
 * ״̬ģʽ��Ҫ������ǵ�����һ������״̬���������ʽ���ڸ���ʱ�������
 * ��״̬���ж��߼�ת�Ƶ���ʾ��ͬ״̬��һϵ�����У����԰Ѹ��ӵ��ж��߼��򻯡�
 * UML��Context{ State state <>---�ۼ�----->State{Handle(Context)}
 *            Request()                      |                
 *         }                      --------------------- 
 *                               |                     |
 *                        ConcreteStateA{          ConcreteStateB{
 *                        	 Handle(Context)		    Handle(Context)
 *                        }
 *���:
 *״̬ģʽ�����ľ��ǽ������״̬�����ͬ״̬����Ϊ����, ��ͬ��״̬�Զ�����в�ͬ����Ϊ
 *   ״̬ģʽ��������ɫ��ɣ�����״̬��ɫ������״̬��ɫ�ͻ�����ɫ
 * �Ե�������Ϊ�� :  �������о�������״̬
	״̬\����	 ����	���� 	����	 	ֹͣ
	����״̬	 X	     O		 X		 X
	����״̬	 O		 X		 O		 O
	����״̬ 	 X	     X		 X		 O
	ֹͣ״̬	 O		 X		 O		 X               
 Context : Ҳ���ǵ��ݣ����ڻ�����ɫ          State: �����״̬��ɫ    
 ConcreteStateA: ���Ǿ����״̬��ɫ 
 */

abstract class State {
    
    public abstract void Handle(Context context);
}
class ConcreteStateA extends State{
	 
    @Override
    public void Handle(Context context) {
        context.setState(new ConcreteStateB()); //����A����һ��״̬��B
        
    }
 
}
class ConcreteStateB extends State{
 
    @Override
    public void Handle(Context context) {
        context.setState(new ConcreteStateA()); //����B����һ��״̬��A
    }
    
}
class Context {
    State state;
 
    public Context(State state) { //����Context�ĳ�ʼ״̬
        super();
        this.state = state;
    }
 
    public State getState() {
        return state;
    }
 
    public void setState(State state) {
        this.state = state;
        System.out.println("��ǰ״̬Ϊ"+state);
    }
    public void request(){
        state.Handle(this); //��������������ָ����һ��״̬
    }
}

public class StateDemo {
	public static void main(String[] args) {
		Context c = new Context(new ConcreteStateB());
		c.request();
	}
}