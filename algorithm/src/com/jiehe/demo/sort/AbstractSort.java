package com.jiehe.demo.sort;

public abstract class AbstractSort<T extends Comparable<T>> {

  public abstract void sort(T[] arr);

  protected boolean less(T var1, T var2) {
    return var1.compareTo(var2) < 0;
  }

  protected void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}
