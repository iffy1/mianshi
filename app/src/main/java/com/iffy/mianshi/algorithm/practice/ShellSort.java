package com.iffy.mianshi.algorithm.practice;

//希尔排序是插入法的增强版
public class ShellSort {
    static int[] data = new int[]{5, 6, 3, 8, 1, 4, 2, 0, 7};
    public static void main(String[] args) {
        printData();
        int size = data.length - 1;
        int gap = size / 2;
        while (gap > 0) {
            for (int i = 0; i < size-gap; i++) {
                int j = i;
                while (j >= 0 && data[j + gap] < data[j]) {
                    //交换数据
                    int temp = data[j];
                    data[j] = data[j + gap];
                    data[j + gap] = temp;
                    j-=gap;
                    printData();
                }
            }
            gap /= 2;
        }
    }

    public static void printData() {
        for (int a : data) {
            System.out.print(a);
            System.out.print(",");
        }
        System.out.println();
    }
}
