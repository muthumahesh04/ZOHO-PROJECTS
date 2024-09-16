import java.util.Random;
import java.util.Scanner;

public class Minesweeper 
{
    private static final int SIZE = 5;
    private static final int BOMBS = 3;//Here i assigned 3 bombs
    private static char[][] board = new char[SIZE][SIZE];
    private static boolean[][] bombs = new boolean[SIZE][SIZE];
    private static boolean[][] revealed = new boolean[SIZE][SIZE];

    public static void main(String[] args) 
    {
        initializeBoard();
        placeBombs();
        playGame();
    }

    private static void initializeBoard() 
    {
        for (int i = 0; i < SIZE; i++) 
        {
            for (int j = 0; j < SIZE; j++) 
            {
                board[i][j] = '-';
                revealed[i][j] = false;
            }
        }
    }

    private static void placeBombs() 
    {
        Random rand = new Random();
        int placedBombs = 0;

        while (placedBombs < BOMBS) 
        {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);
            if (!bombs[row][col]) //if the cell already has the bomb,then it is never executed.Definitely Bombs randonly placed
            {
                bombs[row][col] = true;
                placedBombs++;
            }
        }
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        int cellsRevealed = 0;
        int totalCells = SIZE * SIZE - BOMBS;

        while (!gameOver) 
        {
            printBoard();
            System.out.print("Enter row and column (0-" + (SIZE - 1) + "): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) 
            {
                System.out.println("Invalid coordinates. Try again.");
                continue;
            }

            if (bombs[row][col]) 
            {
                System.out.println("Boom! You hit a Bomb. Game Over.");
                gameOver = true;
            } 
            else 
            {
                revealCell(row, col);
                cellsRevealed++;
                if (cellsRevealed == totalCells) 
                {
                    System.out.println("Congratulations! You have won!");
                    gameOver = true;
                }
            }
        }
        scanner.close();
        printBombs();
    }

    private static void revealCell(int row, int col) 
    {
        if (revealed[row][col])
              return;
    
        revealed[row][col] = true;
    
        int bombCount = countAdjacentBombs(row, col);

        if (bombCount > 0) 
        {
            board[row][col] = (char) (bombCount + '0');
        } 
        else 
        {
            board[row][col] = ' ';
            for (int i = row - 1; i <= row + 1; i++) 
            {
                for (int j = col - 1; j <= col + 1; j++) 
                {
                    if (i >= 0 && i < SIZE && j >= 0 && j < SIZE) 
                    {
                        revealCell(i, j);
                    }
                }
            }
        }
    }

    private static int countAdjacentBombs(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) 
        {
            for (int j = col - 1; j <= col + 1; j++) 
            {
                if (i >= 0 && i < SIZE && j >= 0 && j < SIZE && bombs[i][j]) 
                {
                    count++;
                }
            }
        }
        return count;
    }

    private static void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < SIZE; i++) 
        {
            for (int j = 0; j < SIZE; j++) 
            {
                if (revealed[i][j]) 
                {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    private static void printBombs() 
    {
        System.out.println("Bombs:");
        for (int i = 0; i < SIZE; i++) 
        {
            for (int j = 0; j < SIZE; j++) 
            {
                if (bombs[i][j]) 
                {
                    System.out.print("* ");
                } 
                else 
                {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}