package ui.edit;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
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
import model.Database;
import model.Person;
import ui.notifications.Notification;

public class EditController {
	
	private List<Person> listOfPersons;
	private Person person;
	private Database db;
	private int idx;
	private int size;
	
	
	public EditController(Database db, List<Person> persons) {
		this.db = db;
		this.listOfPersons = persons;
		idx = 0;
		size = persons.size();
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
    private JFXButton nextBut;

    @FXML
    void nextAct(ActionEvent event) {
    	idx++;
    	person = listOfPersons.get(idx);
    	actualizeWindow();

    }

    @FXML
    void delAct(ActionEvent event) {
    	db.deletePerson(person);
    	editWindow.close();
    	new Notification("Succesfull", "Person deleted successfully", Notification.SUCCESS).show();

    }

    @FXML
    void editAct(ActionEvent event) {
    	ModifyController modify = new ModifyController(this.db, this.person);
    	modify.modifyWindow();
    	editWindow.close();

    }

    @FXML
    void finishAct(ActionEvent event) {
    	editWindow.close();

    }
    
    public void actualizeWindow() {
    	codeLbl.setText(person.getCode()+ "");
    	nameLbl.setText(person.getName());
    	surnameLbl.setText(person.getSurname());
    	genreLbl.setText(person.getGender().toString());
    	birthLbl.setText(person.getBirthdate());
    	heightLbl.setText(person.getHeight()+ "");
    	nationLbl.setText(person.getNationality());
    	
    	if(idx>=size-1) {
    		nextBut.setDisable(true);
    	}
    	else {
    		nextBut.setDisable(false);
    	}
    	
    	
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
    	
    	
    	person = listOfPersons.get(idx);
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
    	
    	if(idx>=size-1) {
    		nextBut.setDisable(true);
    	}
    	
    
    	
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
