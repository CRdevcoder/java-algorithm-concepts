package gui;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JPanel;

// draws an array as graph.
public class ArrayPainter<E extends Comparable<E>> extends JPanel {

    private ArrayList<E> focusArray;

    // stores reference to focusArray.
    public ArrayPainter( ArrayList<E> focusArray)
    {
        this.focusArray = focusArray;
    }

    public void drawState(Graphics2D g2, int visitedElement) 
	{
		// divides the width of the canvas component width by the array length.
		// allows us to fit the bars into the window according to it's proportions.
		int deltaX = this.getWidth() / focusArray.size();
		int deltaY = (this.getHeight()) / focusArray.size();

			for (int i = 0; i < focusArray.size(); i++)
			{
				if (i == visitedElement)
				{
					// The currently visited element is THIS color.
					// System.out.println(i + " Red set!");
					g2.setColor(Color.RED);
				}
				else
				{
				// unsorted elements are this color.
					//System.out.println(i + " black set!");
					g2.setColor(Color.BLACK); 
				}
				
				// Draw bar that represents the i-th element in focusArray.
				//g2.fillRect(i * deltaX, 10, ((deltaX + 10)/2), (deltaY)*(focusArray.get(i)));
			}
		
	}

}
