package ie.tudublin;

import processing.core.PApplet;

public class Bullet extends GameObject
{
    float lifetime;

    // Constructor
    public Bullet(YASC yasc, float x, float y, float rotation) // Pass in the starting values and rotation value
    {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.yasc = yasc;
        lifetime = 5; // Bullet will have lifetime of 5 seconds
        timeAlive = 0;

    }

    public void render()
    {   
        yasc.stroke(255);
        yasc.pushMatrix();
        yasc.translate(x, y);
        yasc.rotate(rotation);
        yasc.line(0, -5, 0, 5); // Draw a line for the bullet -> 10 px long line
        yasc.popMatrix();
    }

    float timeAlive;

    public void update() // Calculate the x and y
    {
        dx = PApplet.sin(rotation);
        dy = - PApplet.cos(rotation);

        x += dx * speed;
        y += dy * speed;

        // Bullets wrap around the screen
        // Bullets live for 5 seconds then they get destroyed
        // Update method in bullets get called 60 fps
        timeAlive += (1 / 60.0f); // Allow it to increase 
        if(timeAlive > lifetime)
        {
            // I want to remove this bullet from the array
            yasc.bullets.remove(this); // Pointer to this bullet
        }

        if(x < 0)
        {
            x = yasc.width;
        }
        if(x > yasc.width)
        {
            x = 0;
        }
        if(y < 0)
        {
            y = yasc.height;
        }
        if(y > yasc.height)
        {
            y = 0;
        }

    }

}
