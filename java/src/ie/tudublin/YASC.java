package ie.tudublin;

import processing.core.PApplet;

public class YASC extends PApplet// Name has to match the file name 
{
    boolean[] keys = new boolean[526];

    float x, y; // x and y are going to the centre point of the player's ship
    float w = 50; // The width of the player
    float halfW = w / 2;

    void drawPlayer(float x, float y)
    {

    }

    void movePlayer()
    {
        if(checkKey(UP)) // If we press the up arrow
        {
            y -= 1; 
        }
    }

    public void settings()
    {
        size(500, 500);
    }

    public void setup()
    {
        x = width / 2;
        y = height / 2;
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