package observer;
// ��ͼ������һ��һ�Զ��������ϵ����һ�������״̬�����ı䣬�����������Ķ����ܹ��õ�֪ͨ������
// umlͼ
/*
 * Subject{ --------observers------------------------>o Observer{ Update()}
 * Attach(Observer)                                          |
 * Detach(Observer)                                          |
 * Notify(){for ob : observers{ob.update()}}                 |
 *   }	  |                                                <ʵ��>
 *   	  |                                                  |
 *   	<ʵ��>                               	             |
 *        |                                                  |
 * ConcreteSubject{ <-----------------------subject---ConcreteObserver{    
 *  subjectState                                         observerstate
 *  GetState(){ return subjectState}                     Update(){  
 *  SetState(){ Notify() }                         observerstate=subject.GetState()
 *                                                           } }
 */

import java.util.ArrayList;
import java.util.List;


// ���屻�۲��߶���ӿ�
interface Subject{
	void Attache(Observer obs); //��Ӷ���
	void Detach(Observer obs); //ɾ������
	void Notify(); //֪ͨ���й۲��߸���
}

// ����۲��߽ӿ�
interface Observer{ void Update();}

// ���屻�۲��߶���
class ConcreteSubject implements Subject{
	private List<Observer> observers = new ArrayList<Observer>();
	protected String subjectState = "";
	@Override
	public void Attache(Observer obs) {observers.add(obs);}
	@Override
	public void Detach(Observer obs) {observers.remove(obs);}
	@Override
	public void Notify() {for(Observer ob : observers){ob.Update();}}
	// �ı�״̬��֪ͨ���й۲���
	public void SetState(String state) {
		subjectState = state;
	}
	// ��ȡ���۲��ߵ�״̬
	public String GetState() { return subjectState;}
}

// ����۲��߶���
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
		System.out.println(name+"�յ�����"+"����������Ϊ"+observerState+"��");
	}
}

public class Observer_demo {
	public static void main(String[] args) {
		// �������۲���
		ConcreteSubject subject = new ConcreteSubject();
		// �����۲��߶��󣬲����ù۲����
		ConcreteObserver ob1 = new ConcreteObserver("ob1", subject);
		ConcreteObserver ob2 = new ConcreteObserver("ob2", subject);
		System.out.println("-------��ʼ״̬----------");
		subject.SetState("״̬Ϊ2");
		//֪ͨ�仯
		subject.Notify(); 
		System.out.println("-------״̬�鿴----------");
		System.out.println("subject:" + subject.subjectState);
		System.out.println("ob1:" + ob1.observerState);
		System.out.println("ob2:" + ob2.observerState);
		
		System.out.println("--------ob1ȡ������----------");
		subject.Detach(ob1);
		subject.SetState("״̬Ϊ4");
		//֪ͨ�仯
		subject.Notify(); 
		System.out.println("-------״̬�鿴----------");
		System.out.println("subject:" + subject.subjectState);
		System.out.println("ob1:" + ob1.observerState);
		System.out.println("ob2:" + ob2.observerState);
	}
}
