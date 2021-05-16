package ie.tudublin;

public class Main
{
    public void starmap()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new StarMap());
    }

    public void gantt()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Gantt());
    }

    public void ui()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new UI());
    }
    
    public static void main(String[] arg)
    {
        Main main = new Main();
		main.starmap();        
    }
}
