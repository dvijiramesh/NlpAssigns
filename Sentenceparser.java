package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;



public class Sentenceparser {

	ArrayList<String> listwords = new ArrayList<String>();
	ArrayList<String> listwords_withstop = new ArrayList<String>();
	ArrayList<String> test_listwords_withstop = new ArrayList<String>();
	ArrayList<String> lowercasewords = new ArrayList<String>();
	ArrayList<String> total_countlowercasewords = new ArrayList<String>();
	ArrayList<String> testfile_listwords = new ArrayList<String>();
	float vocabsize;
	int test_vocab;
	double totprob ;
	double totprob1;

	float likelihood;
	float loglikelihood;
	float log_prob;
	String Start = "<S>";
	String Stop = "</S>";
	String Unknown = "<UNK>";
	float testprobability;
	int count1;
	int counttest;
	float totalprob = 0;
	int ct = 0;
	HashMap<String,Integer> words_counts_map = new HashMap<String,Integer>();
	HashMap<String,Integer> testwords_counts_map = new HashMap<String,Integer>();
	HashMap<String,Float> wordprob_map = new HashMap<String,Float>();
	HashMap<String,Float> testwordprob_map = new HashMap<String,Float>();


	int count;
	public void ReadFile() throws IOException {
		Properties p = new Properties();
		File file = new File("C:\\Users\\vijayalakshmi\\Desktop\\nlp\\Assign1\\english-senate-train.txt");
	//	BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\vijayalakshmi\\Desktop\\nlp\\Assign1\\english-senate-train_output.txt"));

		System.out.println("TRAIN FILE UNDER PROGRESS");
		  
	    String wholetextdata = FileUtils.readFileToString(file);
	    String[] sentences = wholetextdata.split("\\.");
	    
	    System.out.println("Sentenses size " + sentences.length);
	    
	    for (int i = 0;i<sentences.length;i++){
	    //	System.out.println(i);
	    	String[] words = sentences[i].trim().split("\\s+");
	    		for (int j=0; j<words.length;j++){
	    			listwords.add(words[j]);
	    			listwords_withstop.add(words[j]);
		    		}
	    		listwords_withstop.add(Stop);
	    		for (int k=0;k<listwords.size();k++){
					listwords.set(k, listwords.get(k).toLowerCase());
	    			}
	    	} 
	   // System.out.println("TOTAL WORDS OF TRAIN FILE       " + listwords.size());
//	    bw.write("TOTAL WORDS OF TRAIN FILE       " + listwords.size());
	    for (int i=0; i<listwords.size();i++){
	    	if(!words_counts_map.containsKey(listwords.get(i))){
	    		words_counts_map.put(listwords.get(i), 1);
	    		vocabsize = vocabsize+1;
	    		} 
	    	else {
	    		count1 = words_counts_map.get(listwords.get(i)) + 1;
	    		words_counts_map.remove(listwords.get(i));
	    		words_counts_map.put(listwords.get(i), count1);
	    	}
//	    	bw.write("VOCAB SIZE OF TRAIN FILE           " + vocabsize);
	    }
    	
	   // System.out.println("VOCAB SIZE OF TRAIN FILE           " + vocabsize);
    	
	 //   double count_listwords = count_arraylist(listwords);
	    double count_listwords = listwords.size();
	    
	    for (Entry<String,Integer> entry  : words_counts_map.entrySet()) {
		    String words = entry.getKey();
		    
	//	    System.out.println("words" + words);
		    
		    int freq = entry.getValue() ;
		    
//		    System.out.println("count_listwords_with_stop"+ count_listwords);
		    
//		    System.out.println("freq" + freq);
		    
		    float probability = (float) (freq/count_listwords);
		    
//		    System.out.println("prob" + probability);
		    
//		    System.out.println("total count " + count_listwords);
		    
		    wordprob_map.put(words, probability);
		}
	    
	    for (Entry<String,Float> entry  : wordprob_map.entrySet()) {
		       
		    totalprob += entry.getValue() ;    

//		   bw.write("TOTAL PROB OF TRAIN SET                 " + totalprob);
		}
		
	    //System.out.println("TOTAL PROB OF TRAIN SET                 " + totalprob);
		   
//		for (Entry<String,Integer> entry  : words_counts_map.entrySet()) {
//		    System.out.println(entry.getKey() + " - " + entry.getValue());
//		}
	    
		System.out.println("TRAIN FILE PROGRESS - DONE");	
	
}
	public boolean checkword(String string){
		if (wordprob_map.containsKey(string)) 
			
			return true;
		else 
			return false;
		
	}
	private int getcount(String string) {
		int word_count = 0;
		for (Entry<String, Integer> entry  : words_counts_map.entrySet()) {
		   word_count = entry.getValue();
		  
		}
		return word_count;
		}
	
