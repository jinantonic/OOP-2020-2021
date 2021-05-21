package ie.tudublin;

import processing.core.PApplet;

// I have encapsulated the player thing in this came into the class
public class Player3 
{
    // Everything related to the player, I have made variables into fields
    // So these are the things that the player owns or the player has 
    float x, y; // x and y are going to the centre point of the player's ship
    float dx, dy;
    float w = 50; // The width of the player
    float halfW = w / 2;
    YASC yasc; // And it has a reference to PApplet to check keys
    float rotation; // To control the amount of the rotation

    // Constructor for the player, no return type
    public Player3(YASC yasc, float x, float y)
    {
        // Taking these parameters and assigning these values in the constructor
        // Assigning the fields in the class from the parameters
        this.yasc = yasc;
        this.x = x;
        this.y = y;
        rotation = 0;
    }
    
    void render() // Drawing itself
    {
        // Make those 2 transforms independent
        // These transforms are matrix multiplications so we need push and pop matrix
        yasc.pushMatrix(); // Stores the old transform, then it does the translate, rotate, draw thing
        yasc.translate(x, y); // Move the origin by this amount 
        yasc.rotate(rotation);
        // We are calling the line method on the PApplet
        //yasc.line(x - halfW, y + halfW, x , y - halfW);
        //yasc.line(x , y - halfW, x + halfW , y + halfW);
        //yasc.line(x + halfW , y + halfW, x, y);
        //yasc.line(x , y, x - halfW, y + halfW);

        // Drawing this spaceship as if it was centred at 0, 0
        yasc.line(- halfW, halfW, 0 ,  - halfW);
        yasc.line(0 , -halfW, halfW, halfW);
        yasc.line(halfW , halfW, 0, 0);
        yasc.line(0 , 0, -halfW, halfW);
        yasc.popMatrix(); // Restores everything -> puts everything back the way it was originally 
    }

    void update() // Upate itself
    {
        //dx = (float) MATH.sin(rotation);
        dx = PApplet.sin(rotation);
        dy = - PApplet.cos(rotation);

        if(yasc.checkKey(PApplet.UP)) // If we press the up arrow
        {
            //y -= 1; 
            x += dx; // Moves to the direction which it's pointing 
            y += dy; 
        }
        if(yasc.checkKey(PApplet.DOWN))
        {
            //y += 1; 
            x -= dx;
            y -= dy; 
        }
        if(yasc.checkKey(PApplet.LEFT))
        {
            //x -= 1; 
            rotation -= 0.1f;
        }
        if(yasc.checkKey(PApplet.RIGHT))
        {
            //x += 1; 
            rotation += 0.1f;
        }
    }

    // Getters and setters
    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getW()
    {
        return w;
    }

    public void setW(float w)
    {
        this.w = w;
    }
}
