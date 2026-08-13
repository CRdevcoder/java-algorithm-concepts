package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JPanel;

// gui that draws a bar graph of the array that is being sorted.

public class BarGraphGui extends JPanel {

    // array we are drawing.
    private ArrayList<Integer> focusArray;

    // store indexes that are being read or moved by algorithm.
    // these indexes will have bars that are colored differently from the unaccessed ones.
    // NOTE: AnimationUnit will provide a reference to a hashset for this variable.
    // Hashset will be modified externally (???)
    private Map<Integer, ColorType> selectionData;

    // associates each color with a enum.
    // Can't retreive reference to it. Only set values.
    private Map<ColorType,Color> colorLibrary;

    // Constructor
    public BarGraphGui(ArrayList<Integer> focusArray) {
        this.focusArray = focusArray;

        // stores colors we use.
        this.colorLibrary = new Hashtable<>();

        colorLibrary.put(ColorType.IDLE, Color.WHITE); // default color
        colorLibrary.put(ColorType.MOVED, Color.RED); // when moving an element.
        colorLibrary.put(ColorType.SCANNED, Color.CYAN); // when searching element.

        // create hashset for selected data.
        this.selectionData = new Hashtable<Integer,ColorType>();

        // set background.
        this.setBackground(Color.BLACK);
    }

    // Setters
    public void setFocusArray(ArrayList<Integer> focusArray) {
        this.focusArray = focusArray;
    }

    public void setBarColor(Color color) {
        // update map for IDLE mode.
        this.colorLibrary.put(ColorType.IDLE, color);
    }
    
    // Getters.
    // PRIVATE: pass ColorType as key. Retreive corresponding Color object from map.
    private Color getColorFromType( ColorType type){
        return colorLibrary.get(type);
    }

    // COLORING SELECTED ELEMENTS IMPLEMENTATION:
    // must create private map to associate enums with colors.
    public enum ColorType{
        MOVED,  // for when elements are being moved.
        SCANNED, // for when elemnts are being compared or "scanned".
        IDLE // for untouched data
    }
    
    // Assign object reference to hashtable variable.
    public void setSelectionData( Hashtable<Integer,ColorType> selectionData){
        this.selectionData = selectionData;
    }

    // set what bars are selected within array.
    // They will be colored differently.
    // AnimationUnit uses this to update graph and pass data to it.
    public void setSelectedBars(int[] indexes, ColorType color) {
        for (int index : indexes) {
            this.selectionData.put( Integer.valueOf(index),color);
        }
    }

    // Will be called by AnimationUnit every time its notified.
    // use to clear index data and make room for new data.
    public void clearSelectionData(){
        this.selectionData.clear();
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

    private void drawIndexBar(int i , int deltaX, int deltaY, Color color, Graphics2D g2)
    {

        // set color for the bars.
            g2.setColor(color);

            int barHeight = focusArray.get(i) * deltaY;

            int yPos = (this.getHeight() - barHeight) - 10;
            int xPos = (int)(i * (deltaX) + 10);
            // calculate bar width
            int barWidth = ((deltaX + 10)/2);

			// Draw bar that represents the i-th element in focusArray.
			g2.fillRect(xPos, yPos, barWidth, barHeight);

            int fontSize = (deltaX)/2;
            if(fontSize<12){
                fontSize = 12;
            }

            Font font = new Font("Serif", Font.PLAIN, fontSize);
            g2.setFont(font);

            g2.drawString(Integer.toString(focusArray.get(i)), xPos, yPos - 10);

    }

    // update and draw bar graph.
    // 1. calculate delta x and y for dimensions of rectangles.
    // 2. iterate through each array element.
    // 3. If index has corresponding ColorType value inside selectionData, get it.
    // 4. Pass that very ColorType enum value to the getColorFromType to get the color.
    public void drawState(Graphics2D g2) 
	{
		// divides the width of the canvas component width by the array length.
		// allows us to fit the bars into the window according to it's proportions.
		int deltaX = this.getWidth() / focusArray.size();
		int deltaY = (this.getHeight()/3) / focusArray.size();

        // if no hashset assigned, or hashset is empty, color all bars the same.
        if ((selectionData == null) || (selectionData.isEmpty()))
        {
	        for (int i = 0; i < focusArray.size(); i++)
		    {   
            // draw bar.
            drawIndexBar(i, deltaX, deltaY, getColorFromType(ColorType.IDLE), g2);
	        }
        }
        else{ // else, then color each selected element differently.

            for (int i = 0; i < focusArray.size(); i++)
            {
                // draw selected bar.
                if(selectionData.containsKey(i)){
                    // get proper color from map.
                    drawIndexBar(i, deltaX, deltaY, getColorFromType(selectionData.get(i)), g2);
                }
                else{
                    drawIndexBar(i, deltaX, deltaY, getColorFromType(ColorType.IDLE), g2);
                }
            }

        }
    } 


    // prints focus array for bar graph.
    @Override
    public String toString(){

        return "Graph GUI, FocusArray: " + focusArray;

    }

    
}
