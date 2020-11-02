package model;

import java.awt.Image;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import javax.imageio.ImageIO;


public class Person {

	public static final String IMAGE_LINK = "https://thispersondoesnotexist.com/image";
	
	private Long code;
	private String name;
	private String surname;
	private Gender gender;
	private String birthdate; //DD/MM/YY
	private double height; //In meters with two decimals
	private String nationality;
	
	public Person(long code, String name, String surname, int age, String nationality) throws MalformedURLException, IOException {
		this.code = code;
		this.name = name;
		this.surname = surname;
		this.gender = (Math.random() < 0.5) ? Gender.Male : Gender.Female;
		this.birthdate = LocalDate.now().minusYears((long)age).minusDays(1).toString();
		this.height = Math.random() * (2 - 1.2 + 1) + 1.2; //generates a num between 1,4 and 2
		this.nationality = nationality;
		
	}
	
	public Image getImage() throws IOException {
		URL url = new URL(IMAGE_LINK);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		//The page rejects request without a standard header
		connection.setRequestProperty("User-Agent", "NING/1.0");
		return ImageIO.read(connection.getInputStream());
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
