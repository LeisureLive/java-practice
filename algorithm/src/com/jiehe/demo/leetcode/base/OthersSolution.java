package com.jiehe.demo.leetcode.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jiehe, leisurehejie@sina.com
 * @Date: 2019/6/30 15:55
 */
public class OthersSolution {

  /**
   * 位1的个数.
   */
  public int hammingWeight(int n) {
    int mask = 1;
    int total = 0;
    for (int i = 0; i < 32; i++) {
      if ((n & mask) == 1) {
        total++;
      }
      n = n >>> 1;
    }
    return total;
  }

  /**
   * 汉明距离（两个数字对应二进制位不同的位置的数目）.
   */
  public int hammingDistance(int x, int y) {
    int mask = 1;
    int total = 0;
    for (int i = 0; i < 32; i++) {
      if ((x & mask) != (y & mask)) {
        total++;
      }
      x = x >>> 1;
      y = y >>> 1;
    }
    return total;
  }

  /**
   * 颠倒二进制位.
   */
  public int reverseBits(int n) {
    int mask = 1;
    int result = 0;
    for (int i = 0; i < 32; i++) {
      if ((n & mask) == 1) {
        result = (result << 1) + 1;
      } else {
        result = result << 1;
      }
      n = n >>> 1;
    }
    return result;
  }

  /**
   * 帕斯卡三角形.
   */
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>();
    if (numRows < 1) {
      return result;
    }
    List<Integer> lastList = new ArrayList<>();
    lastList.add(1);
    result.add(lastList);
    for (int i = 2; i <= numRows; i++) {
      List<Integer> list = new ArrayList<>(i);
      for (int j = 0; j < i; j++) {
        if (j == 0 || j == i - 1) {
          list.add(1);
        } else {
          list.add(lastList.get(j - 1) + lastList.get(j));
        }
      }
      result.add(list);
      lastList = list;
    }
    return result;
  }

  /**
   * 有效的括号.
   */
  public boolean isValid(String s) {
    if (s.length() <= 0) {
      return true;
    }
    int[] arr = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        arr[i] = 1;
      } else if (s.charAt(i) == '[') {
        arr[i] = 2;
      } else if (s.charAt(i) == '{') {
        arr[i] = 3;
      } else if (s.charAt(i) == ')') {
        int j = i - 1;
        for (; j >= 0; j--) {
          if (arr[j] == 0) {
            continue;
          }
          if (arr[j] != 1) {
            return false;
          }
          arr[j] = 0;
          break;
        }
        if (j < 0) {
          return false;
        }
      } else if (s.charAt(i) == ']') {
        int j = i - 1;
        for (; j >= 0; j--) {
          if (arr[j] == 0) {
            continue;
          }
          if (arr[j] != 2) {
            return false;
          }
          arr[j] = 0;
          break;
        }
        if (j < 0) {
          return false;
        }
      } else {
        int j = i - 1;
        for (; j >= 0; j--) {
          if (arr[j] == 0) {
            continue;
          }
          if (arr[j] != 3) {
            return false;
          }
          arr[j] = 0;
          break;
        }
        if (j < 0) {
          return false;
        }
      }
    }
    // arr是否全被清空.
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * 缺失数字( n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数).
   */
  public int missingNumber(int[] nums) {
    int last = 0;
    for (int i = 0; i < nums.length; i++) {
      last = last ^ (i ^ nums[i]);
    }
    return last;
  }

}

