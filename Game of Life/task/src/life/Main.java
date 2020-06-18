package life;
import java.util.*;

public class Main extends Thread{
    private static String[] icons = {" ", "O"};
    private static int size = 20;
    private static String[][] matrix = new String[size][size];
    private static GameOfLife game = new GameOfLife();
    private static boolean pause = true;
    private static int k = 1;

    public static boolean isPause() {
        return pause;
    }

    public static void setPause(boolean pause) {
        Main.pause = pause;
    }

    public static int getSize() {
        return size;
    }

    public static void firstGeneration() {
        Random random = new Random();
        k = 1;
        int alive = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = icons[random.nextInt(2)];
                if (matrix[i][j].equals(icons[1])) alive++;
            }
        }
        game.draw(matrix, alive, 0);
    }

    public void run() {
        while (pause) {
            String[][] nextMatrix = new String[size][size];
            int alive = 0;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (matrix[i][j].equals(icons[0]) && check(i, j) == 3) {
                        nextMatrix[i][j] = icons[1];
                        alive++;
                    } else if (matrix[i][j].equals(icons[1]) && (check(i, j) <= 3 && check(i, j) >= 2)) {
                        nextMatrix[i][j] = icons[1];
                        alive++;
                    } else {
                        nextMatrix[i][j] = icons[0];
                    }
                }
            }

            matrix = nextMatrix;
            game.draw(matrix, alive, k);
            if (alive == 0) break;
            k++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static int check(int i, int j) {
        int count = 0;
        if (matrix[i][j].equals("O")) count--;
        i--;
        j--;
        for (int r = i; r < i + 3; r++) {
            for (int c = j; c < j + 3; c++) {
                int x = r;
                int y = c;
                if (r == -1) x = size - 1;
                if (r == size) x = 0;
                if (c == -1) y = size -1;
                if (c == size) y = 0;
                if (matrix[x][y].equals(icons[1])) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Main main = new Main();
        firstGeneration();
        main.start();
    }
}