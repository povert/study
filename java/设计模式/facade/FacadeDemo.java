package facade;
/* @facade 意图
 * 是一种通过为多个复杂的子系统提供一个一致的接口，而使这些子系统更加容易被访问的模式。
 * 该模式对外有一个统一接口，外部应用程序不用关心内部子系统的具体的细节，
 * 这样会大大降低应用程序的复杂度，提高了程序的可维护性。
 * 外观（Facade）模式是“迪米特法则”的典型应用。
 */

// Facade 知道哪些子系统类负责处理请求。 将客户的请求代理给适当的子系统对象。
// Facade所面对的往往是多个类或其它程序单元，通过重新组合各类及程序单元，对外提供统一的接口/界面。
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
		System.out.println("======未用外观模式开始服务=======");
		ServiceA sa = new ServiceAImpl();
    	ServiceB sb = new ServiceBImpl();
        sa.methodA();
        sb.methodB();
        
        System.out.println("======使用外观模式开始服务=======");
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
	}

}
// 子系统服务A
interface ServiceA {public void methodA();}
//子系统服务B
interface ServiceB {public void methodB();}
//子系统服务V
interface ServiceC {public void methodC();}
// 三个子系统的相应实现
class ServiceAImpl implements ServiceA{
	@Override
	public void methodA() {System.out.println("服务A服务");}
}
class ServiceBImpl implements ServiceB{
	@Override
	public void methodB() {System.out.println("服务B服务");}
}
class ServiceCImpl implements ServiceC{
	@Override
	public void methodC() {System.out.println("服务C服务");}
}
