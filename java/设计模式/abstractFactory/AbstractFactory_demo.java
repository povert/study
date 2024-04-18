

// 意图：提供一个创建一系列对象或相互依赖的对象接口，而无需它们具体的类
// 抽象工厂与工厂区别在于工厂模式是管理一类对象创建，
// 而抽象工厂管理一类工厂，该工厂负责其负责的类创建
// uml图
/*
 * 				AbstractFactory{ <--------------------------------Client
 *  			  CreateProductA()             AbstractProductA <-----|
 *  		      CreateProductB()}                  |                |
 *     				 |                 ProductA2---<继承>--ProductA1	  |
 *     				 |                                                |
 *        ---------<继承>-----------   ProductB2---<继承>--ProductB1	  |
 *    	 |	                       |                 |                |
 * ConcreteFactory1{          ConcreteFactory2{   AbstractProductB <--|
 *  CreateProductA()           CreateProductA()
 *  CreateProductB()           CreateProductA()
 *  }|                           } |
 *   - - ->ProductA1,ProductB1       - - ->ProductA2,ProductB2
 */

// 抽象工厂接口
interface AbstractFactory{
	AbstractProductA CreateProductA();
	AbstractProductB CreateProductB();
}
// 抽象产品A
abstract class AbstractProductA{
	String name = "坦克";
}
// 产品A的具体两个类
class ProductA1 extends AbstractProductA{
	ProductA1(){
		this.name += "> 装甲坦克";
	}
}
class ProductA2 extends AbstractProductA{
	ProductA2(){
		this.name += "> 反飞机坦克";
	}
}
// 抽象产品B
abstract class AbstractProductB{
	String name = "飞机";
}
//产品B的具体两个类
class ProductB1 extends AbstractProductB{
	ProductB1(){
		this.name += "> 直升机";
	}
}
class ProductB2 extends AbstractProductB{
	ProductB2(){
		this.name += "> 战斗飞机";
	}
}


// 具体工厂1 负责生成产品
class ConcreteFactory1 implements AbstractFactory{
	@Override
	public ProductA1 CreateProductA() {
		return new ProductA1();
	}
	@Override
	public ProductB1 CreateProductB() {
		// TODO 自动生成的方法存根
		return new ProductB1();
	}
	
}
// 具体工厂2 负责生成产品
class ConcreteFactory2 implements AbstractFactory{
	@Override
	public ProductA2 CreateProductA() {
		return new ProductA2();
	}
	@Override
	public ProductB2 CreateProductB() {
		// TODO 自动生成的方法存根
		return new ProductB2();
	}
	
}

public class AbstractFactory_demo {
	public static void main(String[] args) {
		/*
		 * 该抽象工厂模式负责生成飞机和坦克，
		 * 飞机有直升机和战斗飞机，坦克有装甲坦克和反飞机坦克
		 * 工厂1负责生成装甲坦克和直升机
		 * 工厂2负责生成反飞机坦克和战斗飞机
		 */
		// 工厂1，2生成飞机和坦克
		AbstractFactory Factory1 = new ConcreteFactory1();
		AbstractFactory Factory2 = new ConcreteFactory2();
		System.out.println(Factory1.CreateProductA().name);
		System.out.println(Factory1.CreateProductB().name);
		System.out.println(Factory2.CreateProductA().name);
		System.out.println(Factory2.CreateProductB().name);
	}
}
