package ui;

import java.util.List;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Database;
import model.Person;
import ui.edit.EditController;
import ui.notifications.Notification;

public class SearchController {
	Database db;
	
	public SearchController(Database db) {
		this.db = db;
	}
	

    @FXML
    private JFXTextField searchField;

    
    
    @FXML
    private JFXTextField surNameField;
    
    @FXML
    private JFXButton editNameButton;
    
    @FXML
    private Label numOfNameSugg;
    
    @FXML
    private Label numOfSurnameSugg;

    @FXML
    private JFXTextField searchCodeField;

    @FXML
    private JFXButton editSurnameBut;
    
    @FXML
    private JFXButton editCodeBut;

    @FXML
    void editCodeAct(ActionEvent event) {
    	try {
    		List<Person> p = db.searchPersonByCode(Long.parseLong(searchCodeField.getText()));
        	EditController edit = new EditController(this.db, p);
        	searchCodeField.setText("");
        	edit.editWindow();
    		
    	}
    	catch(NullPointerException e) {
    		new Notification("Error!", "This user doesn't exists", Notification.ERROR).show();
    	}
    	catch (NumberFormatException e) {
    		new Notification("Error: Wrong format", "The code is numerical", Notification.ERROR).show();
		}

    }

    @FXML
    void editNameAct(ActionEvent event) {
    	try {
    		List<Person> p = db.searchPersonByName(searchField.getText());
        	EditController edit = new EditController(this.db, p);
        	searchField.setText("");
        	edit.editWindow();
    		
    	}
    	catch(NullPointerException e) {
    		new Notification("Error!", "This user doesn't exists", Notification.ERROR).show();
    	}
    	
    }

    @FXML
    void editSurnamaAct(ActionEvent event) {
    	try {
    		List<Person> p = db.searchPersonBySurname(surNameField.getText());
        	EditController edit = new EditController(this.db, p);
        	surNameField.setText("");
        	edit.editWindow();

    	}
    	catch(NullPointerException e) {
    		new Notification("Error!", "This user doesn't exists", Notification.ERROR).show();
    	}
    	
    }

    
    
    
    public void initialize() {
    	
    	BooleanBinding editNameBool = searchField.textProperty().isEmpty();
    	BooleanBinding editSurnameBool = surNameField.textProperty().isEmpty();
    	BooleanBinding editCodeBool = searchCodeField.textProperty().isEmpty();
    	
    	editNameButton.disableProperty().bind(editNameBool);
    	editSurnameBut.disableProperty().bind(editSurnameBool);
    	editCodeBut.disableProperty().bind(editCodeBool);
    	
        JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
        JFXAutoCompletePopup<String> autoCompleteSurname = new JFXAutoCompletePopup<>();

        autoCompletePopup.setSelectionHandler(event -> {
            searchField.setText(event.getObject());
           
        });
        
        autoCompleteSurname.setSelectionHandler(event -> {
        	surNameField.setText(event.getObject());
        });
        
        surNameField.textProperty().addListener(observable -> {


        	if ( surNameField.getText().isEmpty()) {
        		autoCompleteSurname.hide();
        		numOfSurnameSugg.setText("0");
        	} else {
        		try {
        			autoCompleteSurname.getSuggestions().clear();
        			autoCompleteSurname.getSuggestions().addAll(db.getSurnameSuggestions(surNameField.getText()));
        			numOfSurnameSugg.setText(db.getSurnameSuggestions(surNameField.getText()).size() + "");
            		if(db.getSurnameSuggestions(surNameField.getText()).size()<=100) {
            			autoCompleteSurname.show(surNameField);
            		}
            		else {
            			autoCompleteSurname.hide();
            		}
        		}
        		catch(NullPointerException e) {
        			autoCompleteSurname.hide();
        			numOfSurnameSugg.setText("0");
        		}
        	}
        });

        
        searchField.textProperty().addListener(observable -> {
        	
            
            if ( searchField.getText().isEmpty()) {
                autoCompletePopup.hide();
                numOfNameSugg.setText("0");
            } else {
            	try {
            		autoCompletePopup.getSuggestions().clear();
            		autoCompletePopup.getSuggestions().addAll(db.getNameSuggestions(searchField.getText()));
            		numOfNameSugg.setText(db.getNameSuggestions(searchField.getText()).size() + "");
            		if(db.getNameSuggestions(searchField.getText()).size()<=100) {
            			autoCompletePopup.show(searchField);
            		}
            		else {
            			autoCompletePopup.hide();
            		}
            		
            	}
            	catch(NullPointerException e) {
            		autoCompletePopup.hide();
            		numOfNameSugg.setText("0");
            	}
            }
        });
    }

}
