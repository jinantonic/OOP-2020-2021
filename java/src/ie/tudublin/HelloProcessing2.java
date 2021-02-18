package ie.tudublin;

import processing.core.PApplet;

// HelloProcessing2 is the subclass of PApplet
public class HelloProcessing2 extends PApplet
{
	float x = 0;

	// first bit of code that's executed
	public void settings()
	{
		size(500, 500); // 5oo px by 500 px
	}

	// next piece of code that's executed automatically
	public void setup()
	{
		
	}
	
	// This draw method is called 60 times in a second, already in a loop 
	// The codes in here will be executed every frame essentially 
	public void draw()
	{	
		/*
		background(255, 0, 0); // set the background colour as black 
		stroke(0, 255, 0); // Pen colour (white)
		line(10, 10, 200, 200); // line - x1, y1, x2, y2

		noStroke();
		ellipse(200, 200, 100, 50); // circle - cx(center x), cy(center y), w(width), h(height)
		fill(0, 0, 255);
		rect(20, 100, 70, 90); // rectangle - tlx(top left x), tly(top left y), w, h
		point(200, 60); // single pixel - x, y
		fill(0, 255, 255);
		triangle(200, 90, 300, 200, 10, 60); // triangle - x1, y1, x2, y2, x3, y3

		fill(0);
		text("Hello World", 300, 300);
		*/
		
		/*
		background(0);
		// ellipse(x, height / 2, 50, 50); // height/2 -> half way divided by window
		ellipse(x, height / 2, mouseX, mouseY); // height/2 -> half way divided by window
		x ++;
		println(x);
		*/
		background(254, 0, 0);
		noStroke();
		fill(250, 254, 0);
		ellipse(250, 300, 400, 400);
		noStroke();
		fill(0, 254, 254);
		triangle(50, 450, 250, 50, 450, 450); 
		fill(213, 204, 201);
		ellipse(250, 255, 200, 100);
		fill(0);
		ellipse(250, 255, 70, 70);


	}
}
