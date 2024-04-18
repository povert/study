
// 意图 ：保证类仅有一个实例，并提供一个访问它的全局访问点
/*
 * 饿汉式单例模式（在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快）
 * 懒汉模式声明一个静态对象，并且在用户第一次调用getInstance时进行初始化(多线程可能会实例化多个)
 */
// 饿汉模式
class EagerSingle{
	//在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快
    private static EagerSingle single = new EagerSingle();//静态私有成员，已初始化
    //私有构造函数
    private EagerSingle() {}
    public static EagerSingle getInstance() {//静态，不用同步（类加载时已初始化，不会有多线程的问题）
        return single;
    }
}

// 懒汉模式
class LazySingleton{
	private static LazySingleton instance;//静态私有成员，没有初始化
    private LazySingleton() {}
    public static synchronized LazySingleton getInstance() {//静态、同步、公开访问
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

public class Singleton {
	public static void main(String[] args) {
		// 饿汉模式
		System.out.println(EagerSingle.getInstance() == EagerSingle.getInstance());
		// 懒汉模式
		System.out.println(LazySingleton.getInstance() == LazySingleton.getInstance());
	}	
}
