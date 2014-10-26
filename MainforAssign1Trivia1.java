package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainforAssign1Trivia1 {

	ArrayList<String> words = new ArrayList<String>();
	ArrayList<String> lowercasewords = new ArrayList<String>();
	int count;
	public void ReadFile() throws IOException {
	 			
			/*Scanner sc = new Scanner("C:\\Users\\dhamov1\\Desktop\\nlp\\Assign1\\english-senate-train.txt");
			String word = sc.next();
			if(!words.contains(word)){
				words.add(word);
			}*/
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\dhamov1\\Desktop\\nlp\\Assign1\\english-senate-train.txt"));
			String line;
			while ((line = br.readLine()) != null){

				String[] wordarrays = line.split("\\s+");
				
				
				int len = wordarrays.length;
				for (int i = 0; i< len; i++){
					//if (!words.contains(wordarrays[i])){
						words.add(wordarrays[i]);
						//}
				}
				

			}
			for (int i = 0;i<words.size();i++){
				words.set(i, words.get(i).toLowerCase());
			}
			
			for (int i = 0; i<words.size();i++){
				if (!lowercasewords.contains(words.get(i))){
					lowercasewords.add(words.get(i));
				}
			}
		//	for (int i=0; i<lowercasewords.size();i++){
				//System.out.println(lowercasewords.get(i));

				
			//}
		System.out.println(lowercasewords.size());
	}
	public static void main(String[] args) throws IOException {
		
		MainforAssign1Trivia1 main = new MainforAssign1Trivia1();
		main.ReadFile();
		
	}

}
