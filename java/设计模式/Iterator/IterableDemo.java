package Iterator;

import java.util.Iterator;

/*
 * Iterable ������˼�飬ʵ��������ӿڵļ��϶���֧�ֵ������ǿɵ����ġ�able��β�ı�ʾ ��...����������...��
   Iterator:  ��Ӣ����or��β�Ƕ��Ǳ�ʾ ...������,����Ҳ��һ����iterator���ǵ����ߣ�����һ��е��������������ṩ�������ƵĶ���
	������ε���������Iterator�ӿڹ淶�ġ�
 */
public class IterableDemo implements Iterable<Integer> {
	int[] arrgs = {1,2,3,4,5,6};
	/*һ�����϶���Ҫ�����Լ�֧�ֵ���������ʹ��foreach������Ȩ���ͱ���ʵ��Iterable�ӿڣ��������ǿɵ����ģ�
	 Ȼ��ʵ��Iterable�ӿڣ��ͱ���Ϊforeach����ṩһ����������
	 ������������ýӿڶ���� iterator�����ṩ�ġ�Ҳ����iterator������Ҫ����һ��Iterator����*/
	public static void main(String[] args) {
		IterableDemo demo = new IterableDemo();
		Iterator it = demo.iterator();
		//������ѭ��
		while(it.hasNext()) {
			System.out.print(it.next()+",");
		}
		System.out.println();
		//forѭ��
		for(Integer i : demo) {
			System.out.print(i+",");
		}
	}
	@Override
	public Iterator iterator() {
		// TODO �Զ����ɵķ������
		return new IteratorDemo(new IterableDemo() );
	}
}
