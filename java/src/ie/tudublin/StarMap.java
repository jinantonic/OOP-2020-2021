package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;


public class StarMap extends PApplet
{
    // Load an array list of start object
    // Declare at the array list 
    // crtl + . is automatic code fixing in VSC
    // This is an empty arraylist that currently has no elements in it
    ArrayList<Star> stars = new ArrayList<Star>(); // Array list holding the star object (generic -> passing a type to a parameter)

    // Draw grid
    void drawGrid()
    {
        float border = 0.1f * width; 
        textAlign(CENTER, CENTER);
        //float drawable = width - (border * 2.0f);
        //float gap = drawable / 10.0f;
        for(int i = -5; i <= 5; i ++) // Generate numbers from -5 to 5
        {
            float x = map(i, -5, 5, border, width - border);
            float y = map(i, -5, 5, border, height - border);
            //float x = border + ((i + 5) * gap);
            //float y = border + ((i + 5) * gap);

            stroke(0, 0, 255);
            line(x, border, x, height - border); // x, border -> x down border 
            line(border, y, width - border, y);
            fill(255); 
            text(i, x, border / 2);
            text(i, border / 2, y);
            
        }
    }


    // Print out all of those elements in the arraylist 
    void printStars()
    {
        for(Star s: stars)
        {
            println(s);
        }
    }


    // Load the csv file into this arraylist 
    void loadStars()
    {
        // Create a table object, table is one of the processing objects 
        Table table = loadTable("HabHYG15ly.csv", "header"); // 1st -> name of the csv file, 2nd -> tells the low table function that the 0 line in the csv file has to the columns names 
        // Instantiate star object from every row from the table 
        for(TableRow row:table.rows()) // Enhanced for loop -> Iterating over the table row by row
        {
            Star s = new Star(row); // Create a new star
            stars.add(s); // Add the star to the arraylist 
        }
    }


    public void settings()
    {
        size(500, 500);
    }

    float border;

    public void mouseClicked()
    {
       println("Mouse clicked");
    }

    public void setup()
    {
        colorMode(RGB);
        loadStars();
        printStars();
        
    }

   
    public void draw()
    {
       background(0);
       drawGrid();
    }
}