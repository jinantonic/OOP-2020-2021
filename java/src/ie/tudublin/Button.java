package ie.tudublin;

import processing.core.PApplet;

public class Button
{
   PApplet ui;
   private float x, y;
   private float width, height;
   private String text;

   public Button(PApplet ui, float x, float y, float width, float height, String text)
   {
       this.ui = ui;
       this.x = x;
       this.y = y;
       this.width = width;
       this.height = height;
       this.text = text;
   }

   public void render()
   {
       ui.noFill();
       ui.stroke(255);
       ui.rect(x, y, width, height);
       ui.textAlign(PApplet.CENTER, PApplet.CENTER);
       ui.text(text, x + width * 0.5f, y + height * 0.5f);
   }
}
