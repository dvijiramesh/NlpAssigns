package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GoodBad {

	public void goodbad(){
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\dhamov1\\Desktop\\nlp\\Assign1\\good-bad.txt"));
		String line;
		int linenum = 0;
		ArrayList<String> goodwords = new ArrayList<String>();
		ArrayList<String> badwords = new ArrayList<String>();
		ArrayList<Float> goodwords_prob = new ArrayList<Float>();
		while ((line = br.readLine()) != null){
			linenum++;
			String[] wordarrays = line.split("\\s+");
			int len = wordarrays.length;
			if (linenum % 2 != 0){
			for (int i = 0; i< len; i++){
					goodwords.add(wordarrays[i]);
					
			}
			for (int i = 0;i<goodwords.size();i++){
				goodwords.set(i, goodwords.get(i).toLowerCase());
			}
			}
			else {
				for (int i = 0; i< len; i++){
					
					badwords.add(wordarrays[i]);
					
			}
				for (int i = 0;i<goodwords.size();i++){
					goodwords.set(i, goodwords.get(i).toLowerCase());
				}
			}
				
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
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
