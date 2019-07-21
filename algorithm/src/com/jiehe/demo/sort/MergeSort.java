package com.jiehe.demo.sort;

import java.util.Arrays;

/**
 * 归并排序,二分数组到最小为1，然后对每个小数组合并并保持有序.时间复杂度NlogN，空间复杂度N.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {

  // 辅助数组.
  protected T[] aux;

  @Override
  public void sort(T[] arr) {
    aux = (T[]) new Comparable[arr.length];
    sort(arr, 0, arr.length - 1);
  }

  private void sort(T[] arr, int start, int end) {
    if (start >= end) {
      return;
    }
    int mid = (end - start) / 2 + start;
    sort(arr, start, mid);
    sort(arr, mid + 1, end);
    merge(arr, start, mid, end);
  }

  private void merge(T[] arr, int start, int mid, int end) {
     System.arraycopy(arr, start, aux, start, end - start + 1);
//    for (int i = start; i <= end; i++) {
//      aux[i] = arr[i];
//    }
    int i = start;
    int j = mid + 1;
    int index = start;
    while (i <= mid || j <= end) {
      if (i > mid) {
        arr[index++] = aux[j++];
      } else if (j > end) {
        arr[index++] = aux[i++];
      } else if (less(aux[i], aux[j])) {
        arr[index++] = aux[i++];
      } else {
        arr[index++] = aux[j++];
      }
    }
  }

  public static void main(String[] args) {
    Integer[] array = new Integer[]{6, 123, 64, 12, 756, 2, 34, 9, 564, 13, 4};
    MergeSort<Integer> mergeSort = new MergeSort<>();
    mergeSort.sort(array);
    System.out.println(Arrays.toString(array));
  }

}
