package ie.tudublin;

public class Main
{
    public void starmap()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new StarMap());
    }

    public void starmap2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new StarMap2());
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

    /*public void button()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Button());
    }*/
    
    public static void main(String[] arg)
    {
        Main main = new Main();
		main.starmap2();        
    }
}
