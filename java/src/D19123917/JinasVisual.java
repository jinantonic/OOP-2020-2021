package D19123917;

import java.util.ArrayList;

public class JinasVisual extends Visual
{
    public void settings()
    {
        size(512, 512);
    }   

    public void setup()
    {
        colorMode(HSB);
    }

    int which = 0;
    public void keyPressed()
    {
        if(keyCode >= '0' && keyCode <= '5')
        {
            which = keyCode - '0';
        }
    }

    public void draw()
    {   
        background(0);
        stroke(255);

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
            
            }
            case 2:
            {
            
            }
            case 3:
            {
            
            }
            case 4:
            {
            
            }
            case 5:
            {
            
            }
    }

}