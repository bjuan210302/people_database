package ui;

import java.text.NumberFormat;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Database;
import threads.GeneratePeopleThread;

public class GenerateController {
	
	private NumberFormat numberFormat;
	private Database db;
	
	public GenerateController(Database db) {
		this.db = db;
		numberFormat = NumberFormat.getPercentInstance();
		numberFormat.setMaximumFractionDigits(3);
	}
	
	@FXML
    private JFXTextField numOfData;

    @FXML
    private JFXButton generateButton;

    @FXML
    private JFXButton mergeButton;
    
    @FXML
    private JFXProgressBar progressBar;
    
    @FXML
    private Label percentLabel;

    @FXML
    public void generateAct(ActionEvent event) {
    	int amount = Integer.parseInt(numOfData.getText());
    	new GeneratePeopleThread(db, this, amount).generateTempList();
    }
    
    @FXML
    public void mergeAct(ActionEvent event) {
    	int amount = Integer.parseInt(numOfData.getText());
    	new GeneratePeopleThread(db, this, amount).mergeTempList();
    }
    
    public void updateCounters(double num) {
    	progressBar.setProgress(num);
    	percentLabel.setText(numberFormat.format(num));
    }
}
