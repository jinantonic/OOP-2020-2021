package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

// CSV -> Comment seperated values file 
// A text file as oppose to a binary file(img, mp3 file)
// Every coloum is speerated with comma and then evey row is seperated with carriage return
// Every line on this file corresponds to row of data and every comma delimits the column
// Very first line contains the heading 


// Encapsulate the interest of columns of interest into the fields of the class
// Class has fields and methods
// Fields being things that the class is 
// Methods beigng things that the class does 
// Right click -> Source action -> Generate Getters and Setters -> Tick everything and tada
// VSC will write those accessor methods
public class Star
{
    // All private fields -> They can only be accessed by methods inside the star class
    private boolean hab;
    private String displayName;
    private float distance;
    private float xG, yG, zG;
    private float absMag;

    // 3 state system 
    // 1. Haven't clicked anything
    // 2. Clicked one star and want to draw the line to one star to the next star
    // 3. Have clicked two stars

    // Constructor -> The method that gets called when the object gets created
    // They don't have a return type 
    public Star() // Defalut constructor -> constructor that takes no parameters
    {

    }


    // Constructor that takes the table row and creates the star from a table row 
    public Star(TableRow row)
    {
        // Instantiate the star from a single row from the table
        // 1st, we have to see how to get columns from this row
        // 2nd, we have to look at how to do the concept in java called constructor chaining 
        // -> When you have loads of different constructors and u don't want to duplicate the code between the constructors so chain those constructors together 
        this // Call this constructor from this constructor 
        (
            row.getInt("Hab?") == 1 ? true : false,
            row.getString("Display Name"),
            row.getFloat("Distance"),
            row.getFloat("Xg"),
            row.getFloat("Yg"),
            row.getFloat("Zg"),
            row.getFloat("AbsMag")
        ); // The constructor below is getting called from this constructor and we can cosntruct the star by passing in row from the table
    }

    // Render each one of the stars onto the grid 
    public void render(PApplet pa)
    {
        // The code below wouldn't work bc the processign libraries are stored in PApplet so have to pass papplet as a parameter
        //float border = width * 0.1f;
        //float x = map(xG, -5, 5, border, width - border);
        //stroke(255, 255, 0);

        float border = pa.width * 0.1f;
        float x = PApplet.map(xG, -5, 5, border, pa.width - border); // It's calling a method on the class PApplet rather than the instance PApplet -> map is static method
        float y = PApplet.map(yG, -5, 5, border, pa.width - border);
        pa.stroke(255, 255, 0);
        pa.line(x - 5, y, x + 5, y);
        pa.line(x, y - 5, x, y + 5);
        pa.stroke(255, 0, 0);
        pa.noFill();
        pa.circle(x, y, absMag); // width, height, size
        pa.fill(255);
        pa.textAlign(PApplet.LEFT, PApplet.CENTER);
        pa.text(displayName, x + 10, y);

    }

    public Star(boolean hab, String displayName, float distance, float xG, float yG, float zG, float absMag)// Parameterised constructor which will take the default values for all of those fileds
    {
        // Now take all those values above and assign them to the fileds in the class
        // We can disambiguate between the filed hab and the parameter hab by using the keyword, this
        this.hab = hab;
        this.displayName = displayName;
        this.distance = distance;
        this.xG = xG;
        this.yG = yG;
        this.zG = zG; 
        this.absMag = absMag;// Now all of the fields are assigned 
    }

    public boolean isHab()
    {
        return hab;
    }

    public void setHab(boolean hab) // Assigning the pravate field from a parameter
    {
        this.hab = hab;
    }
    public String getDisplayName()
    {
        return displayName;
    }
    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }
    public float getDistance()
    {
        return distance;
    }
    public void setDistance(float distance)
    {
        this.distance = distance;
    }
    public float getxG()
    {
        return xG;
    }
    public void setxG(float xG)
    {
        this.xG = xG;
    }
    public float getyG()
    {
        return yG;
    }
    public void setyG(float yG)
    {
        this.yG = yG;
    }
    public float getzG()
    {
        return zG;
    }
    public void setzG(float zG)
    {
        this.zG = zG;
    }
    public float getAbsMag()
    {
        return absMag;
    }
    public void setAbsMag(float absMag)
    {
        this.absMag = absMag;
    }

    // toString method is the method that gets called automatically whenever you pass this object to something that expects a string
    // Source action -> generate toString()
    /*public String toString()
    {
        return hab + "\t" + displayName + "\t" + distance + "\t" + xG + "\t" + yG + "\t" + zG + "\t" + absMag;
    }*/
    @Override
    public String toString() {
        return "Star [absMag=" + absMag + ", displayName=" + displayName + ", distance=" + distance + ", hab=" + hab
                + ", xG=" + xG + ", yG=" + yG + ", zG=" + zG + "]";
    }
}