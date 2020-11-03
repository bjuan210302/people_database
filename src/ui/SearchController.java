package ui;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Database;
import ui.edit.EditController;

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
    private JFXButton editSurnameBut;

    @FXML
    void editNameAct(ActionEvent event) {
    	EditController edit = new EditController();
    	edit.editWindow();
    }

    @FXML
    void editSurnamaAct(ActionEvent event) {
    	EditController edit = new EditController();
    	edit.editWindow();
    }

    
    
    
    public void initialize() {
    	
    	BooleanBinding editNameBool = searchField.textProperty().isEmpty();
    	BooleanBinding editSurnameBool = surNameField.textProperty().isEmpty();
    	
    	editNameButton.disableProperty().bind(editNameBool);
    	editSurnameBut.disableProperty().bind(editSurnameBool);
    	
        JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
        JFXAutoCompletePopup<String> autoCompleteSurname = new JFXAutoCompletePopup<>();
//        autoCompletePopup.getSuggestions().addAll(db.getNameSuggestions(event.getObject()));
//    	autoCompletePopup.filter(string -> string.toLowerCase().contains(searchField.getText().toLowerCase()));

        autoCompletePopup.setSelectionHandler(event -> {
            searchField.setText(event.getObject());
           
        });
        
        autoCompleteSurname.setSelectionHandler(event -> {
        	surNameField.setText(event.getObject());
        });
        
        surNameField.textProperty().addListener(observable -> {


        	if ( surNameField.getText().isEmpty()) {
        		autoCompleteSurname.hide();
        	} else {
        		try {
        			autoCompleteSurname.getSuggestions().clear();
        			autoCompleteSurname.getSuggestions().addAll(db.getSurnameSuggestions(surNameField.getText()));
        			autoCompleteSurname.show(surNameField);
        		}
        		catch(NullPointerException e) {
        			autoCompleteSurname.hide();
        		}
        	}
        });

        
        searchField.textProperty().addListener(observable -> {
        	
            
            if ( searchField.getText().isEmpty()) {
                autoCompletePopup.hide();
            } else {
            	try {
            		autoCompletePopup.getSuggestions().clear();
            		autoCompletePopup.getSuggestions().addAll(db.getNameSuggestions(searchField.getText()));
            		autoCompletePopup.show(searchField);
            	}
            	catch(NullPointerException e) {
            		autoCompletePopup.hide();
            	}
            }
        });
    }

}
