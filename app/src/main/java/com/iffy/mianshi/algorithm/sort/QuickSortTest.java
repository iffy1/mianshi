package com.iffy.mianshi.algorithm.sort;

import java.util.ArrayList;

/**
 * author : iffy
 * time   : 2020/03/24
 */
public class QuickSortTest {
    static int[] data = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
    public static void main(String[] args) {
        sort(0, data.length - 1);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

    public static void sort(int start, int end) {
        //递归结束条件
        if (start > end) {
            return;
        }
        int pivotIndex = findPivot(start, end);
        sort(start, pivotIndex - 1);
        sort(pivotIndex + 1, end);

    }
    //第一个值作为分隔点
    public static int findPivot(int start, int end) {
        int left = start;
        int right = end;
        int pivot = data[start];
        while (left < right) {
            while (data[right] >= pivot && left < right) {
                right--;
            }
            while (data[left] <= pivot && left < right) {
                left++;
            }
            //交换 右边小于分隔值的值 与 左边大于分隔值的值
            swap(left, right);
        }
        //把分隔值放到中间
        swap(start, left);
        return left;
    }

    public static void swap(int a, int b) {
        int temp = data[b];
        data[b] = data[a];
        data[a] = temp;
    }
}