	public void test() throws IOException{
		float alpha = 1;
		float probability_givenlamda;
		Properties p = new Properties();
		File file = new File("C:\\Users\\vijayalakshmi\\Desktop\\nlp\\Assign1\\english-senate-test.txt");
//		BufferedWriter bw1 = new BufferedWriter(new FileWriter("C:\\Users\\vijayalakshmi\\Desktop\\nlp\\Assign1\\english-senate-test_output.txt"));

		System.out.println("TEST FILE UNDER PROGRESS");
		
	    String wholetextdata = FileUtils.readFileToString(file);
	    String[] sentences = wholetextdata.split("\\.");
	    
	    for (int i = 0;i<sentences.length;i++){
	    	String[] words = sentences[i].trim().split("\\s+");
	    		for (int j=0; j<words.length;j++){
					testfile_listwords.add(words[j]);
	    			test_listwords_withstop.add(words[j]);
		    		}
	    		listwords_withstop.add(Stop);
	    		for (int k=0;k<testfile_listwords.size();k++){
					testfile_listwords.set(k, testfile_listwords.get(k).toLowerCase());
					test_listwords_withstop.set(k, test_listwords_withstop.get(k).toLowerCase());
	    			}
	    	} 
	    
	System.out.println("TEST FILE SENTENCES LENGTH - " + sentences.length);
		
	 	for (int i=0; i<testfile_listwords.size();i++){
	    	if(!testwords_counts_map.containsKey(testfile_listwords.get(i))){
	    		testwords_counts_map.put(testfile_listwords.get(i), 1);
	    		test_vocab = test_vocab+1;
	    } 
    	else {
	    		counttest = testwords_counts_map.get(testfile_listwords.get(i)) + 1;
	    		testwords_counts_map.remove(testfile_listwords.get(i));
	    		testwords_counts_map.put(testfile_listwords.get(i), count1);
    	}
	    	
	// System.out.println("TEST FILE VOCAB SIZE            " + test_vocab);   
//	 bw1.write("TEST FILE VOCAB SIZE            " + test_vocab);
	 
	 }
	 	
//	 double testcount_listwords = count_arraylist(testfile_listwords);
	 
	 double testcount_listwords = testfile_listwords.size();
	 	
	 System.out.println("TOTAL WORD SIZE OF TEST FILE            " + testcount_listwords);

	 //	 bw1.write("TOTAL WORD SIZE OF TEST FILE            " + testcount_listwords);
	    for (Entry<String,Integer> entry  : words_counts_map.entrySet()) {
		    String words = entry.getKey();
		    
		//    System.out.println("words" + words);
		  //  int freq = entry.getValue() ;
		   // System.out.println("count_listwords_with_stop"+testcount_listwords);
		   
		    if (checkword(words)){
		    int freq = entry.getValue(); 
		    probability_givenlamda = (float) ((freq + 50)/(listwords.size() + (50 *vocabsize)));
		    log_prob = (float) (Math.log(probability_givenlamda)/Math.log(2));
		   //System.out.println("prob given lamda" + probability_givenlamda);
		    }
		    else {
		    	probability_givenlamda = (float) ( 50/(listwords.size()+ (50 *vocabsize)));
			    log_prob = (float) (Math.log(probability_givenlamda)/Math.log(2));

		    }
		    loglikelihood += log_prob;
		    System.out.println("TOTAL LOGLIKELIHOOD     " + loglikelihood);
		    testwordprob_map.put(words, probability_givenlamda);
		}
	   //System.out.println("SENTENCES TO CALC PROB" + sentences);
//	   bw1.write("SENTENCES TO CALC PROB" + sentences);
	   
	   /*getsentenceProbability(sentences);
	  System.out.println("Loglikelihood" + loglikelihood); */
	   
	  // for ( int n = 0; n<testfile_listwords.size();n++){
		//   getsentenceProbability(testfile_listwords.get(n));
		//   System.out.println("UNIGRAM SIZES" + listwords.size() + "    " + vocabsize);
	  // }
		//System.out.println("TOTProb LogLikely " + totprob);
		
		System.out.println("TEST FILE PROGRESS DONE");
		
	}
	    
