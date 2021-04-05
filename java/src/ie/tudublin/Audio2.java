package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Audio2 extends PApplet
{

    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    AudioInput ai;
    FFT fft;

    float[] bands;
    float[] smoothedBands;
    
    // Divides the fft into another array 
    void calculateFrequencyBands() // Sums up all of the energies in the fft array into frequency bands (into groups)
    {
        for (int i = 0; i < bands.length; i++)
        {
            int start = (int) pow(2, i) - 1;
            int w = (int) pow(2, i);
            int end = start + w;
            float average = 0;
            for (int j = start; j < end; j++)
            {
                average += fft.getBand(j) * (j + 1);
            }

            average /= (float) w;
            bands[i] = average * 5;
            smoothedBands[i] = lerp(smoothedBands[i], bands[i], 0.05f);
        }
      }
    
    public void settings()
    {
        size(1024, 1024);
    }

    float y = 200;
    float lerpedY = y;

    int which = 0;

    private float[] frequencies = {293.66f, 329.63f, 369.99f, 392.00f, 440.00f, 493.88f, 554.37f, 587.33f
        , 659.25f, 739.99f, 783.99f, 880.00f, 987.77f, 1108.73f, 1174.66f, 1318.51f, 1479.98f, 1567.98f, 1760.00f, 1975.53f, 2217.46f, 2349.32f};
    String[] spellings = {"D,", "E,", "F,", "G,", "A,", "B,", "C", "D", "E", "F", "G", "A", "B","c", "d", "e", "f", "g", "a", "b", "c'", "d'", "e'", "f'", "g'", "a'", "b'", "c''", "d''"}; 

    // Convert a frequency to a letter so pass in frequency as a parameter
    String spell(float freq)
    {
        
        // Return the element from the spellings array that freq is closest to in the frequency array
        // We have to find the element in the array it is closest to and return the corresponding stirng 
        int closestIndex = 0;
        float smallestGap = Float.MAX_VALUE;

        // Iterate over the array frequency 
        for(int i = 0; i < frequencies.length; i++)
        {
            // Get the difference between the frequency and the elements of the array 
            // If that's less than the smallest gap then smallest gap becomes that gap and smallest index becomes i 
            float gap = abs(freq - frequencies[i]); // Absolute value -> If the value it positive, it gives us the positive value
        
            if(gap < smallestGap)
            {
                smallestGap = gap;
                closestIndex = i;
            }
        }
        return spellings[closestIndex];
    }

    float log2(float f)
    {
        return log(f) / log(2.0f);
    }

    public void setup()
    {
        colorMode(HSB);
        // Instantiate the minim object
        minim = new Minim(this); // Creates a connection to the minim library 
        ap = minim.loadFile("scale.wav", width); // Load the audio file -> calling the loadFile method on the minim object and retrieve the audio player
        // It uses the power of 2 bc FFT algorithm only operates on buffers which are of size of power of 2
        // We'll get 512 samples(width) every frame to process
        ab = ap.mix; // Connect audio player to audio buffer
        //ai = minim.getLineIn(Minim.MONO, width, 44100, 16); // Type, bufferSize, sampleRate, bitDepth
        // Instantiate the fft object
        //ab = ai.mix;
        fft = new FFT(width, 44100); // 2 parameters -> frame size, sample rate

        bands = new float[(int) log2(width)];
        smoothedBands = new float[bands.length];

    }

    public void keyPressed()
    {
        if(keyCode >= '0' && keyCode <= '5')
        {
            which = keyCode - '0';
        }
        if(keyCode == ' ') // If i push the space bar 
        {
            if(ap.isPlaying()) // If audioplayer is already playing
            { 
                ap.pause(); // Pause the audio
            }
            else
            {
                ap.rewind(); // Rewind
                ap.play(); // Play 
            }
        }
    }

    float lerpedAverage = 0;

    public void draw() 
    {
        background(0);
        stroke(255);

        float halfHeight = height / 2;
        for(int i = 0; i < ab.size(); i++)
        {
            stroke(map(i, 0, ab.size(), 0, 255), 255, 255);
            //line(i, halfHeight - (ab.get(i) * halfHeight), i, halfHeight + (ab.get(i) * halfHeight)); // It's going to draw below the centre and above the centre
        }

        // Run the fft algorithm over our audio buffer
        fft.window(FFT.HAMMING);
        fft.forward(ab); // Pass in the audio buffer

        
        //int highestBand = 0;
        int highestBand = 0;

        for(int i = 0; i < fft.specSize(); i++) // fft.specSize() is how to get the specsize of fft array
        {
            stroke(map(i, 0, fft.specSize(), 0, 255), 255, 255);
            // Audio buffer goes between -1 and +1 for every element and these guys above go between 0 and 1 
            line(i, height, i, height - fft.getBand(i) * halfHeight); // Draw fft from the top of the screen downwards
            // fft.getBand(i) is how to index into the fft array

            // Figure out which element in the fft array has the higest value and assign that index to highestBand
            if(fft.getBand(i) > fft.getBand(highestBand)) // Comparing every value from fft array 
            {
                highestBand = i;
            }
        }

        float freq = fft.indexToFreq(highestBand); // Gives us the frequency 
        textSize(24);
        fill(255);
        text("Frequency: " + freq, 10, 50); // Figure out the frequency of the music note 
        text("Note: " + spell(freq), 10, 100);

        calculateFrequencyBands();
        
        /*float w = width / (float) bands.length;

        // Plot (render) the frequency bands 
        for(int i = 0; i < bands.length; i++)
        {
            float x = map(i, 0, bands.length, 0, width);
            float c = map(i, 0, bands.length, 0, 255);
            noStroke();
            fill(c, 255, 255);
            
            //rect(x, height, w, -bands[i]); // -bands[i] -> draw rectangle upwards
            rect(x, height, w, -smoothedBands[i]);
        }*/
    }
}