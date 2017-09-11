/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author asharm73
 */
public class AddEntryPanel extends JPanel {
	private ArrayList<WordNode> listOfWords = null;
	private JTextField wordField;
	private JTextField meaningField;
	private JTextField usageField;
	private JTextField synonymsField;
	private JTextField antonymsField;
	private JLabel msgLabel;
	private String dataFilePath = null;

    /**
     *
     */
    private static final long serialVersionUID = 231246293082095300L;
    
    public AddEntryPanel(String dataFilePath) {
    	this.dataFilePath = dataFilePath;
    	initComponents();
    }

    private void initComponents() {
    	this.listOfWords = new ArrayList<WordNode>();
    	
    	JPanel centerPanel = new JPanel();
    	centerPanel.setSize(400,400);
    	centerPanel.setLayout(new GridBagLayout());
    	
    	GridBagConstraints gbc = new GridBagConstraints();
    	
    	    	
    	gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel wordLabel = new JLabel("Word:",SwingConstants.RIGHT);
    	wordField = new JTextField(20);
    	gbc.gridx = 0;
        gbc.gridy = 2;
    	centerPanel.add(wordLabel,gbc);

    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 1;
        gbc.gridy = 2;
    	centerPanel.add(wordField,gbc);
    	
    	JLabel meaningLabel = new JLabel("Meaning:",SwingConstants.RIGHT);
    	meaningField = new JTextField();
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 0;
        gbc.gridy = 3;
    	centerPanel.add(meaningLabel,gbc);
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 1;
        gbc.gridy = 3;
    	centerPanel.add(meaningField,gbc);
    	
    	JLabel usageLabel = new JLabel("Usage:",SwingConstants.RIGHT);
    	usageField = new JTextField();
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 0;
        gbc.gridy = 4;
    	centerPanel.add(usageLabel,gbc);
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 1;
        gbc.gridy = 4;
    	centerPanel.add(usageField,gbc);
    	
    	JLabel synonymsLabel = new JLabel("Synonyms (Separated by 'comma'):",SwingConstants.RIGHT);
    	synonymsField = new JTextField();
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 0;
        gbc.gridy = 5;
    	centerPanel.add(synonymsLabel,gbc);
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 1;
        gbc.gridy = 5;
    	centerPanel.add(synonymsField,gbc);
    	
    	JLabel antonymsLabel = new JLabel("Antonyms (Separated by 'comma'):",SwingConstants.RIGHT);
    	antonymsField = new JTextField();
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 0;
        gbc.gridy = 6;
    	centerPanel.add(antonymsLabel,gbc);
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.gridx = 1;
        gbc.gridy = 6;
    	centerPanel.add(antonymsField,gbc);
    	
    	
    	
//    	msgField = new JTextField(20);
//    	gbc.fill = GridBagConstraints.HORIZONTAL;
//    	gbc.gridx = 1;
//        gbc.gridy = 6;
//    	centerPanel.add(msgField,gbc);
    	
    	JButton processButton = new JButton("Add");
    	processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

    	gbc.insets = new Insets(10,0,0,0);
    	gbc.gridx = 0;
        gbc.gridy = 7;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridwidth = 2;
    	centerPanel.add(processButton,gbc);
    	
    	JButton writeToFileButton = new JButton("Write to File");
    	writeToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

    	gbc.insets = new Insets(10,0,0,0);
    	gbc.gridx = 1;
        gbc.gridy = 7;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridwidth = 2;
    	centerPanel.add(writeToFileButton,gbc);
    	
    	JButton updateButton = new JButton("Update a Word");
    	updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

    	gbc.insets = new Insets(10,0,0,0);
    	gbc.gridx = 1;
        gbc.gridy = 8;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
    	centerPanel.add(updateButton,gbc);
    	
    	
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	msgLabel = new JLabel("Welcome!!!:",SwingConstants.CENTER);
    	gbc.insets = new Insets(10,0,0,0);
    	gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
    	centerPanel.add(msgLabel,gbc);
    	
//    	this.add(headingLabel,BorderLayout.NORTH);
    	this.add(centerPanel);
    	
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
    	String word = wordField.getText().trim().toLowerCase();
    	String meaning = meaningField.getText().trim().toLowerCase();
    	if(!word.equals("") && !meaning.equals("")){
    		String usage = usageField.getText().trim();
    		if(usage.equals("")){
    			usage = "null";
    		}
    		
    		String syns = synonymsField.getText().trim().toLowerCase();
    		String[] synsArr = null;
    		if(!syns.equals("")){
    			synsArr = syns.split(",");
    		}
    		String ants = antonymsField.getText().trim().toLowerCase();
    		String[] antsArr = null;
    		if(!ants.equals("")){
    			antsArr = ants.split(",");
    		}
    		
    		boolean repeatFlag = false;
    		for(WordNode wTemp : listOfWords){
    			if(word.equals(wTemp.getWord())){
    				repeatFlag = true;
    				break;
    			}
    		}
    		if(!repeatFlag){
    			WordNode wn = new WordNode();
        		wn.setWord(word);
        		wn.setMeaning(meaning);
        		wn.setUsage(usage);
        		wn.setSynonyms(synsArr);
        		wn.setAntonyms(antsArr);
        		listOfWords.add(wn);
        		
        		wordField.setText("");
        		meaningField.setText("");
        		usageField.setText("");
        		synonymsField.setText("");
        		antonymsField.setText("");
        		msgLabel.setText("Word added to the list!");
    		}else{
    			msgLabel.setText("Word is repeated, Please use the update button to update word info!");
    		}
    		
    	}else{
    		msgLabel.setText("Word and Meaning fields can not be empty!");
    	}
    }
    
