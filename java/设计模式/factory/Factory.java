package factory;
// ��ͼ����һ����󴴽�����ָ���������������ʵ������һ����
// umlͼ
/*
 * 		Product                          Creator{ FactoryMethod()
 * 		   |									  getProduct()}
 * 		   |
 *       <�̳�>
 *         |
 *   ConcreteProduct <- - - - - - - ConcreleCreator{ FactoryMethod()}
 */

// һ���Ʒ
interface Product{}
// ��Ʒ�ľ������
class ConcreteProduct implements Product{
	String name;
	void setName(String name) {this.name=name;}
	@Override
	public String toString() {
		return "ConcreteProduct [name=" + name + "]";
	}
}

// ������Ĺ���
abstract class Creator{
	abstract Product getProduct();
}

// ���幤����1
class ConcreleCreator1 extends Creator{
	@Override
	ConcreteProduct getProduct() {
		ConcreteProduct product = new ConcreteProduct();
		product.setName("ConcreleCreator1�����Ķ���");
		return product;
	}
}

class ConcreleCreator2 extends Creator{
	@Override
	ConcreteProduct getProduct() {
		ConcreteProduct product = new ConcreteProduct();
		product.setName("ConcreleCreator2�����Ķ���");
		return product;
	}
}
public class Factory {
	public static void main(String[] args) {
		// ����������
		Creator creator1 = new ConcreleCreator1();
		Creator creator2 = new ConcreleCreator2();
		System.out.println(creator1.getProduct());
		System.out.println(creator2.getProduct());
	}
}
