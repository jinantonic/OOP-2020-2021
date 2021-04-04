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
        size(512, 512);
    }

    /*float y = 400;
    float lerpedY = y;*/
    int which = 0;

    public void setup()
    {
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
                
            }
            case 3:
            {
                
            }
        }
    }
}


// c = lerp (a, b, t) // t says how much between a and b you are gonna go 
// c = a + (b - a)t 
// Lerp is doing linear interpolation between a and b 
// t value controls by how much you are going to interpolate (go between)
// If t = 0.1, that means move a 10% closer to b