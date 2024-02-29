package brige;
// ��ͼ�������󲿷�����ʵ�ֲ��ַ��룬ʹ�����Ƕ����Զ����仯
// umlͼ
/*
 * main
 *  |
 *  --->Abstraction{ <>-imp -------------------->Implementor{OperationImp()}
 *  	 |	Operation(){ imp.OperationImp();}             |
 *  	 |			}                                     |
 * 	   <�̳�>                                  ---------<ʵ��>-------
 * 		 |								      |                     |
 * 		 |                                    |                     |
 * RefinedAbstraction              ConcreteImplementorA{   ConcreteImplementorB{
 *                                    OperationImp()}            OperationImp()}
 */

// ����ӿڣ����Զ����仯
interface Implementor{ String OperationImp();}

// ��һ������ʵ��
class ConcreteImplementorA implements Implementor{
	@Override
	public String OperationImp() {
		return "�����仯����-->ConcreteImplementorA";
	}
	
}
//�ڶ�������ʵ��
class ConcreteImplementorB implements Implementor{
	@Override
	public String OperationImp() {
		return "�����仯����-->ConcreteImplementorB";
	}
	
}

// main����Ҫ�Ķ���ӵ�в���ʵ��
abstract class Abstraction{
	Implementor imp;
	Abstraction(Implementor imp){
		this.imp = imp;
	}
	void Operation() {
		System.out.println("δ�������"+imp.OperationImp());
	}
}
//main����Ҫ�Ķ�����������仯����
class RefinedAbstraction extends Abstraction{
	RefinedAbstraction(Implementor imp) {
		super(imp);
	}
	void Operation() {
		System.out.println("������������"+imp.OperationImp());
	}
}
public class Bridge {
	public static void main(String[] args) {
		// δ����������������
		Abstraction abstractionA = new Abstraction(new ConcreteImplementorA()) {};
		Abstraction abstractionB = new Abstraction(new ConcreteImplementorB()) {};
		abstractionA.Operation();
		abstractionB.Operation();
		// �����������������
		RefinedAbstraction abstraction1 = new RefinedAbstraction(new ConcreteImplementorA());
		RefinedAbstraction abstraction2 = new RefinedAbstraction(new ConcreteImplementorB());
		abstraction1.Operation();
		abstraction2.Operation();
		}
}
