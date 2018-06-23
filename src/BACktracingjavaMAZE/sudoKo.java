package BACktracingjavaMAZE;

public class sudoKo {

    private int[][] matrix;

    public sudoKo(int[][] matrix) {
        this.matrix = matrix;
    }

    public static void main(String[] args) {
        // 号称世界上最难数独
        int[][] sudoku = {
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}};
        sudoKo s = new sudoKo(sudoku);
        s.backTrace(0, 0);
    }

    /**
     * 数独算法
     *
     * @param i 行号
     * @param j 列号
     */
    private void backTrace(int i, int j) {
    //结束语始终放在最前面判断，j=9是因为j最后加了一下
        if (i == 8 && j == 9) {
            //已经成功了，打印数组即可
            System.out.println("获取正确解");
            print();
            return;
        }
        //已经到了列末尾了，还没到行尾，就换行,经典换行法子
        if (j == 9) {
            i++;
            j = 0;
        }
        if (matrix[i][j] == 0) {
            for (int k = 1; k < 10; k++) {
                if (check(i, j, k)) {
                    matrix[i][j] = k;
                    //这里不能写j++，而应该是j+1;
                    //backTrace(i, j++);
                    backTrace(i, j + 1);

                    //初始化该空格
                    matrix[i][j] = 0;
                }
            }
        } else {
            backTrace(i, j + 1);
        }
    }

    //k: 被赋值的数字
    boolean check(int row, int line, int number) {
        //方阵,9x9的
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[row][i] == number || matrix[i][line] == number) return false;
        }

        //还要判断一下小九宫格是否重复
        int startRow = (row / 3) * 3;
        for (; startRow < ((row / 3) + 1) * 3; startRow++) {
            for (int startLine = (line / 3) * 3; startLine < ((line / 3) + 1) * 3; startLine++) {
                if (matrix[startRow][startLine] == number && (startLine != line) && (startRow != row))
                    return false;
            }
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}