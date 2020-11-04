package ui.edit;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;

public class EditController {
	
	private Person person;
	
	
	
	public EditController(Person person) {
		this.person = person;
	}
	
	@FXML
	Stage editWindow;

    @FXML
    private JFXButton finishBut;

    @FXML
    private ImageView imgView;
    
    @FXML
    private Label codeLbl;

    @FXML
    private Label nameLbl;

    @FXML
    private Label surnameLbl;

    @FXML
    private Label genreLbl;

    @FXML
    private Label birthLbl;

    @FXML
    private Label heightLbl;

    @FXML
    private Label nationLbl;

    @FXML
    private JFXButton editBut;

    @FXML
    private JFXButton delBut;

    @FXML
    void delAct(ActionEvent event) {

    }

    @FXML
    void editAct(ActionEvent event) {
    	ModifyController modify = new ModifyController(this.person);
    	modify.modifyWindow();

    }

    @FXML
    void finishAct(ActionEvent event) {
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
    	
    	codeLbl.setText(person.getCode()+ "");
    	nameLbl.setText(person.getName());
    	surnameLbl.setText(person.getSurname());
    	genreLbl.setText(person.getGender().toString());
    	birthLbl.setText(person.getBirthdate());
    	heightLbl.setText(person.getHeight()+ "");
    	nationLbl.setText(person.getNationality());
    	
    
    	
    	editWindow.show();
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						try {
							Image img = person.getImage();
							imgView.setImage(img);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				
			}
		}).start();
    }
    
    


}
