package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;

public class MainforA1T2 {

	ArrayList<String> words = new ArrayList<String>();
	ArrayList<String> of_words = new ArrayList<String>();

	HashMap<String,Integer> afterOfwords = new HashMap<String,Integer>();
	int count,count1;
	public void ReadFile() throws IOException {
	 			
			/*Scanner sc = new Scanner("C:\\Users\\dhamov1\\Desktop\\nlp\\Assign1\\english-senate-train.txt");
			String word = sc.next();
			if(!words.contains(word)){
				words.add(word);
			}*/
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\dhamov1\\Desktop\\nlp\\Assign1\\english-senate-train.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\dhamov1\\Desktop\\nlp\\Assign1\\english-senate-train_output.txt"));
			Properties p = new Properties();
			String line;
			while ((line = br.readLine()) != null){

				String[] wordarrays = line.split("\\s+");
				int len = wordarrays.length;
				for (int i = 0; i< len; i++){

					//if (wordarrays[i].equals("of")) {
						//words.add(wordarrays[i+1]);
						
						
					//}
					words.add(wordarrays[i]);
				}
			}
			for (int i = 0;i<words.size();i++){
				words.set(i, words.get(i).toLowerCase());
				//System.out.println(words.get(i));
			}
			for(int i= 0; i<words.size();i++){
				if ((words.get(i).equals("of"))) {
					of_words.add(words.get(i+1));
				}
			}
			
			for (int i=0; i<of_words.size();i++){
if(!afterOfwords.containsKey(of_words.get(i))){
	afterOfwords.put(of_words.get(i), 1);
} 

else {
	count1 = afterOfwords.get(of_words.get(i)) + 1;
	afterOfwords.remove(of_words.get(i));
	afterOfwords.put(of_words.get(i), count1);
	
}
}
			
			for (Entry<String,Integer> entry  : afterOfwords.entrySet()) {
			    System.out.println(entry.getKey() + " - " + entry.getValue());
			bw.write(entry.getKey() + " - " + entry.getValue() + "\r\n") ; 
			}
			
		bw.close();	
	
	}
	public static void main(String[] args) throws IOException {
		
		MainforA1T2 main = new MainforA1T2();
		main.ReadFile();
		
	}

}
