package app.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class TestFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3949612765666378470L;

	public TestFrame(ArrayList<WordNode> listOfWords,String number, String type){
		setTitle("GRE Application");
        JPanel jtp = new JPanel();
        JScrollPane scrollPane = new JScrollPane(jtp);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane);
        jtp.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        ArrayList<WordNode> newList = new ArrayList<WordNode>(listOfWords);
        ArrayList<WordNode> finalList = new ArrayList<WordNode>();
        Collections.shuffle(newList);
        if(number.equals("20")){
        	if(listOfWords.size()>20){
        		finalList = (ArrayList<WordNode>) newList.subList(0, 19);
        	}else{
        		finalList = newList;
        	}
        }else if(number.equals("30")){
        	if(listOfWords.size()>30){
        		finalList = (ArrayList<WordNode>) newList.subList(0, 29);
        	}else{
        		finalList = newList;
        	}
        }else if(number.equals("50")){
        	if(listOfWords.size()>50){
        		finalList = (ArrayList<WordNode>) newList.subList(0, 49);
	        }else{
	    		finalList = newList;
	    	}
        }
        
        
        int index = 1;
        int gridYValue = 0;
        for(WordNode wn : finalList){
        	JPanel tmpPanel = new JPanel();
        	tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));
        	JLabel wordLabel = new JLabel(index + ". " + wn.getWord());
        	tmpPanel.add(wordLabel);
        	JRadioButton b1 = new JRadioButton(wn.getMeaning());
        	tmpPanel.add(b1);
        	
        	String tmpBtnVal = "";
        	if(index-1 >= 0 ){
        		tmpBtnVal = finalList.get(index-1).getMeaning();
        	}else if (index+1 < finalList.size()){
        		tmpBtnVal = finalList.get(index+1).getMeaning();
        	}
        	JRadioButton b2 = new JRadioButton(tmpBtnVal);
        	tmpPanel.add(b2);
        	
        	if(index-2 >= 0 ){
        		tmpBtnVal = finalList.get(index-2).getMeaning();
        	}else if (index+2 < finalList.size()){
        		tmpBtnVal = finalList.get(index+2).getMeaning();
        	}
        	JRadioButton b3 = new JRadioButton(tmpBtnVal);
        	tmpPanel.add(b3);
        	
        	if(index-3 >= 0 ){
        		tmpBtnVal = finalList.get(index-3).getMeaning();
        	}else if (index+3 < finalList.size()){
        		tmpBtnVal = finalList.get(index+3).getMeaning();
        	}
        	JRadioButton b4 = new JRadioButton(tmpBtnVal);
        	tmpPanel.add(b4);
        	final ButtonGroup tmpGrp = new ButtonGroup();
        	tmpGrp.add(b1);
        	tmpGrp.add(b2);
        	tmpGrp.add(b3);
        	tmpGrp.add(b4);
        	
        	gbc.fill = GridBagConstraints.HORIZONTAL;
        	if(index%2 == 1){
        		gbc.gridx = 0;
        		gridYValue++;
        	}else {
        		gbc.gridx = 1;
        	}
        	gbc.gridy = gridYValue;
        	jtp.add(tmpPanel,gbc);
        	index++;
        }
	}
}
