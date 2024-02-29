package Iterator;

import java.util.Iterator;

/*
 * Iterable ：故名思议，实现了这个接口的集合对象支持迭代，是可迭代的。able结尾的表示 能...样，可以做...。
   Iterator:  在英语中or结尾是都是表示 ...样的人,这里也是一样：iterator就是迭代者，我们一般叫迭代器，它就是提供迭代机制的对象，
	具体如何迭代，都是Iterator接口规范的。
 */
public class IterableDemo implements Iterable<Integer> {
	int[] arrgs = {1,2,3,4,5,6};
	/*一个集合对象要表明自己支持迭代，能有使用foreach语句的特权，就必须实现Iterable接口，表明我是可迭代的！
	 然而实现Iterable接口，就必需为foreach语句提供一个迭代器。
	 这个迭代器是用接口定义的 iterator方法提供的。也就是iterator方法需要返回一个Iterator对象。*/
	public static void main(String[] args) {
		IterableDemo demo = new IterableDemo();
		Iterator it = demo.iterator();
		//迭代器循环
		while(it.hasNext()) {
			System.out.print(it.next()+",");
		}
		System.out.println();
		//for循环
		for(Integer i : demo) {
			System.out.print(i+",");
		}
	}
	@Override
	public Iterator iterator() {
		// TODO 自动生成的方法存根
		return new IteratorDemo(new IterableDemo() );
	}
}
