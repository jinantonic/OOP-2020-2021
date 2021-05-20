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

    int startStar = -1;
    int endStar = -1;

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
        size(800, 800);
    }

    float border;

    // If the mouse is clikcked here, it's iterating over and i'm finding the distance and so on
    public void mouseClicked() // Gets called when your mouse is on the screen
    {
        
        //println("Mouse clicked");
        // I have to know which star is clicked
        // Iterate through all of the stars and see which star is clicked 
        for(int i = 0; i < stars.size(); i ++)
        {
            Star s = stars.get(i); // Index into an arraylist as oppose to an array  
            // I'm taking xG and yG fields of each star and mapping it from the range from -5 to 5 and mapping it on to from border to width-border and height-border
            // So that's calculating the centre point of each star
            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);
            // And then i'm checking to see if the distance between mouse X and Y and the x and y codinates is less than the radius of the star = that means i clicked inside of the circle
            // Distance from the mouse to the centre point of the star to see if it's lesser than the radius of the star 
            if(dist(mouseX, mouseY, x, y) < s.getAbsMag() / 2)
            {
                // Now i have to see is it the first star or the second star clicked? 
                if(startStar == -1) // I haven't clicked any star yet 
                {
                    startStar = i; // Clicked on the first star 
                    break;
                }
                else if(endStar == -1) // The startStar is clicked already but the endStar hasn't been clicked
                {
                    endStar = i; // Clicked on the second star 
                    break;
                }
                else // startStar != -1 && endStar != -1 -> Already clicked on 2 stars
                {
                    // Now i clicked the new startStar
                    startStar = i;
                    endStar = -1;
                }
                //println(s.getDisplayName()); // Print out the displayName 
                //break;
            }
            
        }
    }

    public void setup()
    {
        border = width * 0.1f;
        colorMode(RGB);
        loadStars();
        printStars();
        
    }

    public void drawStars()
    {
        for(Star s: stars)
        {
            s.render(this); // this points to the current object
        }
    }

    public void draw()
    {
       background(0);
       drawGrid();
       drawStars();
       if(startStar != -1 && endStar == -1) // I have clicked the startStar but endStar
       {
            // Then draw the line between the startStar and the endStar
            Star s = stars.get(startStar);
            stroke(255, 255, 0);
            // Calculate the centre of the star
            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);
            line(x, y, mouseX, mouseY);
        }
        else if(startStar != -1 && endStar != -1) // I have clicked on both stars
        {
            Star s = stars.get(startStar); // 1st star
            stroke(255, 255, 0);
            // Need to draw the line between 2 stars
            float x1 = map(s.getxG(), -5, 5, border, width - border);
            float y1 = map(s.getyG(), -5, 5, border, height - border);

            Star s1 = stars.get(endStar); // 2nd star
            float x2 = map(s1.getxG(), -5, 5, border, width - border);
            float y2 = map(s1.getyG(), -5, 5, border, height - border);
            line(x1, y1, x2, y2);

            // Print the distance between 2 stars
            float dist = dist(s.getxG(), s.getyG(), s.getzG(), s1.getxG(), s1.getyG(), s1.getzG());
            stroke(255);
            textAlign(CENTER, CENTER);
            text("Distance between " + s.getDisplayName() + " and " + s1.getDisplayName() + " is " + dist + " parsecs", width / 2, height - (border / 2));
        }
    }
}