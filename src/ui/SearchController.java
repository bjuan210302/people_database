package ui;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import model.Database;

public class SearchController {
	Database db;
	
	public SearchController(Database db) {
		this.db = db;
	}
	

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXButton editButton;
    
    @FXML
    private JFXTextField surNameField;
    
    public void initialize() {
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
        		//            	System.out.println(db.getNameSuggestions(searchField.getText()));
        		try {
        			autoCompleteSurname.getSuggestions().clear();
        			autoCompleteSurname.getSuggestions().addAll(db.getSurnameSuggestions(surNameField.getText()));
        			autoCompleteSurname.show(surNameField);
        		}
        		catch(NullPointerException e) {
        			autoCompleteSurname.hide();
        			System.out.println("error");
        		}
        	}
        });

        
        searchField.textProperty().addListener(observable -> {
        	
            
            if ( searchField.getText().isEmpty()) {
                autoCompletePopup.hide();
            } else {
//            	System.out.println(db.getNameSuggestions(searchField.getText()));
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
