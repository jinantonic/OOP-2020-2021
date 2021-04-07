package ie.tudublin;

import processing.core.PApplet;

public class YASKSke extends PApplet// Name has to match the file name 
{
    boolean[] keys = new boolean[526];
    
    public void settings()
    {
        size(500, 500);
    }

    public void setup()
    {

    }

    public void draw()
    {

    }

    boolean checkKey(int k)
    {
        if (keys.length >= k)
        {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }

    public void mousePressed()
    {

    }

    public void keyPressed()
    {
        keys[keyCode] = true;
    }

    public void keyReleased()
    {
        keys[keyCode] = false;
    }

}