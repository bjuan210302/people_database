package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class PrincipalController {
	
	private GenerateController genControl;
	
	private AddPersonController addControl;
	
	@FXML
    private MenuItem generateDataBut;
	

    @FXML
    private MenuItem addPersonBut;
	
    @FXML
    private Pane paneChange;
    
   @FXML
   private Pane firstPane;
   
   @FXML
   private Pane secondPane;

    @FXML
    public void generateDataButton(ActionEvent event) {
    	paneChange.getChildren().clear();
    	paneChange.getChildren().add(firstPane);

    }

    @FXML
    public void addPersonAct(ActionEvent event) {
    	
    	paneChange.getChildren().clear();
    	paneChange.getChildren().add(secondPane);

    }
	
	
	public void whenInitializing() {
		genControl = new GenerateController();
		addControl = new AddPersonController();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("generatePane.fxml"));
		fxmlLoader.setController(genControl);
		
		try {
		
			firstPane = fxmlLoader.load();
			
		} catch (IOException e) {
			
		}
		
		fxmlLoader = new FXMLLoader(getClass().getResource("addPersonPane.fxml"));
		fxmlLoader.setController(addControl);
		
		try {
		
			secondPane = fxmlLoader.load();
			
		} catch (IOException e) {
			
		}
		
	}

}
