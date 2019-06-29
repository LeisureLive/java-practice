package com.jiehe.demo.leetcode.base;

/**
 * @Author: Jiehe, leisurehejie@sina.com
 * @Date: 2019/6/29 22:18
 */
public class SortAndSearchSolution {

  /**
   * 合并两个有序数组.
   */
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int cur = m + n - 1;
    while (m - 1 >= 0 && n - 1 >= 0) {
      if (nums1[m - 1] > nums2[n - 1]) {
        nums1[cur] = nums1[m - 1];
        m--;
      } else {
        nums1[cur] = nums2[n - 1];
        n--;
      }
      cur--;
    }
    System.arraycopy(nums2, 0, nums1, 0, n + 1);
  }

  /**
   * 第一个错误的版本.
   */
  public int firstBadVersion(int n) {
    if (n <= 1) {
      return n;
    }
    int left = 1;
    int right = n;
    while (left < right) {
      // 此处用(left+right)/2 可能会溢出
      int mid = left + (right - left) / 2;
      if (isBadVersion(mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }


  boolean isBadVersion(int version) {
    if (version >= 4) {
      return true;
    }
    return false;
  }

}
