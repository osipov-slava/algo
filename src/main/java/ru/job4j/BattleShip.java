package ru.job4j;

public class BattleShip {
    private int rows;
    private int cols;
    private int[][] checkedMatrix;
    public int countAliveShips(int[][] sea) {
        var arr = new int[sea.length + 1][sea[0].length+1];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                if (sea[i-1][j-1] == 1 && ((j == 1 || sea[i-1][j-2] == 0) && (i == 1 || sea[i-2][j-1] == 0))
                ) {
                    arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]) + 1;
                } else {
                    arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);

                }
                if (j == arr.length - 1 && i < arr.length - 1) {
                    arr[i+1][0] = arr[i][j];
                }
            }
        }
        return arr[arr.length - 1][arr[0].length - 1];
    }
//    public int countAliveShips(int[][] sea) {
//        rows = sea.length;
//        cols = sea[0].length;
//        checkedMatrix = new int[rows][cols];
//        int ships = 0;
//
//        for (int i = 0; i < sea.length; i++) {
//            for (int j = 0; j < sea[0].length; j++) {
//                if (checkedMatrix[i][j] > 0) { //если эту ячейку еще не проверяли
//                    continue;
//                }
//                checkedMatrix[i][j] = 1; //помечаем ячейку проверенной
//                if (sea[i][j] == 0) { //если вода
//                    continue;
//                }
//                ships++;
//                if (horizontalSearch(sea, i, j)) { //проверяем только по горизонтали или вертикали
//                    continue;
//                }
//                verticalSearch(sea, i, j);
//            }
//        }
//        return ships;
//    }

    private void verticalSearch(int[][] sea, int i, int j) {
        int k = i + 1;
        if (k == rows || sea[k][j] == 0) { //уперлись в границу или корабль не продолжается в этом направлении
            return;
        }
        do {
            if (k == rows) { //если достигли границы выходим, минимум 1 раз пройдем
                return;
            }
            checkedMatrix[k][j] = 1;
            if (j + 1 < cols) {
                checkedMatrix[k][j + 1] = 1;
            }
        } while (sea[k++][j] == 1); //если корабль кончится проверяем в конце
    }

    private boolean horizontalSearch(int[][] sea, int i, int j) {
        int k = j + 1;
        if (k == cols || sea[i][k] == 0) { //уперлись в границу или корабль не продолжается в этом направлении
            return false;
        }
        do {
            if (k == cols) { //если достигли границы выходим, минимум 1 раз пройдем
                return true;
            }
            checkedMatrix[i][k] = 1;
            if (i + 1 < rows) {
                checkedMatrix[i + 1][k] = 1;
            }
        } while (sea[i][k++] == 1); //если корабль кончится проверяем в конце
        return true;
    }

}
