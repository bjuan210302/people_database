package ui.edit;

import java.io.IOException;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Database;
import model.Person;
import ui.notifications.Notification;

public class ModifyController {
	
	private Person p;
	private Database db;
	
	public ModifyController(Database db, Person p) {
		this.db = db;
		this.p = p;
	}
	
	@FXML
	Stage modifyWindow;
	
    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField surnameField;

    @FXML
    private JFXButton modifyBut;

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
    private JFXButton cancelBut;

    @FXML
    void cancelAct(ActionEvent event) {
    	modifyWindow.close();

    }
    
    

    @FXML
    void modifyAct(ActionEvent event) {
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
    		if(!name.equalsIgnoreCase(p.getName()) || !surname.equalsIgnoreCase(p.getSurname())) {
    			db.modidyPerson(p, name, surname, genre, birthDate.toString(), height, nationality);
    			new Notification("Success", "Person modified successfully", Notification.SUCCESS).show();
    		}
    		else {
    			db.modidyPerson(p, genre, birthDate.toString(), height, nationality);
    			new Notification("Success", "Person modified successfully", Notification.SUCCESS).show();
    		}
    		modifyWindow.close();
    	}
    	catch(NumberFormatException e) {
    		new Notification("Error!", "Please write the height in the correct format", Notification.ERROR).show();
    	}

    }
    
    public void modifyWindow() {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyPane.fxml"));
    	fxmlLoader.setController(this);
    	Pane modifyPane = null;
		try {
			modifyPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Scene scene = new Scene(modifyPane);
    	
    	modifyWindow = new Stage();
    	modifyWindow.setScene(scene);
    	modifyWindow.setResizable(false);
    	modifyWindow.initModality(Modality.APPLICATION_MODAL);
    	
    	nameField.setText(p.getName());
    	surnameField.setText(p.getSurname());
    	String gender = p.getGender().toString();
    	if(gender.equalsIgnoreCase("male")) {
    		Genre.selectToggle(mascRbut);
    	}else {
    		Genre.selectToggle(femRBut);
    	}
    	birthDatePick.setValue(LocalDate.parse(p.getBirthdate()));
    	heightField.setText(p.getHeight()+ "");
    	nationField.setText(p.getNationality());
    	
    	modifyWindow.show();
    }
    
   public void initialize() {
	   BooleanBinding boolBind = nameField.textProperty().isEmpty().or(surnameField.textProperty().isEmpty())
			   .or(Genre.selectedToggleProperty().isNull()).or(birthDatePick.valueProperty().isNull()
					   .or(heightField.textProperty().isEmpty()).or(nationField.textProperty().isEmpty()));
	   
	   modifyBut.disableProperty().bind(boolBind);
   }

}
