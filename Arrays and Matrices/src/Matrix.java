public class Matrix {

    public static void main(String[] args) {

        // Structure Matrix
        int sales[][]; // the first index represents lines, and the second index represent columns

        int salesTwo[][] = new int[5][4];
        /*
         * [] [] [] []
         * [] [] [] []
         * [] [] [] []
         * [] [] [] []
         * [] [] [] []
         * */

        System.out.println(salesTwo[1][2]); // line 1 and column 2

        int[][] salesThree = {
                {35, 18, 12, 22},
                {40, 21, 15, 25},
                {32, 20, 17, 19},
                {45, 24, 13, 30},
                {58, 31, 20, 42}
        };

        for (int line = 0; line < salesThree.length; line++) {
            for (int column = 0; column < salesThree[line].length; column++) {
                System.out.print(salesThree[line][column] + " ");
            }
            System.out.println();
        }

    }
}
