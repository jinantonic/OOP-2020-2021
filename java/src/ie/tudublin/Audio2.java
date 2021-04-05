package ie.tudublin;

import processing.core.PApplet;

public class Audio2 extends PApplet
{

    public void settings()
    {
        size(512, 512);
    }
    
    float y = 200;
    float lerpedY = y;

    int which = 0;

    public void setup()
    {
        colorMode(HSB);
    }

    public void keyPressed()
    {
        if (keyCode >= '0' && keyCode <= '5')
        {
            which = keyCode - '0';
        }
    }

    float lerpedAverage = 0;

    public void draw() 
    {
        background(0);
        stroke(255);
    }
}