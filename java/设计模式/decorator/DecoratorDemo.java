package decorator;
// 意图：动态地给对象添加一些额外的功能。就添加功能而言，比子类继承更加灵活
/* 装饰器模式和pyhton装饰器有很大区别，装饰器模式看起来还是会改变对象的调用方式，
 *  它看起还是利用了继承关系，java 的IO流就是这样设计的
 */
// UML图
/*
 *             Component{ Operation() } <------------------
 *                      |                                 |
 *             -------<实现>------------                  |
 *             |                       |                  |
 *ConcreteComponent{            Decorator{<>-component-----
 *		Operation()}             Operation()o-------{component.Operation()}
 *									}|
 *                       ----------<继承>-------------
 *                       |                           |
 *             ConcreteDecoratorA{            ConcreteDecoratorB{
 *             	 Operation()                      Operation()
 *             AddedBehaviorA()}               AddedBehaviorB()}           
 */                   

// Component 定义一个对象接口，可以给这些对象动态添加职能
interface Component{ void Operation(); }

// ConcreteComponent 具体对象继承并实现Component接口，需要添加职能的对象
class ConcreteComponent implements Component{
	String name;
	ConcreteComponent(String name){ this.name=name;}
	@Override
	public void Operation() {
		System.out.println("我是"+name);
	}
}

// 抽象装饰者，维护一个Component对象，并定义一个Component接口一致的接口来保持透明性
abstract class Decorator implements Component{
	Component component;
	@Override
	public void Operation(){
		component.Operation();
	}
}

// 具体装饰者A
class ConcreteDecoratorA extends Decorator{
	ConcreteDecoratorA(Component component){
		this.component = component;
	}
	// 添加的方法
	public String AddedBehaviorA() {
		return "被AddedBehaviorA装饰了";
	}
	@Override
	public void Operation(){
		super.Operation();
		System.out.println("调用后装饰："+AddedBehaviorA());
	}
}
//具体装饰者B
class ConcreteDecoratorB extends Decorator{
	ConcreteDecoratorB(Component component){
		this.component = component;
	}
	// 添加的方法
	public String AddedBehaviorB() {
		return "被AddedBehaviorB装饰了";
	}
	@Override
	public void Operation(){
		System.out.println("调用前装饰："+AddedBehaviorB());
		super.Operation();
	}
}
public class DecoratorDemo  {
	public static void main(String[] args) {
		// 创建被装饰的对象
		ConcreteComponent con = new ConcreteComponent("房子");
		// 创建两个装饰者装饰对象
		ConcreteDecoratorA decoratorA = new ConcreteDecoratorA(con);
		ConcreteDecoratorB decoratorB = new ConcreteDecoratorB(con);
		ConcreteDecoratorB decoratorc = new ConcreteDecoratorB(decoratorA);
		// 装饰后结果
		System.out.println("=============未装饰===========");
		con.Operation();
		System.out.println("================A=============");
		decoratorA.Operation();
		System.out.println("================B=============");
		decoratorB.Operation();
		System.out.println("================C=============");
		decoratorc.Operation();
		
	}
}
