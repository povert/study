package adpter;
// 意图：使得原本因为接口不兼容的类可以一起工作
// uml 图
/*
 * main --->Target { Request()}        Adptee{ SpecificRequet()}         
 * 				  |								|
 * 				  |								|
 * 				<继承>----------Adapter{------<实现>
 * 							Request(){ SpecificRequet()}
 * 										 }
 * 
 */

// 客户端所需要的接口
interface Target{ void Request(); }

// 已存在但不使用的接口
class Adptee{ void SpecificRequet() {
	System.out.println("适配者中的业务代码被调用!");
	} 
}

// 类适配器
class Adapter extends Adptee implements Target{
	public void Request() {
		SpecificRequet();
	}
}
// 客户端测试
public class AdpterClass {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.Request();
	}
}
