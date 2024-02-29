package decorator;
// ��ͼ����̬�ظ��������һЩ����Ĺ��ܡ�����ӹ��ܶ��ԣ�������̳и������
/* װ����ģʽ��pyhtonװ�����кܴ�����װ����ģʽ���������ǻ�ı����ĵ��÷�ʽ��
 *  �������������˼̳й�ϵ��java ��IO������������Ƶ�
 */
// UMLͼ
/*
 *             Component{ Operation() } <------------------
 *                      |                                 |
 *             -------<ʵ��>------------                  |
 *             |                       |                  |
 *ConcreteComponent{            Decorator{<>-component-----
 *		Operation()}             Operation()o-------{component.Operation()}
 *									}|
 *                       ----------<�̳�>-------------
 *                       |                           |
 *             ConcreteDecoratorA{            ConcreteDecoratorB{
 *             	 Operation()                      Operation()
 *             AddedBehaviorA()}               AddedBehaviorB()}           
 */                   

// Component ����һ������ӿڣ����Ը���Щ����̬���ְ��
interface Component{ void Operation(); }

// ConcreteComponent �������̳в�ʵ��Component�ӿڣ���Ҫ���ְ�ܵĶ���
class ConcreteComponent implements Component{
	String name;
	ConcreteComponent(String name){ this.name=name;}
	@Override
	public void Operation() {
		System.out.println("����"+name);
	}
}

// ����װ���ߣ�ά��һ��Component���󣬲�����һ��Component�ӿ�һ�µĽӿ�������͸����
abstract class Decorator implements Component{
	Component component;
	@Override
	public void Operation(){
		component.Operation();
	}
}

// ����װ����A
class ConcreteDecoratorA extends Decorator{
	ConcreteDecoratorA(Component component){
		this.component = component;
	}
	// ��ӵķ���
	public String AddedBehaviorA() {
		return "��AddedBehaviorAװ����";
	}
	@Override
	public void Operation(){
		super.Operation();
		System.out.println("���ú�װ�Σ�"+AddedBehaviorA());
	}
}
//����װ����B
class ConcreteDecoratorB extends Decorator{
	ConcreteDecoratorB(Component component){
		this.component = component;
	}
	// ��ӵķ���
	public String AddedBehaviorB() {
		return "��AddedBehaviorBװ����";
	}
	@Override
	public void Operation(){
		System.out.println("����ǰװ�Σ�"+AddedBehaviorB());
		super.Operation();
	}
}
public class DecoratorDemo  {
	public static void main(String[] args) {
		// ������װ�εĶ���
		ConcreteComponent con = new ConcreteComponent("����");
		// ��������װ����װ�ζ���
		ConcreteDecoratorA decoratorA = new ConcreteDecoratorA(con);
		ConcreteDecoratorB decoratorB = new ConcreteDecoratorB(con);
		ConcreteDecoratorB decoratorc = new ConcreteDecoratorB(decoratorA);
		// װ�κ���
		System.out.println("=============δװ��===========");
		con.Operation();
		System.out.println("================A=============");
		decoratorA.Operation();
		System.out.println("================B=============");
		decoratorB.Operation();
		System.out.println("================C=============");
		decoratorc.Operation();
		
	}
}
