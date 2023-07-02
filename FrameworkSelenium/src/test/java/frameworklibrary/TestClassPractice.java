package frameworklibrary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TestClassPractice {
	
	public static void main(String[] args) {
		
		System.out.println("Hello");
		
		List<String> lst = new LinkedList<String>();
		
		lst.add("Girish");
		lst.add("Harish");
		lst.add("Dinesh");
		lst.add("Pritam");
		
		Iterator<String> ite = lst.iterator();
		
		while (ite.hasNext()) {
			
			String str = ite.next();
			System.out.println("Value is: " + str);
		}
		
	}

}