	private double getsentenceProbability(String string) throws IOException {
	    	double prob = 0;
	    	
//			BufferedWriter bw2 = new BufferedWriter(new FileWriter("C:\\Users\\vijayalakshmi\\Desktop\\nlp\\Assign1\\english-senate-test_output1.txt"));

//	    	while (ct<= testfile_listwords.size()){
				for (Entry<String,Float> entry  : testwordprob_map.entrySet()) {
					String word1 = entry.getKey();
					if (word1.equals(string)) {	    						
						prob =  (Math.log(entry.getValue())/Math.log(2));
//						System.out.println("PROBAB" +prob);
						totprob += prob;
					//totprob1 *= totprob;
					}
//	    		bw2.write("TOTProb"+ totprob);

//				}	ct++;	
    			
				//System.out.println("Iteration couts to find prob..............." +ct);
				//bw2.write("Iteration couts to find prob..............." +ct);
	    		System.out.println("TOTProb LogLikely - string - " + totprob);

			
		
	}
			return totprob;
}
	    	/*for (int i= 0; i<string.length; i++){
	    		System.out.println("SENTENCES" + string[i]);
	    		bw2.write("SENTENCES" + string[i]);
	    		String[] words = string[i].split("\\s+");
	    			for (int j = 0; j<words.length; j++){
	    				//System.out.println("Sentence words: " +words[j]);
	    				for (Entry<String,Float> entry  : testwordprob_map.entrySet()) {

	    					String word1 = entry.getKey();
	    					
	    					//System.out.println(" MAP WORD  ***************      " + word1  + " = " + words[j]);
	    					
	    					if (word1.equals(words[j])) {
		    					//System.out.println("******** MATCH FOUND FOR **********      " + entry.getKey());
		    					
	    						//System.out.println("PROB GIVEN LAMDA" +entry.getValue());
	    						prob =  (Math.log(entry.getValue())/Math.log(2));
	    						System.out.println("PROBAB" +prob);
	    						totprob += prob;
	    						//totprob1 *= totprob;
	    						}
	    		    		else{
	    		    			//System.out.println("********WORD NOT EXIST");
	    		    		}
	    		    		System.out.println("TOTProb"+ totprob);
	    		    		bw2.write("TOTProb"+ totprob);

	    					}	ct++;	
	    	    			
	    					System.out.println("Iteration couts to find prob..............." +ct);
	    					bw2.write("Iteration couts to find prob..............." +ct);

	    		
	    		
	    	}*/
  			   
	
		
	
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
	public static void main(String[] args) throws IOException {
		
		Sentenceparser main = new Sentenceparser();
		main.ReadFile();
		main.test();
		
	}

}
