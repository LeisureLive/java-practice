package com.jiehe.demo.leetcode.base;

import java.util.Arrays;

public class ArraySolution {


  public static void rotate(int[][] matrix) {
    // 翻转数组
    for (int i = 0; i < matrix.length; i++) {
      for (int j = i; j < matrix[i].length; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }
    // 每一列上下颠倒
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length / 2; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][matrix[i].length - j - 1];
        matrix[i][matrix[i].length - 1 - j] = temp;
      }
    }
  }

  public static void main(String[] args) {
    int[][] array = new int[][]{
        {5, 1, 9, 11},
        {2, 4, 8, 10},
        {13, 3, 6, 7},
        {15, 14, 12, 16}
    };
    rotate(array);
    System.out.println(Arrays.toString(array));
  }

}
