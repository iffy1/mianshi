package com.iffy.mianshi.algorithm.sort.practice;

public class SelectSort {
    static int[] data = new int[]{5, 6, 3, 32, 13, 5, 6, 7, 78};

    public static void main(String[] args) {
        printData();
        int size = data.length - 1;
        for (int i = 0; i < size; i++) {
            int index = i;
            //找到最小的值索引
            for (int j = i + 1; j < size; j++) {
                if (data[j] < data[index]) {
                    index = j;
                }
            }
            //交换
            int temp = data[i];
            data[i] = data[index];
            data[index] = temp;
            printData();
        }

    }

    public static void printData(){
        for(int a:data){
            System.out.print(a);
            System.out.print(",");
        }
        System.out.println();
    }

}
