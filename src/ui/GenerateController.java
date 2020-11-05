package ui;

import java.text.NumberFormat;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.BooleanBinding;
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
	
	public void initialize() {
		BooleanBinding numOfDataIsEmpty = numOfData.textProperty().isEmpty();
		generateButton.disableProperty().bind(numOfDataIsEmpty);
		mergeButton.disableProperty().bind(numOfDataIsEmpty);
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
    private Label statusLabel;

    @FXML
    public void generateAct(ActionEvent event) {
    	int amount = Integer.parseInt(numOfData.getText());
    	new GeneratePeopleThread(db, this, amount).generateTempList();
    	statusLabel.setText("Generating data, please wait...");
    	disableAllFields();
    }
    
    @FXML
    public void mergeAct(ActionEvent event) {
    	int amount = Integer.parseInt(numOfData.getText());
    	new GeneratePeopleThread(db, this, amount).mergeTempList();
    	statusLabel.setText("Merging data. This takes a little while, please wait...");
    	disableAllFields();
    }
    
    public void updateCounters(double num) {
    	progressBar.setProgress(num);
    	percentLabel.setText(numberFormat.format(num));
    }
    public void updateCountersFinish() {
    	progressBar.setProgress(0);
    	percentLabel.setText("Done!");
    	statusLabel.setText("");
    	enableAllFields();
    }
    private void disableAllFields() {
    	numOfData.setDisable(true);
    	generateButton.setDisable(true);
    	mergeButton.setDisable(true);
    }
    private void enableAllFields() {
    	numOfData.setDisable(false);
    	generateButton.setDisable(false);
    	mergeButton.setDisable(false);
    	percentLabel.setText("");
    }
}
