package com.jiehe.demo.leetcode.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringSolution {

  public static void reverseString(char[] s) {
    int i = 0;
    int j = s.length - 1;
    while (j > i) {
      char temp = s[i];
      s[i] = s[j];
      s[j] = temp;
      i++;
      j--;
    }
  }

  public static int reverse(int x) {
    // 判断负数.
    boolean negative = false;
    if (x < 0) {
      negative = true;
      x = 0 - x;
    }
    // 转为正数
    int[] arr = new int[32];
    int cur = 0;
    while (x > 0) {
      arr[cur] = x % 10;
      x = x / 10;
      cur++;
    }
    int[] arr2 = new int[cur];
    System.arraycopy(arr, 0, arr2, 0, cur);
    int result = 0;
    for (int i = 0; i < arr2.length; i++) {
      int val = arr2[i];
      // 判断溢出
      if (result > Integer.MAX_VALUE / 10 || (result ==
          Integer.MAX_VALUE / 10 && val >= Integer.MAX_VALUE % 10)) {
        return 0;
      }
      result = result * 10 + val;
    }
    return negative ? 0 - result : result;
  }

  /**
   * return m^n.
   */
  private static int pow(int m, int n) {
    if (n == 0) {
      return 1;
    }
    while (--n > 0) {
      m = m * m;
    }
    return m;
  }

  public static int firstUniqChar(String s) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      if (!map.containsKey((int) s.charAt(i))) {
        map.put((int) s.charAt(i), i);
      } else {
        map.put((int) s.charAt(i), -1);
      }
    }
    int index = -1;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() != -1 && (index < 0 || entry.getValue() < index)) {
        index = entry.getValue();
      }
    }
    return index;
  }


  public static boolean isAnagram(String s, String t) {

    return true;
  }

  public static int myAtoi(String str) {
    int i = 0, j = 0, len = str.length();
    boolean negative = false;
    for (i = 0; i < len; i++) {
      if ('0' <= str.charAt(i) && str.charAt(i) <= '9') {
        break;
      } else if (str.charAt(i) == '-' || str.charAt(i) == '+') {
        negative = str.charAt(i) == '-';
        i++;
        break;
      } else if (str.charAt(i) != ' ') {
        return 0;
      }
    }
    for (j = i; j < len; j++) {
      if (str.charAt(j) < '0' || '9' < str.charAt(j)) {
        break;
      }
    }
    int ret = 0;
    String num = str.substring(i, j);
    for (int x = 0; x < num.length(); x++) {
      int cur = num.charAt(x) - '0';
      if (negative) {
        //这里判断溢出的情况和第7题一样
        if (ret < Integer.MIN_VALUE / 10 || ret == Integer.MIN_VALUE / 10 && cur > Math
            .abs(Integer.MIN_VALUE % 10)) {
          return Integer.MIN_VALUE;
        }
        ret = ret * 10 - cur;
      } else {
        if (ret > Integer.MAX_VALUE / 10
            || ret == Integer.MAX_VALUE / 10 && cur > Integer.MAX_VALUE % 10) {
          return Integer.MAX_VALUE;
        }
        ret = ret * 10 + cur;
      }
    }
    return ret;
  }

  /**
   * 寻找子串(Sunday算法).
   */
  public static int strStr(String haystack, String needle) {
    if (needle.length() > haystack.length()) {
      return -1;
    }
    if (needle.length() == 0) {
      return 0;
    }
    int i = 0;
    while (i <= haystack.length() - needle.length()) {
      int match = 0;
      while (match < needle.length() && needle.charAt(match) == haystack.charAt(i + match)) {
        match++;
      }
      if (match == needle.length()) {
        return i;
      }
      // 此处需要考虑越界
      if (i + needle.length() >= haystack.length()) {
        i++;
      } else {
        // 匹配失败时，找对齐之后的下一个字符在needle中从右向左第一次出现的index，needle右移的距离为needle.length-index
        char temp = haystack.charAt(i + needle.length());
        int index = findLastIndex(temp, needle);
        i += needle.length() - index;
      }
    }
    return -1;
  }

  /**
   * 找到指定字符在字符串从右到左第一次出现的位置.
   */
  private static int findLastIndex(char cr, String str) {
    int ret = -1;
    for (int i = str.length() - 1; i >= 0; i--) {
      if (str.charAt(i) == cr) {
        return i;
      }
    }
    return ret;
  }

  /**
   * 数组拆分 I.
   */
  public int arrayPairSum(int[] nums) {
    Arrays.sort(nums);
    int i = 0;
    int j = 1;
    int total = 0;
    while (j < nums.length) {
      total += nums[i];
      i += 2;
      j += 2;
    }
    return total;
  }

  /**
   * 最大连续1的个数.
   */
  public int findMaxConsecutiveOnes(int[] nums) {
    int i = 0;
    int j = 0;
    int max = 0;
    while (i < nums.length) {
      // 第一个1出现的位置
      while (i < nums.length && nums[i] != 1) {
        i++;
        j++;
      }
      while (j < nums.length && nums[j] == 1) {
        j++;
      }
      max = Math.max(max, j - i);
      i = j;
    }
    return max;
  }

  /**
   * 长度最小的子数组（找到大于等于指定数值最短子数组，滑动窗口）.
   */
  public int minSubArrayLen(int s, int[] nums) {
    if (nums.length <= 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0] == s ? 1 : 0;
    }
    int left = 0;
    int sum = 0;
    int minSize = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      while (sum >= s) {
        minSize = Math.min(minSize, i - left + 1);
        sum -= nums[left++];
      }
    }
    return minSize == Integer.MAX_VALUE ? 0 : minSize;
  }

  /**
   * 最长公共前缀.
   */
  public static String longestCommonPrefix(String[] strs) {
    if (strs.length <= 0) {
      return "";
    }
    if (strs.length == 1) {
      return strs[0];
    }
    Arrays.sort(strs);
    String str1 = strs[0];
    String str2 = strs[strs.length - 1];
    int i = 0;
    while (i < str1.length() && i < str2.length()) {
      if (str1.charAt(i) != str2.charAt(i)) {
        break;
      }
      i++;
    }
    return str1.substring(0, i);
  }

  /**
   * 在一个字符串中找到最长的回文子串(思路：每个位置为中心，计算奇偶长度的回文子串，找到最长的).
   */
  public String longestPalindrome(String s) {
    if (s == null || s.length() <= 1) {
      return s;
    }
    int length = 0;
    int index = 0;
    for (int i = 0; i < s.length() - 1; i++) {
      palindromeHelper(s, i, i, index, length);
      palindromeHelper(s, i, i + 1, index, length);
    }
    return s.substring(index, index + length);
  }

  private void palindromeHelper(String s, int l, int r, int index, int length) {
    while (l >= 0 && r <= s.length() - 1) {
      if (s.charAt(l) == s.charAt(r)) {
        l--;
        r++;
      } else {
        break;
      }
    }
    if (r - l - 1 > length) {
      index = l + 1;
      length = r - l + 1;
    }
  }

  /**
   * 字符串的最长回文字符列，和子串不同，字符列允许字符不连续(思路：动态规划).
   */
  public int longestPalindromeSubseq(String s) {
    if (s == null || s.length() <= 0) {
      return 0;
    }
    if (s.length() == 1) {
      return 1;
    }
    int[][] dp = new int[s.length()][s.length()];
    for (int i = s.length() - 1; i >= 0; i--) {
      dp[i][i] = 1;
      for (int j = i + 1; j < s.length(); j++) {
        if (s.charAt(i) == s.charAt(j)) {
          // 这里如果i j 相邻，dp[2][3] = dp[3][2] +2，dp[3][2]有默认值0
          dp[i][j] = dp[i + 1][j - 1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[0][s.length() - 1];
  }

  /**
   * 最小覆盖子串(A串包含B串所有字符的最小子串).
   */
  public String minWindow(String s, String t) {
    Map<Character, Integer> dicT = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      int count = dicT.getOrDefault(t.charAt(i), 0);
      dicT.put(t.charAt(i), ++count);
    }
    int required = dicT.size();
    // 已对应上的字符的数量
    int formed = 0;
    // 表示窗口中包含的字符及对应个数.
    Map<Character, Integer> windowMap = new HashMap<>();
    // 表示窗口的长度，左指针位置，右指针位置.
    int[] ans = new int[]{-1, 0, 0};
    int l = 0;
    int r = 0;
    while (r < s.length()) {
      char cr = s.charAt(r);
      int count = windowMap.getOrDefault(cr, 0);
      windowMap.put(cr, ++count);
      // 两个哈希表对应字符数量相等时，formed记录数+1.
      if (dicT.containsKey(cr) && dicT.get(cr).intValue() == windowMap.get(cr).intValue()) {
        formed++;
      }
      while (l <= r && formed == required) {
        if (ans[0] == -1 || r - l + 1 < ans[0]) {
          ans[0] = r - l + 1;
          ans[1] = l;
          ans[2] = r;
        }
        char temp = s.charAt(l);
        int ct = windowMap.get(temp);
        windowMap.put(temp, --ct);
        if (dicT.containsKey(temp) && windowMap.get(temp).intValue() < dicT.get(temp).intValue()) {
          formed--;
        }
        l++;
      }
      r++;
    }
    return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);

  }

  public static void main(String[] args) {
    // 1.反转字符串
//    char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
//    reverseString(s);
//    System.out.println(Arrays.toString(s));
    // 2.整数反转
//    int x = 123;
//    System.out.println(reverse(x));
    // 3.字符串中第一个唯一字符
//    String s = "loveleetcode";
//    System.out.println(firstUniqChar(s));
    // 4.有效的字母异位词
//    String s = "anagram";
//    String t = "nagaram";
//    System.out.println(isAnagram(s, t));
    // 7.字符串转换整数
//    String s = "words and 987";
//    System.out.println(myAtoi(s));
    // 8.   实现strStr()
//    String s = "hello";
//    String needle = "ll";
//    strStr(s, needle);
//    // 9.最长公共前缀
//    String[] strs = new String[]{"flower", "flow", "flight"};
//    System.out.println(longestCommonPrefix(strs));
    System.out.println(Math.abs(Integer.MIN_VALUE % 10));
  }

}
