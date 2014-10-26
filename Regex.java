package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Regex {
	Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
	Matcher m = p.matcher("23!");
	String s1 = "uppercase";
	String s2 = "ABC";
	
	
	public void test(){
		boolean b = m.find();
		if (b)
			   {System.out.println("There is a special character in my string");}
		
		if(Character.isUpperCase(s1.charAt(0))==true)
		   {System.out.println("There is a upper case in s1");}
		else
		   {System.out.println("There is no upper case in s1" );}
		
		if (StringUtils.isAllUpperCase(s2)){
			System.out.println("All upper case");
		}

		
		
	}
	public static void main(String[] args) {
		Regex r = new Regex();
	r.test();	

	}

}
