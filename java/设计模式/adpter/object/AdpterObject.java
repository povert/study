package adpter.object;

//uml ͼ
/*
* main --->Target { Request()}        Adptee{ SpecificRequet()}         
* 				  |								|
* 				  |								|
* 				<�̳�>----------Adapter{------<����>
* 							Request(){ SpecificRequet()}
* 										 }
* 
*/

//�ͻ�������Ҫ�Ľӿ�
interface Target{ void Request(); }

//�Ѵ��ڵ���ʹ�õĽӿ�
class Adptee{ void SpecificRequet() {
	System.out.println("�������е�ҵ����뱻����!");
	} 
}

//��������
class Adapter implements Target{
	// ����һ��������ӿڶ���ͨ����������SpecificRequet����
	Adptee adptee = new Adptee();
	public void Request() {
		adptee.SpecificRequet();
	}
}
//�ͻ��˲���
public class AdpterObject {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.Request();
	}
}
