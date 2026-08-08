package gui;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
// a gui that displays a list of graphs.
// can add or remove graphs from the list.
public class GraphListDisplay extends JPanel {

    private ArrayList<BarGraphGui> graphList;

    public GraphListDisplay() {
        this.graphList = new ArrayList<>();

        // verticle layout for the list of graphs.
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

    }

    // add a graph to the list.
    public void addGraph(BarGraphGui graph) {
        this.graphList.add(graph);
        this.add(graph); // add the graph to the JPanel.
        this.revalidate(); // revalidate the JPanel to update the layout.
        this.repaint(); // repaint the JPanel to update the display.
    }

    public void addGraph( AnimationUnit animManager) {
        BarGraphGui graph = animManager.getBarGraphGui();
        this.addGraph(graph);
    }

    // remove a graph from the list.
    public void removeGraph(BarGraphGui graph) {
        this.graphList.remove(graph);
        this.remove(graph); // remove the graph from the JPanel.
        this.revalidate(); // revalidate the JPanel to update the layout.
        this.repaint(); // repaint the JPanel to update the display.
    }

    public void removeGraph(AnimationUnit animManager){
        BarGraphGui graph = animManager.getBarGraphGui();
        this.removeGraph(graph);
    }
    
}
