package composite;
import java.util.*;
/*
 * 组合模式（CompositeDemo Pattern）将对象组合成树形结构以表示“部分-整体”的层次结构。
 * 		组合模式使得用户可以使用一致的方法操作单个对象和组合对象。
 * 和树很像
 */
// UML 图
/*
 * main ---->Component{ o<--------------------
 * 			add(Component compent)           |
 * 			remove(Component compent)        |
 * 			display(int i)                   |
 * 				}|                           |
 *               |                           |
 *       -----<实现>------                   |           
 *       |               |                   |
 *   Left{name       Composite{<-------------- 
 *  display(int i)		name   
 *      } 			add(Component compent)
 *   				remove(Component compent)
 *   				display(int i) }          
 */                                          

//声明一个接口用于管理和访问Component的子部件。
interface  Component {
    public void add(Component compent);
    public void remove(Component compent);
    public void display(int i);
}
// Leaf表示叶节点对象，没有子节点
class Leaf implements Component{
    private String name ;
    public Leaf(String name) {
        this.setName(name);
    }

    @Override
    //叶节点没有子节点，实现add和remove没有意义，但是这样可以消除叶节点和枝节点的层次差别，使他们具备完全一致的接口
    public void add(Component compent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component compent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void display(int i) {
        for(int m=0;m<i;m++){
            System.out.print("--");
        }
        System.out.println(this.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
// 节点
class Composite implements Component {

    private String name ;
    public Composite(String name) {
        this.setName(name);
    }
//一个子对象集合用来存储其下的枝节点和叶节点
    private List<Component> children = new ArrayList<Component>();

    @Override
    public void add(Component compent) {
        children.add(compent);
    }

    @Override
    public void remove(Component compent) {
        children.remove(compent);
    }

    @Override
    public void display(int i) {
        //显示枝节点名称
        for(int m=0;m<i;m++){
            System.out.print("--");
        }
        System.out.println(this.getName());
        ++i;
        //对子节点进行遍历
        for (Component compent : children) {
            compent.display(i);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
public class CompositeDemo {
	 public static void main(String[] args) {
	        Component root = new Composite("总公司"); 
	        root.add(new Leaf("北京总部"));
	        Composite company = new Composite("海外分公司");
	        Composite department = new Composite("上海研发中心");
	        Composite club = new Composite("研发俱乐部");
	        root.add(company);
	        root.add(department);
	        department.add(club);
	        company.add(new Leaf("人力资源部"));
	        company.add(new Leaf("财务部"));
	        department.add(new Leaf("财务部"));
	        club.add(new Leaf("羽毛球俱乐部"));
	        Leaf leaf = new Leaf("足球俱乐部");
	        club.add(leaf);
	        club.add(new Leaf("电竞俱乐部"));
	        club.remove(leaf);
	        root.display(2);
	    }
}
