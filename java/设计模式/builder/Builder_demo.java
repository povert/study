package builder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
// ��ͼ����һ������Ĺ��������ʾ���룬ʹ�ö��󹹽����̿��Դ�����ͬ��ʾ
// umlͼ
/*
 *  Director{ -------builder--------------------->Builder{ BuildPart() }
 *   Construct(){ builder.BuildPart() }                  |
 *   }												   <ʵ��>
 *                                                       |
 *                   Product ------------------>ConcreteBuilder{
 *                                                BuildPart()
 *                                                GetResult()
 *                                                }                                                                          
 */
// �����Ʒ��һ����ʵ�����
class Product{
	private String name;
	private int age;
	@Override
	public String toString() {
		return "Product [name=" + name + ", age=" + age + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

// ָ���߹����������죬���Թ�����������
class Director{
	Builder builder;
	Director(Builder builder){
		this.builder = builder;
	}
	Product Construct() {
		return builder.BuildPart();
	}
}
// �������ӿ�
interface Builder{ Product BuildPart();}

// ���幹����
class ConcreteBuilder implements Builder{
	// �����������xmlע�ͼӷ����ȡ����ע��ֵ
	public Product GetResult() {
		Product product = null;
		try {
			Class<?> classproduct = Class.forName("builder.Product");
			product = (Product) classproduct.newInstance();
			// �����ȡ�����ֶΣ�Ȼ�����set+�ֶ�����ĸ��д������ֵ
			Field[] fields = classproduct.getDeclaredFields();
			 //�������ɶ�Ӧ����map ����ͨ��xml�����ȥ
			Map<String, Class> map1 = new HashMap<String, Class>();
			Map<String, Object> map2 = new HashMap<String, Object>();
			map1.put("age", int.class);
			map1.put("name", String.class);
			map2.put("age", 12);
			map2.put("name", "����");
			for(Field f : fields) {
				String setter = "set" + firstchartoUp(f.getName());
				Method method = classproduct.getMethod(setter,map1.get(f.getName()));
				method.invoke(product,map2.get(f.getName()));
				//����jacoco�����ڼ����� JacocoData �ֶ�			
			}
			// ���jacoco �������쳣
		/*
		 * Eclipse�е�Coverage �����ʹ��jacoco��ͳ�Ƶ�Ԫ���ԵĴ��븲���ʣ�
		 * �������м��� JacocoData��Ա������
		 * �ᵼ�·���ѭ����Ա��������ƴ��set����get����ʱ�׳�NoSuchMethodException��
		 */
//			Method method1 = classproduct.getMethod("setAge", int.class);
//			method1.invoke(product, 11);
//			Method method2 = classproduct.getMethod("setName", String.class);
//			method2.invoke(product, "����");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	// ����ĸ��д
	public String firstchartoUp(String string) {
		StringBuffer buff = new StringBuffer(string);
		buff.deleteCharAt(0);
		buff.insert(0, (char)(string.charAt(0)-32));
		return String.valueOf(buff);
	}
	@Override
	public Product BuildPart() {
		return GetResult();
	}
	
}


public class Builder_demo {
	public static void main(String[] args) {
		// ����������
		ConcreteBuilder concreteBuilder = new ConcreteBuilder();
		// ָ����
		Director director = new Director(concreteBuilder);
		Product product = director.Construct();
		System.out.println(product.toString());
	}
}
