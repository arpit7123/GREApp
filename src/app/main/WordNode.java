package app.main;


public class WordNode {

	private String word;
	private String meaning;
	private String usage;
	private String[] synonyms = null;
	private String[] antonyms = null;
	
	public void setWord(String word){
		this.word = word;
	}
	
	public void setMeaning(String meaning){
		this.meaning = meaning;
	}
	
	public void setUsage(String usage){
		this.usage = usage;
	}
	
	public void setSynonyms(String[] synonyms){
		this.synonyms = synonyms;
	}
	
	public void setAntonyms(String[] antonyms){
		this.antonyms = antonyms;
	}
	
	public String getWord(){
		return this.word;
	}
	
	public String getMeaning(){
		return this.meaning;
	}
	
	public String getUsage(){
		return this.usage;
	}
	
	public String[] getSynonyms(){
		return this.synonyms;
	}
	
	public String[] getAntonyms(){
		return this.antonyms;
	}
	
	@Override
	public String toString(){
		String syns = "";
		if(synonyms!=null){
			for(String s : synonyms){
				syns += s+",";
			}
			syns = syns.substring(0, syns.length()-1);
		}
		
		String ants = "";
		if(antonyms!=null){
			for(String s : antonyms){
				ants += s+",";
			}
			ants = ants.substring(0, ants.length()-1);
		}
		
		String result = word+"\t"+meaning+"\t"+usage+"\t";
		if(syns.equals("")){
			result = result + "null";
		}else{
			result = result + syns;
		}
		
		if(ants.equals("")){
			result = result + "\t" + "null";
		}else{
			result = result + "\t" + ants;
		}
		
		return result;		
	}
}
