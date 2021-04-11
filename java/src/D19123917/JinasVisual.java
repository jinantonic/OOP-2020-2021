package D19123917;

public class JinasVisual extends Visual
{
    // setting, setup, keypressed, draw, 
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
    }

}