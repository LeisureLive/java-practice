package com.jiehe.demo.leetcode.base;

/**
 * @Author: Jiehe, leisurehejie@sina.com
 * @Date: 2019/6/29 23:38
 */
public class DynamicPrograming {

  /**
   * 爬楼梯.
   */
  public int climbStairs(int n) {
    int[] arr = new int[n + 1];
    arr[1] = 1;
    arr[2] = 2;
    if (n <= 1) {
      return 1;
    }
    if (n == 2) {
      return 2;
    }
    int i = 1;
    int j = 2;
    int temp = 0;
    while (j < n) {
      temp = arr[i++] + arr[j++];
      arr[j] = temp;
    }
    return temp;
  }

  /**
   * 买卖股票的最佳时机.
   */
  public int maxProfit(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }
    int[] arr = new int[prices.length];
    arr[0] = 0;
    int cur = 1;
    // 记录卖出价格
    int max = prices[0];
    // 记录之前时间的最低价.
    int min = prices[0];
    while (cur < prices.length) {
      // 利润更高，替换卖出价.
      if (prices[cur] - min > arr[cur - 1]) {
        arr[cur] = prices[cur] - min;
        max = prices[cur];
      } else if (prices[cur] > max) {
        arr[cur] = arr[cur - 1] + (prices[cur] - max);
        max = prices[cur];
      } else {
        arr[cur] = arr[cur - 1];
      }
      if (prices[cur] < min) {
        min = prices[cur];
      }
      cur++;
    }
    return arr[prices.length - 1];
  }

  /**
   * 最大子序和.
   */
  public int maxSubArray(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    int head = 0;
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (dp[i - 1] + nums[i] > nums[i]) {
        dp[i] = dp[i - 1] + nums[i];
      } else {
        head = i;
        dp[i] = nums[i];
      }
    }
    int max = dp[0];
    for (int j = 1; j < dp.length; j++) {
      if (dp[j] > max) {
        max = dp[j];
      }
    }
    return max;
  }

  /**
   * 打家劫舍.
   */
  public int rob(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    dp[1] = Math.max(nums[0], nums[1]);
    for (int i = 2; i < nums.length; i++) {
      if (dp[i - 2] + nums[i] > dp[i - 1]) {
        dp[i] = dp[i - 2] + nums[i];
      } else {
        dp[i] = dp[i - 1];
      }
    }
    int max = dp[0];
    for (int i : dp) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

}
