package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	private float border1;
	private float border2;

	int whichTask = -1;
	boolean isEnd = false;

	public void settings()
	{
		size(800, 600);
	}


	public void displayTasks()
	{
		
		textAlign(CENTER, CENTER);
		for(int i = 1; i <= 30; i++) // Vertical
		{
			float x = map(i, 1, 30, border2, width - border1);
			stroke(255);
			line(x, border1, x, height - border1);
			
			fill(255);
			text(i, x, border1 / 2);
		}

		for(int i = 0; i < tasks.size(); i++) // Horizontal
		{
			Task t = tasks.get(i);
			float y = map(i, 0, tasks.size(), border1 + 50, height - border1 - 50);
			float x1 = map(t.getStart(), 1, 30, border2, width - border1);
			float x2 = map(t.getEnd(), 1, 30, border2, width - border1);
			int c = (int)map(i, 0, tasks.size(), 0, 255);
			
			noStroke();
			fill(255);
			textAlign(CENTER, CENTER);
			text(t.getTask(), border2 / 2, y);
			
			fill(c, 255, 255);
			rect(x1, y - 20, x2 - x1, 40, 7);
		}
	}

	
	public void loadTasks()
	{
		Table table = loadTable("tasks.csv", "header");

		for(TableRow row:table.rows())
		{
			Task t = new Task(row);
			tasks.add(t); 
		}
	}
	

	public void printTasks()
	{
		for(Task t: tasks)
		{
			println(t);
		}
	}
	
	
	public void mousePressed()
	{
		for(int i = 0; i < tasks.size(); i++)
		{
			float y = map(i, 0, tasks.size(), border1 + 50, height - border1 - 50);
			float y1 = y - 20;
			float y2 = y + 20;
			float x1 = map(tasks.get(i).getStart(), 1, 30, border2, width - border1);
			float x2 = map(tasks.get(i).getEnd(), 1, 30, border2, width - border1);

			if(mouseX >= x1 && mouseX <= x1 + 20 && mouseY >= y1 && mouseY <= y2)
			{
				whichTask = i;
				isEnd = false;
				return;
			} 

			if(mouseX >= x2 - 20 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2)
			{
				whichTask = i;
				isEnd = true;
				return;
			}
			whichTask = -1;
		}
	}

	public void mouseDragged()
	{
		if(whichTask != -1)
		{
			int month = (int)map(mouseX, border2, width - border1, 1, 30);

			if(month >= 1 && month <= 30)
			{
				Task task = tasks.get(whichTask);
				if(isEnd)
				{
					if(month - task.getStart() > 0)
					{
						task.setEnd(month);
					}
				}
				else
				{
					if(task.getEnd() - month > 0)
					{
						task.setStart(month);
					}
				}
			}
		}
	}

	
	
	public void setup() 
	{
		loadTasks();
		printTasks();
		border1 = width * 0.05f;
		border2 = width * 0.2f;
		colorMode(HSB);
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}