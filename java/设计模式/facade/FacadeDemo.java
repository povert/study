package facade;
/* @facade ��ͼ
 * ��һ��ͨ��Ϊ������ӵ���ϵͳ�ṩһ��һ�µĽӿڣ���ʹ��Щ��ϵͳ�������ױ����ʵ�ģʽ��
 * ��ģʽ������һ��ͳһ�ӿڣ��ⲿӦ�ó����ù����ڲ���ϵͳ�ľ����ϸ�ڣ�
 * �������󽵵�Ӧ�ó���ĸ��Ӷȣ�����˳���Ŀ�ά���ԡ�
 * ��ۣ�Facade��ģʽ�ǡ������ط��򡱵ĵ���Ӧ�á�
 */

// Facade ֪����Щ��ϵͳ�ฺ�������� ���ͻ������������ʵ�����ϵͳ����
// Facade����Ե������Ƕ�������������Ԫ��ͨ��������ϸ��༰����Ԫ�������ṩͳһ�Ľӿ�/���档
class Facade {
    ServiceA sa;
    ServiceB sb;
    ServiceC sc;
    public Facade() {
        sa = new ServiceAImpl();
        sb = new ServiceBImpl();
        sc = new ServiceCImpl(); 
    }
    
    public void methodA() {
        sa.methodA();
        sb.methodB();
    }
    
    public void methodB() {
        sb.methodB();
        sc.methodC();
    }
    
    public void methodC() {
        sc.methodC();
        sa.methodA();
    }
}

public class FacadeDemo {
	public static void main(String[] args) {
		System.out.println("======δ�����ģʽ��ʼ����=======");
		ServiceA sa = new ServiceAImpl();
    	ServiceB sb = new ServiceBImpl();
        sa.methodA();
        sb.methodB();
        
        System.out.println("======ʹ�����ģʽ��ʼ����=======");
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
	}

}
// ��ϵͳ����A
interface ServiceA {public void methodA();}
//��ϵͳ����B
interface ServiceB {public void methodB();}
//��ϵͳ����V
interface ServiceC {public void methodC();}
// ������ϵͳ����Ӧʵ��
class ServiceAImpl implements ServiceA{
	@Override
	public void methodA() {System.out.println("����A����");}
}
class ServiceBImpl implements ServiceB{
	@Override
	public void methodB() {System.out.println("����B����");}
}
class ServiceCImpl implements ServiceC{
	@Override
	public void methodC() {System.out.println("����C����");}
}
