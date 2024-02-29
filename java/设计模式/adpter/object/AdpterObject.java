package adpter.object;

//uml 图
/*
* main --->Target { Request()}        Adptee{ SpecificRequet()}         
* 				  |								|
* 				  |								|
* 				<继承>----------Adapter{------<依赖>
* 							Request(){ SpecificRequet()}
* 										 }
* 
*/

//客户端所需要的接口
interface Target{ void Request(); }

//已存在但不使用的接口
class Adptee{ void SpecificRequet() {
	System.out.println("适配者中的业务代码被调用!");
	} 
}

//类适配器
class Adapter implements Target{
	// 创建一个待适配接口对象，通过它来访问SpecificRequet方法
	Adptee adptee = new Adptee();
	public void Request() {
		adptee.SpecificRequet();
	}
}
//客户端测试
public class AdpterObject {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.Request();
	}
}
