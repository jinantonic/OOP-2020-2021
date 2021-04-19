package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{
    ArrayList<Star> stars = new ArrayList<Star>();

    void loadData()
    {
        Table table = loadTable("HabHYG15ly.csv", "header");

        for(TableRow row:table.rows())
        {
            Star s = new Star(row);
            stars.add(s);
        }   
    }

    void printStars()
    {
        for(Star s: stars)
        {
            println(s);
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
        loadData();
        printStars();
        
    }

   
    public void draw()
    {
       background(0);
    }
}
