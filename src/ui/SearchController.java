package ui;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;

public class SearchController {
	

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXButton editButton;
    
    public void initialize() {
        JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
        autoCompletePopup.getSuggestions().addAll("Sebastian", "Bjuan", "...");

        autoCompletePopup.setSelectionHandler(event -> {
            searchField.setText(event.getObject());

        });

        
        searchField.textProperty().addListener(observable -> {
            autoCompletePopup.filter(string -> string.toLowerCase().contains(searchField.getText().toLowerCase()));
            if (autoCompletePopup.getFilteredSuggestions().isEmpty() || searchField.getText().isEmpty()) {
                autoCompletePopup.hide();
            } else {
                autoCompletePopup.show(searchField);
            }
        });
    }

}
