package otros;

import java.util.Scanner;

public class pr1 {

	public static void main(String[] args) {

		System.out.print("Write a phrase: ");
		
		Scanner input = new Scanner(System.in);
		
		String input1 = input.nextLine();
		
		input.close();
		
		String[] inputArray = input1.split(" ");
		
		
				
		for(int i=0;i<inputArray.length;i++) {
			System.out.println(inputArray[i]);
		}
		
	}

}
