package ie.tudublin;

import processing.core.PApplet;

public class YASC extends PApplet// Name has to match the file name 
{
    boolean[] keys = new boolean[102];

    Player p;
    Health h;
    
    public void settings()
    {
        size(500, 500);
    }

    void checkCollisions()
    {
        if(dist(p.x, p.y, h.x, h.y) < p.halfW + h.halfW) // They are collided
        {
            p.health += 10; // It gains health
            h.respond();
        }
    }

    public void setup()
    {
        p = new Player(this, width / 2, height / 2); // 1st parameter is reference to the PApplet so this 
        h = new Health(this);
    }

    public void draw()
    {
        background(0);
        stroke(255);

        // Separate instances of the player class
        // Player is encapsulated into this player object that does player stuff
        p.update();
        p.render();

        h.update();
        h.render();

        // Check collisions
        checkCollisions();
    }

    // This is how you can check multiple keys being held down at the same time and also there's no delay now with the keys 
    // Declare a boolean aray keys[] first
    // You pass in the keys (k) and it checks the boolean array at position k or the upper case version of it 
    boolean checkKey(int k)
    {
        if (keys.length >= k)
        {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }

    public void mousePressed()
    {

    }

    public void keyPressed() // Assigns the keys array at position keyCode to be true
    {
        keys[keyCode] = true; // keyCode -> what key was pressed
    }

    public void keyReleased() // Assigns the keys array at position keyCode to be false
    {
        keys[keyCode] = false;
    }

}