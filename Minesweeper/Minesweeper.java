import java.util.Random;
class GameBoard
{
    static final int Size=4;
    static int matrix[][]=new int[Size][Size];
    GameBoard()
    {
            initializeboardwith180();
    }
    static void initializeboardwith180()
    {
        Random random=new Random();
        for(int i=0;i<Size;i++)
        {
            for(int j=0;j<Size;j++)
            {
                int randomnumber=random.nextInt(3);
                if(randomnumber==0)
                    matrix[i][j]=0;//Here 0 is used to indicate Bomb
                else if(randomnumber==1)
                   matrix[i][j]=1;//Here 1 is used to indicate 
                
            }
        }
    }
}















public class Minesweeper {
    public static void main(String[] args) 
    {
        GameBoard gameboard=new GameBoard();
    }
    
}
