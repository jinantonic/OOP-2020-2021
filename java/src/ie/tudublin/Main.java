package ie.tudublin;

public class Main
{
    public void catsAndDogs()
    {
        System.out.println("Hello world");

        // Polymorphism - We use the type as a base class but the instance is one of the subclasses of the base class 
        Animal misty = new Dog("Misty"); // That's why we can say misty = Animal because both are animals 

        Animal topCat = new Cat("TopCat");

        System.out.println(misty);
        System.out.println(topCat);

        misty = topCat;

        topCat.setName("Garfield");

        System.out.println(misty);
        System.out.println(topCat);

        
        Cat ginger = new Cat("Ginger"); // pass ginger string to the cat 

        while(ginger.getNumLives() > 0)
        {
            ginger.kill();
        }

        ginger.kill(); // print i am dead
    }

    public void helloProcessing1()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new HelloProcessing1());
    }

    public void helloProcessing2()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new HelloProcessing2());
    }

    public void bugZap()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new BugZap()); // instanciate the BugZap here
    }

    public void arrays()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Arrays()); // instanciate the BugZap here
    }
    
    public void life()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Life1());
    }
    
    public void colorfulLife()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new ColorfulLife());
    }

    public void starMap()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new StarMap());
    }
    public void gantt()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Gantt());
    }

    public void audio1()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio1());
    }

    public void audio2()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio2());
    }

    public void yasc()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new YASC());
    }

    public void strings()
    {
        String s = "I may be hungry, but I sure ain't weird.";
        String a = s.substring(0, 5); // Retrieve a substring starting at 0 and finishing at character 5 - 1 (4)
        String b = s.substring(9, 15);
        String c = s.substring(0); // Entire screen, it's gonna read till the end 
        String d = s.substring(34);

        //System.out.println(s.substring(0, 5));
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

        if(s.startsWith("I may")) // Returns true if s starts with "I may"
        {
            System.out.println("Starts with I may");
        }
        if(s.endsWith("weird"))
        {
            System.out.println("Ends with weird");
        }
        System.out.println(s.toUpperCase());
        
        int hungryIndex = s.indexOf("hungry");
        System.out.println(hungryIndex);

        int weridIndex = s.lastIndexOf("e"); // Start searching backwards and return the location of the string
        System.out.println(weridIndex);

        String[] words = s.split(" "); // Splits the string into multiple strings based on a delimiter

        for(String ss: words)
        {
            System.out.println(ss);
        }

        for(int i = s.length() - 1; i >= 0; i--) // Print the string backwards 
        {
            System.out.println(s.substring(i, i + 1)); 
        }
    }

    public static void main(String[] args)
    {
        Main main = new Main();
        main.strings();
    }
    
    
} 