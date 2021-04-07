package ie.tudublin;

import processing.core.PApplet;

public class Health 
{
    float x, y; 
    float dx, dy;
    float w = 50; 
    float halfW = w / 2;
    YASC yasc; 
    float rotation;
   
    // Constructor
    public Health(YASC yasc) // Health is goint to determine its x and y values when it's created
    {
        this.yasc = yasc; // Assign the field from the parameter
        respond();
    }

    public  void respond()
    {
        int dice = (int) yasc.random(4); // Generate the random number between 0 and 3.999999999 
        switch(dice)
        {
            case 0: // Left
            {
                // Set the starting position off the left hand-side of the screen
                x = - halfW; 
                y = yasc.random(halfW, yasc.height - halfW);
                // Generate a random direction that basically send this from the left-hand side of the screen to the right-hand side of the screen
                dx = yasc.random(1, 4);
                dy = yasc.random(-1, 1);
                break;
            }
            case 1: // Top
            {
                x = yasc.random(halfW, yasc.width - halfW);
                y = -halfW;
                dx = yasc.random(-1, 1);
                dy = yasc.random(1, 4);
                break;
            }
            case 2: // Right
            {
                x = yasc.width + halfW;
                y = yasc.random(halfW, yasc.height - halfW);
                dx = yasc.random(-1, -4);
                dy = yasc.random(-1, 1);
                break;
            }
            case 3: // Bottom
            {
                x = yasc.random(halfW, yasc.width - halfW);
                y = yasc.height + halfW;
                dx = yasc.random(-1, 1);
                dy = yasc.random(-1, -4);
                break;
            }
        }
    } 

    void render()
    {
        yasc.pushMatrix();
        yasc.translate(x, y);
        yasc.rotate(rotation);
        yasc.rectMode(PApplet.CENTER);
        yasc.stroke(255);
        yasc.noFill();
        yasc.rect(0, 0, w, w);
        yasc.line(0, halfW, 0, -halfW);
        yasc.line(-halfW, 0, halfW, 0); // Horizontal line
        yasc.popMatrix();
    }

    void update()
    {
        x += dx;
        y += dy;
        rotation += 0.05f;

        if(x < - halfW || x > yasc.width + halfW || y < - halfW || y > yasc.height + halfW) // If it's gone completely off the screen
        {
            respond();
        }
    }
}

