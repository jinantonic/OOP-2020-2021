package ie.tudublin;

import processing.data.TableRow;

public class Task
{
    // 1st - make the fileds from csv file 
    private String task;
    private int start;
    private int end;

    // 2nd - make constructors, toString method, accessor method and so on
    // Rightclick - source action - generate getter and setter -> constructor -> tostring

    // Getters and setters below 
    public String getTask()
    {
        return task;
    }
    public void setTask(String task)
    {
        this.task = task;
    }
    public int getStart()
    {
        return start;
    }
    public void setStart(int start)
    {
        this.start = start;
    }
    public int getEnd()
    {
        return end;
    }
    public void setEnd(int end)
    {
        this.end = end;
    }

    // Constructor
    public Task(String task, int start, int end)
    {
        this.task = task;
        this.start = start;
        this.end = end;
    }

    // 3rd - make constructor that takes processign tablerow as a parameter
    public Task(TableRow r) // This is a constructor bc it doens't have a return type
    {
        // Constructor chaining
        this(r.getString("Task"), r.getInt("Start"), r.getInt("End"));
    }
    
    @Override
    public String toString() {
        return "Task [end=" + end + ", start=" + start + ", task=" + task + "]";
    }

   


}