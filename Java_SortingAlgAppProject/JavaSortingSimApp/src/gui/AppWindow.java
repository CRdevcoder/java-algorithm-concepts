package gui;

import javax.swing.JFrame;

public class AppWindow extends JFrame {
	
	 public AppWindow(String title, int width, int height, boolean resizable){
	        this.setTitle(title);
	        this.setSize(width, height);
	        this.setResizable(resizable);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        // add components to JFrame.
	        
	        
	        this.setVisible(true);
	        
	 }

}
