package com.jiehe.demo.sort;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> extends AbstractSort<T> {

  @Override
  public void sort(T[] arr) {
    int N = arr.length - 1;
    // 构建一个最大堆
    for (int k = N / 2; k >= 1; k--) {
      sink(arr, k, N);
    }
    // 将头部最大的元素与尾部交换，交换后新头部沉降到合适位置.再将头部与倒数第二个元素交换，一次类推.，
    while (N > 1) {
      swap(arr, 1, N--);
      sink(arr, 1, N);
    }
  }

  private void sink(T[] nums, int k, int N) {
    while (2 * k <= N) {
      int j = 2 * k;
      if (j < N && less(nums[j], nums[j + 1])) {
        j++;
      }
      if (!less(nums[k], nums[j])) {
        break;
      }
      swap(nums, j, k);
      k = j;
    }
  }

  public static void main(String[] args) {
    Integer[] array = new Integer[]{null, 6, 123, 64, 12, 756, 2, 34, 9, 564, 13, 4};
    HeapSort<Integer> heapSort = new HeapSort<>();
    heapSort.sort(array);
    System.out.println(Arrays.toString(array));
  }

}
