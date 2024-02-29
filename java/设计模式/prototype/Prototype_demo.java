package prototype;

import java.util.ArrayList;
import java.util.List;

// ��ͼ����ԭ��ʵ����ָ�����������࣬��ͨ��������Щ���󴴽��µĶ���
// ԭ��ģʽ�뵥��ģʽ���Ӧ����Ϊǳ��¡�����¡
// uml ͼ �뵥��ģʽ���ƣ�û��Ҫ���Լ�
/*
 *                       Prototype{ clone() }
 *                             |
 *                 ----------<ʵ��>--------
 *                 |                      |       
 *            DeepPrototype           ClonPrototype      
 */
// ����һ���и����������ӿ�
interface Prototype{ Object Clone(); }
// ���¡
class DeepPrototype implements Prototype{
	public List list;
	public String text;
	DeepPrototype(List list,String text){
		this.list = list;
		this.text = text;
	}
	@Override
	public DeepPrototype Clone() {
		List li = new ArrayList<>();
		for(Object ob : list) {
			li.add(ob);
		}
		return new DeepPrototype(li,String.valueOf(text));
	}
	
}
// ǳ��¡
class ClonPrototype implements Prototype{
	public List list;
	public String text;
	ClonPrototype(List list,String text){
		this.list = list;
		this.text = text;
	}
	@Override
	public ClonPrototype Clone() {
		return new ClonPrototype(list,String.valueOf(text));
	}
	
}
public class Prototype_demo {
	public static void main(String[] args) {
		List list = new ArrayList<>();
		list.add("as");
		list.add("dfgf");
		// �����ȴ���¡����
		DeepPrototype deep = new DeepPrototype(list,"deep");
		ClonPrototype clone = new ClonPrototype(list, "clone");
		// ��¡ԭ��
		DeepPrototype deep1 = deep.Clone();
		ClonPrototype clone1 = clone.Clone();
		// �Ƚ�
		System.out.println("���¡����Ƚ�" + String.valueOf(deep == deep1));
		System.out.println("���¡�Ƚϻ�����������:" + String.valueOf(deep1.text == deep.text));
		System.out.println("���¡�Ƚ�������������:" + String.valueOf(deep1.list == deep.list));
		System.out.println("ǳ��¡����Ƚ�" + String.valueOf(clone1 == clone));
		System.out.println("ǳ��¡�Ƚϻ�����������:" + String.valueOf(clone1.text == clone.text));
		System.out.println("ǳ��¡�Ƚ�������������:" + String.valueOf(clone1.list == clone.list));
	}
}
