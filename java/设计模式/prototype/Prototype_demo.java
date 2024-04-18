

import java.util.ArrayList;
import java.util.List;

// 意图：用原型实例化指定创建的种类，并通过负责这些对象创建新的对象
// 原型模式与单例模式相对应，分为浅克隆与深克隆
// uml 图 与单例模式相似，没必要可以记
/*
 *                       Prototype{ clone() }
 *                             |
 *                 ----------<实现>--------
 *                 |                      |       
 *            DeepPrototype           ClonPrototype      
 */
// 声明一个有复制自身方法接口
interface Prototype{ Object Clone(); }
// 深克隆
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
// 浅克隆
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
		// 声明等待克隆对象
		DeepPrototype deep = new DeepPrototype(list,"deep");
		ClonPrototype clone = new ClonPrototype(list, "clone");
		// 克隆原型
		DeepPrototype deep1 = deep.Clone();
		ClonPrototype clone1 = clone.Clone();
		// 比较
		System.out.println("深克隆对象比较" + String.valueOf(deep == deep1));
		System.out.println("深克隆比较基本数据类型:" + String.valueOf(deep1.text == deep.text));
		System.out.println("深克隆比较引用数据类型:" + String.valueOf(deep1.list == deep.list));
		System.out.println("浅克隆对象比较" + String.valueOf(clone1 == clone));
		System.out.println("浅克隆比较基本数据类型:" + String.valueOf(clone1.text == clone.text));
		System.out.println("浅克隆比较引用数据类型:" + String.valueOf(clone1.list == clone.list));
	}
}
