package faceQuestion;

public class KMF_laji {
	public static void main(String[] args) {
		String str1 = "abcbabbbabcaababcabccads";
		String str2 = "abacababc";
//		int i =KmpSearch(str1,str2);
//		System.out.println(i);
		int[] next = next(str2);
		for(int j : next)
			System.out.print(j+" ");
		System.out.println();
		int[] next1 = next1(str2);
		for(int j : next1)
			System.out.print(j+" ");
	}

	public static int KmpSearch(String str1, String str2)  
	{  
	    int i = 0;  
	    int j = 0;  
	    char[] s = str1.toCharArray();
	    char[] p = str2.toCharArray();
	    int[] next = next(str2);
	    while (i < s.length && j < p.length)  
	    {  
	        //�����j = -1�����ߵ�ǰ�ַ�ƥ��ɹ�����S[i] == P[j]��������i++��j++      
	        if (j == -1 || s[i] == p[j])  
	        {  
	            i++;  
	            j++;  
	        }  
	        else  
	        {  
	            //�����j != -1���ҵ�ǰ�ַ�ƥ��ʧ�ܣ���S[i] != P[j]�������� i ���䣬j = next[j]      
	            //next[j]��Ϊj����Ӧ��nextֵ        
	            j = next[j];  
	        }  
	    }  
	    if (j == p.length)  
	        return i - j;  
	    else  
	        return -1;  
	}  
	public static int[] next1(String s) {
		//next �㷨���ƾ���һ��KMP�㷨
		
		
		// next ������ ��ǰ��ǰ׺���׺�����Ĵ���󳤶�Ȼ��������һλ����һλĬ��Ϊ-1;
		// ����next���鳤��λ�ַ�������
		char[] arr = s.toCharArray();
		int [] next = new int[arr.length];
		next[0] = -1;
		//��ʼ����
		int j = 0,k=-1;
		while(j<arr.length-1) {
			if(k==-1||arr[j]==arr[k]) {
				//��arr[k] == arr[j] ʱ��
				//	next[j+1]=next[j]+1;
				next[++j]=++k; //���Լӣ�������
//				if(arr[j]==arr[k])
//					next[j]=next[k];
				/*��Ϊ��P[j]֮ǰ�Ѿ���P[0 ~ k-1] == p[j-k ~ j-1]����next[j] == k��
				 * ��ʱ������P[k] == P[j]�������ǲ��ǿ��Եõ�P[0 ~ k-1] + P[k] == p[j-k ~ j-1] + P[j]��
				 * ����P[0 ~ k] == P[j-k ~ j]����next[j+1] == k + 1 == next[j] + 1��
				 * 
				 */
			}else {
				k = next[k];
			}
		}
		return next;
	}
	public static int[] next(String s) {
		//next �㷨���ƾ���һ��KMP�㷨
		
		
		// next ������ ��ǰ��ǰ׺���׺�����Ĵ���󳤶�Ȼ��������һλ����һλĬ��Ϊ-1;
		// ����next���鳤��λ�ַ�������
		char[] arr = s.toCharArray();
		int [] next = new int[arr.length];
		next[0] = -1;
		//��ʼ����
		int j = 0,k=-1;
		while(j<arr.length-1) {
			if(k==-1||arr[j]==arr[k]) {
				//��arr[k] == arr[j] ʱ��
				//	next[j+1]=next[j]+1;
				next[++j]=++k; //���Լӣ�������
				if(arr[j]==arr[k])
					next[j]=next[k];
				/*��Ϊ��P[j]֮ǰ�Ѿ���P[0 ~ k-1] == p[j-k ~ j-1]����next[j] == k��
				 * ��ʱ������P[k] == P[j]�������ǲ��ǿ��Եõ�P[0 ~ k-1] + P[k] == p[j-k ~ j-1] + P[j]��
				 * ����P[0 ~ k] == P[j-k ~ j]����next[j+1] == k + 1 == next[j] + 1��
				 * 
				 */
			}else {
				k = next[k];
			}
		}
		return next;
	}
}
