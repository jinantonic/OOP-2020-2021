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

    public void setup()
    {
        // Instantiates these different objects
        minim = new Minim(this); // Pass the reference in PApplet 
        ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // 1st : no.of channelels -> mono or stereo
        // 2nd : the frame size -> How much audio i want to process at a time? 512 samples of audio at a time
        // 3rd : sample rate -> 44100 CD quality audio
        // 4th : size of every sameple in bits (sample resolution) -> 16 bit audio
        ap = minim.loadFile("heroplanet.mp3", width); // file name, width
        ap.play(); // Start streaming the mp3 file
        ab = ai.mix;// Assin the audio buffer from the mic -> mix of left and right channel -> stereo mixed down into 1 single channel // Connect buffer to the microphone
        // Now we are able to read the samples into basically in an array which is what are audio buffer is 
        ab = ap.mix; // Load buffer from the mp3 file // Connect the buffer to the mp3 file
        colorMode(HSB);
        lerpedBuffer = new float[width]; // Allocate the memory for lerped buffer inside setup
    }
    
    
    float lerpedAverage = 0;

    public void draw()
    {   
        background(0);
        stroke(255);
        float halfHeight = height / 2;
        float average = 0;
        float sum = 0;
        // Iterate over the audio buffer
        for(int i = 0; i < ab.size(); i++) // ab is an array list of audio buffer so ab.size() gives us the size of array buffer
        {
            // Take each sample and map it onto colour from the HSB colour range
            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);

            // Everytime i read a sample, i lerp the sample value from lerpedbuffer[i] to the sample value that's read by 10%
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f); // Every frame i lerped from the old value of the lerped buffer to the value to the value in the audio buffer by 10%
            //line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, i, halfHeight + lerpedBuffer[i] * halfHeight * 4);
            
            //line(i, halfHeight, i, halfHeight + ab.get(i) * halfHeight); // In arraylist, we use .get
            // I is used for the start of the line's x value and end of the line's x value
            // The start of the line will be half way down the screen and the end of the line is going to be half way down the screen 
            // println(ab.get(i));
            //line(i, halfHeight - ab.get(i) * halfHeight, i, halfHeight + ab.get(i) * halfHeight);
            
            line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
            sum += abs(ab.get(i)); // This is how you look inside the arraylist and get element i out of the arraylist
        }

        // Get the average value of all of the sampels in a frame of audio to estimate how loud the frame audio is
        // Calculate the AVERAGE amplitude
        // Max amplitude -> 1, Min amplitude -> -1 so you'll get an average amplitude of 0 so that's not going to be useful
        average = sum / (float) ab.size();
        
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);

        ellipse(width / 4, 100, average * 500, average * 500);
        ellipse(width / 2, 100, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));

        /*ellipse(200, y, 30, 30);
        ellipse(300, lerpedY, 30, 30);
        y += random(-10, 10); 
        lerpedY = lerp(lerpedY, y, 0.1f); // Move lerpedY 10% closer to y every frame*/
    }
}


// c = lerp (a, b, t) // t says how much between a and b you are gonna go 
// c = a + (b - a)t 
// Lerp is doing linear interpolation between a and b 
// t value controls by how much you are going to interpolate (go between)
// If t = 0.1, that means move a 10% closer to b