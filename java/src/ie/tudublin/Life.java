package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet
{
    // What size of the board would be 
    // We have to map the board onto the screen cordinates
    int size = 100; // variable that's going to store the board
    float cellSize; // width and height of every cell drawn on to the screen
    // 500(screensize) / 100(boardsize) = 5
    boolean[][] board = new boolean[size][size]; // Declare boolean 2D array where we have each element being alive or dead
    // When you are specifying the elements of 2D array, it's apposite to scrren cordinates
    // For screen cordinates -> x is how far over you go, y is how far down you go screen
    // For 2D arrays -> row goes down to the screen, col goes across a screen

    public int countCellsAround(int row, int col)
    {
        int count = 0;

        // Use getCell here 
        // Check the cells around the current cell
        if(getCell(board, row-1, col-1)) // top left
        {
            count++;
        }
        if(getCell(board, row-1, col)) // One directly above
        {
            count++;
        }
        if(getCell(board, row-1, col+1)) // top right
        {
            count++;
        }
        if(getCell(board, row, col-1)) // one to the left
        {
            count++;
        }
        if(getCell(board, row, col+1)) // one to the right
        {
            count++;
        }
        if(getCell(board, row+1, col-1)) // bottom left
        {
            count++;
        }
        if(getCell(board, row+1, col)) // one directly below
        {
            count++;
        }
        if(getCell(board, row+1, col+1)) // bottom right
        {
            count++;
        }

        /*for(int r = row - 1; r <= row + 1; r++)
        {
            for(int c = col - 1; c <= col + 1; c++)
            {
                if(r != row && c != col) // Not counting ourselves
                {
                    if(getCell(board, r, c))
                    {
                        count++;
                    }
                }
            }
        }*/
        
        return count;
    }



    // Accesser methods -> allow us to query the boolean array and say whether the element is true or false
    // Setter
    public void setCell(boolean[][] board, int row, int col, boolean b)
    {
        // Set the cell at position row and col to the value of b 
        // Bounce checking -> makes sure those 2 parameters don't go outside the range 
        // Making sure we can't write outside of the 2d array
        if(row >= 0 && row < size - 1 && col >= 0 && col < size - 1)
        {
            board[row][col] = b; // Assign the boolean value into the 2d array
        }
    }

    // Getter
    public boolean getCell(boolean[][] board, int row, int col) // It will returen boolean
    {   
        if(row >= 0 && row < size - 1 && col >= 0 && col < size - 1) // Bounce checking
        {
            // Return the cell at position row and col 
            return board[row][col];
        }
        return false;
    }

    public void drawBoard(boolean[][] board)
    {
        // Use a nested loop
        // Use map to calculate x and y
        // Rect draw the cell
        // Use the cell size variable
        // Use some colours
        for(int row = 0; row < size; row++)
        {
            for(int col = 0; col < size; col++)
            {
                float x = map(col, 0, size, 0, width);
                //float x = col * cellSize;
                float y = map(row, 0, size, 0, height);
                //float y = row * cellSize;
                if(board[row][col]) // It's already a boolean expression so i don't need to say board == true
                {
                    rect(x, y, cellSize, cellSize);
                    fill(255);
                }
                
            }
        }

    }

    
    private void printBoard(boolean[][] board)
    {
        // col -> x, row -> y
        for(int row = 0; row < size; row++)
        {
            for(int col = 0; col < size; col++)
            {
                print(board[row][col] ? 1 : 0);
            }
            println();
        }
    }

    // Assign random elements to the 2D array
    public void randomize()
    {   
        for(int row = 0; row < size; row++)
        {
            for(int col = 0; col < size; col++)
            {
                float dice = random(0.0f, 1.0f); // This will give me a random no. that goes between 0 and 1
                /*if(dice < 0.5) // Set half of them as true and half of them as false
                {
                    board[row][col] = true;
                }
                else
                {
                    board[row][col] = false;
                }*/
                board[row][col] = (dice < 0.5f) ? true : false;
            }
        }
    }

    public void settings()
    {
        size(500, 500);
    }

    int mode = 0;
    public void keyPressed() 
    {
        // The value of mode will be the number of the numbe key pressed
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
        
        //printBoard(board); // print it on the console

        // Variable width does not get assigned until after we call size() so we put the cellSize here
        cellSize = width / (size);
    }

    public void draw()
    {
        background(0);
        drawBoard(board); // Gets drawn every frame

    }
}
