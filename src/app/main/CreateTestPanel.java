/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author asharm73
 */
public class CreateTestPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 231246293082095300L;
    
    private String dataFilePath = "data.txt";
    private JTextField filePathField;
    private ButtonGroup quesTypeGroup;
    private ButtonGroup quesNumGroup;
    
    public CreateTestPanel(String dataFilePath) {
    	this.dataFilePath = dataFilePath;
        initComponents();
    }

    private void initComponents() {

//    	listOfWords = new ArrayList<WordNode>();
    	JPanel leftPanel = new JPanel();
//    	leftPanel.setSize(400,400);
    	leftPanel.setLayout(new GridBagLayout());
    	
    	GridBagConstraints gbc = new GridBagConstraints();
        
    	gbc.fill = GridBagConstraints.HORIZONTAL;

    	JRadioButton twenty = new JRadioButton("20", false);
    	twenty.setActionCommand("20");
        JRadioButton thirty = new JRadioButton("30", false);
        thirty.setActionCommand("30");
        JRadioButton fifty = new JRadioButton("50", false);
        fifty.setActionCommand("50");

        quesNumGroup = new ButtonGroup();
        quesNumGroup.add(twenty);
        quesNumGroup.add(thirty);
        quesNumGroup.add(fifty);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 0;
        gbc.gridy = 0;
    	leftPanel.add(twenty,gbc);
    	
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 0;
        gbc.gridy = 1;
    	leftPanel.add(thirty,gbc);
    	
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 0;
        gbc.gridy = 2;
    	leftPanel.add(fifty,gbc);
    	
    	
    	JPanel rightPanel = new JPanel();
    	leftPanel.setLayout(new GridBagLayout());
    	
    	GridBagConstraints gbc1 = new GridBagConstraints();
        
    	gbc1.fill = GridBagConstraints.HORIZONTAL;       
        JRadioButton meaningOnly = new JRadioButton("Meanings Only", false);
        meaningOnly.setActionCommand("mean");
        JRadioButton synsOnly = new JRadioButton("Synonyms Only", false);
        synsOnly.setActionCommand("syns");
        JRadioButton antsOnly = new JRadioButton("Antonyms Only", false);
        antsOnly.setActionCommand("ants");
        JRadioButton bothSynsAndAnts = new JRadioButton("Synonyms and Antonyms", false);
        bothSynsAndAnts.setActionCommand("both");

        quesTypeGroup = new ButtonGroup();
        quesTypeGroup.add(meaningOnly);
        quesTypeGroup.add(synsOnly);
        quesTypeGroup.add(antsOnly);
        quesTypeGroup.add(bothSynsAndAnts);
        
        gbc1.fill = GridBagConstraints.HORIZONTAL;
    	gbc1.gridx = 0;
        gbc1.gridy = 0;
    	rightPanel.add(meaningOnly,gbc1);
    	
    	gbc1.fill = GridBagConstraints.HORIZONTAL;
    	gbc1.gridx = 1;
        gbc1.gridy = 0;
    	rightPanel.add(synsOnly,gbc1);
    	
    	gbc1.fill = GridBagConstraints.HORIZONTAL;
    	gbc1.gridx = 2;
        gbc1.gridy = 0;
    	rightPanel.add(antsOnly,gbc1);
    	
    	gbc1.fill = GridBagConstraints.HORIZONTAL;
    	gbc1.gridx = 3;
        gbc1.gridy = 0;
    	rightPanel.add(bothSynsAndAnts,gbc1);
   	
    	JButton processButton = new JButton("Create Test");
    	processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
    	processButton.setMaximumSize(new Dimension(40, 40));

    	this.setLayout(new GridBagLayout());
    	GridBagConstraints gbc2 = new GridBagConstraints();
    	gbc2.fill = GridBagConstraints.HORIZONTAL;       
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        this.add(new JLabel("Data File: "),gbc2);
        
        filePathField = new JTextField(25);
        gbc2.fill = GridBagConstraints.HORIZONTAL;       
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        this.add(filePathField,gbc2);
        
        gbc2.fill = GridBagConstraints.HORIZONTAL;       
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        this.add(leftPanel,gbc2);
    	
        gbc2.fill = GridBagConstraints.HORIZONTAL;       
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        this.add(rightPanel,gbc2);
    	   	
        gbc2.gridx = 0;
        gbc2.gridy = 3;
    	gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridwidth = 2;
    	this.add(processButton,gbc2);

//    	this.add(headingLabel,BorderLayout.NORTH);
    	
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
    	if(!filePathField.getText().trim().equals("")){
    		dataFilePath = filePathField.getText().trim();
    	}
    	
    	ArrayList<WordNode> listOfWords = new ArrayList<WordNode>();
    	try(BufferedReader br = new BufferedReader(new FileReader(dataFilePath))){
    		String line = null;
    		while((line=br.readLine())!=null){
    			String[] tmp = line.split("\t");
    			WordNode wn = new WordNode();
    			wn.setWord(tmp[0]);
    			wn.setMeaning(tmp[1]);
    			wn.setUsage(tmp[2]);
    			
    			String[] syns = null;
    			if(!tmp[3].equals("")){
    				syns = tmp[3].split(",");
    			}
    			wn.setSynonyms(syns);
    			
    			String[] ants = null;
    			if(!tmp[4].equals("")){
    				ants = tmp[4].split(",");
    			}
    			wn.setAntonyms(ants);
    			listOfWords.add(wn);
    		}
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
    	TestFrame tf = new TestFrame(listOfWords,quesNumGroup.getSelection().getActionCommand(),quesTypeGroup.getSelection().getActionCommand());
//    	tf.setPreferredSize(new Dimension(1000, 600));
    	tf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	tf.setVisible(true);
    	tf.pack();
    	
    	
    }


}
