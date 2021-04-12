package ie.tudublin;

import processing.core.PApplet;

public class Health 
{
    private float x, y; 
    float dx, dy;
    float w = 50; 
    float halfW = w / 2;
    YASC yasc; 
    float rotation;
   
    // Constructor
    public Health(YASC yasc) // Health is goint to determine its x and y values when it's created
    {
        this.yasc = yasc; // Assign the field from the parameter
        rotation = 0;
        respawn();   
    }

    public  void respawn()
    {
        int dice = (int) yasc.random(5); // Generate the random number between 0 and 3.999999999 
        switch(dice)
        {
            case 0: // Left
            {
                // Set the starting position off the left hand-side of the screen
                x = - w; 
                y = yasc.random(halfW, yasc.height);
                // Generate a random direction that basically send this from the left-hand side of the screen to the right-hand side of the screen
                dx = yasc.random(1, 4);
                dy = yasc.random(-1, 1);
                break;
            }
            case 1: // Top
            {
                x = yasc.random(halfW, yasc.width);
                y = -w;
                dx = yasc.random(-1, 1);
                dy = yasc.random(1, 4);
                break;
            }
            case 2: // Right
            {
                x = yasc.width + w;
                y = yasc.random(halfW, yasc.height);
                dx = yasc.random(-1, -4);
                dy = yasc.random(-1, 1);
                break;
            }
            case 3: // Bottom
            {
                x = yasc.random(0, yasc.width);
                y = yasc.height + w;
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

        yasc.line(-halfW, halfW, -halfW, -halfW);
        yasc.line(-halfW, -halfW, halfW, -halfW);
        yasc.line(halfW, -halfW, halfW, halfW);        
        yasc.line(halfW, halfW, -halfW, halfW);
        
        yasc.line(0, -halfW, 0, halfW);
        yasc.line(-halfW, 0, halfW, 0); // Horizontal line
        
        yasc.popMatrix();
    }

    void update()
    {
        x += dx;
        y += dy;
        rotation += 0.01f;

        if(x < - w)
        {
            respawn();
        }
        if (x > yasc.width + w)
        {
            respawn();
        }

        if (y < - w)
        {
            respawn();
        }
        if (y > yasc.height + w)
        {
            respawn();
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

}

