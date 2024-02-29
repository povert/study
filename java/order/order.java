package order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 * 十大排序算法 
 * 动图演示 ：https://blog.csdn.net/rocling/article/details/82832998#1%E3%80%81%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F%EF%BC%88Bubble%20Sort%EF%BC%89
 */
public class order {
	public static void main(String[] args) {
		int[] a = {11,5,99,3,0,6,-3,3,19,2,4,3};
		int[] b = HeapSort(a);
		for(int i=0;i<b.length;i++) {
			System.out.print(b[i]+" ");
		}
		
	}
	//冒泡排序  最简单的排序方法
	public static int[] BubbleSort(int[] a) {
		/*
		 * 简单来说就是让最大的数或最小的数慢慢向后走
		 * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
		 */
		//第一层循环是让大数一个个向后冒出来
		for(int i=0;i<a.length;i++) 
			//第二层循环是找出当前没有排过的大数
			for(int j=0;j<a.length-1-i;j++) 
				//如果前面的数比后面的数大，这两个数交换
				//这样一个来回，最大的数就慢慢浮到最后面了。
				if(a[j] > a[j+1]) 
				{int temp = a[j];a[j] = a[j+1];a[j+1] = temp;}
		return a;
	}
	//选择排序  最稳定的排序方法，无论什么情况都是O(n^2)适用于小规模数据
	public static int[] SelectionSort(int[] a) {
		/*
		 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
		 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，
		 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
		 */
		//第一层循环是要存放的位置
		for(int i = 0; i<a.length; i++) {
			//第二次循环是找到未排序中最小的元素
			int k = i;//标识当前最大的元素下标
			for(int j = i; j<a.length-1; j++) {
				if(a[k] > a[j+1])
					k = j+1;
			}
			//如果最小的元素不是当前元素，交换成最小的元素
			if(k!=i) 
			{int temp = a[i];a[i] = a[k];a[k] = temp;}
		}	
		return a;
	}
	//插入排序  比较好记好用的算法
	public static int[] InsertionSort(int[] a) {
		/*
		 * 它的工作原理是通过构建有序序列，对于未排序数据
		 * 在已排序序列中从后向前扫描，找到相应位置并插入。
		 * 最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
		 */
		//第一层循环是构建有序序列 
		for(int i = 1; i<a.length; i++) {
			//第二层循环是向里面插入数据
			//这个是结合冒泡插入
			for(int j = i; j>0; j--)
				if(a[j] < a[j-1])
				{int temp = a[j];a[j] = a[j-1]; a[j-1] = temp;}
				else break;
				/*
				//这个是利用下标判断插入位置
		       int current = a[i];  //当前要插入的值
		       int index = i;   // 记录要插入的位置
		       while(index>0&&current<a[index-1]){
		       		//不是正确的插入位置,这个值向前面移动一位
		       		a[index] = a[index-1];
		       		index--;
		       }
		       a[index] = current;  //插入值
		      */
		}
		return a;
	}
	//希尔排序 一种改进后的插入排序算法
	public static int[] ShellSort(int[] a) {
		/*
		最佳情况：T(n) = O(nlog2 n)  最坏情况：T(n) = O(nlog2 n)  平均情况：T(n) =O(nlog2n)　
		 */
		int gap = a.length / 2; //设置增量
		while(gap > 0) {
			//这里对分组的进行插入排序
			//gap对应组数，i = gap 是因为每组的第一个数是不需要插入，从每组的第二个数开始插入
			for(int i = gap; i<a.length; i++) {
				int temp = a[i]; //标将当前要插入的数
				int index = i;  //标将要插入的位置
				//index>i-gap;因为i-gap是这个分组的第一个数
				while(index>i-gap && temp < a[index-gap]) {
					//插入的数和它对应组里的数字对比
					a[index] = a[index-gap];
					index -= gap;
				}
				a[index] = temp;	
			}
			gap/=2;
		}
		return a;
	}
	//归并排序  需要额外存储空间 小规模数据表现不好，适合大规模数据
	public static int[] Merge(int[] a) {
		/*
		 * 归并排序是将序列逐渐分割成左右两部分，保证左右部分有序，然后拼成一个整体有序序列
		 最佳情况：T(n) = O(n)  最差情况：T(n) = O(nlogn)  平均情况：T(n) = O(nlogn)
		 */
		if(a.length<2) return a; //如果不够分，就返回这个序列
		int mid = a.length/2; //定义分割的中点
		int[] left = Arrays.copyOfRange(a, 0, mid); 
		//Arrays是数组操作类 copyOfRange复制数组,左闭右开
		int[] right = Arrays.copyOfRange(a, mid, a.length);
		return MergeSort(Merge(left), Merge(right));
	}
	public static int[] MergeSort(int[] left, int[] right) {
		int[] a = new int[left.length+right.length];
		//这里就开始进行把有序的两个数组依大小填入结果数组中去
		for(int index = 0, i = 0, j =0; index < a.length;index++) {
			if(i>=left.length)
				a[index] = right[j++];
			else if(j>=right.length)
				a[index] = left[i++];
			else if(left[i]>right[j])
				a[index] = right[j++];
			else a[index] = left[i++];
		}
		return a;
	}
	//快速排序  和归并一样，利用分治治法解决问题 
	public static int[] QuickSort(int[] a, int start, int end) {
		/*
		 * 工作原理：从数列中挑出一个元素，称为 “基准”（pivot）；
		 * 重新排序数列，所有元素比基准值小的摆放在基准前面，
		 * 所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
		 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
		 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
		 */
		if(a.length < 1 || start < 0 || end >= a.length || start > end) return null;
		//得到基数正确的索引下标
		int index = partition(a, start, end);
		if(index > start)
			QuickSort(a, start, index-1);
		if(index < end)
			QuickSort(a, index+1, end);
		return a;
	}
	public static int partition(int[] a, int start, int end) {
		int pivot = a[start]; //定义基准数据
		while(start < end) {
			//当队尾元素大于基准数据时，向前挪动队尾元素
			while(start < end && a[end]>=pivot) end--;
			//如果队尾元素小于基准，将其赋值给队首元素
			a[start] = a[end];
			// 当队首元素小于等于基准数据时,向前挪动指针
			while(start < end && a[start]<=pivot) start++;
			// 当队首元素大于基准数据时,需要将其赋值给end
            a[end] = a[start];
		}
		// 跳出循环时start和end相等,此时的start或end就是基准数据的正确索引位置
        // 将正确索引位置值赋值给基准数据
        a[end] = pivot;
		return end;
	}
	//堆排序   利用堆的数据结构
	public static int[] HeapSort(int[] a) {
		/*
		 * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。
		 * 堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
		 * 对于一个数组构建成最大堆，对于结点位于数组第i个位置，其左子叶位置位于数组第2i个位置，右子树为2i+1
		 * 堆排序主要分为三步：
		 * 		第一步，给定数组，构建成一个最大堆
		 * 		第二步，将根节点(也就是最大值)于末尾值交换，此时新的最大堆长度减一
		 * 		第三步，维护这个最大堆(算法的核心)，让最大推重新有序，重复以上步骤，直到堆长度为1；
		 */
		int len = a.length; //定义数组长度
		BuildMaxHeap(a, len);
		while(len > 0) {
			// 交换根节点于末尾值
			int temp = a[len-1]; a[len-1] = a[0]; a[0] = temp;
			len--;
			//再次维护最大堆
			MaxHeaplfy(a, 0, len);
		}
		return a;
	}
	public static void BuildMaxHeap(int[] a,int size) {
		/*
		 * 建立最大堆  要从最下端开始维护
		 * 为什么从最下端开始维护是因为，维护算法是基于左子树于右子树能够成为最大堆，开始递归维护，
		 * 			保证根节点大于左子叶于右子叶，如果根节点于某个子叶交换，这从此子叶开始再次维护
		 */
		//从最下端的非叶子结点开始维护最大堆
		for(int i = (size-1)/2;i>=0;i--) {
			MaxHeaplfy(a, i, size);
		}
	}
	public static void MaxHeaplfy(int[] a, int i, int size) {
		/*
		 * 最大堆的维护算法，核心，理解记忆
		 */
		int maxindex = i; //定义最大值的下标
		//如果左子叶存在且大于根结点 
		if(2*(i+1)<=size&&a[2*(i+1)-1]>a[maxindex])
			maxindex = 2*i+1;
		//如果右子叶存在且大于根节点
		if(2*(i+1)+1<=size&&a[2*(i+1)]>a[maxindex])
			maxindex = 2*(i+1);
		//递归往下维护
		if(maxindex!=i) {
			int temp = a[i]; a[i] = a[maxindex]; a[maxindex] = temp;
			MaxHeaplfy(a, maxindex, size);
		}
	}
	//计数排序  比较简单，易于理解的,但不适用于数值差距悬殊的数值
	public static int[] CountingSort(int[] a) {
		/*
		 * 计数排序(Counting sort)是一种稳定的排序算法。计数排序使用一个额外的数组C，
		 * 其中第i个元素是待排序数组A中值等于i的元素的个数。然后根据数组C来将A中的元素排到正确的位置。它只能对整数进行排序。
	 算法描述：
	 找出待排序的数组中最大和最小的元素；
	统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
	对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
	反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
		 */
		int max = a[0];//定义数组中最大值
		int min = a[0];//定义数组中最小值
		//找出最大值于最小值
		for(int i = 1; i<a.length; i++) {
			if(a[i]>max) max = a[i];
			if(a[i]<min) min = a[i];
		}
		int [] count = new int[max-min+1]; //创建计数数组 并初始化为0;
		Arrays.fill(count, 0);
		int bais = 0 - min; //定义0的差值基数
		//开始计数
		for(int i = 0; i<a.length; i++) count[a[i]+bais]++;
		//反向填充目标数组
		int i = 0, j = 0;
		while(i < a.length) {
			if(count[j]!=0) {
				a[i] = j - bais;
				i++;
				count[j]--;
			}else j++;
		}
		return a;
	}
	//桶排序 是计数排序的升级版
	public static int[] BucketSort(int[] a) {
		/*
		 * 工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，
		 * 	每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序
		 * 简单来说，原本数组计数，我换成集合，链表，可以存储更多信息
		 */
		int max = a[0];//定义数组中最大值
		int min = a[0];//定义数组中最小值
		//找出最大值于最小值
		for(int i = 1; i<a.length; i++) {
			if(a[i]>max) max = a[i];
			if(a[i]<min) min = a[i];
		}
		int bucketsize = 5; //设置桶的大小，可以更具需求设置
		int backetCount = (max - min)/bucketsize +1; //计算桶的数量
		ArrayList<LinkedList<Integer>> Bucket = new ArrayList<>(); //新建一个桶的集合
		for(int i = 0; i < backetCount; i++) {
			// 新建一个桶，并将其添加到桶的集合中去。
            // 由于桶内元素会频繁的插入，所以选择 LinkedList 作为桶的数据结构
			// 桶数量大小已经如何确定把数据放入那个桶中可以更加实际需要判断或自己写算法计算
            Bucket.add(new LinkedList<Integer>());
		}
		//将所有的数据放入桶中并完成排序
		for(int data : a) {
			//计算应该放入那个桶中，可以自己写一个算法判断
			int index = (data - min) / bucketsize;
			//桶内用插入算法排序
			Insert(Bucket.get(index),data);
		}
		// 将桶中元素全部取出来
        int index = 0;
        for (LinkedList<Integer> bucket : Bucket) {
            for (int data : bucket) {
                a[index++] = data;
            }
        }
		return a;
	}
	private static void Insert(LinkedList<Integer> bucket, int data) {
		/*Iterator和ListIterator的区别是什么？
		 * Iterator可用来遍历Set和List集合，但是ListIterator只能用来遍历List。
		 * Iterator对集合只能是前向遍历，ListIterator既可以前向也可以后向。
		 * ListIterator实现了Iterator接口，并包含其他的功能，比如：增加元素，替换元素，获取前一个和后一个元素的索引，等等。
		 */
		ListIterator<Integer> it = bucket.listIterator(); //利用迭代器作为下标
		boolean inserFlag = true; //作为判断是否插入的标记
		while(it.hasNext()) {
			if(data<it.next()) {
				it.previous(); //把迭代器位置向前偏移一位
				it.add(data); //插入数据刀当前位置
				inserFlag = false; //标记插入
				break;
			}
		}
		//如果没有插入，证明数据是最大的，插入到链表末端
		if(inserFlag) bucket.add(data); 
	}
	//基数排序 了解即可，适用性不强  类比桶排序算法
	public static int[] RadixSort(int[] a) {
		/*算法描述
		 * 取得数组中的最大数，并取得位数；
		 * a为原始数组，从最低位开始取每个位组成radix数组；
		 * 对radix进行计数排序（利用计数排序适用于小范围数的特点）；
		 */
		return a;
	}
}
