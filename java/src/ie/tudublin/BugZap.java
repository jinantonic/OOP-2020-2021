package ie.tudublin; // package name is matched with the file name 

import processing.core.PApplet;

// make another subclass of PApplet
// class name has to match the file name (BugZap)
public class BugZap extends PApplet
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
		reset(); // call reset
	}
	
	
	// these variables are declared when those classes are instanciated and the size method get calld a little while later 
	// (Variables don't get called after the size is called)
	// so we can't use width and height here
	// better place to put them is inside of setup or settings 
	float playerX, playerY;
	float playerSpeed = 5;
	float playerWidth = 40;
	float halfPlayerWidth = playerWidth / 2;

	float bugX, bugY, bugWidth = 30;
	float halfBugWidth = bugWidth / 2;
	
	int score = 0;

	void reset() // Call resetBug and assign players to the starting positions
	{
		resetBug();
		// These values are defined and assigned values in the PApplet super class
		playerX = width / 2; // half way across the screen
		playerY = height - 50; // 50 pixels up from the bottom of the screen
	}

	// This is in the seperate function because everytime i shoot the bug, i wanna call the resetBug 
	// And assign it back to the its starting position
	void resetBug()
	{
		// I want to start at least the half width of the bug in and the half of the width from the right hand side of the screen
		bugX = random(halfBugWidth, width - halfBugWidth);
		bugY = 50;
	}

	void drawBug(float x, float y)
	{	
		// draw the bug
		stroke(255); // Pen colour (white)
		float saucerHeight = bugWidth * 0.7f; // shape of the top of the bug
		line(x, y - saucerHeight, x - halfBugWidth, y); // draw the line of the side of the bug
		line(x, y - saucerHeight, x + halfBugWidth, y); // right hand side of the bug 
		// line(x - halfBugWidth, y, x - halfBugWidth, y); // k
		line(x - halfBugWidth, y, x + halfBugWidth, y); // line of the bottom triangle 

		// 0.1 is double constant so putting f makes it to the float 
		float feet = bugWidth * 0.1f; // bug feet
		line(x - feet, y, x - halfBugWidth, y + halfBugWidth);
		line(x + feet, y, x + halfBugWidth, y + halfBugWidth);

		float eyes = bugWidth * 0.1f; // bug eyes suing scaling
		line(x - eyes, y - eyes, x - eyes, y - eyes * 2f);
		line(x -+eyes, y - eyes, x + eyes, y - eyes * 2f);

	}

	void drawPlayer(float x, float y, float w) // Using the values that passed in 
	{
		stroke(255);
		float playerHeight = w / 2;
		line(x - halfPlayerWidth, y + playerHeight, x + halfPlayerWidth, y + playerHeight);
		line(x - halfPlayerWidth, y + playerHeight, x - halfPlayerWidth, y + playerHeight * 0.5f);
		line(x + halfPlayerWidth, y + playerHeight, x + halfPlayerWidth, y + playerHeight * 0.5f);

		line(x - halfPlayerWidth, y + playerHeight * 0.5f, x - (halfPlayerWidth * 0.8f), y + playerHeight * 0.3f);
		line(x + halfPlayerWidth, y + playerHeight * 0.5f, x + (halfPlayerWidth * 0.8f), y + playerHeight * 0.3f);

		line(x - (halfPlayerWidth * 0.8f), y + playerHeight * 0.3f, x + (halfPlayerWidth * 0.8f),
				y + playerHeight * 0.3f);

		line(x, y, x, y + playerHeight * 0.3f);
	}
	
	// simple keyboard handling 
	public void keyPressed() 
	{
		if (keyCode == LEFT) // If the left arrow has pressed
		{
			// We only want to move the bug to left if the player hasn't already reached the left handside of the screen
			if (playerX > halfPlayerWidth) // If we can move to the left 
			{
				playerX -= playerSpeed;
			}
		}

		if (keyCode == RIGHT) // If the right arrow has pressed
		{
			// Without this, it will go to the rightside forever
			if (playerX < width - halfPlayerWidth)  // If it's okay to move to the right, bouce checking
			{
				playerX += playerSpeed;
			}
		}

		// I want to shoot the razer 
		if (keyCode == ' ')
		{	
			// If the playerX is greater than the left hand side of the bug 
			if (playerX > bugX - halfBugWidth && playerX < bugX + halfBugWidth) // If i hit the bug 
			{
				line(playerX, playerY, playerX, bugY);
				score ++; // If i hit the bug, my score increments 
				resetBug();
			}
			else
			{
				line(playerX, playerY, playerX, 0); // Draw the shooting       
			}
		}
	}

	void moveBug()
	{
		// if ((framCount % 30) == 0) this is gonna check every half second (every half second the bug is gonna come down the screen)
		if ((frameCount % 1) == 0)
		{
			bugX += random(-5, 5); // bugX moves by the random amount

			if (bugX < halfBugWidth) // make sure X doesn't go beyond the screen
			{
				bugX = halfBugWidth;
			}

			if (bugX > width - halfBugWidth)
			{
				bugX = width - halfBugWidth;
			}
			bugY += 2;
		}
	}

	int gameMode = 0;

	public void draw() 
	{
		background(0);

		if (gameMode == 0)
		{
			fill(255); // text 
			drawPlayer(playerX, playerY, playerWijjJavaJadth);
			drawBug(bugX, bugY);
			moveBug();

			text("Score: " + score, 20, 20);
		}
		else
		{
			textAlign(CENTER, CENTER);
			text("GAME OVER!!!", width / 2, height / 2);
		}

		if (bugY > height - 50) // Once the bug reaches the bottom
		{
			gameMode = 1;
		}

	}
}


