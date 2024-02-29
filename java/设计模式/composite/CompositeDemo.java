package composite;
import java.util.*;
/*
 * ���ģʽ��CompositeDemo Pattern����������ϳ����νṹ�Ա�ʾ������-���塱�Ĳ�νṹ��
 * 		���ģʽʹ���û�����ʹ��һ�µķ������������������϶���
 * ��������
 */
// UML ͼ
/*
 * main ---->Component{ o<--------------------
 * 			add(Component compent)           |
 * 			remove(Component compent)        |
 * 			display(int i)                   |
 * 				}|                           |
 *               |                           |
 *       -----<ʵ��>------                   |           
 *       |               |                   |
 *   Left{name       Composite{<-------------- 
 *  display(int i)		name   
 *      } 			add(Component compent)
 *   				remove(Component compent)
 *   				display(int i) }          
 */                                          

//����һ���ӿ����ڹ���ͷ���Component���Ӳ�����
interface  Component {
    public void add(Component compent);
    public void remove(Component compent);
    public void display(int i);
}
// Leaf��ʾҶ�ڵ����û���ӽڵ�
class Leaf implements Component{
    private String name ;
    public Leaf(String name) {
        this.setName(name);
    }

    @Override
    //Ҷ�ڵ�û���ӽڵ㣬ʵ��add��removeû�����壬����������������Ҷ�ڵ��֦�ڵ�Ĳ�β��ʹ���Ǿ߱���ȫһ�µĽӿ�
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
// �ڵ�
class Composite implements Component {

    private String name ;
    public Composite(String name) {
        this.setName(name);
    }
//һ���Ӷ��󼯺������洢���µ�֦�ڵ��Ҷ�ڵ�
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
        //��ʾ֦�ڵ�����
        for(int m=0;m<i;m++){
            System.out.print("--");
        }
        System.out.println(this.getName());
        ++i;
        //���ӽڵ���б���
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
	        Component root = new Composite("�ܹ�˾"); 
	        root.add(new Leaf("�����ܲ�"));
	        Composite company = new Composite("����ֹ�˾");
	        Composite department = new Composite("�Ϻ��з�����");
	        Composite club = new Composite("�з����ֲ�");
	        root.add(company);
	        root.add(department);
	        department.add(club);
	        company.add(new Leaf("������Դ��"));
	        company.add(new Leaf("����"));
	        department.add(new Leaf("����"));
	        club.add(new Leaf("��ë����ֲ�"));
	        Leaf leaf = new Leaf("������ֲ�");
	        club.add(leaf);
	        club.add(new Leaf("�羺���ֲ�"));
	        club.remove(leaf);
	        root.display(2);
	    }
}
