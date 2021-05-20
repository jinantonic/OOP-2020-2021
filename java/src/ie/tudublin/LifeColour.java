package ie.tudublin;

import processing.core.PApplet;

public class LifeColour extends PApplet
{
    int size = 200; // variable that's going to store the board
    float cellSize; // width and height of every cell drawn on to the screen
    float[][] board = new float[size][size]; 
    float[][] next = new float[size][size];

    public void clear() // Set all the cells as false
    {
        for(int row = 0; row < size; row ++)
        {
            for(int col = 0; col < size; col ++)
            {
                setCell(board, row, col, -1);
            }
        }
    }

    
    public int countNeighbours(int row, int col)
    {
        int count = 0;

        for(int r = row - 1; r <= row + 1; r ++)
        {
            for(int c = col - 1; c <= col + 1; c ++)
            {
                if(! (r == row && c == col)) // Not counting ourselves
                {
                    if(getCell(board, r, c) > 0)
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
    public void setCell(float[][] board, int row, int col, float b)
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
                float y = map(row, 0, size, 0, height);
                float c = getCell(board, row, col);
                if(c > 0) // If it's greater than 0, it's alive
                {
                    noStroke();
                    fill(c, 255, 255);
                    rect(x, y, cellSize, cellSize);
                }
            }
        }
    }
    
    
    private void printBoard(boolean[][] board)
    {
        // col -> x, row -> y
        for(int row = 0; row < size; row ++)
        {
            for(int col = 0; col < size; col ++)
            {
                print(board[row][col] ? 1 : 0);
            }
            println();
        }
    }

    // Assign random elements to the 2D array
    public void randomize()
    {   
        for(int row = 0; row < size; row ++)
        {
            for(int col = 0; col < size; col ++)
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
                board[row][col] = (dice < 0.5f) ? random(255) : -1; // random value between 0 and 255, otherwise we set the value as 0
            }
        }
    }

    public void settings()
    {
        size(800, 800);
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
        for(int i = 0; i < size; i ++)
        {
            setCell(board, size / 2, i, random(255)); // size/2 -> halfway upward -> set row halfway up the columns
            setCell(board, i, size / 2, random(255)); // size/2 -> halfway upward -> set col halfway up the rows
        }
    }

// Take the average of 3 surrounding colours and use that to generate the new cell when the cell comes to life
    // I'm only going to call this method if there's exactly 3 neighbours
    public float averageAround(float[][] board, int row, int col)
    {
        float xsum = 0;
        float ysum = 0;
        // Sum up the 3 surrounding values and divide by 3
        for(int r = row - 1; r <= row + 1; r ++)
        {
            for(int c = col - 1; c <= col + 1; c ++)
            {
                float color = getCell(board, r, c);
                if(!(r == row && c == col) && color != -1) // Not counting ourselves
                {
                    float angle = map(color, 0, 255, -PI, PI);// has to go between -pi and pi
                    xsum += cos(angle); // cosine of angle
                    ysum += sin(angle); // sin of the angle
                } 
            }
        }

        xsum /= 3.0f;
        ysum /= 3.0f;

        return map(atan2(ysum, xsum), -PI, PI, 0, 255);
        // Get the inverse tangent of ysum from xsum and we are mapping that angle from -pi to pi to mapping on 0 and 255
    }

    public void setup()
    {
        colorMode(HSB);
        randomize();
        /*board[0][1] = true;
        board[1][2] = true;
        board[3][2] = true;*/

        println(countNeighbours(0, 2));
        
        //printBoard(board); // print it on the console

        // Variable width does not get assigned until after we call size() so we put the cellSize here
        cellSize = width / (size);
        
        frameRate(20); // 20 frames per second
    }

    private void updateBoard()
    {
        for(int row = 0; row < size; row ++)
        {
            for(int col = 0; col < size; col ++)
            {
                int count = countNeighbours(row, col);
                float c = getCell(board, row, col);
                
                if(c >= 0) // Keeping the frame colours to the one generation to the next
                {
                    if(count == 2 || count == 3)
                    {
                        setCell(next, row, col, c);
                    }
                    else
                    {
                        setCell(next, row, col, -1);
                    }
                }
                else // Dead cell
                {
                    if(count == 3)
                    {
                        setCell(next, row, col, averageAround(board, row, col));
                    }
                    else
                    {
                        setCell(next, row, col, -1);
                    }
                }
            }
        }

        // Swap board and next
        float[][] temp = board;
        board = next;
        next = temp;
    }

    // This method gets called automatically when the mouse is dragged across the screen
    public void mouseDragged()
    {
        int row = (int) map(mouseY, 0, height, 0, size); // Function returns a float so i have to cast to an int 
        int col = (int) map(mouseX, 0, width, 0, size);
        setCell(board, row, col, random(255)); // Set the cells to be true and start the simulation 
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
