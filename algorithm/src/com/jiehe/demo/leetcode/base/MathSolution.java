package com.jiehe.demo.leetcode.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jiehe, leisurehejie@sina.com
 * @Date: 2019/6/30 15:01
 */
public class MathSolution {

  /**
   * Fizz Buzz.
   */
  public List<String> fizzBuzz(int n) {
    List<String> result = new LinkedList<>();
    if (n < 1) {
      return result;
    }
    for (int i = 1; i <= n; i++) {
      if (i % 3 == 0 && i % 5 == 0) {
        result.add("FizzBuzz");
      } else if (i % 3 == 0) {
        result.add("Fizz");
      } else if (i % 5 == 0) {
        result.add("Buzz");
      } else {
        result.add(String.valueOf(i));
      }
    }
    return result;
  }

  /**
   * 计数质数.
   */
  public int countPrimes(int n) {
    if (n < 2) {
      return 0;
    }
    int[] flags = new int[n];
    for (int i = 0; i < n; i++) {
      flags[i] = 1;
    }
    for (int i = 2; i < n; i++) {
      if (flags[i] != 1) {
        continue;
      }
      int m = 2;
      while (m * i < n) {
        flags[m * i] = 0;
        m++;
      }
    }
    int total = 0;
    for (int i = 2; i < n; i++) {
      if (flags[i] == 1) {
        total++;
      }
    }
    return total;
  }

  /**
   * 3的幂.
   */
  public boolean isPowerOfThree(int n) {
    if (n < 1) {
      return false;
    }
    if (n == 1) {
      return true;
    }
    while (n > 1) {
      if (n % 3 != 0) {
        return false;
      }
      n = n / 3;
    }
    return true;
  }

  /**
   * 罗马数字转整数.
   */
  public int romanToInt(String s) {
    Map<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);
    int total = 0;
    int i = 0;
    while (i < s.length()) {
      if (s.charAt(i) == 'I' && i < s.length() - 1 && s.charAt(i + 1) == 'V') {
        total += 4;
        i += 2;
      } else if (s.charAt(i) == 'I' && i < s.length() - 1 && s.charAt(i + 1) == 'X') {
        total += 9;
        i += 2;
      } else if (s.charAt(i) == 'X' && i < s.length() - 1 && s.charAt(i + 1) == 'L') {
        total += 40;
        i += 2;
      } else if (s.charAt(i) == 'X' && i < s.length() - 1 && s.charAt(i + 1) == 'C') {
        total += 90;
        i += 2;
      } else if (s.charAt(i) == 'C' && i < s.length() - 1 && s.charAt(i + 1) == 'D') {
        total += 400;
        i += 2;
      } else if (s.charAt(i) == 'C' && i < s.length() - 1 && s.charAt(i + 1) == 'M') {
        total += 900;
        i += 2;
      } else {
        total += map.getOrDefault(s.charAt(i), 0);
        i++;
      }
    }
    return total;
  }

}
