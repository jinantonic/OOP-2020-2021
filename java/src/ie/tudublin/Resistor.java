package ie.tudublin;

import processing.core.PApplet;

// Make a class called Resistor that has the following public int fields : value, ones, tens, hundreds
public class Resistor
{
    int value;
    UI ui;
    Colour hc;
    Colour tc;
    Colour oc;

    // Make a constructor that takes value as a parameter and assign all fields
    public Resistor(UI ui, int value)
    {
        this.ui = ui;
        this.value = value;
        int hundreds = (value / 100);
		int tens = (value - (hundreds * 100)) / 10;
		int ones = value - ((hundreds * 100)  + (tens * 10));
		hc = ui.findColour(hundreds);
        tc = ui.findColour(tens);
        oc = ui.findColour(ones);
    }

    public void render(float x, float y)
    {
        ui.pushMatrix();
        ui.translate(x, y);
        ui.stroke(0);
        ui.line(-100, 0, 50, 0);
        ui.line(-50, 0, -50, -50);
        ui.line(-50, -50, 50, -50);

        ui.line(50, -50, 50, 0);
        ui.line(50, 0, 100, 0);
        ui.line(50, 0, 50, 50);
        ui.line(50, 50, -50, 50);
        ui.line(-50, 50, -50, 0);

        // Draw the color bars
        ui.noStroke();
        ui.fill(hc.r, hc.g, hc.b);
        ui.rect(-40, -49, 10, 99);
        
        ui.fill(tc.r, tc.g, tc.b);
        ui.rect(-20, -49, 10, 99);
        
        ui.fill(oc.r, oc.g, oc.b);
        ui.rect(0, -49, 10, 99);
        ui.fill(0);
        ui.textAlign(PApplet.CENTER, PApplet.CENTER);
        ui.textSize(30);
        ui.text(value, 200, 0);
        ui.popMatrix();
    }
}
