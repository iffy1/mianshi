package com.iffy.mianshi.algorithm.sort.practice;

//插入排序
public class InsertionSort {
    static int[] data = new int[]{5, 6, 3, 32, 13, 578, 6, 3, 325, 6, 7, 78, 6, 3, 32, 13, 5, 6, 7,
            78, 6, 3, 32, 13, 5, 6, 7, 78, 6, 3, 32, 13, 5, 6, 7, 78, 6, 3, 32, 13, 5, 6, 7, 78, 6,
            32, 13, 5, 6, 7, 78, 6, 3, 32, 13, 5, 6, 7, 78, 6, 3, 32, 13, 5, 6, 7, 78, 6, 3, 32, 13, 5, 6, 7,
            78, 6, 3, 32, 13, 5, 6, 7, 78, 6, 3, 32, 13, 5, 6, 7, 78, 6, 3, 32, 13, 5, 6, 7, 78, 6, 3, 32, 13, 5, 6, 7, 78};

    public static void main(String[] args) {
        printData();
        int size = data.length - 1;
        for (int i = 0; i < size; i++) {
            int j = i;
            while (j >= 0 && data[j + 1] < data[j]) {
                //交换数据
                int temp = data[j];
                data[j] = data[j + 1];
                data[j + 1] = temp;
                j--;
                printData();
            }
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
