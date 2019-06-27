package com.jiehe.demo.sort;

import java.util.Arrays;

/**
 * 选择排序，时间复杂度O(n2),空间复杂度1.
 * <p>将它与数组的第一个元素交换位置。再从数组剩下的元素中选择出最小的元素，将它与数组的第二个元素交换位置。</p>
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {

  @Override
  public void sort(T[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      int minNumIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (less(arr[minNumIndex], arr[j])) {
          continue;
        }
        minNumIndex = j;
      }
      // 每次找出剩下中的最小值放到剩余元素的头部的位置
      swap(arr, i, minNumIndex);
    }
  }

  public static void main(String[] args) {
    Integer[] array = new Integer[]{6, 123, 64, 12, 756, 2, 34, 9, 564, 13, 4};
    SelectionSort<Integer> selectionSort = new SelectionSort<>();
    selectionSort.sort(array);
    System.out.println(Arrays.toString(array));
  }

}
