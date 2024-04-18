

import java.util.HashMap;

/*
 * 提供了减少对象数量从而改善应用所需的对象结构的方式，运用共享技术有效地支持大量细粒度的对象
 * Integer类就是典型的享元模式的例子
 * 以一个业务场景为例，假设每年年底的时候公司中的部门经理都需要写年终报告，
 * 但是可能不止要报告一次，需要重复报告。使用享元模式，可以很好的完成上面的例子。
 */
// uml图
/*  FlyweightFactory{ <>-flyweights--------------------->o Flyweight{Operation()}
 *   GetFlyweight(key) o----{ if(flyweights[key])                   |
 *    	^						 return flyweights[key]             |        
 *      |                     else{ flyweights.add(                 |
 *      |                            new flyweight as A)            |
 *      |                           return A }     --------------<实现>---------------
 *      |                                          |                                 |
 *      |                                   ConcreteFlyweight{              UnsharedFlyweight{
 *      |                                        Operation()                Operation()
 *      |                                        State}                          AllState}
 *      |                                           |                                |
 *    main------------------------------------------>-------------------------------->
 */
// Flyweight 描述一个接口，可以接受并作用于外部状态
interface Flyweight{ void Operation();}
/*ConcreteFlyweight实现Flyweight接口,
 * 其中reportContent为外部属性，依赖于外部的传入
 * department为内部属性，不随外部状态的变化而变化。
*/
class ConcreteFlyweight implements Flyweight{
    private String department;//部门
    private String reportContent;//报告内容
    ConcreteFlyweight(String department){
    	this.department = department;
    }
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }
	@Override
	public void Operation() {
		System.out.println(department+"报告=>"+"报告内容为："+reportContent);
	}	
}
// 创建管理Flyweight对象，确保合理的共享Flyweight对象
class FlyweightFactory {
	HashMap<String, Flyweight> flyweights = new HashMap<>();
	public Flyweight GetFlyweight(String key) {
		if (flyweights.get(key)!= null) 
			return flyweights.get(key);
		else {
			ConcreteFlyweight A = new ConcreteFlyweight(key);
			flyweights.put(key,A);
			return A;
		}
	}
	// 计数
	public int count() {
		return flyweights.size();
	}
}
public class FlyweightDemo {
	public static void main(String[] args) {
		String departments[] = {"人事部门", "财务部门", "管理部门"};
		FlyweightFactory flyweightFactory = new FlyweightFactory();
		for (int i = 0; i < 10; i++) {
            String department = departments[(int) (Math.random() * departments.length)];
            ConcreteFlyweight manager = (ConcreteFlyweight) flyweightFactory.GetFlyweight(department);
            manager.setReportContent("第"+String.valueOf(i+1)+"次报告");
            manager.Operation();
        }
		System.out.println("===对象池对象计数："+flyweightFactory.count()+"=====");
	}
}
//Flyweight 并非所有Flyweight对象都需要共享，UnsharedFlyweight对象通常将ConcreteFlyweight对象作为子节点
abstract class UnsharedFlyweight implements Flyweight{
	ConcreteFlyweight[] AllState;
	public abstract void Operation();
}
