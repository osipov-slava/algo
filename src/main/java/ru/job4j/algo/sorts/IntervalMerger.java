package ru.job4j.algo.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class IntervalMerger {

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return null;
        }
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        ArrayList<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int j = result.size() - 1;
            if (result.get(j)[1] >= intervals[i][0] && result.get(j)[1] < intervals[i][1]) {
                result.get(j)[1] = intervals[i][1];
            } else {
                result.add(intervals[i]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
