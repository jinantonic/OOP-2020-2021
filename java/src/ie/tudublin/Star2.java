package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Star2
{
    private String displayName;
    private float xG;
    private float yG;

    public Star2() // Defalut constructor -> constructor that takes no parameters
    {

    }

    // Constructor that takes the table row and creates the star from a table row 
    public Star2(TableRow row)
    {
        this // Call this constructor from this constructor 
        (
            row.getString("Display Name"),
            row.getFloat("Xg"),
            row.getFloat("Yg")
        ); // The constructor below is getting called from this constructor and we can cosntruct the star by passing in row from the table
    }


    public Star2(String displayName, float xG, float yG)// Parameterised constructor which will take the default values for all of those fileds
    {
        this.displayName = displayName;
        this.xG = xG;
        this.yG = yG;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public float getxG() {
        return xG;
    }

    public void setxG(float xG) {
        this.xG = xG;
    }

    public float getyG() {
        return yG;
    }

    public void setyG(float yG) {
        this.yG = yG;
    }

    @Override
    public String toString() {
        return "Star2 [displayName=" + displayName + ", xG=" + xG + ", yG=" + yG + "]";
    }
    
}