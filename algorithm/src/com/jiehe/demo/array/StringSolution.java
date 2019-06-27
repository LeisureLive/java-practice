package com.jiehe.demo.array;

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
        if (ret < Integer.MIN_VALUE / 10|| ret == Integer.MIN_VALUE / 10 && cur > 8) {
          return Integer.MIN_VALUE;
        }
        ret = ret * 10 - cur;
      } else {
        if (ret > Integer.MAX_VALUE / 10 || ret == Integer.MAX_VALUE / 10 && cur > 7) {
          return Integer.MAX_VALUE;
        }
        ret = ret * 10 + cur;
      }
    }
    return ret;
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
    String s = "words and 987";
    System.out.println(myAtoi(s));
  }

}
