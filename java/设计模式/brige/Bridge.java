
// 意图：将抽象部分与其实现部分分离，使得他们都可以独立变化
// uml图
/*
 * main
 *  |
 *  --->Abstraction{ <>-imp -------------------->Implementor{OperationImp()}
 *  	 |	Operation(){ imp.OperationImp();}             |
 *  	 |			}                                     |
 * 	   <继承>                                  ---------<实现>-------
 * 		 |								      |                     |
 * 		 |                                    |                     |
 * RefinedAbstraction              ConcreteImplementorA{   ConcreteImplementorB{
 *                                    OperationImp()}            OperationImp()}
 */

// 抽象接口，可以独立变化
interface Implementor{ String OperationImp();}

// 第一个具体实现
class ConcreteImplementorA implements Implementor{
	@Override
	public String OperationImp() {
		return "独立变化部分-->ConcreteImplementorA";
	}
	
}
//第二个具体实现
class ConcreteImplementorB implements Implementor{
	@Override
	public String OperationImp() {
		return "独立变化部分-->ConcreteImplementorB";
	}
	
}

// main所需要的对象，拥有部分实现
abstract class Abstraction{
	Implementor imp;
	Abstraction(Implementor imp){
		this.imp = imp;
	}
	void Operation() {
		System.out.println("未扩充对象"+imp.OperationImp());
	}
}
//main所需要的对象，让其扩充变化部分
class RefinedAbstraction extends Abstraction{
	RefinedAbstraction(Implementor imp) {
		super(imp);
	}
	void Operation() {
		System.out.println("经过扩充后对象"+imp.OperationImp());
	}
}
public class Bridge {
	public static void main(String[] args) {
		// 未扩充对象的两种类型
		Abstraction abstractionA = new Abstraction(new ConcreteImplementorA()) {};
		Abstraction abstractionB = new Abstraction(new ConcreteImplementorB()) {};
		abstractionA.Operation();
		abstractionB.Operation();
		// 扩充后对象的两种类型
		RefinedAbstraction abstraction1 = new RefinedAbstraction(new ConcreteImplementorA());
		RefinedAbstraction abstraction2 = new RefinedAbstraction(new ConcreteImplementorB());
		abstraction1.Operation();
		abstraction2.Operation();
		}
}
