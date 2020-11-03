package ui.edit;

import java.io.IOException;

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
import javafx.stage.StageStyle;

public class ModifyController {
	public ModifyController() {
		// TODO Auto-generated constructor stub
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
    	
    	modifyWindow.show();
    }
    
   public void initialize() {
	   BooleanBinding boolBind = nameField.textProperty().isEmpty().or(surnameField.textProperty().isEmpty())
			   .or(Genre.selectedToggleProperty().isNull()).or(birthDatePick.valueProperty().isNull()
					   .or(heightField.textProperty().isEmpty()).or(nationField.textProperty().isEmpty()));
	   
	   modifyBut.disableProperty().bind(boolBind);
   }

}
