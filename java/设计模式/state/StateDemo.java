
/*状态模式：https://blog.csdn.net/weixin_39397471/article/details/82843404
 * 意图：允许一个对象在其内部状态改变时改变它的行为。 对象看起来似乎修改了它的类。
 * 状态模式主要解决的是当控制一个对象状态的条件表达式过于复杂时的情况。
 * 把状态的判断逻辑转移到表示不同状态的一系列类中，可以把复杂的判断逻辑简化。
 * UML：Context{ State state <>---聚集----->State{Handle(Context)}
 *            Request()                      |                
 *         }                      --------------------- 
 *                               |                     |
 *                        ConcreteStateA{          ConcreteStateB{
 *                        	 Handle(Context)		    Handle(Context)
 *                        }
 *理解:
 *状态模式，核心就是将对象的状态与对象不同状态的行为分离, 不同的状态对对象具有不同的行为
 *   状态模式由三个角色组成：抽象状态角色、具体状态角色和环境角色
 * 以电梯运行为例 :  电梯运行具有以下状态
	状态\动作	 开门	关门 	运行	 	停止
	开门状态	 X	     O		 X		 X
	关门状态	 O		 X		 O		 O
	运行状态 	 X	     X		 X		 O
	停止状态	 O		 X		 O		 X               
 Context : 也就是电梯，属于环境角色          State: 抽象的状态角色    
 ConcreteStateA: 就是具体的状态角色 
 */

abstract class State {
    
    public abstract void Handle(Context context);
}
class ConcreteStateA extends State{
	 
    @Override
    public void Handle(Context context) {
        context.setState(new ConcreteStateB()); //设置A的下一个状态是B
        
    }
 
}
class ConcreteStateB extends State{
 
    @Override
    public void Handle(Context context) {
        context.setState(new ConcreteStateA()); //设置B的下一个状态是A
    }
    
}
class Context {
    State state;
 
    public Context(State state) { //定义Context的初始状态
        super();
        this.state = state;
    }
 
    public State getState() {
        return state;
    }
 
    public void setState(State state) {
        this.state = state;
        System.out.println("当前状态为"+state);
    }
    public void request(){
        state.Handle(this); //对请求做处理并且指向下一个状态
    }
}

public class StateDemo {
	public static void main(String[] args) {
		Context c = new Context(new ConcreteStateB());
		c.request();
	}
}