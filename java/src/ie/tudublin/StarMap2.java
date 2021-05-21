package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap2 extends PApplet
{
    ArrayList<Star2> stars = new ArrayList<Star2>(); // Array list holding the star object (generic -> passing a type to a parameter)
    
    public void setup()
    {
        render();
    }
    
    //Star s = new Star();
    
    //s.setDisplayName("DIT");
    void render()
    {
        Star2 s = new Star2();
        s.setDisplayName("DIT");
        stars.add(s);

        Star2 s1 = stars.get(0); // s1 points to the star s
        // Star s1 is now s
        s1.setDisplayName("TU Dublin"); // Overwrites DIT with TUDUblin 

        System.out.println(s); 
        System.out.println(stars.get(0));
        System.out.println(s1);
    }
}
    