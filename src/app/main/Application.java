package app.main;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Application extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Application(){	
		try{
			String path = Application.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			path = path.substring(0,path.lastIndexOf("/")+1);
			String dataFilePath = path + "data.txt";
			setTitle("GRE Application");
			JTabbedPane jtp = new JTabbedPane();
			jtp.setPreferredSize(new Dimension(1000, 300));
			getContentPane().add(jtp);
			JPanel addWordPanel = new AddEntryPanel(dataFilePath);
			JPanel createTestPanel = new CreateTestPanel(dataFilePath);
			jtp.addTab("Add a Word", addWordPanel);
			jtp.addTab("Create a Test", createTestPanel);
		}catch(Exception e){
			System.err.println("ERROR: Could not load the data file !!!");
		}
	}
	
	public static void main(String[] args){
		Application app = new Application();
		app.pack();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
//        app.setResizable(false);
	}
}
