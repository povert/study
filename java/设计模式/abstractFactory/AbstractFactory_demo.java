package abstractFactory;

// ��ͼ���ṩһ������һϵ�ж�����໥�����Ķ���ӿڣ����������Ǿ������
// ���󹤳��빤���������ڹ���ģʽ�ǹ���һ����󴴽���
// �����󹤳�����һ�๤�����ù��������为����ഴ��
// umlͼ
/*
 * 				AbstractFactory{ <--------------------------------Client
 *  			  CreateProductA()             AbstractProductA <-----|
 *  		      CreateProductB()}                  |                |
 *     				 |                 ProductA2---<�̳�>--ProductA1	  |
 *     				 |                                                |
 *        ---------<�̳�>-----------   ProductB2---<�̳�>--ProductB1	  |
 *    	 |	                       |                 |                |
 * ConcreteFactory1{          ConcreteFactory2{   AbstractProductB <--|
 *  CreateProductA()           CreateProductA()
 *  CreateProductB()           CreateProductA()
 *  }|                           } |
 *   - - ->ProductA1,ProductB1       - - ->ProductA2,ProductB2
 */

// ���󹤳��ӿ�
interface AbstractFactory{
	AbstractProductA CreateProductA();
	AbstractProductB CreateProductB();
}
// �����ƷA
abstract class AbstractProductA{
	String name = "̹��";
}
// ��ƷA�ľ���������
class ProductA1 extends AbstractProductA{
	ProductA1(){
		this.name += "> װ��̹��";
	}
}
class ProductA2 extends AbstractProductA{
	ProductA2(){
		this.name += "> ���ɻ�̹��";
	}
}
// �����ƷB
abstract class AbstractProductB{
	String name = "�ɻ�";
}
//��ƷB�ľ���������
class ProductB1 extends AbstractProductB{
	ProductB1(){
		this.name += "> ֱ����";
	}
}
class ProductB2 extends AbstractProductB{
	ProductB2(){
		this.name += "> ս���ɻ�";
	}
}


// ���幤��1 �������ɲ�Ʒ
class ConcreteFactory1 implements AbstractFactory{
	@Override
	public ProductA1 CreateProductA() {
		return new ProductA1();
	}
	@Override
	public ProductB1 CreateProductB() {
		// TODO �Զ����ɵķ������
		return new ProductB1();
	}
	
}
// ���幤��2 �������ɲ�Ʒ
class ConcreteFactory2 implements AbstractFactory{
	@Override
	public ProductA2 CreateProductA() {
		return new ProductA2();
	}
	@Override
	public ProductB2 CreateProductB() {
		// TODO �Զ����ɵķ������
		return new ProductB2();
	}
	
}

public class AbstractFactory_demo {
	public static void main(String[] args) {
		/*
		 * �ó��󹤳�ģʽ�������ɷɻ���̹�ˣ�
		 * �ɻ���ֱ������ս���ɻ���̹����װ��̹�˺ͷ��ɻ�̹��
		 * ����1��������װ��̹�˺�ֱ����
		 * ����2�������ɷ��ɻ�̹�˺�ս���ɻ�
		 */
		// ����1��2���ɷɻ���̹��
		AbstractFactory Factory1 = new ConcreteFactory1();
		AbstractFactory Factory2 = new ConcreteFactory2();
		System.out.println(Factory1.CreateProductA().name);
		System.out.println(Factory1.CreateProductB().name);
		System.out.println(Factory2.CreateProductA().name);
		System.out.println(Factory2.CreateProductB().name);
	}
}
