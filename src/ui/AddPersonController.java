package ui;

import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import ui.notifications.Notification;

public class AddPersonController {
	@FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField surnameField;

    @FXML
    private JFXButton createBut;

    @FXML
    private JFXRadioButton mascRbut;

    @FXML
    private ToggleGroup Genre;

    @FXML
    private JFXRadioButton femRBut;

    @FXML
    private JFXDatePicker birthDatePick;

    @FXML
    private JFXTextField heightField;

    @FXML
    private JFXTextField nationField;

    @FXML
    public void createAct(ActionEvent event) {
    	try {
    		String name = nameField.getText();
    		String surname = surnameField.getText();
    		String genre;
    		if(Genre.getSelectedToggle().equals(mascRbut)) {
    			genre = "Male";
    		}else {
    			genre = "Female";
    		}
    		LocalDate birthDate = birthDatePick.getValue();
    		double height = Double.parseDouble(heightField.getText());
    		String nationality = nationField.getText();
    	}
    	catch(NumberFormatException e) {
    		new Notification("Error!", "Please write the height in the correct format", Notification.ERROR).show();
    	}

    }
    
    public void initialize() {
 	   BooleanBinding boolBind = nameField.textProperty().isEmpty().or(surnameField.textProperty().isEmpty())
 			   .or(Genre.selectedToggleProperty().isNull()).or(birthDatePick.valueProperty().isNull()
 					   .or(heightField.textProperty().isEmpty()).or(nationField.textProperty().isEmpty()));
 	   
 	   createBut.disableProperty().bind(boolBind);
    }
}
