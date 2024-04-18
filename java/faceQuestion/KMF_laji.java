

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
	        //①如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++      
	        if (j == -1 || s[i] == p[j])  
	        {  
	            i++;  
	            j++;  
	        }  
	        else  
	        {  
	            //②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]      
	            //next[j]即为j所对应的next值        
	            j = next[j];  
	        }  
	    }  
	    if (j == p.length)  
	        return i - j;  
	    else  
	        return -1;  
	}  
	public static int[] next1(String s) {
		//next 算法类似就是一个KMP算法
		
		
		// next 数组是 当前串前缀与后缀交集的串最大长度然后在右移一位。第一位默认为-1;
		// 所以next数组长度位字符串长度
		char[] arr = s.toCharArray();
		int [] next = new int[arr.length];
		next[0] = -1;
		//初始条件
		int j = 0,k=-1;
		while(j<arr.length-1) {
			if(k==-1||arr[j]==arr[k]) {
				//当arr[k] == arr[j] 时，
				//	next[j+1]=next[j]+1;
				next[++j]=++k; //先自加，在运算
//				if(arr[j]==arr[k])
//					next[j]=next[k];
				/*因为在P[j]之前已经有P[0 ~ k-1] == p[j-k ~ j-1]。（next[j] == k）
				 * 这时候现有P[k] == P[j]，我们是不是可以得到P[0 ~ k-1] + P[k] == p[j-k ~ j-1] + P[j]。
				 * 即：P[0 ~ k] == P[j-k ~ j]，即next[j+1] == k + 1 == next[j] + 1。
				 * 
				 */
			}else {
				k = next[k];
			}
		}
		return next;
	}
	public static int[] next(String s) {
		//next 算法类似就是一个KMP算法
		
		
		// next 数组是 当前串前缀与后缀交集的串最大长度然后在右移一位。第一位默认为-1;
		// 所以next数组长度位字符串长度
		char[] arr = s.toCharArray();
		int [] next = new int[arr.length];
		next[0] = -1;
		//初始条件
		int j = 0,k=-1;
		while(j<arr.length-1) {
			if(k==-1||arr[j]==arr[k]) {
				//当arr[k] == arr[j] 时，
				//	next[j+1]=next[j]+1;
				next[++j]=++k; //先自加，在运算
				if(arr[j]==arr[k])
					next[j]=next[k];
				/*因为在P[j]之前已经有P[0 ~ k-1] == p[j-k ~ j-1]。（next[j] == k）
				 * 这时候现有P[k] == P[j]，我们是不是可以得到P[0 ~ k-1] + P[k] == p[j-k ~ j-1] + P[j]。
				 * 即：P[0 ~ k] == P[j-k ~ j]，即next[j+1] == k + 1 == next[j] + 1。
				 * 
				 */
			}else {
				k = next[k];
			}
		}
		return next;
	}
}
