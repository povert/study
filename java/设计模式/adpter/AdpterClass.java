package adpter;
// ��ͼ��ʹ��ԭ����Ϊ�ӿڲ����ݵ������һ����
// uml ͼ
/*
 * main --->Target { Request()}        Adptee{ SpecificRequet()}         
 * 				  |								|
 * 				  |								|
 * 				<�̳�>----------Adapter{------<ʵ��>
 * 							Request(){ SpecificRequet()}
 * 										 }
 * 
 */

// �ͻ�������Ҫ�Ľӿ�
interface Target{ void Request(); }

// �Ѵ��ڵ���ʹ�õĽӿ�
class Adptee{ void SpecificRequet() {
	System.out.println("�������е�ҵ����뱻����!");
	} 
}

// ��������
class Adapter extends Adptee implements Target{
	public void Request() {
		SpecificRequet();
	}
}
// �ͻ��˲���
public class AdpterClass {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.Request();
	}
}
