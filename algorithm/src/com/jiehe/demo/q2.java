package com.jiehe.demo;

/**
 * 长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内,找出任意一个重复的数字，O(n).
 */
public class q2 {

    public static void main(String[] args) {
        int array[] = new int[]{0, 1, 2, 2, 5, 4, 3};
        int duplicateNum = findDuplicate(array);
        System.out.println("duplicated num is " + duplicateNum);
    }

    private static int findDuplicate(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = array[i];
            if (i == j) {
                continue;
            }
            int temp = array[j];
            if (j == temp) {
                return j;
            }
            array[i] = temp;
            array[j] = j;
        }
        return -1;
    }

}
