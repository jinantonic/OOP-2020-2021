package ie.tudublin;

import processing.core.PApplet;

public class ArraysSke extends PApplet {

    public float map1(float from, float start1, float stop1, float start2, float stop2) // what we are mapping from, range of values for from, what are we mapping these onto 
    {
        return 0;
    }

    public void settings() {
        size(500, 500);

    }

    int mode = 0;

    public void keyPressed() {
        // the value of mode will be the number of the
        // number key pressed
        if (keyCode >= '0' && keyCode <= '9')
            mode = keyCode - '0';
    }

    public void setup() {
        colorMode(HSB);

        }



    public void draw() {
        background(0);
        noStroke();
    }
}