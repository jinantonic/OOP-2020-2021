package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet // Subclass of PApplet
{
    // Declare the arraylist
    ArrayList<Task> tasks = new ArrayList<Task>();

    int whichTask = -1; // Which task is being clicked 
    boolean isEnd = false ; // Whether it selected the end of the class or the beginning of the class
    
    private float leftBorder; // These are private because they are not used at the outside of the class
    private float border;


    public void mouseDragged()
	{
		if (whichTask != -1)
		{
			int month = (int)map(mouseX, leftBorder, width - border, 1, 30);
			
			if (month >= 1 && month <= 30)
			{
				Task task = tasks.get(whichTask);  // Find out which task i clicked 
				if(isEnd) // If it's the end then 
				{
					if(month - task.getStart() > 0) // Bounce checking that i can't drag the month before the startmonth 
					{
						task.setEnd(month); // I update the end of the task 
					}
				}
				else // If it's the start then 
				{
					if(task.getEnd() - month > 0) // or the end before the endmonth
					{
						task.setStart(month); // I update the start of the task 
					}
				}
			}
		}
    }

    public void mousePressed() // This gets called when you clicked the mouse onto the processing drawing window
    {
        // Figure put which task i have clicked 
        for(int i = 0 ; i < tasks.size() ; i ++) // Iterating all over the tasks
		{
            float y = map(i, 0, tasks.size(), border + 50, height - border - 50); // Calculate the y value where the tasks are on the screen and draw the area for the tasks 
            float y1 = y - 20; // Which are the top and the bottom of the task 
            float y2 = y + 20; // Check displayTasks()
			float x1 = map(tasks.get(i).getStart(), 1, 30, leftBorder, width - border);
			float x2 = map(tasks.get(i).getEnd(), 1, 30, leftBorder, width - border);
			
			if (mouseX >= x1 && mouseX <= x1 + 20 && mouseY >= y1 && mouseY <= y2)
			{
				whichTask = i;
				isEnd = false;
				return;
			}

			if (mouseX <= x2 && mouseX >= x2 - 20 && mouseY >= y1 && mouseY <= y2)
			{
				whichTask = i;
				isEnd = true;
				return;
			}
		}		
		// default value for whichTask
		whichTask = -1;	
    }


    public void printTasks()
    {
        for(Task t:tasks)
        {
            println(t);
        }
    }

    // Rightclick -> rename symbol -> it renames all of the variables including references 
    public void displayTasks()
    {
        stroke(225);
        fill(255);
        textAlign(CENTER, CENTER);
        for(int i = 1; i <= 30; i++) // vertical lines
        {
            float x = map(i, 1, 30, leftBorder, width - border);
            line(x, border, x, height - border);
            text(i, x, border / 2); 
        }

        for(int i = 0; i < tasks.size(); i++) // tasks.size() -> no. of elements in the arrayList
        {
            float y = map(i, 0, tasks.size(), border + 50, height - border - 50); // Bc when i draw the, i centered them around y so i'm using y-20 and y+20
            Task t = tasks.get(i);  
            float x1 = map(t.getStart(), 1, 30, leftBorder, width - border);
            float x2 = map(t.getEnd(), 1, 30, leftBorder, width - border);
            int c = (int) map(i, 0, tasks.size(), 0, 255); // colour 
            noStroke();
            fill(c, 255, 255);
            rect(x1, y - 20, x2 - x1, 40);
            fill(255);
            textAlign(LEFT, CENTER);
            text(t.getTask(), 20, y);
        }

    }

    public void loadTasks()
    {   
        Table t = loadTable("tasks.csv", "header");
        for(TableRow r:t.rows())
        {
            Task task = new Task(r);
            tasks.add(task);
        }
    }


    // 1st
    public void setup()
    {
        loadTasks();
        printTasks();
        leftBorder = width * 0.2f;
        border = width * 0.05f;
        colorMode(HSB);
    }

    // 2nd
    public void settings()
    {
        size(800, 800);
       
    }

    // 3rd
    public void draw()
    {
        background(0);
        displayTasks();
    }
} 