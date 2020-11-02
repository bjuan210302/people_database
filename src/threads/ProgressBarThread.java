package threads;

import model.Database;

public class ProgressBarThread extends Thread{
	public Database db;
	public int numOfPeople;
	public int percentage;
	
	public ProgressBarThread(Database db, int num) {
		this.db = db;
		this.numOfPeople = num;
		this.percentage = num/100;
	}
	
	public void run() {
		
	}
	

}
