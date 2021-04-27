package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput; // Import audioinput
import ddf.minim.AudioPlayer;
import ddf.minim.Minim; // Import minim class from minim package
import processing.core.PApplet;

public class Audio1 extends PApplet
{
    // Create a type of object minim
    Minim minim; // This is the thing which will connect us to minimum library // Connect to minim
    AudioInput ai; // Allow us to connect to microphone and read sample data from microphone // How to connect to mic
    AudioPlayer ap; // How to connect to mp3 
    AudioBuffer ab; // Make data structure to hold the actual buffer of samples, encapsulated in array // Samples

    float[] lerpedBuffer; // Make an array of the same size of the buffers

    public void settings()
    {
        //size(512, 512);
        size(512, 512, P3D);
        //fullScreen(P3D, SPAN);
    }

    /*float y = 400;
    float lerpedY = y;*/
    int which = 0;

    public void setup()
    {
        surface.setResizable(true);
        // Instantiates these different objects
        minim = new Minim(this); // Pass the reference in PApplet 
        ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        ap = minim.loadFile("heroplanet.mp3", width); // file name, width
        ap.play(); // Start streaming the mp3 file
        ab = ai.mix;// Assin the audio buffer from the mic -> mix of left and right channel -> stereo mixed down into 1 single channel // Connect buffer to the microphone
        ab = ap.mix; // Load buffer from the mp3 file // Connect the buffer to the mp3 file
        colorMode(HSB);
        lerpedBuffer = new float[width]; // Allocate the memory for lerped buffer inside setup
    }
    
    public void keyPressed()
    {
        if(keyCode >= '0' && keyCode <= '5')
        {
            which = keyCode - '0';
        }
    }
    float lerpedAverage = 0;

    public void draw()
    {   
        background(0);
        stroke(255);
        float halfHeight = height / 2;
        float average = 0;
        float sum = 0;
        // Iterate over all the elementsthe audio buffer
        for(int i = 0; i < ab.size(); i++) // ab is an array list of audio buffer so ab.size() gives us the size of array buffer
        {
            sum += abs(ab.get(i)); // This is how you look inside the arraylist and get element i out of the arraylist
        }

        average = sum / (float) ab.size();
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);

        switch(which)
        {
            case 0:
            {
                for(int i = 0; i < ab.size(); i++) // ab is an array list of audio buffer so ab.size() gives us the size of array buffer
                {
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);

                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f); // Every frame i lerped from the old value of the lerped buffer to the value to the value in the audio buffer by 10%

                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
                }
                break;
            }
            case 1:
            {
                for(int i = 0; i < ab.size(); i++) // ab is an array list of audio buffer so ab.size() gives us the size of array buffer
                {
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);

                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f); // Every frame i lerped from the old value of the lerped buffer to the value to the value in the audio buffer by 10%

                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, i, halfHeight + lerpedBuffer[i] * halfHeight * 4);
                }
                break;
            }
            case 2:
            {
                for(int i = 0; i < ab.size(); i++) 
                {
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);

                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f); 
                    // Draw once from the left side of the screen going outwards
                    line(0, i, lerpedBuffer[i]* halfHeight * 4, i);
                    line(width, i, width - (lerpedBuffer[i] * halfHeight * 4), i); // Right handside coming inwards
                    line(i, 0, i, lerpedBuffer[i]* halfHeight * 4); // Top of the screen
                    line(i, height , i, height - (lerpedBuffer[i] * halfHeight * 4)); // Up tp screen
                }
                break;
            }
            case 3:
            {
                // Mapping the amplitude on to the colour
                float c = map(average, 0, 1, 0, 255);
                stroke(c, 255, 255);
                strokeWeight(2); // Thicker lines
                noFill();

                //ellipse(width / 4, 100, 50 + (average * 500), 50 +  (average * 500));
                ellipse(width / 2, 100, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
                break;
            }
            case 4:
            {
                float c = map(average, 0, 1, 0, 255);
                stroke(c, 255, 255);
                strokeWeight(2); // Thicker lines
                noFill();
                rectMode(CENTER); // When we use rect, the x,y cordinates are centre of the rectangle
                float size = 50 + (lerpedAverage * 500);
                rect(width / 2, height / 2, size, size);
                break;
            }
            case 5:
            {
                float r = 1f; // radius
                int numPoints = 3; // no. of points that we are gonna calculate on the outside of a spiral
                float thetaInc = TWO_PI / (float)numPoints;
                strokeWeight(2);
                stroke(255);
                float lastX = width / 2; 
                float lastY = height / 2;
                for(int i = 0; i < 1000; i++) // Calculate 1000 points on the outside of a spiral -> doing that for 1000 times
                {
                    float c = map(i, 0, 300, 0, 255) % 255.0f;
                    stroke(c, 255, 255, 100);
                    float theta = i * (thetaInc + lerpedAverage * 5);
                    float x = width / 2 + sin(theta) * r; // Calculating the points on the outside of the circle 
                    float y = height / 2 - cos(theta) * r;
                    r += 0.5f + lerpedAverage; // Increase the radius by some small amount
                    numPoints++;
                    //point(x, y);
                    line(lastX, lastY, x, y); // Drawing a line from previously calculated to the current calculated point
                    lastX = x;
                    lastY = y;
                }
                break;
            }
            

    
        }
    }
}


// c = lerp (a, b, t) // t says how much between a and b you are gonna go 
// c = a + (b - a)t 
// Lerp is doing linear interpolation between a and b 
// t value controls by how much you are going to interpolate (go between)
// If t = 0.1, that means move a 10% closer to b