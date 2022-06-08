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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class prizeController implements Initializable {
	
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
			FXMLLoader storeLoader = new FXMLLoader(getClass().getResource("/FXML/store.fxml"));
			Parent storeRoot = storeLoader.load();
			Stage storeStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			storeController storeController = storeLoader.getController();
			storeController.setUsername(nameholderLabel.getText());
			
			// Creating the scene with the loaded FXML file.
			Scene storeScene = new Scene(storeRoot);
			
			// Setting the scene
			storeStage.setScene(storeScene);
			storeStage.show();

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
