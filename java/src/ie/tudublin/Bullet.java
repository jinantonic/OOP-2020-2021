package ie.tudublin;

import processing.core.PApplet;

public class Bullet
{
    float x, y;
    float dx, dy; // direction
    float rotation = 0; // rotation value
    float speed = 5; // Bullet will travel at 5 seconds per unit
    YASC yasc;


    // Bullets wrap around the screen
    // Bullets live for 5 seconds then they get destroyed
    // Update method in bullets get called 60 fps
    
    // Constructor
    public Bullet(YASC yasc, float x, float y, float rotation) // Pass in the starting values and rotation value
    {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.yasc = yasc;

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

    public void update() // Calculate the x and y
    {
        dx = PApplet.sin(rotation);
        dy = - PApplet.cos(rotation);

        x += dx * speed;
        y += dy * speed;
    }

}
