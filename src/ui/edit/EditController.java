package ui.edit;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditController {
	
	public EditController() {
		
	}
	
	@FXML
	Stage editWindow;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField surnameField;

    @FXML
    private JFXButton acceptBut;

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
    private ImageView imgView;

    @FXML
    void acceptAct(ActionEvent event) {
    	editWindow.close();
    }
    
    public void editWindow() {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editPane.fxml"));
    	fxmlLoader.setController(this);
    	Pane editPane = null;
		try {
			editPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Scene scene = new Scene(editPane);
    	
    	editWindow = new Stage();
    	editWindow.setScene(scene);
    	editWindow.setResizable(false);
    	editWindow.initModality(Modality.APPLICATION_MODAL);
    	editWindow.initStyle(StageStyle.UNDECORATED);
    	
    	editWindow.show();
    }


}
