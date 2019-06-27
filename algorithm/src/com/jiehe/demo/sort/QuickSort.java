package com.jiehe.demo.sort;

import java.util.Arrays;

/**
 * 快速排序，选择最左边一个元素，将大于他的元素放到数组右边，小于他的元素放到数组左边，依次对2分数组进行.
 * <p>时间复杂度NlogN,空间复杂度logN</p>
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> {

  @Override
  public void sort(T[] arr) {
    sort(arr, 0, arr.length - 1);
  }

  private void sort(T[] arr, int start, int end) {
    if (end <= start) {
      return;
    }
    int midIndex = partition(arr, start, end);
    sort(arr, start, midIndex);
    sort(arr, midIndex + 1, end);
  }

  private int partition(T[] arr, int start, int end) {
    int i = start;
    int j = end + 1;
    T val = arr[i];
    while (true) {
      while (less(arr[++i], val) && i != end) {
      }
      while (less(val, arr[--j]) && j != start) {
      }
      if (i >= j) {
        break;
      }
      swap(arr, i, j);
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
