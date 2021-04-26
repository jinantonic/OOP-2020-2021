package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class YASC extends PApplet {
    boolean[] keys = new boolean[1024];

    
    // Update your forks!
    // Create a branch for today monday9
    // Write drawPlayer
    // Write movePlayer

    Player p;
    // It has an arraylist of a gameObject
    // This array can contain any subclass of the gameObject superclass
    // And superclass keeps tracks of all of the things 
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    // Polymorphism!
    // The type is of the base class, but the instance is a subclass

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        p = new Player(this, width / 2, height / 2);

        gameObjects.add(p);
        gameObjects.add(new Health(this));
        gameObjects.add(new Ammo(this));
        
    }

    public void draw() {

        fill(255);
        background(0);
        text("Bullets: " + gameObjects.size(), 50, 50);
        text("FPS: " + frameRate, 50, 100);
        
        stroke(255);
        
        // Iterate through the gameObjects
        for(int i = gameObjects.size() - 1; i >= 0 ; i--)
        {
            GameObject go = gameObjects.get(i);
            go.update(); // Calls the appropriate method on the appropriate subclass of the gameObject
            go.render();

            // Dynamic method binding
            // Bc this is polymorphism, this could be any of the number of different methods might get called here depending on what the instance is 
            // So at runtime, the runtime actually has to look up and see what type this is and what the appropriate method that i call

            // We use instanceof method which is the operator in java which tells you if it implements the interface or subclass or if it's a class of a particular type and so one
            // You can use this to check to see that 
            if (go instanceof PowerUp) // If they are powerUp, we do the collision test
            {
                if (dist(go.x, go.y, p.x, p.y) < go.halfW + p.halfW)
                {
                    ((PowerUp) go).applyTo(p);
                    gameObjects.remove(go);
                }
            }
        }
    }

    boolean checkKey(int k) {
        if (keys.length >= k) {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }

    public void mousePressed() {
    }

    public void keyPressed() {
        keys[keyCode] = true;
    }

    public void keyReleased() {
        keys[keyCode] = false;
    }
}