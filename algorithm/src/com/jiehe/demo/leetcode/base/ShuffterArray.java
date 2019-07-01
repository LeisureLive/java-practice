package com.jiehe.demo.leetcode.base;

import java.util.Random;

/**
 * @Author: Jiehe, leisurehejie@sina.com
 * @Date: 2019/6/30 13:33
 */
public class ShuffterArray {

  class Solution {

    private final int[] array;
    private Random random = new Random();

    public Solution(int[] nums) {
      this.array = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
      return this.array;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
      int[] result = new int[this.array.length];
      System.arraycopy(array, 0, result, 0, array.length);
      for (int i = 0; i < this.array.length; i++) {
        int j = randomInt(i, array.length);
        swap(result, i, j);
      }
      return result;
    }

    private void swap(int[] arr, int i, int j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }

    private int randomInt(int min, int max) {
      return random.nextInt(max - min) + min;
    }

  }

}
