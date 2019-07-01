package com.jiehe.demo.leetcode.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArraySolution {

  /**
   * 加一.
   */
  public int[] plusOne(int[] digits) {
    for (int i = digits.length - 1; i >= 0; i--) {
      if (digits[i] + 1 < 10) {
        digits[i] = digits[i] + 1;
        return digits;
      }
      digits[i] = (digits[i] + 1) % 10;
    }
    int[] result = new int[digits.length + 1];
    System.arraycopy(digits, 0, result, 1, digits.length);
    result[0] = 1;
    return result;
  }

  /**
   * 移动零,必须在原数组上操作，不能拷贝额外的数组,尽量减少操作次数.
   */
  public void moveZeroes(int[] nums) {
    if (nums.length <= 1) {
      return;
    }

    int j = 0;
    for (int i = 0; i < nums.length && j < nums.length; i++) {
      if (nums[i] != 0) {
        continue;
      }
      if (j == 0) {
        j = i;
      }
      while (j < nums.length && nums[j] == 0) {
        j++;
      }
      if (j == nums.length) {
        break;
      }
      swap(nums, i, j);
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  /**
   * 两数之和.
   */
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int componet = target - nums[i];
      if (map.containsKey(componet)) {
        return new int[]{map.get(componet), i};
      }
      map.put(nums[i], i);
    }
    return new int[0];
  }

  /**
   * 旋转数组.
   */
  public static void rotate(int[][] matrix) {
    // 翻转数组
    for (int i = 0; i < matrix.length; i++) {
      for (int j = i; j < matrix[i].length; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }
    // 每一列上下颠倒
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length / 2; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][matrix[i].length - j - 1];
        matrix[i][matrix[i].length - 1 - j] = temp;
      }
    }
  }

  /**
   * 买卖股票的最佳时机 II.
   */
  public int maxProfit(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }
    int valley = prices[0];
    int peak = prices[0];
    int maxProfit = 0;
    int i = 0;
    while (i < prices.length) {
      while (i < prices.length - 1 && prices[i + 1] < prices[i]) {
        i++;
      }
      valley = prices[i];
      while (i < prices.length - 1 && prices[i + 1] > prices[i]) {
        i++;
      }
      peak = prices[i];
      maxProfit += peak - valley;
      i++;
    }
    return maxProfit;
  }

  /**
   * 寻找数组的中心索引.
   */
  public int pivotIndex(int[] nums) {
    if (nums.length <= 2) {
      return -1;
    }
    int total = 0;
    for (int i = 0; i < nums.length; i++) {
      total += nums[i];
    }
    int leftTotal = nums[0];
    for (int j = 1; j < nums.length; j++) {
      int rightTotal = total - leftTotal - nums[j];
      if (leftTotal == rightTotal) {
        return j;
      }
      leftTotal += nums[j];
    }
    return -1;
  }


  public static void main(String[] args) {
    int[][] array = new int[][]{
        {5, 1, 9, 11},
        {2, 4, 8, 10},
        {13, 3, 6, 7},
        {15, 14, 12, 16}
    };
    rotate(array);
    System.out.println(Arrays.toString(array));
  }

}
