package com.jiehe.demo.sort;

import java.util.Arrays;

/**
 * 冒泡排序.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSort<T> {

  @Override
  public void sort(T[] arr) {
    boolean isSorted = false;
    for (int i = arr.length - 1; i > 0 && !isSorted; i--) {
      isSorted = true;
      for (int j = 0; j < i; j++) {
        if (less(arr[j + 1], arr[j])) {
          // 如果一次循环中没有进行交换，说明已经有序了.
          isSorted = false;
          swap(arr, j, j + 1);
        }
      }
    }
  }

  public static void main(String[] args) {
    Integer[] array = new Integer[]{6, 123, 64, 12, 756, 2, 34, 9, 564, 13, 4};
    BubbleSort<Integer> bubbleSort = new BubbleSort<>();
    bubbleSort.sort(array);
    System.out.println(Arrays.toString(array));
  }

}
