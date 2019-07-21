package com.jiehe.demo.sort;

import java.util.Arrays;

/**
 * 快速排序，选择最左边一个元素，将大于他的元素放到数组右边，小于他的元素放到数组左边，依次对2分数组进行.
 * <p>时间复杂度NlogN,空间复杂度logN</p>
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> {

  @Override
  public void sort(T[] arr) {
    threeWaySort(arr, 0, arr.length - 1);
  }

  private void sort(T[] arr, int start, int end) {
    if (start >= end) {
      return;
    }
    int mid = partition(arr, start, end);
    sort(arr, start, mid);
    sort(arr, mid + 1, end);
  }

  /**
   * 三路快排，适用于重复元素较多.
   */
  private void threeWaySort(T arr[], int start, int end) {
    if (start >= end) {
      return;
    }
    int lt = start;
    int gt = end;
    int i = start + 1;
    T val = arr[start];
    while (i <= gt) {
      int res = arr[i].compareTo(val);
      if (res > 0) {
        swap(arr, i, gt--);
      } else if (res < 0) {
        swap(arr, i++, lt++);
      } else {
        i++;
      }
    }
    threeWaySort(arr, start, lt - 1);
    threeWaySort(arr, gt + 1, end);
  }

  private int partition(T[] arr, int start, int end) {
    T val = arr[start];
    int i = start;
    int j = end + 1;
    while (i < j) {
      while (i < end && less(arr[++i], val)) {
      }
      while (j > start && less(val, arr[--j])) {
      }
      if (i < j) {
        swap(arr, i, j);
      }
    }
    swap(arr, start, j);
    return j;
  }

  public static void main(String[] args) {
    Integer[] array = new Integer[]{6, 123, 64, 12, 756, 2, 34, 9, 564, 13, 4};
    QuickSort<Integer> quickSort = new QuickSort<>();
    quickSort.sort(array);
    System.out.println(Arrays.toString(array));
  }

}
