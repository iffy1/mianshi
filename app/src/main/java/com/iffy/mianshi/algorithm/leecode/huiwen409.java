package com.iffy.mianshi.algorithm.leecode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * author : iffy
 * time   : 2020/03/19
 */
public class huiwen409 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aabbccddd"));
    }

    public static int longestPalindrome(String s) {

        int result = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
//            System.out.println(s.charAt(i) + "");
//            System.out.println("map:" + map);
            int count = 0;
            try {
                count = map.get(s.charAt(i));
            } catch (NullPointerException e) {
                map.put(s.charAt(i), 1);
            }
            map.put(s.charAt(i), ++count);
        }
        int singlenumber = 0;
        Iterator<Map.Entry<Character, Integer>> entrySet = map.entrySet().iterator();
        while (entrySet.hasNext()) {
            int value = entrySet.next().getValue();
            if (value % 2 == 0) {
                result += value;
            } else if (value > 1) {
                result += value - 1;
                singlenumber++;
            } else if (value == 1) {
                singlenumber++;
            }
        }
        if (singlenumber == 0) {
            return result;
        } else {
            return result + 1;
        }

    }

}
