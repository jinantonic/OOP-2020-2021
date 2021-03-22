package ie.tudublin;

import processing.core.PApplet;

public class ArraysSke extends PApplet
{
    // It's taking the value from go between this range of numbers(start1-stop1) and we are mapping on to these range of numbers(start2-stop2)
    public float map1(float from, float start1, float stop1, float start2, float stop2) // what we are mapping from, range of values for from, what are we mapping these onto 
    {
        float range1 = stop1 - start1; // Mapping from
        float range2 = stop2 - start2; // Mapping to 
        float howFar = from - start1;

        return start2 + (howFar / range1) * range2;
    }

    public void drawGrid()
    {
        stroke(0, 255, 0);
        float border = width * 0.1f;
        textAlign(CENTER, CENTER);
        for(int i = -5; i <= 5; i++)
        {
            float x = map(i, -5, 5, border, width - border);
            println(x);
            line(x, border, x, height - border);
            //line(border, x, width - border, x);
            fill(255);
            text(i, x, border * 0.5f); // 2 pieces of texts
            text(i, border * 0.5f, x);

        }
    }

    public void settings()
    {
        size(500, 500);

        float f = map1(2, 0, 10, 0, width); // 2 is the 20% of the way between 0 and 10 so we go 20% of the way betweeen 0 and width
        println(f); // Should print 100

        f = map1(9, 0, 1, 0, 10); // 9 is the 9 times of the range of 1,0 so we go 9 times of 0, 90
        println(f); // Should print 90

        f = map1(250, 200, 300, 400, 500); // 250 is halfway between(50%) 200 and 300 so it should print 450
        println(f); // Should print 450

        f = map1(5, 0, 10, 1000, 2000);
        println(f); // Should print 1500
    }

    int mode = 0;

    public void keyPressed() {
        // the value of mode will be the number of the
        // number key pressed
        if (keyCode >= '0' && keyCode <= '9')
            mode = keyCode - '0';
    }

    public void setup() {
        colorMode(RGB);

        }



    public void draw() {
        background(0);
        drawGrid();
    }
}