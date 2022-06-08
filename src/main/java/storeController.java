import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class storeController implements Initializable {
	
	@FXML
	private Label nameholderLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public void setUsername(String username) {
		nameholderLabel.setText(username);
	}
	
	public void backMethod(ActionEvent e) throws IOException {
		try {
			// Switch scenes
			// Loading the FXML file.
			FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/FXML/mainMenu.fxml"));
			Parent mainMenuRoot = mainMenuLoader.load();
			Stage mainMenuStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			mainMenuController MainMenuController = mainMenuLoader.getController();
			MainMenuController.setUsername(nameholderLabel.getText());
			
			// Creating the scene with the loaded FXML file.
			Scene mainMenuScene = new Scene(mainMenuRoot);
			
			// Setting the scene
			mainMenuStage.setScene(mainMenuScene);
			mainMenuStage.show();

	        } catch(Exception e1) {
	            e1.printStackTrace();
	            System.exit(1);
	        }
	}
	
	public void prizeMethod(ActionEvent e) throws IOException {
		//System.out.println("Pressed the Select Avatar Icon button.");
		try {
			// Switch scenes
			// Loading the FXML file.
			FXMLLoader prizeLoader = new FXMLLoader(getClass().getResource("/FXML/prize.fxml"));
			Parent prizeRoot = prizeLoader.load();
			Stage prizeStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			//System.out.println("name: " + nameholderLabel.getText());
			prizeController prizeController = prizeLoader.getController();
			prizeController.setUsername(nameholderLabel.getText());
			
			// Creating the scene with the loaded FXML file.
			Scene prizeScene = new Scene(prizeRoot);
			
			// Setting the scene
			prizeStage.setScene(prizeScene);
			prizeStage.show();

	        } catch(Exception e1) {
	            e1.printStackTrace();
	            System.exit(1);
	        }
	}
	
	public void prizeTwoMethod(ActionEvent e) throws IOException {
		//if (gamemodeChoiceBox.getValue() == null) {
		try {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Store Warning");
			alert.setHeaderText("Insufficient Funds!");
			alert.showAndWait();
		//}
		} catch(Exception e1) {
            e1.printStackTrace();
            System.exit(1);
        }
	}
	
	public void prizeThreeMethod(ActionEvent e) throws IOException {
		//if (gamemodeChoiceBox.getValue() == null) {
		try {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Store Warning");
			alert.setHeaderText("Insufficient Funds!");
			alert.showAndWait();
		//}
		} catch(Exception e1) {
            e1.printStackTrace();
            System.exit(1);
        }
	}
	
	// Menubar Methods Start Here --------------------------------
	public void settingsMethod() throws IOException {
        System.out.println("Pressed the Settings button.");
	}
	
	public void aboutMethod() throws IOException {
        System.out.println("Pressed the About button.");
	}
	
	public void exitMethod() throws IOException {
		Platform.exit();
	    System.exit(0);
	}
	// Menubar Methods End Here -----------------------------------
	
}
