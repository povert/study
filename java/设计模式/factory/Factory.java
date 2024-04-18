
// 意图：将一类对象创建交由指定工厂，让其决斗实例化哪一个类
// uml图
/*
 * 		Product                          Creator{ FactoryMethod()
 * 		   |									  getProduct()}
 * 		   |
 *       <继承>
 *         |
 *   ConcreteProduct <- - - - - - - ConcreleCreator{ FactoryMethod()}
 */

// 一类产品
interface Product{}
// 产品的具体对象
class ConcreteProduct implements Product{
	String name;
	void setName(String name) {this.name=name;}
	@Override
	public String toString() {
		return "ConcreteProduct [name=" + name + "]";
	}
}

// 抽象类的工厂
abstract class Creator{
	abstract Product getProduct();
}

// 具体工厂类1
class ConcreleCreator1 extends Creator{
	@Override
	ConcreteProduct getProduct() {
		ConcreteProduct product = new ConcreteProduct();
		product.setName("ConcreleCreator1创建的对象");
		return product;
	}
}

class ConcreleCreator2 extends Creator{
	@Override
	ConcreteProduct getProduct() {
		ConcreteProduct product = new ConcreteProduct();
		product.setName("ConcreleCreator2创建的对象");
		return product;
	}
}
public class Factory {
	public static void main(String[] args) {
		// 创建工厂类
		Creator creator1 = new ConcreleCreator1();
		Creator creator2 = new ConcreleCreator2();
		System.out.println(creator1.getProduct());
		System.out.println(creator2.getProduct());
	}
}
