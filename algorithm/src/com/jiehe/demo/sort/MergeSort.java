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
    if (end <= start) {
      return;
    }
    int mid = start + (end - start) / 2;
    // mid是前一个数组的尾部，mid+1是后一个数组的头部
    sort(arr, start, mid);
    sort(arr, mid + 1, end);
    merge(arr, start, mid, end);
  }

  private void merge(T[] arr, int start, int mid, int end) {
    int i = start;
    int j = mid + 1;

    for (int k = start; k <= end; k++) {
      // 将数据复制到辅助数组
      aux[k] = arr[k];
    }
    // 一个指针从整个数组头遍历到尾部，另外两个指针分别指向两块分数组的起始位置，依次比较，将较小的数放到遍历的位置
    for (int k = start; k <= end; k++) {
      // 如果某块分数组指针超过了尾部，则直接将另一个数组的元素依次遍历放到结果数组中.
      if (i > mid) {
        arr[k] = aux[j++];
      } else if (j > end) {
        arr[k] = aux[i++];
      } else if (less(arr[j], arr[i])) {
        arr[k] = aux[j++];
      } else {
        arr[k] = aux[i++];
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
