package ie.tudublin;

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




    // Constructor -> The method that gets called when the object gets created
    // They don't have a return type 
    public Star() // Defalut constructor -> constructor that takes no parameters
    {

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
