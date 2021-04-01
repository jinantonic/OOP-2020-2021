package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet {

    ArrayList<Task> task = new ArrayList<Task>();

    public void setup()
    {
        
    }

    void loadTasks()
    {
        Table table = loadTable("Task.csv", "header");
        for(TableRow row:table.rows())
        {
            
        }
    }


    public void settings()
    {
       
        
    }
    
}