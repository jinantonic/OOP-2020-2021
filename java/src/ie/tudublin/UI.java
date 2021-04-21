package ie.tudublin;


import java.util.ArrayList;
import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;


public class UI extends PApplet
{	
	ArrayList<Colour> colours = new ArrayList<Colour>();
	ArrayList<Resistor> resistors = new ArrayList<Resistor>();


	public Colour findColour(int value)
	{
		for(Colour c:colours)
		{
			if(c.value == value)
			{
				return c;
			}
		}
		return null;
	}

	public void loadColours()
	{
		Table table = loadTable("colours.csv", "header");

		for(TableRow row:table.rows())
		{
			Colour c = new Colour(row);
			colours.add(c);
		}
	}

	public void loadResistors()
	{
		Table t = loadTable("resistors.csv");
		for(TableRow row:t.rows())
		{
			resistors.add(new Resistor(this, row.getInt(0)));
		}
	}

	public void pirntResistors()
	{
		for(Resistor r:resistors)
		{
			int i = r.value;
			int hundreds = (i / 100);
			int tens = (i - (hundreds * 100)) / 10;
			int ones = i - ((hundreds * 100)  + (tens * 10));
			print(hundreds + ",");
			print(tens + ",");
			println(ones);
		}
	}

	public void printColours()
	{
		for(Colour c:colours)
		{
			println(c);
		}
	}

	public void settings()
	{
		size(500, 800);
		loadColours();
		printColours();
		loadResistors();
		pirntResistors();
		//separate(381);
		//separate(1);
		//separate(92);
	}

	public void setup() 
	{
		
	}
	
	int resistorId = 0;
	boolean lastPressed = false;

	public void draw()
	{
		background(200);
		stroke(255);
		for(int i = 0; i < resistors.size(); i++)
		{
			float y = map(i, 0, resistors.size(), 100, height - 100);
			resistors.get(i).render(width / 2, y);
		}
	}
}