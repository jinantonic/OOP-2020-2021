package ie.tudublin;

// GameObject is a superclass for everything and it's an abstract class
// abstract means the class cannot be instantiated

public abstract class GameObject {
    float x, y;
    float dx, dy;
    float rotation = 0;
    float speed = 5;
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

    // Abstract methods -> Don't have to have any body, just a semicolon
    // Any class that extends gameObject has to implement those methods, otherwise that class is also an abstract class
    // You can have some code in it, some constructors, methods with bodies, fields, abstract methods on it 
    // subclasses must implement these abstract methods, otherwise they will be abstract
    public abstract void render();

    public abstract void update();

    
}
