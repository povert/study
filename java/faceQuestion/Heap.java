

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
/*	注:数据流是由我们来维护
	如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
	那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
	那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法将数据插入到数据流，
	使用GetMedian()方法获取当前读取数据的中位数。
	解法一: 利用堆的数据结构
	为了保证插入新数据和取中位数的时间效率都高效，这里使用大顶堆+小顶堆的容器，并且满足：
	1、两个堆中的数据数目差不能超过1，这样可以使中位数只会出现在两个堆的交接处；
	2、大顶堆的所有数据都小于小顶堆，这样就满足了排序要求。
	解法二：平衡二叉搜索树
	
 */
public class Heap {
	/*
	 * PriorityQueue是一个基于优先级堆的极大优先级队列。默认是最小堆
	 *  堆建议用的方法 offer 加    poll() 移除队头    peek() 查看队头
	 */
	static int count = 0;
	static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	/* 默认最小堆，实现最大堆是在构造方法传入一个比较器 */
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11, new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO 自动生成的方法存根
			return o1.compareTo(o2);
		}
		
	});
	public static void main(String[] args) {
		
	}
	public static void insret(int num) {
		 count++;
		if ((count & 1) == 0) { // 判断偶数的高效写法
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
	       //总数为奇数时，大顶堆堆顶就是中位数
	       if((count&1)==1)
	            result=maxHeap.peek();
	        else
	            result=(minHeap.peek()+maxHeap.peek())/2.0;
	        return result;
	}

}
