package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

// gui that draws a bar graph of the array that is being sorted.

public class BarGraphGui extends JPanel {

    // array we are drawing.
    private ArrayList<Integer> focusArray;



    // Constructor
    public BarGraphGui(ArrayList<Integer> focusArray) {
        this.focusArray = focusArray;
        
    }

    // call repaint() to update the bar graph when the array changes.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Draw the bar graph based on numArray.
        // You can use g.fillRect() to draw rectangles for each number in numArray.
        drawState(g2d);
    }

    // update and draw bar graph.
    public void drawState(Graphics2D g2) 
	{
		// divides the width of the canvas component width by the array length.
		// allows us to fit the bars into the window according to it's proportions.
		int deltaX = this.getWidth() / focusArray.size();
		int deltaY = (this.getHeight()/3) / focusArray.size();


	    for (int i = 0; i < focusArray.size(); i++)
		{
            g2.setColor(Color.BLUE);

            int barHeight = focusArray.get(i) * deltaY;

            int yPos = (this.getHeight() - barHeight) - 10;
            int xPos = (int)(i * (deltaX) + 10);

			// Draw bar that represents the i-th element in focusArray.
			g2.fillRect(xPos, yPos, ((deltaX + 10)/2), barHeight);
	    }
    }

    
}