    private void jButton2ActionPerformed(ActionEvent evt) {
    	try(BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilePath,true))){
    		for(WordNode w : listOfWords){
    			bw.append(w.toString());
    			bw.newLine();
    		}
    		bw.close();
    		listOfWords.clear();
    		msgLabel.setText("Words appended to the data file!");
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
    }
    
    private void loadDataFileBtnActionPerformed(ActionEvent evt) {
    	if(!listOfWords.isEmpty()){
			jButton2ActionPerformed(evt);
		}
    	try(BufferedReader br = new BufferedReader(new FileReader(dataFilePath))){
    		String line = null;
    		while((line = br.readLine())!=null){
    			String[] tmp = line.split("\t");
    			WordNode wn = new WordNode();
    			wn.setWord(tmp[0]);
    			wn.setMeaning(tmp[1]);
    			wn.setUsage(tmp[2]);
    			wn.setSynonyms(tmp[3].split(","));
    			wn.setAntonyms(tmp[4].split(","));
    			listOfWords.add(wn);   			
    		}
    	}catch(IOException e){
    		e.printStackTrace();
    	}   	
    }
    
    private void updateButtonActionPerformed(ActionEvent evt) {
    	loadDataFileBtnActionPerformed(evt);
    	String word = wordField.getText().trim().toLowerCase();
    	String meaning = meaningField.getText().trim().toLowerCase();
    	if(!word.equals("") && !meaning.equals("")){
    		String usage = usageField.getText().trim();
    		if(usage.equals("")){
    			usage = "null";
    		}
    		
    		String syns = synonymsField.getText().trim().toLowerCase();
    		String[] synsArr = null;
    		if(!syns.equals("")){
    			synsArr = syns.split(",");
    		}
    		String ants = antonymsField.getText().trim().toLowerCase();
    		String[] antsArr = null;
    		if(!ants.equals("")){
    			antsArr = ants.split(",");
    		}
    		
    		for(WordNode wTemp : listOfWords){
    			if(word.toLowerCase().equals(wTemp.getWord())){
    				wTemp.setWord(word);
    				wTemp.setMeaning(meaning);
    				wTemp.setUsage(usage);
    				wTemp.setSynonyms(synsArr);
    				wTemp.setAntonyms(antsArr); 				
    			}
    		}
    		
    		try(BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilePath))){
        		for(WordNode w : listOfWords){
        			bw.append(w.toString());
        			bw.newLine();
        		}
        		bw.close();
        		listOfWords.clear();
        	}catch(IOException e){
        		e.printStackTrace();
        	}
        	
    		wordField.setText("");
    		meaningField.setText("");
    		usageField.setText("");
    		synonymsField.setText("");
    		antonymsField.setText("");
    		msgLabel.setText("Word Updated in the list!");  
    	}else{
    		msgLabel.setText("Word and Meaning fields can not be empty!");
    	}
    	
    }

}
