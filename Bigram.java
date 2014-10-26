package main;

import java.util.List;
import java.util.ArrayList;

public class Bigram {
	int count = 0;

  protected String word1;
  protected String word2;

  public Bigram() {
    word1 = "";
    word2 = "";
  }

  public Bigram(String s1, String s2) {
    word1 = s1;
    word2 = s2;
  }

  public String getWord1() {
    return word1;
  }

  public String getWord2() {
    return word2;
  }

  public String getkey(){
	  String key = word1.concat(word2);
	  
	return key;
	  
	  
  }
  public void setcount(){
	  count = count +1;
	//return count;
	  
  }
  
  public int getcount(){
	  
	return count;
	  
  }
  public List<String> bigramlist() {
          List<String> bigramlistwords = new ArrayList<String>();
          bigramlistwords.add(word1);
          bigramlistwords.add(word2);
          return bigramlistwords;
        } 
}