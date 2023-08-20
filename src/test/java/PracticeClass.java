import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class PracticeClass {

	public static void main(String args[]) throws IOException {
		
		int num = 14;
		
		for(int i=0;i<7;i++) {
			
			
			for(int k=0;k<=i;k++) {
				System.out.print(" ");
			}
			
			for(int j = 7; j>i; j--) {
				System.out.print(num + " ");
			}
			
			num = num -2;
		
			System.out.println();
		}
	
	}
}
