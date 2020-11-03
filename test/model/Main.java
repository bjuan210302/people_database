package model;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;

public class Main {

	IntegerPropertyBase amount;
	
	public Main() {
		amount = new IntegerPropertyBase() {
			@Override
			public String getName() {
				return "what";
			}
			
			@Override
			public Object getBean() {
				return Main.this;
			}
		};
		
		amount.set(100);
		
		amount.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println("aaaaa");
			}
		});
		
		
	}
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		Database db = new Database();
		
		db.generateTempList(main.amount);
		System.out.println("Termino");
		db.mergeTempList();
		String prefix = new Scanner(System.in).nextLine();
		List<String> sugs = db.getSurnameSuggestions(prefix);
		
		sugs.forEach((String s) -> {
			System.out.println(s);
		});
		
			}

}
