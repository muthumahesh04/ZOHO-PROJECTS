import java.util.Scanner;
import java.util.Random;

public class Fifteennumber 
{
    private static final int SIZE = 4;
    private static final int[][] solvedBoard = {{ 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },{ 13, 14, 15, 0 } };
    private int[][] board;
    private int emptyRow, emptyCol;

    Fifteennumber()
    {
        board = new int[SIZE][SIZE];
        spaceatrightbottomandiniitsposition();
        shuffleBoard();
    }

    // Initialize the board in a solved state
    private void spaceatrightbottomandiniitsposition()
    {
        int value = 1;
        for (int i = 0; i < SIZE; i++) 
        {
            for (int j = 0; j < SIZE; j++) 
            {
                if (value < SIZE * SIZE) 
                {
                    board[i][j] = value++;
                } 
                else 
                {
                    board[i][j] = 0; // Empty space
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }
    }

    // Shuffle the board 
    private void shuffleBoard() {
        Random random = new Random();
        
        for (int i = 0; i < 10; i++) 
        {
            int  direction = random.nextInt(4); 
            switch (direction)
            {
                case 0: moveUp(); break;
                case 1: moveDown(); break;
                case 2: moveLeft(); break;
                case 3: moveRight(); break;
            }
         }
      
    }

    // Move tiles
    private boolean moveTile(String direction) 
    {
        switch (direction) 
        {
            case "up": 
                  return moveUp();
            case "down": 
                  return moveDown();
            case "left": 
                  return moveLeft();
            case "right": 
                  return moveRight();
            default:
                 return false;
        }
    }

    private boolean moveUp() 
    {
        if (emptyRow < SIZE - 1 && emptyRow>=0) 
        {
            int temp= board[emptyRow][emptyCol];
            board[emptyRow][emptyCol] = board[emptyRow + 1][emptyCol];
            board[emptyRow + 1][emptyCol] = temp;
            emptyRow++;
            return true;
        }
        return false;
    }

    private boolean moveDown() 
    {
        if (emptyRow > 0 && emptyRow<=SIZE-1)
        {
            int temp=board[emptyRow][emptyCol];
            board[emptyRow][emptyCol] = board[emptyRow - 1][emptyCol];
            board[emptyRow - 1][emptyCol] = temp;
            emptyRow--;
            return true;
         }
        return false;
    }

    private boolean moveLeft() 
    {
        if (emptyCol < SIZE - 1 && emptyCol>=0) 
        {
            int temp= board[emptyRow][emptyCol];
            board[emptyRow][emptyCol] = board[emptyRow][emptyCol + 1];
            board[emptyRow][emptyCol + 1] = temp;
            emptyCol++;
            return true;
        }
        return false;
    }

    private boolean moveRight() 
    {
        if (emptyCol > 0 && emptyCol<=SIZE-1) 
        {
            int temp=board[emptyRow][emptyCol];
            board[emptyRow][emptyCol] = board[emptyRow][emptyCol - 1];
            board[emptyRow][emptyCol - 1] = temp;
            emptyCol--;
            return true;
        }
        return false;
    }

    // Check if the current board matches the solved board
    private boolean isSolved() 
    {
        for (int i = 0; i < SIZE; i++) 
        {
            for (int j = 0; j < SIZE; j++) 
            {
                if (board[i][j] != solvedBoard[i][j]) 
                {
                    return false;
                }
            }
        }
        return true;
    }


    private void printBoard() 
    {
        for (int i = 0; i < SIZE; i++) 
        {
            for (int j = 0; j < SIZE; j++) 
            {
                if (board[i][j] == 0) {
                    System.out.print("   ");  // Empty space
                } else {
                    System.out.printf("%2d ",board[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Fifteennumber game = new Fifteennumber();

        System.out.println("Welcome to the 15 Puzzle Game!");
        System.out.println("Use 'up', 'down', 'left', 'right' to move tiles.");
        

        while (!game.isSolved())
        {
            game.printBoard();
            System.out.print("Your move: ");
            String move = scanner.nextLine();

            if (!game.moveTile(move)) 
            {
                System.out.println("Invalid move! Try again.");
            }
        }

        System.out.println("Congratulations! You've solved the puzzle!");
        game.printBoard();
        scanner.close();
    }
}