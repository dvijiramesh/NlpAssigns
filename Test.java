package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

public class Test extends Sentenceparser {
	
	public void test() throws IOException{
		float alpha = 1;
		
		Properties p = new Properties();
		File file = new File("C:\\Users\\dhamov1\\Desktop\\nlp\\Assign1\\english-senate-test1.txt");
	    String wholetextdata = FileUtils.readFileToString(file);
	    String[] sentences = wholetextdata.split("\\.");
	    for (int i = 0;i<sentences.length;i++){
	    	String[] words = sentences[i].split("\\s+");
	    	
	    	
	    		for (int j=0; j<words.length;j++){
	    			
					testfile_listwords.add(words[j]);
	    			test_listwords_withstop.add(words[j]);
		    		}
	    		listwords_withstop.add(Stop);
	    		for (int k=0;k<listwords.size();k++){
					testfile_listwords.set(k, testfile_listwords.get(k).toLowerCase());
					test_listwords_withstop.set(k, test_listwords_withstop.get(k).toLowerCase());
	    			}
	    	} 
	    for ( int m = 0; m<testfile_listwords.size();m++){
	     if (checkword(testfile_listwords.get(m))){
	    	 int count = getcount(testfile_listwords.get(m));
	    	 testprobability = (count+1)/(listwords.size() + vocabsize);
	    	 
	     }
	     else {testprobability = 1 / (listwords.size() + vocabsize);}
	     testprobability  += testprobability ;
	     System.out.println("testset probabilit" + testprobability);
	     
	    }
	   
	    
	    for (int i=0; i<listwords.size();i++){
	    	if(!words_counts_map.containsKey(listwords.get(i))){
	    		words_counts_map.put(listwords.get(i), 1);
	    	} 

	    	else {
	    		count1 = words_counts_map.get(listwords.get(i)) + 1;
	    		words_counts_map.remove(listwords.get(i));
	    		words_counts_map.put(listwords.get(i), count1);
	    		
	    	
}}
	    
	    double count_listwords = count_arraylist(listwords);
	    for (Entry<String,Integer> entry  : words_counts_map.entrySet()) {
		    String words = entry.getKey();
		    
		    System.out.println("words" + words);
		    int freq = entry.getValue() ;
		    System.out.println("count_listwords_with_stop"+count_listwords);
		    System.out.println("freq" + freq);
		    float probability = (float) (freq/count_listwords);
		    System.out.println("prob" + probability);
		    System.out.println("total count " + count_listwords);
		    wordprob_map.put(words, probability);
		}
	    
	    for (Entry<String,Float> entry  : wordprob_map.entrySet()) {
		    System.out.println("Word" + entry.getKey() + " - " + "prob" + entry.getValue());    
		    totalprob += entry.getValue() ;    
		    System.out.println("totalprob" + totalprob);
		}
	    
		for (Entry<String,Integer> entry  : words_counts_map.entrySet()) {
		    System.out.println(entry.getKey() + " - " + entry.getValue());
		  
		}
		
	}
	
	public double count_arraylist(ArrayList<String> listwords){
			double total_count = 0;
			double vocab_size = 0;
	    		for (int l = 0; l<listwords.size();l++){
	    			total_countlowercasewords.add(listwords.get(l));
	    			System.out.println(total_countlowercasewords.get(l));
					if (!lowercasewords.contains(listwords.get(l))){
						lowercasewords.add(listwords.get(l));
					}
	    		
	    		}
	    		total_count = total_countlowercasewords.size();
	    		vocab_size = lowercasewords.size();
	    		System.out.println(lowercasewords.size());
	    		System.out.println("total_words" + total_countlowercasewords.size());
				return total_count;

	    }

}
