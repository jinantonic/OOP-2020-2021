package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Audio2 extends PApplet
{

    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    FFT fft;
    
    
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
        // Instantiate the minim object
        minim = new Minim(this); // Creates a connection to the minim library 
        ap = minim.loadFile("heroplanet.mp3", width); // Load the audio file -> calling the loadFile method on the minim object and retrieve the audio player
        // It uses the power of 2 bc FFT algorithm only operates on buffers which are of size of power of 2
        // We'll get 512 samples(width) every frame to process
        ab = ap.mix; // Connect audio player to audio buffer

        // Instantiate the fft object
        fft = new FFT(width, 44100); // 2 parameters -> frame size, sample rate
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
            line(i, halfHeight - (ab.get(i) * halfHeight), i, halfHeight + (ab.get(i) * halfHeight)); // It's going to draw below the centre and above the centre
        }

        // Run the fft algorithm over our audio buffer
        fft.window(FFT.HAMMING);
        fft.forward(ab); // Pass in the audio buffer

        for(int i = 0; i < fft.specSize(); i++) // fft.specSize() is how to get the specsize of fft array
        {
            stroke(map(i, 0, fft.specSize(), 0, 255), 255, 255);
            // Audio buffer goes between -1 and +1 for every element and these guys above go between 0 and 1 
            line(i, 0, i, fft.getBand(i) * halfHeight); // Draw fft from the top of the screen downwards
            // fft.getBand(i) is how to index into the fft array
        }
    }
}