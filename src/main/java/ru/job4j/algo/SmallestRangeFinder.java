package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int start = 0;
        int end = 0;
        for (int i = 1; i < nums.length; i++) {
               if (nums[end] != nums[i]) {
                   end++;
                   if (end - start == k - 1) {
                       return new int[]{start, end};
                   }
               } else {
                   start = i;
                   end = i;
               }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 5, 6, 7};
        int k = 4;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}