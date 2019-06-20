package com.jiehe.demo.sort;

import java.util.Arrays;

/**
 * 插入排序.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSort<T> {

  @Override
  public void sort(T[] arr) {
    for (int i = 1; i < arr.length; i++) {
      for (int j = i; j > 0 && less(arr[j], arr[j - 1]); j--) {
        swap(arr, j, j - 1);
      }
    }
  }

  public static void main(String[] args) {
    Integer[] array = new Integer[]{6, 123, 64, 12, 756, 2, 34, 9, 564, 13, 4};
    InsertionSort<Integer> insertionSort = new InsertionSort<>();
    insertionSort.sort(array);
    System.out.println(Arrays.toString(array));
  }

}
