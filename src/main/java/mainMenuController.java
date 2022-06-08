import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class mainMenuController implements Initializable {
	
	@FXML
	private Button playButton;
	
	@FXML
	private Button storeButton;
	
	@FXML
	private Button logoutButton;
	
	@FXML
	private Label nameholderLabel;
	
	private MenuBar menuBar = new MenuBar();
	private Menu menuOne = new Menu("Options");
	private Menu menuTwo = new Menu("Help");
	
	private MenuItem about = new MenuItem("About");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setUsername(String username) {
		nameholderLabel.setText(username);
	}
	
	public void logoutMethod(ActionEvent e) throws IOException {
		try {
			// Switch scenes
			// Loading the FXML file.
			Parent loginRoot = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
			Stage loginStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			// Creating the scene with the loaded FXML file.
			Scene loginScene = new Scene(loginRoot);
			
			// Setting the scene
			loginStage.setScene(loginScene);
			loginStage.show();

	        } catch(Exception e1) {
	            e1.printStackTrace();
	            System.exit(1);
	        }
	}
	
	public void playOnlineMethod(ActionEvent e) throws IOException {
		try {
			// Switch scenes
			// Loading the FXML file.
			FXMLLoader clientLauncherLoader = new FXMLLoader(getClass().getResource("/FXML/clientLauncher.fxml"));
			Parent clientLauncherRoot = clientLauncherLoader.load();
			Stage clientLauncherStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			ClientController clientLauncherController = clientLauncherLoader.getController();
			clientLauncherController.setUsername(nameholderLabel.getText());
			
			// Creating the scene with the loaded FXML file.
			Scene clientLauncherScene = new Scene(clientLauncherRoot);
			
			// Setting the scene
			clientLauncherStage.setScene(clientLauncherScene);
			clientLauncherStage.show();

	        } catch(Exception e1) {
	            e1.printStackTrace();
	            System.exit(1);
	        }
	}
	
	public void playOfflineMethod(ActionEvent e) throws IOException {
		try {
			// Switch scenes
			// Loading the FXML file.
			FXMLLoader gameLauncherOfflineLoader = new FXMLLoader(getClass().getResource("/FXML/gameLauncherOffline.fxml"));
			Parent gameLauncherOfflineRoot = gameLauncherOfflineLoader.load();
			Stage gameLauncherOfflineStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			gameLauncherOfflineController GameLauncherOfflineController = gameLauncherOfflineLoader.getController();
			GameLauncherOfflineController.setUsername(nameholderLabel.getText());
			
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
	
	public void storeMethod(ActionEvent e) throws IOException {
		try {
			// Switch scenes
			// Loading the FXML file.
			FXMLLoader storeMenuLoader = new FXMLLoader(getClass().getResource("/FXML/store.fxml"));
			Parent storeMenuRoot = storeMenuLoader.load();
			Stage storeMenuStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			//System.out.println("name: " + nameholderLabel.getText());
			storeController StoreController = storeMenuLoader.getController();
			StoreController.setUsername(nameholderLabel.getText());
			
			// Creating the scene with the loaded FXML file.
			Scene storeMenuScene = new Scene(storeMenuRoot);
			
			// Setting the scene
			storeMenuStage.setScene(storeMenuScene);
			storeMenuStage.show();

	        } catch(Exception e1) {
	            e1.printStackTrace();
	            System.exit(1);
	        }
	}
	
	// Menubar Methods Start Here --------------------------------
	public void settingsMethod() throws IOException {
	    try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/settings.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root1));  
	        stage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
        System.out.println("Pressed the Settings button.");
	}
	
	public void aboutMethod(ActionEvent event) throws IOException {
	    try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/about.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root1));  
	        stage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
        System.out.println("Pressed the About button.");
	}
	
	public void exitMethod() throws IOException {
		Platform.exit();
	    System.exit(0);
	}
	// Menubar Methods End Here -----------------------------------

}
