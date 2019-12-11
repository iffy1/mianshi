package com.iffy.mianshi.algorithm.leecode;


class once136 {
    class Solution {
        public int singleNumber(int[] data) {
            return sort(data);

        }

        public int sort(int[] data) {
            if (data.length == 1) {
                return data[0];
            }
            for (int i = 0; i < data.length - 1; i++) {
                boolean replaced = false;
                for (int j = 0; j < data.length - 1; j++) {
                    if (data[j] > data[j + 1]) {
                        int temp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                        replaced = true;
                    }
                }
                if (!replaced) {
                    break;
                }
            }

            int a = data[data.length - 1];
            int b = data[data.length - 2];
            if (a != b) {
                return a;
            }

            for (int k = 0; k < data.length - 1; k = k + 2) {
                if (data[k] != data[k + 1]) {
                    System.out.println("aaaaaaaaaaaa" + data[k]);
                    return data[k];
                }
            }
            return 0;
        }
    }

}

