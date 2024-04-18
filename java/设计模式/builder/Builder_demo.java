
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
// 意图，将一个对象的构建与其表示分离，使得对象构建过程可以创建不同表示
// uml图
/*
 *  Director{ -------builder--------------------->Builder{ BuildPart() }
 *   Construct(){ builder.BuildPart() }                  |
 *   }												   <实现>
 *                                                       |
 *                   Product ------------------>ConcreteBuilder{
 *                                                BuildPart()
 *                                                GetResult()
 *                                                }                                                                          
 */
// 具体产品，一般是实体对象
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

// 指挥者管理构造器构造，可以管理多个构造器
class Director{
	Builder builder;
	Director(Builder builder){
		this.builder = builder;
	}
	Product Construct() {
		return builder.BuildPart();
	}
}
// 构造器接口
interface Builder{ Product BuildPart();}

// 具体构造器
class ConcreteBuilder implements Builder{
	// 这里面可以用xml注释加反射获取对象注入值
	public Product GetResult() {
		Product product = null;
		try {
			Class<?> classproduct = Class.forName("builder.Product");
			product = (Product) classproduct.newInstance();
			// 反射获取所有字段，然后调用set+字段首字母大写方法赋值
			Field[] fields = classproduct.getDeclaredFields();
			 //构造生成对应参数map 可以通过xml传入进去
			Map<String, Class> map1 = new HashMap<String, Class>();
			Map<String, Object> map2 = new HashMap<String, Object>();
			map1.put("age", int.class);
			map1.put("name", String.class);
			map2.put("age", 12);
			map2.put("name", "张三");
			for(Field f : fields) {
				String setter = "set" + firstchartoUp(f.getName());
				Method method = classproduct.getMethod(setter,map1.get(f.getName()));
				method.invoke(product,map2.get(f.getName()));
				//过滤jacoco编译期间加入的 JacocoData 字段			
			}
			// 理解jacoco 引起反射异常
		/*
		 * Eclipse中的Coverage 插件是使用jacoco来统计单元测试的代码覆盖率，
		 * 会在类中加入 JacocoData成员变量，
		 * 会导致反射循环成员变量进而拼凑set或者get方法时抛出NoSuchMethodException。
		 */
//			Method method1 = classproduct.getMethod("setAge", int.class);
//			method1.invoke(product, 11);
//			Method method2 = classproduct.getMethod("setName", String.class);
//			method2.invoke(product, "张三");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	// 首字母大写
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
		// 创建构造器
		ConcreteBuilder concreteBuilder = new ConcreteBuilder();
		// 指挥者
		Director director = new Director(concreteBuilder);
		Product product = director.Construct();
		System.out.println(product.toString());
	}
}
