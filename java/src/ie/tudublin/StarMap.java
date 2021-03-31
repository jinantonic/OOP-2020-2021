package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;


public class StarMap extends PApplet
{
    // Load an array list of start object
    // Declare at the array list 
    // crtl + . is automatic code fixing in VSC
    // This is an empty arraylist that currently has no elements in it
    ArrayList<Star> starts = new ArrayList<Star>(); // Array list holding the star object (generic -> passing a type to a parameter)

    // Load the csv file into this arraylist 
    void loadStars()
    {
        // Create a table object, table is one of the processing objects 
        Table table = loadTable("HabHYG15ly.csv", "header"); // 1st -> name of the csv file, 2nd -> tells the low table function that the 0 line in the csv file has to the columns names 
        // Instantiate star object from every row from the table 
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
        
    }

   
    public void draw()
    {
       background(0);
    }
}