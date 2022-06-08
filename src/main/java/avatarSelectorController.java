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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class avatarSelectorController implements Initializable {
	
	@FXML
	private Label nameholderLabel;
	private Image X = new Image("images/X.png");
	private boolean avatarB;
	private boolean avatarY;
	private boolean avatarG;
	private boolean avatarP;
	
	private String prevScene;

	private Label myLabel;
	private ChoiceBox<String> choice;
	private String[] avatarName = {"yellow", "green", "blue"};
	private String gamemode;
	private String ip;
	private String port;
	
	
	private static avatarSelectorController instance;
	public static avatarSelectorController getInstance() {
	    // if(instance == null) instantiate();
	    return instance;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	//	choice.getItems().addAll(avatarName);
	}
	
	public void setUsername(String username) {
		nameholderLabel.setText(username);
	}
	
	public void setPrevScene(String scene) {
		prevScene = scene;
	}
	
	public void setGamemode(String mode) {
		gamemode = mode;
	}
	
	public void setIP(String IP) {
		ip = IP;
	}
	
	public void setPort(String PORT) {
		port = PORT;
	}

	public void backMethod(ActionEvent e) throws IOException {
		try {

			if (prevScene == "online") {
				System.out.println("Here");
				FXMLLoader clientLauncherLoader = new FXMLLoader(getClass().getResource("/FXML/clientLauncher.fxml"));
				Parent clientLauncherRoot = clientLauncherLoader.load();
				Stage clientLauncherStage = (Stage)((Node)e.getSource()).getScene().getWindow();
				
				ClientController clientLauncherController = clientLauncherLoader.getController();
				clientLauncherController.setUsername(nameholderLabel.getText());
				clientLauncherController.setGamemode(gamemode);
				clientLauncherController.setIP(ip);
				clientLauncherController.setPort(port);
				
				if (avatarY == true) {
					clientLauncherController.setAvatarY(X);
				}
				if (avatarB == true) {
					clientLauncherController.setAvatarB(X);
				}
				if (avatarG == true) {
					clientLauncherController.setAvatarG(X);
				}
				if (avatarP == true) {
					clientLauncherController.setAvatarP(X);
				}
				
				// Creating the scene with the loaded FXML file.
				Scene clientLauncherScene = new Scene(clientLauncherRoot);
				
				// Setting the scene
				clientLauncherStage.setScene(clientLauncherScene);
				clientLauncherStage.show();
			} else {
				FXMLLoader gameLauncherOfflineLoader = new FXMLLoader(getClass().getResource("/FXML/gameLauncherOffline.fxml"));
				Parent gameLauncherOfflineRoot = gameLauncherOfflineLoader.load();
				Stage gameLauncherOfflineStage = (Stage)((Node)e.getSource()).getScene().getWindow();
				
				gameLauncherOfflineController GameLauncherOfflineController = gameLauncherOfflineLoader.getController();
				GameLauncherOfflineController.setUsername(nameholderLabel.getText());
				GameLauncherOfflineController.setGamemode(gamemode);
		
				
				
				if (avatarY == true) {
					GameLauncherOfflineController.setAvatarY(X);
				}
				if (avatarB == true) {
					GameLauncherOfflineController.setAvatarB(X);
				}
				if (avatarG == true) {
					GameLauncherOfflineController.setAvatarG(X);
				}
				if (avatarP == true) {
					GameLauncherOfflineController.setAvatarP(X);
				}
				// Creating the scene with the loaded FXML file.
				Scene gameLauncherOfflineScene = new Scene(gameLauncherOfflineRoot);
				
				// Setting the scene
				gameLauncherOfflineStage.setScene(gameLauncherOfflineScene);
				gameLauncherOfflineStage.show();
			}
			
		} catch(Exception e1) {
			e1.printStackTrace();
	        System.exit(1);
	    }
	}
	
	
	public boolean getAvatarY() {
		return avatarY;
	}
	public boolean getAvatarB() {
		return avatarB;
	}
	public boolean getAvatarG() {
		return avatarG;
	}
	public boolean getAvatarP() {
		return avatarP;
	}
	
	public void setAvatarY() {
	    System.out.println("Pressed the Avatar button.");
	    avatarY = true;
	}
	public void setAvatarB() {
	    System.out.println("Pressed the Avatar button.");
	    avatarB = true;
	}
	public void setAvatarG() {
	    System.out.println("Pressed the Avatar button.");
	    avatarG = true;
	}
	public void setAvatarP() {
	    System.out.println("Pressed the Avatar button.");
	    avatarP = true;
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
