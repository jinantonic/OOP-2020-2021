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
            float x = map1(i, -5, 5, border, width - border);
            line(x, border, x, height - border);
            line(border, x, width - border, x);
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

    // Allocating arrays
    float[] rainfall = {45, 37, 55, 27, 38, 50, 79, 48, 104, 31, 100, 58};
    String[] months = {"Jan", "Feb", "March", "April", "May", "June", "July", "August", "Sept", "Oct", "Nov", "Dec"};
    float[] arr = new float[100]; // Other way to allocate the array, 100 floar array

    public void keyPressed() {
        // the value of mode will be the number of the
        // number key pressed
        if (keyCode >= '0' && keyCode <= '9')
            mode = keyCode - '0';
    }

    public void setup()
    {
        colorMode(RGB);

        for(int i = 0; i < rainfall.length; i++)// rainfall.length gives you the length of the rainfall array
        {
            //System.out.println(x);
            println(months[i] + "\t" + rainfall[i]);
        } 
        
        // Enhanced for loop
        for(float f:rainfall) // Every element in rainfall is copied into f 
        {
            println(f);
        }

        // What month had the most and least rainfall?
        // What is the total rainfall?
        // What is the average rainfall?
        int minIndex = 0; // These will hold the position where we find min and max 
        int maxIndex = 0;
        float sum = 0;
        for(int i = 0; i < rainfall.length; i++)
        {
            if(rainfall[i] < rainfall[minIndex]) // if rainfall at position i is less than rainfall at position minIndex, then that becomes the new minIndex
            {
                minIndex = i;
            }
            if(rainfall[i] > rainfall[maxIndex])
            {
                maxIndex = i;
            }
            sum += rainfall[i];
        }

        float average = sum / (float) rainfall.length;

        println("Least rainfall was in " + months[minIndex] + " with " + rainfall[minIndex]);
        println("Most rainfall was in " + months[maxIndex] + " with " + rainfall[maxIndex]);
        println("Average rainfall: " + average);

        // rect(x, y, w, h); -h will draw the rectangle upwards
        // Draw a bar chart of the rainfall
        // Use the map function
        colorMode(HSB);
        float w = width / (float) rainfall.length; // width of the individual bar
        for(int i = 0; i < rainfall.length; i++)
        {
            noStroke();
            fill(random(255), 255, 255);
            float x = map(i, 0, rainfall.length, 0, width);
            rect(x, height, w, -rainfall[i]);
        }
    }
    


    public void draw() {
        //background(0);
        //drawGrid();
        colorMode(HSB);
        float c = map(mouseX, 0, width, 0, 255);
        //background(c, 255, 255);
    }
}