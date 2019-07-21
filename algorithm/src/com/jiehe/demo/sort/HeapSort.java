package com.jiehe.demo.sort;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> extends AbstractSort<T> {

  /**
   * 堆中节点i的父节点索引为(i-1)/2,两个孩子索引为2i+1 和 2i+2
   */
  @Override
  public void sort(T[] arr) {
    // 构建堆
    int N = arr.length - 1;
    int i = (N - 1) / 2;
    for (; i >= 0; i--) {
      sink(arr, i, N);
    }
    while (N > 0) {
      swap(arr, 0, N--);
      sink(arr, 0, N);
    }
  }

  /**
   * 构建最大堆，当节点小于孩子时，与孩子中最大的交换.
   */
  private void sink(T[] nums, int k, int N) {
    while (2 * k + 1 <= N) {
      int j = 2 * k + 1;
      if (j < N && less(nums[j], nums[j + 1])) {
        j = j + 1;
      }
      if (less(nums[k], nums[j])) {
        swap(nums, k, j);
      }
      k = j;
    }
  }

  private void siftUp(T[] nums, int k, int N) {

  }

  public static void main(String[] args) {
    Integer[] array = new Integer[]{6, 123, 64, 12, 756, 2, 34, 9, 564, 13, 4};
    HeapSort<Integer> heapSort = new HeapSort<>();
    heapSort.sort(array);
    System.out.println(Arrays.toString(array));
  }

}
