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
        size(700, 700);

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
    


    public void draw()
    {
        background(0);
        switch(mode)
        {
            case 0:
            {
                float border = width * 0.1f; // 10% of the screen either side, top, bottom
                stroke(255);
                colorMode(HSB);
                line(border, border, border, height - border); // border, border is top left hand corner -> vertical line 
                line(border, height - border, width - border, height - border);
                textAlign(CENTER, CENTER);
                for(float f = 0; f <= 120; f += 10)
                {
                    float y = map(f, 0, 120, height - border, border);
                    line(border - 5, y, border, y);
                    fill(255);
                    text((int) f, border * 0.5f, y); // text exists in the middle of border so divide the border into half 
                }
                // need float here cuz length of rainfall is integer
                float w = (width - border * 2) / (float)rainfall.length; // width of every bar-> border * 2 bc there are 2 borders at each end
                for(int i = 0; i < rainfall.length; i++)
                {
                    float x = map(i, 0, rainfall.length, border, width - border);
                    //float x = map(i, 0, rainfall.length - 1, border, width - border - w);
                    float c = map(i, 0, rainfall.length, 0, 255);
                    fill(c, 255, 255);
                    float h = map(rainfall[i], 0, 120, 0, -(height - (border * 2))); // convert the pixels to this range
                    rect(x, height - border - 1, w, h); // - means upwards
                    fill(255);
                    text(months[i], x + (w * 0.5f), height - (border * 0.5f)); // x + (w * 0.5f) -> centre of the bar 
                }
                text("Rainfall barchart", width * 0.5f, border * 0.5f);
                break;
            }
            case 1:
            {
                float border = width * 0.1f; // 10% of the screen either side, top, bottom
                stroke(255);
                colorMode(HSB);
                line(border, border, border, height - border); // border, border is top left hand corner -> vertical line 
                line(border, height - border, width - border, height - border);
                textAlign(CENTER, CENTER);
                for(float f = 0; f <= 120; f += 10)
                {
                    float y = map(f, 0, 120, height - border, border);
                    line(border - 5, y, border, y);
                    fill(255);
                    text((int) f, border * 0.5f, y); // text exists in the middle of border so divide the border into half 
                }
                // need float here cuz length of rainfall is integer
                float w = (width - border * 2) / (float)rainfall.length; // width of every bar-> border * 2 bc there are 2 borders at each end
                
                textAlign(CENTER, CENTER);
                for(int i = 0; i < rainfall.length; i++)
                {
                    float x1 = map(i, 0, rainfall.length, border, width - border);
                    line(x1, height - border, x1, height - border + 5);
                    fill(255);
                    text(months[i], x1 + (w * 0.5f), height - (border * 0.5f));
                }

                for(int i = 1; i < rainfall.length; i++)
                {
                    float x1 = map(i - 1, 0, rainfall.length - 1, border + (w * 0.5f), width - border - (w * 0.5f));
                    float y1 = map(rainfall[i - 1], 0, 120, height - border, border);
                    float x2 = map(i, 0, rainfall.length - 1, border + (w * 0.5f), width - border - (w * 0.5f));;
                    float y2 = map(rainfall[i], 0, 120, height - border, border);
                    line(x1, y1, x2, y2);
                }
        
            }
            case 2:
            {

            }
        }
    }
}