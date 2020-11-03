package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import model.Database;

public class PrincipalController {
	
	private Database db;
	
	public PrincipalController(Database db) {
		this.db = db;
	}
	
	private GenerateController genControl;
	
	private AddPersonController addControl;
	
	private SearchController searchControl;
	
	@FXML
    private MenuItem generateDataBut;
	

    @FXML
    private MenuItem addPersonBut;
    
    @FXML
    private MenuItem searchBut;
	
    @FXML
    private Pane paneChange;
    
   @FXML
   private Pane firstPane;
   
   @FXML
   private Pane secondPane;
   
   @FXML
   private Pane thirdPane;

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
    
    @FXML
    void searchAct(ActionEvent event) {
    	paneChange.getChildren().clear();
    	paneChange.getChildren().add(thirdPane);

    }
	
	
	public void whenInitializing() {
		genControl = new GenerateController(this.db);
		addControl = new AddPersonController(this.db);
		searchControl = new SearchController(this.db);
		
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
		
		fxmlLoader = new FXMLLoader(getClass().getResource("searchPane.fxml"));
		fxmlLoader.setController(searchControl);
		
		try {
		
			thirdPane = fxmlLoader.load();
			
		} catch (IOException e) {
			
		}
		
	}

}
