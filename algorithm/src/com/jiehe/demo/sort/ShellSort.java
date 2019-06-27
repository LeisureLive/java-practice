package com.jiehe.demo.sort;

import java.util.Arrays;

/**
 * 希尔排序,使用插入排序对间隔 h 的序列进行排序。通过不断减小 h，最后令 h=1，就可以使得整个数组是有序的.
 * <p>改进版插入排序，不稳定，最好O(N),最差O(n^2),空间复杂度O(1)</p>
 */
public class ShellSort<T extends Comparable<T>> extends AbstractSort<T> {

  @Override
  public void sort(T[] arr) {
    // 首次的increasement取长度除3加1
    int h = arr.length / 3 + 1;
    while (h >= 1) {
      for (int i = h; i < arr.length; i++) {
        for (int j = i; j >= h; j -= h) {
          if (less(arr[j], arr[j - h])) {
            swap(arr, j, j - h);
          }
        }
      }
      h = h / 3;
    }
  }

  public static void main(String[] args) {
    Integer[] array = new Integer[]{6, 123, 64, 12, 756, 2, 34, 9, 564, 13, 4};
    ShellSort<Integer> shellSort = new ShellSort<>();
    shellSort.sort(array);
    System.out.println(Arrays.toString(array));
  }

}
