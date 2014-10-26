package main;

import java.io.IOException;

public class MainForLangModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sentenceparser main = new Sentenceparser();
		try {
			main.ReadFile();
			main.test();
			main.goodbad();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
