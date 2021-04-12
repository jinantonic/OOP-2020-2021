package D19123917;



public class JinasVisual extends Visual
{

    
    public void settings()
    {
        //size(512, 512, P3D);
        size(512, 512);
    }   

    public void setup()
    {
        startMinim();
        //surface.setResizable(true);
        loadAudio("heroplanet.mp3");
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
                fill(c, 255, 255);        
             
                // See the difference lerping makes? It smooths out the jitteryness of average, so the visual looks smoother
                //ellipse(width / 4, 100, 50 + average * 500, 50 + average * 500);
                ellipse(width / 2, height / 2, 300 + (lerpedAverage * 500), 300 + (lerpedAverage * 500));                
                
                noStroke(); 
                fill(255);  
                ellipse(width / 2 - 50, height / 2 - 150, 80 + (lerpedAverage * 500), 80 + (lerpedAverage * 500)); // Elmo eye balls
                ellipse(width / 2 + 50, height / 2 - 150, 80 + (lerpedAverage * 500), 80 + (lerpedAverage * 500));
                fill(0);
                ellipse(width / 2 - 50, height / 2 - 150, 20 + (lerpedAverage * 500), 20 + (lerpedAverage * 500));// Elmo pupils
                ellipse(width / 2 + 50, height / 2 - 150, 20 + (lerpedAverage * 500), 20 + (lerpedAverage * 500));
                fill(33,363,234);
                ellipse(width / 2, height / 2 - 90, 70 + (lerpedAverage * 500), 90 + (lerpedAverage * 500)); // Elmo nose
                               
                
                
                
                
                
                
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