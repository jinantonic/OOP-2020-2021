package ie.tudublin;

// Abstract means the class cannot be instantiated
public abstract class GameObject
{
    float x, y;
    float dx, dy; // direction
    float rotation = 0; // rotation value
    float speed = 5; // Bullet will travel at 5 seconds per unit
    YASC yasc;
    float w = 50; 
    float halfW = w / 2;

    public GameObject(YASC yasc, float x, float y, float rotation)
    {
        this.yasc = yasc;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
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

    // Subclasses must implement these abstract methods, otherwise they will be abstract 
    public abstract void render();

    public abstract void update();
    
}

