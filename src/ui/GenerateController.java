package ui;


import java.io.IOException;
import java.net.MalformedURLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Database;

public class GenerateController {
	
	private Database db;
	
	public GenerateController(Database db) {
		this.db = db;
	}
	
	@FXML
    private JFXTextField numOfData;

    @FXML
    private JFXButton generateButton;

    @FXML
    private JFXProgressBar progressBar;

    @FXML
    public void generateAct(ActionEvent event) {
    	int amount = Integer.parseInt(numOfData.getText());
    	try {
			db.generateTempList(amount);
			db.mergeTempList();
			numOfData.clear();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
}
