
/*
 * 表示一个作用于某对象各元素的操作
 * 它允许在不改变各元素的前提下定义作用于这些元素的新操作
 */
// UML 图
/*
 * main ---------------------------> Visitor{
 *  |                         visit(ConcreteElementA);
 *  |                         visit(ConcreteElementB);}
 *  |                                     |
 *  |                 ------------------<实现>-----------------
 *  |                 |                                       |
 *  |       ConcreteVisitorA{                         ConcreteVisitorB{     
 *  |      visit(ConcreteElementA)                  visit(ConcreteElementA);                     
 *  |      visit(ConcreteElementB)}                 visit(ConcreteElementB);}
 *  ---------->ObjectStructure------>o Element{ accept(Visitor) }
 *                                              |
 *                            ----------------<继承>----------
 *                            |                              |
 *                      ConcreteElementA{             ConcreteVisitorB{
{visitor.visit(this)}----o accept(Visitor)              accept(Visitor)
 *                         operationA()}                operationB()} 
 *                                              
 */

import java.util.*;
public class VisitorPattern
{
    public static void main(String[] args)
    {
        ObjectStructure os=new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor=new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor=new ConcreteVisitorB();
        os.accept(visitor);
    }
}
//抽象访问者
interface Visitor
{
    void visit(ConcreteElementA element);
    void visit(ConcreteElementB element);
}
//具体访问者A类
class ConcreteVisitorA implements Visitor
{
    public void visit(ConcreteElementA element)
    {
        System.out.println("具体访问者A访问-->"+element.operationA());
    }
    public void visit(ConcreteElementB element)
    {
        System.out.println("具体访问者A访问-->"+element.operationB());
    }
}
//具体访问者B类
class ConcreteVisitorB implements Visitor
{
    public void visit(ConcreteElementA element)
    {
        System.out.println("具体访问者B访问-->"+element.operationA());
    }
    public void visit(ConcreteElementB element)
    {
        System.out.println("具体访问者B访问-->"+element.operationB());
    }
}
//抽象元素类
interface Element
{
    void accept(Visitor visitor);
}
//具体元素A类
class ConcreteElementA implements Element
{
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
    public String operationA()
    {
        return "具体元素A的操作。";
    }
}
//具体元素B类
class ConcreteElementB implements Element
{
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
    public String operationB()
    {
        return "具体元素B的操作。";
    }
}
//对象结构角色,提供一个高层接口允许访问者访问它里面的元素
class ObjectStructure
{   
    private List<Element> list=new ArrayList<Element>();   
    public void accept(Visitor visitor)
    {
        Iterator<Element> i=list.iterator();
        while(i.hasNext())
        {
            ((Element) i.next()).accept(visitor);
        }      
    }
    public void add(Element element)
    {
        list.add(element);
    }
    public void remove(Element element)
    {
        list.remove(element);
    }
}
