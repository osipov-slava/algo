package ru.job4j.algo.greedy;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int tank = 0;
        int start = 0;
        int movements = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];
            movements++;

            if (movements == gas.length) {
                return totalGas >= totalCost ? start : -1;
            }
            if (tank < 0) {
                if (start == gas.length - 1 || i < start) {
                    return -1;
                }
                start = i + 1;
                tank = 0;
                totalCost = 0;
                totalGas = 0;
                movements = 0;
            }
            if (i == gas.length - 1) {
                i = -1;
            }
        }
        return start;
    }
}
