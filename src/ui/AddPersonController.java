package ui;

import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

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
    void createAct(ActionEvent event) {
    	String name = nameField.getText();
    	String surname = surnameField.getText();
    	String genre;
    	if(Genre.getSelectedToggle().equals(mascRbut)) {
    		genre = "Masculine";
    	}else {
    		genre = "Femenine";
    	}
    	LocalDate birthDate = birthDatePick.getValue();
    	double height = Double.parseDouble(heightField.getText());
    	String nationality = nationField.getText();

    }
}
