package flyweight;

import java.util.HashMap;

/*
 * �ṩ�˼��ٶ��������Ӷ�����Ӧ������Ķ���ṹ�ķ�ʽ�����ù�������Ч��֧�ִ���ϸ���ȵĶ���
 * Integer����ǵ��͵���Ԫģʽ������
 * ��һ��ҵ�񳡾�Ϊ��������ÿ����׵�ʱ��˾�еĲ��ž�����Ҫд���ձ��棬
 * ���ǿ��ܲ�ֹҪ����һ�Σ���Ҫ�ظ����档ʹ����Ԫģʽ�����Ժܺõ������������ӡ�
 */
// umlͼ
/*  FlyweightFactory{ <>-flyweights--------------------->o Flyweight{Operation()}
 *   GetFlyweight(key) o----{ if(flyweights[key])                   |
 *    	^						 return flyweights[key]             |        
 *      |                     else{ flyweights.add(                 |
 *      |                            new flyweight as A)            |
 *      |                           return A }     --------------<ʵ��>---------------
 *      |                                          |                                 |
 *      |                                   ConcreteFlyweight{              UnsharedFlyweight{
 *      |                                        Operation()                Operation()
 *      |                                        State}                          AllState}
 *      |                                           |                                |
 *    main------------------------------------------>-------------------------------->
 */
// Flyweight ����һ���ӿڣ����Խ��ܲ��������ⲿ״̬
interface Flyweight{ void Operation();}
/*ConcreteFlyweightʵ��Flyweight�ӿ�,
 * ����reportContentΪ�ⲿ���ԣ��������ⲿ�Ĵ���
 * departmentΪ�ڲ����ԣ������ⲿ״̬�ı仯���仯��
*/
class ConcreteFlyweight implements Flyweight{
    private String department;//����
    private String reportContent;//��������
    ConcreteFlyweight(String department){
    	this.department = department;
    }
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }
	@Override
	public void Operation() {
		System.out.println(department+"����=>"+"��������Ϊ��"+reportContent);
	}	
}
// ��������Flyweight����ȷ������Ĺ���Flyweight����
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
	// ����
	public int count() {
		return flyweights.size();
	}
}
public class FlyweightDemo {
	public static void main(String[] args) {
		String departments[] = {"���²���", "������", "������"};
		FlyweightFactory flyweightFactory = new FlyweightFactory();
		for (int i = 0; i < 10; i++) {
            String department = departments[(int) (Math.random() * departments.length)];
            ConcreteFlyweight manager = (ConcreteFlyweight) flyweightFactory.GetFlyweight(department);
            manager.setReportContent("��"+String.valueOf(i+1)+"�α���");
            manager.Operation();
        }
		System.out.println("===����ض��������"+flyweightFactory.count()+"=====");
	}
}
//Flyweight ��������Flyweight������Ҫ����UnsharedFlyweight����ͨ����ConcreteFlyweight������Ϊ�ӽڵ�
abstract class UnsharedFlyweight implements Flyweight{
	ConcreteFlyweight[] AllState;
	public abstract void Operation();
}
