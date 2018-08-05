import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;

public class palindrome {
	
	// the output function will display the standard test results as well as the advanced.
	private void output(String[] palindromes, String user_s) {
		String temp = "";
		int count = 0;
		
		System.out.println("\nSub-palindromes: ");
		for(int i = 0; i < user_s.length(); i++) {
			if(palindromes[i] != null) {
				count++;
				temp = user_s;
				temp = temp.replaceAll(palindromes[i], "(" + palindromes[i] + ")");
				System.out.println("Palindromes" + "(" + count + "): " + temp);
			}
		}
		if(count == 0) {
			System.out.println("could not find any sub-palindromes...");
		}
	}
	
	// This is the advanced test for palindrome programs. This one will find all possible palindromes in a String. this function uses recursion.
	private String[] adv_test(String user_string, int x, String test_string, String[] sub_palindromes) {
		String[] adv_array = new String[user_string.length()];
		String[] test_array = new String[user_string.length()];
		String[] test_array2 = new String[user_string.length()];
		String temp = "", temp2 = "";
		boolean same = false;
		
		adv_array = user_string.split("");
		
		// when the array is out of bounds the function will end.
		try {
			test_string += adv_array[x-1];
			test_array = test_string.split("");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return sub_palindromes;
		}
		
		// nested loops: these loops find the sub-palindromes in the Strings
		for(int i = test_string.length()-1; i >= 0; i--) {
			if(test_string.length() > 1) {
				temp2 = "";
				temp += test_array[i];
				test_array2 = temp.split("");
			}
			
			for(int y = temp.length()-1; y >= 0; y--) {
				if(temp.length() > 1) {
					temp2 += test_array2[y];
				}
			}
			
			if(temp2.equals(temp) && temp.length() > 1) {
				same = true;
				break;
			}
		}
		
		// if a sub-palindrome is found the program will store it in an array for later.
		if(same == true) {
			sub_palindromes[x] = temp;
		}
		
		return adv_test(user_string, x-1, test_string, sub_palindromes);
	}
	
	// This is the Standard test for palindrome programs E.g: Madam is the same spelt backwards.
	private void Standard_test(String user_string) {
		String[] test_Sarray = new String[user_string.length()+1];
		String test_string = "";
		
		test_Sarray = user_string.split("");
		
		for(int i = user_string.length() - 1; i >= 0; i--) {
			test_string += test_Sarray[i];
		}
		
		if(test_string.equals(user_string)) {
			System.out.println("\nStandard test Result: true");
		}
		else {
			System.out.println("\nStandard test Result: false");
		}
	}
	
	// This function will get the String from the user.
	private String get_string() {
		Scanner user_input = new Scanner(System.in);
		String user_i = "";
		
		try {
			System.out.print("(Please remove any punctuation marks)\nPlease enter your word to be tested: ");
			user_i = user_input.nextLine();
			user_input.close();
		}
		catch(InputMismatchException e) {
			System.out.println("ERROR: input is invaild...");
			user_input.close();
		}
		
		return user_i;
	}
	
	//###--MAIN--###//
	public static void main(String[] args) {
		palindrome obj = new palindrome();
		String user_s, test_string = "";
		int x = 0;
		
		// making sure Strings are as usable as possible.
		user_s = obj.get_string();
		user_s = user_s.toLowerCase();
		user_s = user_s.replaceAll(" ", "");
		String[] sub_palindromes = new String[user_s.length()];
		String[] palindromes = new String[user_s.length()];
		
		x = user_s.length();
		obj.Standard_test(user_s);
		palindromes = obj.adv_test(user_s, x, test_string, sub_palindromes);
		obj.output(palindromes, user_s);
		
		try {
			System.out.println("Shutting down...");
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			
		}
	}
}
