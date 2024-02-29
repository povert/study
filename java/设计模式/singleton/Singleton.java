package singleton;
// ��ͼ ����֤�����һ��ʵ�������ṩһ����������ȫ�ַ��ʵ�
/*
 * ����ʽ����ģʽ���������ʱ������˳�ʼ������������ؽ���������ȡ������ٶȿ죩
 * ����ģʽ����һ����̬���󣬲������û���һ�ε���getInstanceʱ���г�ʼ��(���߳̿��ܻ�ʵ�������)
 */
// ����ģʽ
class EagerSingle{
	//�������ʱ������˳�ʼ������������ؽ���������ȡ������ٶȿ�
    private static EagerSingle single = new EagerSingle();//��̬˽�г�Ա���ѳ�ʼ��
    //˽�й��캯��
    private EagerSingle() {}
    public static EagerSingle getInstance() {//��̬������ͬ���������ʱ�ѳ�ʼ���������ж��̵߳����⣩
        return single;
    }
}

// ����ģʽ
class LazySingleton{
	private static LazySingleton instance;//��̬˽�г�Ա��û�г�ʼ��
    private LazySingleton() {}
    public static synchronized LazySingleton getInstance() {//��̬��ͬ������������
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

public class Singleton {
	public static void main(String[] args) {
		// ����ģʽ
		System.out.println(EagerSingle.getInstance() == EagerSingle.getInstance());
		// ����ģʽ
		System.out.println(LazySingleton.getInstance() == LazySingleton.getInstance());
	}	
}
