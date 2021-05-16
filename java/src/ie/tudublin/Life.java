package ie.tudublin;

import javazoom.jl.decoder.FrameDecoder;
import processing.core.PApplet;

public class Life extends PApplet 
{
    int size = 100; 
    float cellSize;
    boolean[][] board = new boolean[size][size]; // This variable will be making the array of 2 dimensions
    
    public int countCellsAround(int row, int col)
    {
        int count = 0;

        for(int r = row - 1; r <= row + 1; r ++)
        {
            for(int c = col - 1; c <= col + 1; c ++)
            {
                if(r != row && c != col)
                {
                    if(getCell(board, r ,c))
                    {
                        count ++;
                    }
                }
            }
        }

        // Use getCell here
        /*
        if(getCell(board, row-1, col-1)) // one above on to the left
        {
            count ++;
        }

        if(getCell(board, row-1, col)) // one directly above
        {
            count ++;
        }

        if(getCell(board, row-1, col+1))
        {
            count ++;
        }

        if(getCell(board, row, col-1))
        {
            count ++;
        }

        if(getCell(board, row, col+1))
        {
            count ++;
        }

        if(getCell(board, row+1, col-1))
        {
            count ++;
        }

        if(getCell(board, row+1, col))
        {
            count ++;
        }

        if(getCell(board, row+1, col+1))
        {
            count ++;
        }

        if(getCell(board, row+1, col+1))
        {
            count ++;
        }
        */

        return count;
    }

    public void setCell(boolean[][] board, int row, int col, boolean b)
    {
        if(row > 0 && row < size - 1 && col > 0 && col < size - 1)
        {
            board[row][col] = b;
        }
    }

    public boolean getCell(boolean[][] board, int row, int col)
    {
        if(row > 0 && row < size - 1 && col > 0 && col < size - 1)
        {
            return board[row][col];
        }
        else
        {
            return false;
        }
    }

    public void drawBoard(boolean[][] board)
    {
         
        // Use a nested loop
        // Use map to calcuate x and y
        // Rect draw the cell
        // Use the cell size variable 
        // Use some colours 

        for(int row = 0; row < size; row ++) 
        {
            for(int col = 0; col < size; col ++)
            {
                //float x = col * cellSize;
                //float y = row * cellSize;
                float x = map(col, 0, size, 0, width);
                float y = map(row, 0, size, 0, height);
                if(board[row][col])
                {
                    rect(x, y, cellSize, cellSize);
                }
            }
            println();
        }
    }

    private void printBoard(boolean[][] board)
    {
        for(int row = 0; row < size; row ++) // Iterating element by element through the array 
        {
            for(int col = 0; col < size; col ++)
            {
                print(board[row][col] ? 1 : 0);
            }
            println();
        }
    }

    public void randomize()// Assign random elements to this array
    {
        for(int row = 0; row < size; row ++) // Iterating element by element through the array 
        {
            for(int col = 0; col < size; col ++)
            {
                float dice = random(0.0f, 1.0f); // This will give me the random number
                /*
                if(dice < 0.5)
                {
                    board[row][col] = true;
                }

                else
                {
                    board[row][col] = false;
                }
                */
                board[row][col] = (dice < 0.5f) ? true : false;

            }
        }
    }

    public void settings()
    {
        size(500, 500); // 250000
    }

    int mode = 0;

    public void keyPressed()
    {
        // the value of mode will be the number of the
        // number key pressed
        if (keyCode >= '0' && keyCode <= '9')
            mode = keyCode - '0';
    }

    public void setup() 
    {
        colorMode(RGB);
        //randomize();
        board[0][1] = true;
        board[1][2] = true;
        board[3][2] = true;

        println(countCellsAround(0, 2));

        cellSize = width / (size);

        //printBoard(board);   
    }


    public void draw() {
        background(0);
        drawBoard(board);
    }
}
