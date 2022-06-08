import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class winningController implements Initializable {
	@FXML
	private Label playerWonID;
	private String username;
	
	public void setPlayerWonID(String ID) {
		playerWonID.setText(ID);
	}
	
	public void setUsername(String user) {
		username = user;
	}
	
	public Scene getWinningScene() {
		return playerWonID.getScene();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void continueMethod(ActionEvent e) throws IOException {
		
		try {
			// Switch scenes
			// Loading the FXML file.
			FXMLLoader gameLauncherOfflineLoader = new FXMLLoader(getClass().getResource("/FXML/gameLauncherOffline.fxml"));
			Parent gameLauncherOfflineRoot = gameLauncherOfflineLoader.load();
			Stage gameLauncherOfflineStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			gameLauncherOfflineController GameLauncherOfflineController = gameLauncherOfflineLoader.getController();
			GameLauncherOfflineController.setUsername(username);
			
			// Creating the scene with the loaded FXML file.
			Scene gameLauncherOfflineScene = new Scene(gameLauncherOfflineRoot);
			
			// Setting the scene
			gameLauncherOfflineStage.setScene(gameLauncherOfflineScene);
			gameLauncherOfflineStage.show();

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
