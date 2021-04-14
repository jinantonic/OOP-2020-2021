package D19123917;



public class JinasVisual extends Visual
{

    
    public void settings()
    {
        //size(512, 512, P3D);
        size(800, 800, P3D);
    }   

    public void setup()
    {
        startMinim();
        surface.setResizable(true);
        loadAudio("Boney M - Rasputin Remix.mp3");
        colorMode(HSB);
    }

    int which = 0;
    public void keyPressed()
    {
        if(keyCode >= '0' && keyCode <= '5')
        {
            which = keyCode - '0';
        }
        if(keyCode == ' ') // If i push the space bar 
        {
            if(getAp().isPlaying()) // If audioplayer is already playing
            { 
                getAp().pause(); // Pause the audio
            }
            else
            {
                getAp().rewind(); // Rewind
                getAp().play(); // Play 
            }
        }
    }

    float lerpedAverage = 0;

    public void draw()
    {   
        background(0);
        stroke(255);

        float average = 0;
        float sum = 0;

        float halfW = width / 2;
        float halfH = height / 2;

        // Iterate over all the elementsthe audio buffer
        for(int i = 0; i < getAb().size(); i++) // ab is an array list of audio buffer so ab.size() gives us the size of array buffer
        {
            sum += abs(getAb().get(i)); // This is how you look inside the arraylist and get element i out of the arraylist
        }
        
        average = sum / (float) getAb().size();
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);

        switch(which)
        {
            case 0: 
            {
                float c = map(average, 0, 1, 0, 255);
                
                noStroke(); 
                
                fill(c, 255, 255); // Elmo arms
                rect(halfW - 200, halfH - 55, 400 + (lerpedAverage * 500), 70 + (lerpedAverage * 500), 18);

                fill(c, 255, 255); // Elmo upper body
                arc(halfW, halfH + 250, 300 + (lerpedAverage * 500), 700 + (lerpedAverage * 500), PI, TWO_PI);

                fill(c, 255, 255); // Elmo face    
                ellipse(halfW, halfH - 200, 300 + (lerpedAverage * 500), 300 + (lerpedAverage * 500));                

                fill(255); // Elmo eye balls
                ellipse(halfW - 50, halfH - 350, 80 + (lerpedAverage * 500), 80 + (lerpedAverage * 500)); 
                ellipse(halfW + 50, halfH - 350, 80 + (lerpedAverage * 500), 80 + (lerpedAverage * 500));

                fill(0); // Elmo pupils
                ellipse(halfW - 50, halfH - 350, 20 + (lerpedAverage * 500), 20 + (lerpedAverage * 500));
                ellipse(halfW + 50, halfH - 350, 20 + (lerpedAverage * 500), 20 + (lerpedAverage * 500));

                fill(33,363,234); // Elmo nose
                ellipse(halfW, halfH - 300, 70 + (lerpedAverage * 500), 90 + (lerpedAverage * 500)); 

                fill(0); // Elmo mouth
                arc(halfW, halfH - 180, 250 + (lerpedAverage * 500), 220 + (lerpedAverage * 500), 0, PI); 
                /*quad(halfW - 40, halfH - 80, halfW - 80, halfH, halfW / 2, halfH, halfW / 2, halfH - 80); // Left arm
                quad(halfW + 40, halfH - 80, halfW + 80, halfH, halfW / 2, halfH, halfW + (halfW / 2), halfH - 80); // Right arm*/

                
                
                
                
                
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

}