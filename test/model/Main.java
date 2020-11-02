package model;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Database db = new Database();
		db.generateTempList(100);
		db.mergeTempList();
		
		String prefix = new Scanner(System.in).nextLine();
		
		List<String> sugs = db.getSurnameSuggestions(prefix);
		
		sugs.forEach((String s) -> {
			System.out.println(s);
		});
	}

}
