package faceQuestion;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
/*	ע:����������������ά��
	��εõ�һ���������е���λ����������������ж�����������ֵ��
	��ô��λ������������ֵ����֮��λ���м����ֵ��������������ж���ż������ֵ��
	��ô��λ������������ֵ����֮���м���������ƽ��ֵ������ʹ��Insert()���������ݲ��뵽��������
	ʹ��GetMedian()������ȡ��ǰ��ȡ���ݵ���λ����
	�ⷨһ: ���öѵ����ݽṹ
	Ϊ�˱�֤���������ݺ�ȡ��λ����ʱ��Ч�ʶ���Ч������ʹ�ô󶥶�+С���ѵ��������������㣺
	1���������е�������Ŀ��ܳ���1����������ʹ��λ��ֻ������������ѵĽ��Ӵ���
	2���󶥶ѵ��������ݶ�С��С���ѣ�����������������Ҫ��
	�ⷨ����ƽ�����������
	
 */
public class Heap {
	/*
	 * PriorityQueue��һ���������ȼ��ѵļ������ȼ����С�Ĭ������С��
	 *  �ѽ����õķ��� offer ��    poll() �Ƴ���ͷ    peek() �鿴��ͷ
	 */
	static int count = 0;
	static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	/* Ĭ����С�ѣ�ʵ���������ڹ��췽������һ���Ƚ��� */
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11, new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO �Զ����ɵķ������
			return o1.compareTo(o2);
		}
		
	});
	public static void main(String[] args) {
		
	}
	public static void insret(int num) {
		 count++;
		if ((count & 1) == 0) { // �ж�ż���ĸ�Чд��
		        if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
		            maxHeap.offer(num);
		            num = maxHeap.poll();
		        }
		        minHeap.offer(num);
		    } else {
		        if (!minHeap.isEmpty() && num > minHeap.peek()) {
		            minHeap.offer(num);
		            num = minHeap.poll();
		        }
		        maxHeap.offer(num);
		    }
	}
	public static double GetMedian() {
	 if(count==0)
	          	throw new RuntimeException("no available number!");
	        double result;
	       //����Ϊ����ʱ���󶥶ѶѶ�������λ��
	       if((count&1)==1)
	            result=maxHeap.peek();
	        else
	            result=(minHeap.peek()+maxHeap.peek())/2.0;
	        return result;
	}

}
