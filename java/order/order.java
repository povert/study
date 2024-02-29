package order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 * ʮ�������㷨 
 * ��ͼ��ʾ ��https://blog.csdn.net/rocling/article/details/82832998#1%E3%80%81%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F%EF%BC%88Bubble%20Sort%EF%BC%89
 */
public class order {
	public static void main(String[] args) {
		int[] a = {11,5,99,3,0,6,-3,3,19,2,4,3};
		int[] b = HeapSort(a);
		for(int i=0;i<b.length;i++) {
			System.out.print(b[i]+" ");
		}
		
	}
	//ð������  ��򵥵����򷽷�
	public static int[] BubbleSort(int[] a) {
		/*
		 * ����˵����������������С�������������
		 * ��������T(n) = O(n)   ��������T(n) = O(n2)   ƽ�������T(n) = O(n2)
		 */
		//��һ��ѭ�����ô���һ�������ð����
		for(int i=0;i<a.length;i++) 
			//�ڶ���ѭ�����ҳ���ǰû���Ź��Ĵ���
			for(int j=0;j<a.length-1-i;j++) 
				//���ǰ������Ⱥ��������������������
				//����һ�����أ�����������������������ˡ�
				if(a[j] > a[j+1]) 
				{int temp = a[j];a[j] = a[j+1];a[j+1] = temp;}
		return a;
	}
	//ѡ������  ���ȶ������򷽷�������ʲô�������O(n^2)������С��ģ����
	public static int[] SelectionSort(int[] a) {
		/*
		 * ������δ�����������ҵ���С����Ԫ�أ���ŵ��������е���ʼλ�ã�
		 * Ȼ���ٴ�ʣ��δ����Ԫ���м���Ѱ����С����Ԫ�أ�
		 * Ȼ��ŵ����������е�ĩβ���Դ����ƣ�ֱ������Ԫ�ؾ�������ϡ�
		 */
		//��һ��ѭ����Ҫ��ŵ�λ��
		for(int i = 0; i<a.length; i++) {
			//�ڶ���ѭ�����ҵ�δ��������С��Ԫ��
			int k = i;//��ʶ��ǰ����Ԫ���±�
			for(int j = i; j<a.length-1; j++) {
				if(a[k] > a[j+1])
					k = j+1;
			}
			//�����С��Ԫ�ز��ǵ�ǰԪ�أ���������С��Ԫ��
			if(k!=i) 
			{int temp = a[i];a[i] = a[k];a[k] = temp;}
		}	
		return a;
	}
	//��������  �ȽϺüǺ��õ��㷨
	public static int[] InsertionSort(int[] a) {
		/*
		 * ���Ĺ���ԭ����ͨ�������������У�����δ��������
		 * �������������дӺ���ǰɨ�裬�ҵ���Ӧλ�ò����롣
		 * ��������T(n) = O(n)   ������T(n) = O(n2)   ƽ�������T(n) = O(n2)
		 */
		//��һ��ѭ���ǹ����������� 
		for(int i = 1; i<a.length; i++) {
			//�ڶ���ѭ�����������������
			//����ǽ��ð�ݲ���
			for(int j = i; j>0; j--)
				if(a[j] < a[j-1])
				{int temp = a[j];a[j] = a[j-1]; a[j-1] = temp;}
				else break;
				/*
				//����������±��жϲ���λ��
		       int current = a[i];  //��ǰҪ�����ֵ
		       int index = i;   // ��¼Ҫ�����λ��
		       while(index>0&&current<a[index-1]){
		       		//������ȷ�Ĳ���λ��,���ֵ��ǰ���ƶ�һλ
		       		a[index] = a[index-1];
		       		index--;
		       }
		       a[index] = current;  //����ֵ
		      */
		}
		return a;
	}
	//ϣ������ һ�ָĽ���Ĳ��������㷨
	public static int[] ShellSort(int[] a) {
		/*
		��������T(n) = O(nlog2 n)  ������T(n) = O(nlog2 n)  ƽ�������T(n) =O(nlog2n)��
		 */
		int gap = a.length / 2; //��������
		while(gap > 0) {
			//����Է���Ľ��в�������
			//gap��Ӧ������i = gap ����Ϊÿ��ĵ�һ�����ǲ���Ҫ���룬��ÿ��ĵڶ�������ʼ����
			for(int i = gap; i<a.length; i++) {
				int temp = a[i]; //�꽫��ǰҪ�������
				int index = i;  //�꽫Ҫ�����λ��
				//index>i-gap;��Ϊi-gap���������ĵ�һ����
				while(index>i-gap && temp < a[index-gap]) {
					//�������������Ӧ��������ֶԱ�
					a[index] = a[index-gap];
					index -= gap;
				}
				a[index] = temp;	
			}
			gap/=2;
		}
		return a;
	}
	//�鲢����  ��Ҫ����洢�ռ� С��ģ���ݱ��ֲ��ã��ʺϴ��ģ����
	public static int[] Merge(int[] a) {
		/*
		 * �鲢�����ǽ������𽥷ָ�����������֣���֤���Ҳ�������Ȼ��ƴ��һ��������������
		 ��������T(n) = O(n)  ��������T(n) = O(nlogn)  ƽ�������T(n) = O(nlogn)
		 */
		if(a.length<2) return a; //��������֣��ͷ����������
		int mid = a.length/2; //����ָ���е�
		int[] left = Arrays.copyOfRange(a, 0, mid); 
		//Arrays����������� copyOfRange��������,����ҿ�
		int[] right = Arrays.copyOfRange(a, mid, a.length);
		return MergeSort(Merge(left), Merge(right));
	}
	public static int[] MergeSort(int[] left, int[] right) {
		int[] a = new int[left.length+right.length];
		//����Ϳ�ʼ���а������������������С������������ȥ
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
	//��������  �͹鲢һ�������÷����η�������� 
	public static int[] QuickSort(int[] a, int start, int end) {
		/*
		 * ����ԭ��������������һ��Ԫ�أ���Ϊ ����׼����pivot����
		 * �����������У�����Ԫ�رȻ�׼ֵС�İڷ��ڻ�׼ǰ�棬
		 * ����Ԫ�رȻ�׼ֵ��İ��ڻ�׼�ĺ��棨��ͬ�������Ե���һ�ߣ���
		 * ����������˳�֮�󣬸û�׼�ʹ������е��м�λ�á������Ϊ������partition��������
		 * �ݹ�أ�recursive����С�ڻ�׼ֵԪ�ص������кʹ��ڻ�׼ֵԪ�ص�����������
		 */
		if(a.length < 1 || start < 0 || end >= a.length || start > end) return null;
		//�õ�������ȷ�������±�
		int index = partition(a, start, end);
		if(index > start)
			QuickSort(a, start, index-1);
		if(index < end)
			QuickSort(a, index+1, end);
		return a;
	}
	public static int partition(int[] a, int start, int end) {
		int pivot = a[start]; //�����׼����
		while(start < end) {
			//����βԪ�ش��ڻ�׼����ʱ����ǰŲ����βԪ��
			while(start < end && a[end]>=pivot) end--;
			//�����βԪ��С�ڻ�׼�����丳ֵ������Ԫ��
			a[start] = a[end];
			// ������Ԫ��С�ڵ��ڻ�׼����ʱ,��ǰŲ��ָ��
			while(start < end && a[start]<=pivot) start++;
			// ������Ԫ�ش��ڻ�׼����ʱ,��Ҫ���丳ֵ��end
            a[end] = a[start];
		}
		// ����ѭ��ʱstart��end���,��ʱ��start��end���ǻ�׼���ݵ���ȷ����λ��
        // ����ȷ����λ��ֵ��ֵ����׼����
        a[end] = pivot;
		return end;
	}
	//������   ���öѵ����ݽṹ
	public static int[] HeapSort(int[] a) {
		/*
		 * ������Heapsort����ָ���ö��������ݽṹ����Ƶ�һ�������㷨��
		 * �ѻ���һ��������ȫ�������Ľṹ����ͬʱ����ѻ������ʣ����ӽ��ļ�ֵ����������С�ڣ����ߴ��ڣ����ĸ��ڵ㡣
		 * ����һ�����鹹�������ѣ����ڽ��λ�������i��λ�ã�������Ҷλ��λ�������2i��λ�ã�������Ϊ2i+1
		 * ��������Ҫ��Ϊ������
		 * 		��һ�����������飬������һ������
		 * 		�ڶ����������ڵ�(Ҳ�������ֵ)��ĩβֵ��������ʱ�µ����ѳ��ȼ�һ
		 * 		��������ά���������(�㷨�ĺ���)������������������ظ����ϲ��裬ֱ���ѳ���Ϊ1��
		 */
		int len = a.length; //�������鳤��
		BuildMaxHeap(a, len);
		while(len > 0) {
			// �������ڵ���ĩβֵ
			int temp = a[len-1]; a[len-1] = a[0]; a[0] = temp;
			len--;
			//�ٴ�ά������
			MaxHeaplfy(a, 0, len);
		}
		return a;
	}
	public static void BuildMaxHeap(int[] a,int size) {
		/*
		 * ��������  Ҫ�����¶˿�ʼά��
		 * Ϊʲô�����¶˿�ʼά������Ϊ��ά���㷨�ǻ������������������ܹ���Ϊ���ѣ���ʼ�ݹ�ά����
		 * 			��֤���ڵ��������Ҷ������Ҷ��������ڵ���ĳ����Ҷ��������Ӵ���Ҷ��ʼ�ٴ�ά��
		 */
		//�����¶˵ķ�Ҷ�ӽ�㿪ʼά������
		for(int i = (size-1)/2;i>=0;i--) {
			MaxHeaplfy(a, i, size);
		}
	}
	public static void MaxHeaplfy(int[] a, int i, int size) {
		/*
		 * ���ѵ�ά���㷨�����ģ�������
		 */
		int maxindex = i; //�������ֵ���±�
		//�������Ҷ�����Ҵ��ڸ���� 
		if(2*(i+1)<=size&&a[2*(i+1)-1]>a[maxindex])
			maxindex = 2*i+1;
		//�������Ҷ�����Ҵ��ڸ��ڵ�
		if(2*(i+1)+1<=size&&a[2*(i+1)]>a[maxindex])
			maxindex = 2*(i+1);
		//�ݹ�����ά��
		if(maxindex!=i) {
			int temp = a[i]; a[i] = a[maxindex]; a[maxindex] = temp;
			MaxHeaplfy(a, maxindex, size);
		}
	}
	//��������  �Ƚϼ򵥣���������,������������ֵ����������ֵ
	public static int[] CountingSort(int[] a) {
		/*
		 * ��������(Counting sort)��һ���ȶ��������㷨����������ʹ��һ�����������C��
		 * ���е�i��Ԫ���Ǵ���������A��ֵ����i��Ԫ�صĸ�����Ȼ���������C����A�е�Ԫ���ŵ���ȷ��λ�á���ֻ�ܶ�������������
	 �㷨������
	 �ҳ��������������������С��Ԫ�أ�
	ͳ��������ÿ��ֵΪi��Ԫ�س��ֵĴ�������������C�ĵ�i�
	�����еļ����ۼӣ���C�еĵ�һ��Ԫ�ؿ�ʼ��ÿһ���ǰһ����ӣ���
	�������Ŀ�����飺��ÿ��Ԫ��i����������ĵ�C(i)�ÿ��һ��Ԫ�ؾͽ�C(i)��ȥ1��
		 */
		int max = a[0];//�������������ֵ
		int min = a[0];//������������Сֵ
		//�ҳ����ֵ����Сֵ
		for(int i = 1; i<a.length; i++) {
			if(a[i]>max) max = a[i];
			if(a[i]<min) min = a[i];
		}
		int [] count = new int[max-min+1]; //������������ ����ʼ��Ϊ0;
		Arrays.fill(count, 0);
		int bais = 0 - min; //����0�Ĳ�ֵ����
		//��ʼ����
		for(int i = 0; i<a.length; i++) count[a[i]+bais]++;
		//�������Ŀ������
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
	//Ͱ���� �Ǽ��������������
	public static int[] BucketSort(int[] a) {
		/*
		 * ������ԭ�������������ݷ��Ӿ��ȷֲ��������ݷֵ�����������Ͱ�
		 * 	ÿ��Ͱ�ٷֱ������п�����ʹ�ñ�������㷨�����Եݹ鷽ʽ����ʹ��Ͱ�����������
		 * ����˵��ԭ������������һ��ɼ��ϣ��������Դ洢������Ϣ
		 */
		int max = a[0];//�������������ֵ
		int min = a[0];//������������Сֵ
		//�ҳ����ֵ����Сֵ
		for(int i = 1; i<a.length; i++) {
			if(a[i]>max) max = a[i];
			if(a[i]<min) min = a[i];
		}
		int bucketsize = 5; //����Ͱ�Ĵ�С�����Ը�����������
		int backetCount = (max - min)/bucketsize +1; //����Ͱ������
		ArrayList<LinkedList<Integer>> Bucket = new ArrayList<>(); //�½�һ��Ͱ�ļ���
		for(int i = 0; i < backetCount; i++) {
			// �½�һ��Ͱ����������ӵ�Ͱ�ļ�����ȥ��
            // ����Ͱ��Ԫ�ػ�Ƶ���Ĳ��룬����ѡ�� LinkedList ��ΪͰ�����ݽṹ
			// Ͱ������С�Ѿ����ȷ�������ݷ����Ǹ�Ͱ�п��Ը���ʵ����Ҫ�жϻ��Լ�д�㷨����
            Bucket.add(new LinkedList<Integer>());
		}
		//�����е����ݷ���Ͱ�в��������
		for(int data : a) {
			//����Ӧ�÷����Ǹ�Ͱ�У������Լ�дһ���㷨�ж�
			int index = (data - min) / bucketsize;
			//Ͱ���ò����㷨����
			Insert(Bucket.get(index),data);
		}
		// ��Ͱ��Ԫ��ȫ��ȡ����
        int index = 0;
        for (LinkedList<Integer> bucket : Bucket) {
            for (int data : bucket) {
                a[index++] = data;
            }
        }
		return a;
	}
	private static void Insert(LinkedList<Integer> bucket, int data) {
		/*Iterator��ListIterator��������ʲô��
		 * Iterator����������Set��List���ϣ�����ListIteratorֻ����������List��
		 * Iterator�Լ���ֻ����ǰ�������ListIterator�ȿ���ǰ��Ҳ���Ժ���
		 * ListIteratorʵ����Iterator�ӿڣ������������Ĺ��ܣ����磺����Ԫ�أ��滻Ԫ�أ���ȡǰһ���ͺ�һ��Ԫ�ص��������ȵȡ�
		 */
		ListIterator<Integer> it = bucket.listIterator(); //���õ�������Ϊ�±�
		boolean inserFlag = true; //��Ϊ�ж��Ƿ����ı��
		while(it.hasNext()) {
			if(data<it.next()) {
				it.previous(); //�ѵ�����λ����ǰƫ��һλ
				it.add(data); //�������ݵ���ǰλ��
				inserFlag = false; //��ǲ���
				break;
			}
		}
		//���û�в��룬֤�����������ģ����뵽����ĩ��
		if(inserFlag) bucket.add(data); 
	}
	//�������� �˽⼴�ɣ������Բ�ǿ  ���Ͱ�����㷨
	public static int[] RadixSort(int[] a) {
		/*�㷨����
		 * ȡ�������е����������ȡ��λ����
		 * aΪԭʼ���飬�����λ��ʼȡÿ��λ���radix���飻
		 * ��radix���м����������ü�������������С��Χ�����ص㣩��
		 */
		return a;
	}
}
