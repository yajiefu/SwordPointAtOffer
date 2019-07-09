package leetCode;
/**
 * 题目：9.回文数
 * 描述：判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1：
 * 输入: 121
 * 输出: true
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数
 * 
 * 
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 * 
 * 方法1：将整数转为字符串再转为字符数组，从两头开始进行判断
 * 方法2：用StringBuild的reverse翻转字符串，进行比较，如果相等则是回文数
 * 方法3：对整数进行取整和取模运算，依次对最高位和最低位进行比较
 * 方法4：取出后半段数字进行翻转。
 *              这里需要注意的一个点就是由于回文数的位数可奇可偶，所以当它的长度是偶数时，它对折过来应该是相等的；
 *              当它的长度是奇数时，那么它对折过来后，有一个的长度需要去掉一位数（除以 10 并取整）。
 *              
 *    每次进行取余操作 （ %10），取出最低的数字：y = x % 10
 *    将最低的数字加到取出数的末尾：revertNum = revertNum * 10 + y
 *    每取一个最低位数字，x 都要自除以 10
 *    判断 x 是不是小于 revertNum ，当它小于的时候，说明数字已经对半或者过半了
 *    最后，判断奇偶数情况：如果是偶数的话，revertNum 和 x 相等；如果是奇数的话，最中间的数字就在revertNum 的最低位上，将它除以 10 以后应该和 x 相等。
       
 * @author yajie
 *
 */
public class IsPalindrome {
	// 方法1：将整数转为字符串再转为字符数组，从两头开始进行判断
	public static boolean isPalindrome1(int x) {

		if (x < 0) {
			return false;
		}
		char[] chs = Integer.toString(x).toCharArray();
		int i = 0;
		int j = chs.length - 1;
		while (i <= j) {
			if (chs[i] != chs[j]) {
				return false;
			}
			i++;
			j--;
		}
		return true;

	}

	// 方法2：用StringBuild的reverse翻转字符串，进行比较，如果相等则是回文数
	public static boolean isPalindrome2(int x) {
		if (x < 0) {
			return false;
		}
		String string = (new StringBuilder(x + "")).reverse().toString();
		return (x + "").equals(string);
	}

	// 方法3：对整数进行取整和取模运算，依次对最高位和最低位进行比较
	public static boolean isPalindrome3(int x) {
		if (x < 0) {
			return false;
		}
		int div = 1;
		// 先将最高位取出
		// 假设x=12321,div = 10000
		while ((x / div) >= 10) {
			div *= 10;
		}
		while (x > 0) {
			// 假设x=12321
			int left = x / div;// left=1
			int right = x % 10;// right=1
			if (left != right) {
				return false;
			}
			// 将最高位和最低位去掉
			x = (x % div) / 10;// x=232
			div /= 100;// div=100
		}
		return true;
	}

	// 方法4：取出后半段数字进行翻转。
	public static boolean isPalindrome4(int x) {
		// 思考：可以思考一下，为什么末尾为 0 就可以直接返回 false
		if (x < 0 || (x % 10 == 0 && x != 0)) {
			return false;
		}
		int revertNum = 0;
		while (x > revertNum) {
			revertNum = revertNum * 10 + x % 10;
			x = x / 10;
		}
		return revertNum == x || x == revertNum / 10;
	}

	public static void main(String[] args) {
		System.out.println(isPalindrome1(-44));
		System.out.println(isPalindrome1(454));
		System.out.println(isPalindrome1(4149));
		System.out.println("*******************");
		System.out.println(isPalindrome2(-44));
		System.out.println(isPalindrome2(454));
		System.out.println(isPalindrome2(4149));
		System.out.println("*******************");
		System.out.println(isPalindrome3(-44));
		System.out.println(isPalindrome3(454));
		System.out.println(isPalindrome3(4149));
		System.out.println("*******************");
		System.out.println(isPalindrome4(-44));
		System.out.println(isPalindrome4(454));
		System.out.println(isPalindrome4(4149));
	}
}
