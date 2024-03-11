package faceQuestion;
/* KMP �㷨��ƥ���ַ���һ���Ż��㷨  ��ʱ�临�Ӷ�λO(m+n) m��ƥ���ַ������� n��ƥ���ַ�������
 * KMP�㷨˼�룺����ǰ��ƥ�����Ϣ������iָ�벻�䣬ͨ���޸�jָ�룬���Ӵ��������ƶ�����Ч��λ�á�
 * 		  ��ƥ��ʧ�ܵ�ʱ��j��Ϊk,jǰ��ĵ�n���ַ������Ӵ���ͷ��kλ�õ�n���ַ���ֵ����P[0 ~ k-1] == P[j-k ~ j-1]
 * 		 PMT�е�ֵ���ַ�����ǰ׺�������׺���ϵĽ������Ԫ�صĳ��� next�������PMTֵ����һλ��
 * KMP �㷨��������
 * 	 	1.��next���� next������ľ���next[j] = k ��ʾ��M[i] != N[j]ʱ��jӦ�ñ�Ϊk��
 *  	2.ƥ���ַ���������ڴ�ƥ���ַ���j����ƥ�䣬����j = next[j]
 */
//�ο�ѧϰ��https://cloud.tencent.com/developer/news/390688
public class KFM_demo {
	public static void main(String[] args) {
		String str1 = "abababbbabcaababcabccads";
		String str2 = "aba";
		int[] next = next(str2);
		for(int i:next)
			System.out.print(i+",");
//		//System.out.println(KMP(str1,str2));
	}
	//KMP ƥ��
	public static int KMP(String ts, String ps) {
	    char[] t = ts.toCharArray();
	    char[] p = ps.toCharArray();
	    int i = 0; // ������λ��
	    int j = 0; // ģʽ����λ��
	    int[] next = next(ps);
	    while (i < t.length && j < p.length) {
	       if (j == -1 || t[i] == p[j]) { // ��jΪ-1ʱ��Ҫ�ƶ�����i����ȻjҲҪ��0
	           i++;
	           j++;
	       } else {
	           // i����Ҫ������
	           // i = i - j + 1;
	           j = next[j]; // j�ص�ָ��λ��
	       }
	    }
	    if (j == p.length) {
	       return i - j;
	    } else {
	       return -1;
	    }
	}
	//next ���� ���������next[j] = k  next ������ֵ���ַ���ǰ׺���׺���ϵ��Ԫ�ظ���(������һλ)
	/*
	 * ͨ������������ֱ�����jΪ0��1ʱ��k����jΪ0ʱ���Ѿ��޷���������������Ϊ-1��ʼ��ֵ��
	 * ��jΪ1ʱ������ǰ��ֻ���±�0�ˣ�����next[0]=-1,next[1]=0.
	 * ����������������Ҫ�����
	 * ��һ��p[j] == p[k]
	 *   p[j] == p[k]ʱ����next[++j] = ++k;
	 * 	 ����������Ϊ����p[j-1]��ƥ��ʧ�ܺ�j-1��Ϊk-1��
	 * 	 ������k-1�����¿�ʼƥ�䣬ԭ��������ǹ�ͬ��һ��ǰ׺A��
	 * 	 �������Ե�p[j] == p[k]�����Ǿ�ӵ����ǰ׺AB����k++
	 * �ڶ���P[j]!=p[k]
	 * 		��k=next[k]    k ֮������Ѿ�����next������ַ����������൱����������õ��ַ�������������
	 * 					  KMP�㷨ȥ�����ǹ�ͬ��һ��ǰ׺
	 * 			
			���ϱߵ����ӣ������Ѿ��������ҵ�[ A��B��A��B ]�����ĺ�׺���ˣ�
			�����ǻ��ǿ����ҵ�[ A��B ]��[ B ]������ǰ׺���ġ�
			����������̾����ڶ�λ[ A��B��A��C ]�������
			��C��������һ���ˣ�Ҳ����kλ�ò�һ���ˣ����ǵ�Ȼ�ǰ�ָ���ƶ���next[k]��
			
			
		 * 		��p[k ] �� p[j]�������ʱp[ next[k] ] == p[j ]����next[ j + 1 ] =  next[k] + 1��
		 * 		��������ݹ�ǰ׺����k = next[k]�������ظ��˹��̡�
		 * 	        �൱�����ַ�p[j+1]֮ǰ�����ڳ���Ϊk+1��ǰ׺"p0 p1, ��, pk-1 pk"����׺��pj-k pj-k+1, ��, pj-1 pj"��ȣ�
		 * 		��ô�Ƿ���ܴ�����һ��ֵt+1 < k+1��ʹ�ó��ȸ�С��ǰ׺ ��p0 p1, ��, pt-1 pt�� ���ڳ��ȸ�С�ĺ�׺ ��pj-t pj-t+1, ��, pj-1 pj�� �أ�
		 * 		������ڣ���ô���t+1 ����next[ j+1]��ֵ��
		 * 		���൱�������Ѿ���õ�next ���飨next [0, ..., k, ..., j]������P��ǰ׺��P����׺��ƥ�䡣
	 */
	public static int[] next(String s) {
		char[] arr = s.toCharArray();
		int [] next = new int[arr.length];
		next[0] = -1;
		//��ʼ����
		int j = 0,k=-1;
		while(j<arr.length-1) {
			if(k==-1||arr[j]==arr[k]) {
				//��������next�Ż�
				//��Ϊ ����arr[j] ƥ��ʧ�ܣ�����j=next[j]=k��������ƥ��arr[k]
				//���arr[k] == arr[j],���ʱƥ��ض�ʧ�ܣ���������k=next[k] ƥ�� arr[next[k]]
				//��ˣ����ǲ���ֱ����arr[k] == arr[j] �� next[j] = next[k]
				if(arr[++j]==arr[++k])
					next[j]=next[k];
				else
					next[j]=k;   //ԭ������ֻ��next[++j] = ++k;
				
			}else {
				k = next[k];
			}
		}
		return next;
	}
}
