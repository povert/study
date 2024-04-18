
// 意图：定义一种一对多的依赖关系，当一个对象的状态发生改变，所有依赖它的对象能够得到通知并更新
// uml图
/*
 * Subject{ --------observers------------------------>o Observer{ Update()}
 * Attach(Observer)                                          |
 * Detach(Observer)                                          |
 * Notify(){for ob : observers{ob.update()}}                 |
 *   }	  |                                                <实现>
 *   	  |                                                  |
 *   	<实现>                               	             |
 *        |                                                  |
 * ConcreteSubject{ <-----------------------subject---ConcreteObserver{    
 *  subjectState                                         observerstate
 *  GetState(){ return subjectState}                     Update(){  
 *  SetState(){ Notify() }                         observerstate=subject.GetState()
 *                                                           } }
 */

import java.util.ArrayList;
import java.util.List;


// 定义被观察者对象接口
interface Subject{
	void Attache(Observer obs); //添加订阅
	void Detach(Observer obs); //删除订阅
	void Notify(); //通知所有观察者更新
}

// 定义观察者接口
interface Observer{ void Update();}

// 具体被观察者对象
class ConcreteSubject implements Subject{
	private List<Observer> observers = new ArrayList<Observer>();
	protected String subjectState = "";
	@Override
	public void Attache(Observer obs) {observers.add(obs);}
	@Override
	public void Detach(Observer obs) {observers.remove(obs);}
	@Override
	public void Notify() {for(Observer ob : observers){ob.Update();}}
	// 改变状态并通知所有观察者
	public void SetState(String state) {
		subjectState = state;
	}
	// 获取被观察者的状态
	public String GetState() { return subjectState;}
}

// 具体观察者对象
class ConcreteObserver implements Observer{
	String observerState;
	String name;
	ConcreteSubject subject;
	public ConcreteObserver(String name, ConcreteSubject subject) {
		this.name=name;this.subject=subject;subject.Attache(this);
		}
	@Override
	public void Update() {
		observerState = subject.GetState(); 
		System.out.println(name+"收到更新"+"【更新内容为"+observerState+"】");
	}
}

public class Observer_demo {
	public static void main(String[] args) {
		// 创建被观察者
		ConcreteSubject subject = new ConcreteSubject();
		// 创建观察者对象，并设置观察对象
		ConcreteObserver ob1 = new ConcreteObserver("ob1", subject);
		ConcreteObserver ob2 = new ConcreteObserver("ob2", subject);
		System.out.println("-------初始状态----------");
		subject.SetState("状态为2");
		//通知变化
		subject.Notify(); 
		System.out.println("-------状态查看----------");
		System.out.println("subject:" + subject.subjectState);
		System.out.println("ob1:" + ob1.observerState);
		System.out.println("ob2:" + ob2.observerState);
		
		System.out.println("--------ob1取消订阅----------");
		subject.Detach(ob1);
		subject.SetState("状态为4");
		//通知变化
		subject.Notify(); 
		System.out.println("-------状态查看----------");
		System.out.println("subject:" + subject.subjectState);
		System.out.println("ob1:" + ob1.observerState);
		System.out.println("ob2:" + ob2.observerState);
	}
}
