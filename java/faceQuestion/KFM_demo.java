package faceQuestion;
/* KMP 算法是匹配字符串一种优化算法  其时间复杂度位O(m+n) m被匹配字符串长度 n待匹配字符串长度
 * KMP算法思想：利用前面匹配的信息，保持i指针不变，通过修改j指针，让子串尽量地移动到有效的位置。
 * 		  当匹配失败的时候，j变为k,j前面的的n个字符等于子串开头到k位置的n个字符的值，即P[0 ~ k-1] == P[j-k ~ j-1]
 * 		 PMT中的值是字符串的前缀集合与后缀集合的交集中最长元素的长度 next数组就是PMT值右移一位。
 * KMP 算法基本步骤
 * 	 	1.求next数组 next数组核心就是next[j] = k 表示当M[i] != N[j]时，j应该变为k。
 *  	2.匹配字符串，如果在待匹配字符串j处不匹配，则令j = next[j]
 */
//参考学习：https://cloud.tencent.com/developer/news/390688
public class KFM_demo {
	public static void main(String[] args) {
		String str1 = "abababbbabcaababcabccads";
		String str2 = "aba";
		int[] next = next(str2);
		for(int i:next)
			System.out.print(i+",");
//		//System.out.println(KMP(str1,str2));
	}
	//KMP 匹配
	public static int KMP(String ts, String ps) {
	    char[] t = ts.toCharArray();
	    char[] p = ps.toCharArray();
	    int i = 0; // 主串的位置
	    int j = 0; // 模式串的位置
	    int[] next = next(ps);
	    while (i < t.length && j < p.length) {
	       if (j == -1 || t[i] == p[j]) { // 当j为-1时，要移动的是i，当然j也要归0
	           i++;
	           j++;
	       } else {
	           // i不需要回溯了
	           // i = i - j + 1;
	           j = next[j]; // j回到指定位置
	       }
	    }
	    if (j == p.length) {
	       return i - j;
	    } else {
	       return -1;
	    }
	}
	//next 数组 数组核心是next[j] = k  next 数组存的值是字符串前缀与后缀集合的最长元素个数(再右移一位)
	/*
	 * 通过上面代码可以直接算出j为0和1时的k，当j为0时，已经无法后退了所以设置为-1初始化值，
	 * 当j为1时，它的前面只有下标0了，所以next[0]=-1,next[1]=0.
	 * 接下来就是两种主要情况了
	 * 第一种p[j] == p[k]
	 *   p[j] == p[k]时，有next[++j] = ++k;
	 * 	 》》这是因为当在p[j-1]处匹配失败后，j-1变为k-1，
	 * 	 》》从k-1处重新开始匹配，原因就是他们共同有一个前缀A，
	 * 	 》》所以当p[j] == p[k]后，他们就拥有了前缀AB所以k++
	 * 第二种P[j]!=p[k]
	 * 		则k=next[k]    k 之后就是已经做好next数组的字符串，我们相当于再这个做好的字符串数组中利用
	 * 					  KMP算法去找他们共同的一个前缀
	 * 			
			像上边的例子，我们已经不可能找到[ A，B，A，B ]这个最长的后缀串了，
			但我们还是可能找到[ A，B ]、[ B ]这样的前缀串的。
			所以这个过程就像在定位[ A，B，A，C ]这个串，
			当C和主串不一样了（也就是k位置不一样了），那当然是把指针移动到next[k]。
			
			
		 * 		若p[k ] ≠ p[j]，如果此时p[ next[k] ] == p[j ]，则next[ j + 1 ] =  next[k] + 1，
		 * 		否则继续递归前缀索引k = next[k]，而后重复此过程。
		 * 	        相当于在字符p[j+1]之前不存在长度为k+1的前缀"p0 p1, …, pk-1 pk"跟后缀“pj-k pj-k+1, …, pj-1 pj"相等，
		 * 		那么是否可能存在另一个值t+1 < k+1，使得长度更小的前缀 “p0 p1, …, pt-1 pt” 等于长度更小的后缀 “pj-t pj-t+1, …, pj-1 pj” 呢？
		 * 		如果存在，那么这个t+1 便是next[ j+1]的值，
		 * 		此相当于利用已经求得的next 数组（next [0, ..., k, ..., j]）进行P串前缀跟P串后缀的匹配。
	 */
	public static int[] next(String s) {
		char[] arr = s.toCharArray();
		int [] next = new int[arr.length];
		next[0] = -1;
		//初始条件
		int j = 0,k=-1;
		while(j<arr.length-1) {
			if(k==-1||arr[j]==arr[k]) {
				//这里做了next优化
				//因为 若再arr[j] 匹配失败，则令j=next[j]=k看，继续匹配arr[k]
				//如果arr[k] == arr[j],则此时匹配必定失败，接着再令k=next[k] 匹配 arr[next[k]]
				//因此，我们不如直接再arr[k] == arr[j] 令 next[j] = next[k]
				if(arr[++j]==arr[++k])
					next[j]=next[k];
				else
					next[j]=k;   //原本这里只有next[++j] = ++k;
				
			}else {
				k = next[k];
			}
		}
		return next;
	}
}
