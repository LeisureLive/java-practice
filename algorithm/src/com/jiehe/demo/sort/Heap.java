package com.jiehe.demo.sort;

/**
 * 堆排序，堆中某个节点的值总是大于等于其子节点的值.
 */
public class Heap<T extends Comparable<T>> {

  /**
   * 存储堆结构的内部数组,不使用index=0的位置.
   */
  private T[] arr;

  /**
   * 堆中当前的元素个数.
   */
  private int N = 0;

  public Heap(int maxN) {
    arr = (T[]) new Comparable[maxN + 1];
  }

  private boolean less(int i, int j) {
    return arr[i].compareTo(arr[j]) < 0;
  }

  private void swap(int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private void swim(int k) {
    while (k > 1 && less(k / 2, k)) {
      swap(k, k / 2);
    }
  }

  private void sink(int k) {
    while (2 * k <= N) {
      int j = 2 * k;
      if (j < N && less(j, j++)) {
        // 下沉时如果有两个子节点，与较大的一个交换.
        j++;
      }
      if (!less(k, j)) {
        break;
      }
      swap(k, j);
      k = j;
    }
  }

  private void insert(T v) {
    arr[++N] = v;
    swim(N);
  }

  private T delMax() {
    T max = arr[1];
    swap(1, N);
    arr[N--] = null;
    sink(1);
    return max;
  }

}
