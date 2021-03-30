package ie.tudublin;

import processing.core.PApplet;

public class LifeColour extends PApplet
{
    int size = 100; // variable that's going to store the board
    float cellSize; // width and height of every cell drawn on to the screen
    float[][] board = new float[size][size]; 
    float[][] next = new float[size][size];

    
    public int countCellsAround(int row, int col)
    {
        int count = 0;

        for(int r = row - 1; r <= row + 1; r++)
        {
            for(int c = col - 1; c <= col + 1; c++)
            {
                if(! (r == row && c == col)) // Not counting ourselves
                {
                    if(getCell(board, r, c))
                    {
                        count++;
                    }
                }
            }
        }
        return count;
    }


    // Accesser methods -> allow us to query the boolean array and say whether the element is true or false
    // Setter
    public void setCell(float[][] board, int row, int col, boolean b)
    {
        // Set the cell at position row and col to the value of b 
        // Bounce checking -> makes sure those 2 parameters don't go outside the range 
        // Making sure we can't write outside of the 2d array
        if(row >= 0 && row < size && col >= 0 && col < size)
        {
            board[row][col] = b; // Assign the boolean value into the 2d array
        }
    }

    // Getter
    public float getCell(float[][] board, int row, int col) // It will returen boolean
    {   
        if(row >= 0 && row < size && col >= 0 && col < size ) // Bounce checking
        {
            // Return the cell at position row and col 
            return board[row][col];
        }
        else
        {
            return 0;
        }
    }

    public void drawBoard(float[][] board)
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
                if(board[row][col] > 0) // If it's greater than 0, it's alive
                {
                    rect(x, y, cellSize, cellSize);
                    //fill(255);
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

    public void clear() // Set all the cells as false
    {
        for(int row = 0; row < size; row++)
        {
            for(int col = 0; col < size; col++)
            {
                setCell(board, row, col, false);
            }
        }
    }


    int mode = 0;
    boolean paused = false;
    public void keyPressed() 
    {
        if(keyCode == ' ')
        {
            paused = ! paused; // If paused is false, it will end up true
        }
        if(keyCode == '1')
        {
            randomize();
        }
        if(keyCode == '2')
        {
            clear();
        }
        if(keyCode == '3')
        {
            drawCross();
        }
    }

    public void drawCross()
    {
        for(int i = 0; i < size; i++)
        {
            setCell(board, size / 2, i, true); // size/2 -> halfway upward -> set row halfway up the columns
            setCell(board, i, size / 2, true); // size/2 -> halfway upward -> set col halfway up the rows
        }
    }

    public void setup()
    {
        colorMode(RGB);
        randomize();
        /*board[0][1] = true;
        board[1][2] = true;
        board[3][2] = true;*/

        println(countCellsAround(0, 2));
        
        //printBoard(board); // print it on the console

        // Variable width does not get assigned until after we call size() so we put the cellSize here
        cellSize = width / (size);

        frameRate(20); // 20 frames per second
    }

    private void updateBoard()
    {
        for(int row = 0; row < size; row++)
        {
            for(int col = 0; col < size; col++)
            {
                int count = countCellsAround(row, col);
                if(getCell(board, row, col)) // Alive cell
                {
                    if(count == 2 || count == 3)
                    {
                        setCell(next, row, col, true);
                        //next[row][col] == true;
                    }
                    else
                    {
                        setCell(next, row, col, false);
                        //next[row][col] == false;
                    }
                }
                else // Dead cell
                {
                    if(count == 3)
                    {
                        setCell(next, row, col, true);
                        //next[row][col] == true;
                    }
                    else
                    {
                        setCell(next, row, col, false);
                        //next[row][col] == false;
                    }
                }
            }
        }

        // Swap board and next
        boolean[][] temp = board;
        board = next;
        next = temp;
    }

    // This method gets called automatically when the mouse is dragged across the screen
    public void mouseDragged()
    {
        int row = (int) map(mouseY, 0, height, 0, size); // Function returns a float so i have to cast to an int 
        // When the mouseY is at the top of the screen, I'm going to get row 0
        // When the mouseY is all the way down bottom of the screen, I'm going to get the no. of rows
        int col = (int) map(mouseX, 0, width, 0, size);
        setCell(board, row, col, true); // Set the cells to be true and start the simulation 
    }


    public void draw()
    {
        background(0);
        drawBoard(board); // Gets drawn every frame
        if(! paused)
        {
            updateBoard();
        }
        

    }
}
