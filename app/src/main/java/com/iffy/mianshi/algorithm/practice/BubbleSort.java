package com.iffy.mianshi.algorithm.practice;

class BubbleSort {
    public static void main(String[] args) {
        int[] data = new int[]{1, 3, 5, 6, 78, 5};
        int size = data.length - 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
        }

        for (int i : data
        ) {
            System.out.print(i);
        }
        System.out.println();
    }
}