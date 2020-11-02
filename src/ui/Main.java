package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Database;

public class Main extends Application{

	PrincipalController principalController;
	Database dataBase;

	public Main() throws IOException {
		dataBase = new Database();
		principalController = new PrincipalController(dataBase);
		
	}

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPane.fxml"));

		fxmlLoader.setController(principalController);

		Parent root = fxmlLoader.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("People database");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("file:assets/icon.png"));

		primaryStage.show();
		principalController.whenInitializing();
		

	}
}
