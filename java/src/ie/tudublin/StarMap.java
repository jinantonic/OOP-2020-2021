package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{
    ArrayList<Star> stars = new ArrayList<Star>();

    int startStar = -1;
    int endStar = -1;


    void drawGrid()
    {
        float border = width * 0.1f;
        textAlign(CENTER, CENTER);
        for(int i = -5; i <= 5; i++)
        {
            float x = map(i, -5, 5, border, width - border);
            float y = map(i, -5, 5, border, width - border);
            stroke(204, 0, 204);
            line(x, border, x, height - border);
            line(border, y, width - border, y);

            fill(204, 0, 204);
            text(i, x, border / 2);
            text(i, border / 2, y);
            
        }
    }

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

    public void drawStars()
    {
        for(Star s: stars)
        {
            s.render(this);
        }
    }

    public void settings()
    {
        size(800, 800);
    }
    


    public void mouseClicked()
    {
       for(int i = 0; i < stars.size(); i++)
       {
           Star s = stars.get(i);
           float x = map(s.getxG(), -5, 5, border, width - border);
           float y = map(s.getyG(), -5, 5, border, height - border);
           if(dist(mouseX, mouseY, x, y) < s.getAbsMag() / 2)
           {
                if(startStar == -1)
                {
                    startStar = i;
                    break;
                }
                else if(endStar == -1)
                {
                    endStar = i;
                    break;
                }
                else
                {
                    startStar = i;
                    endStar = -1;
                }
           }
       }
    }
    
    float border;
    public void setup()
    {
        border = width * 0.1f;
        colorMode(RGB);
        loadData();
        printStars();
        
    }

   
    public void draw()
    {
       background(0);
       drawGrid();
       drawStars();

       if(startStar != -1 && endStar == -1)
       {
            Star s = stars.get(startStar);
            stroke(255, 255, 0);
            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);
            line(x, y, mouseX, mouseY);
       }
       else if(startStar != -1 && endStar != -1)
       {
            Star s1 = stars.get(startStar);
            stroke(255, 255, 0);
            float x1 = map(s1.getxG(), -5, 5, border, width - border);
            float y1 = map(s1.getyG(), -5, 5, border, height - border);

            Star s2 = stars.get(endStar);
            float x2 = map(s1.getxG(), -5, 5, border, width - border);
            float y2 = map(s1.getyG(), -5, 5, border, height - border);
            line(x1, y1, x2, y2);

            float dist = dist(s1.getxG(), s1.getyG(), s1.getzG(), s2.getxG(), s2.getyG(), s2.getzG());
            stroke(255);
            textAlign(CENTER, CENTER);
            text("DIstance from " + s1.getDisplayName() + " to " + s2.getDisplayName() + " is " + dist + " parsecs", width / 2, height - (border /  2));

       }
    }
}
