package model;

import javafx.scene.image.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;


public class Person {

	public static final String IMAGE_LINK = "https://thispersondoesnotexist.com/image";
	
	private Long code;
	private String name;
	private String surname;
	private Gender gender;
	private String birthdate; //DD/MM/YY
	private double height; //In meters with two decimals
	private String nationality;
	
	public Person(long code, String name, String surname, int age, String nationality) {
		this.code = code;
		this.name = name;
		this.surname = surname;
		this.gender = (Math.random() < 0.5) ? Gender.Male : Gender.Female;
		this.birthdate = LocalDate.now().minusYears((long)age).minusDays(1).toString();
		this.height = ThreadLocalRandom.current().nextDouble(1.3, 2); //generates a num in range [1.3, 2)
		this.nationality = nationality;
		
	}
	
	public Person(long code, String name, String surname, String gender, String birthdate, double height, String nationality) {
		this.code = code;
		this.name = name;
		this.surname = surname;
		this.gender = Gender.valueOf(gender);
		this.birthdate = birthdate;
		this.height = height;
		this.nationality = nationality;
		
	}
	
	public Image getImage() throws IOException {
		URL url = new URL(IMAGE_LINK);
		URLConnection connection = url.openConnection();
		//The page rejects request without a standard header
		connection.setRequestProperty("User-Agent", "NING/1.0");
		
		InputStream stream = connection.getInputStream();
		
		//It is necessary to use 
		return new Image(stream);
	}
	
	public void modifyAll(String gender, String birthdate, double height, String nationality) {
		this.gender = Gender.valueOf(gender);
		this.birthdate = birthdate;
		this.height = height;
		this.nationality = nationality;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCompoundName() {
		return name + " " + surname;
	}
	public String getInvertedCompoundName() {
		return surname + " " + name;
	}
	public Long getCode() {
		return this.code;
	}
}
