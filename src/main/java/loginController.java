import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class loginController implements Initializable {
	
	@FXML
	private Pane welcomePane;
	
	@FXML
	private Button googleButton;
	
	@FXML
	private Button facebookButton;
	
	@FXML
	private Button appleButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        
	}
	
	public void guestMethod(ActionEvent e) throws IOException {
		try {
			Random randomNumberGenerator = new Random();
			int randomNumber = randomNumberGenerator.nextInt(9999);
			String username = "Guest-" + randomNumber;
			
			// Switch scenes
			// Loading the FXML file.
			FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/FXML/mainMenu.fxml"));
			Parent mainMenuRoot = mainMenuLoader.load();
			Stage mainMenuStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			
			mainMenuController MainMenuController = mainMenuLoader.getController();
			MainMenuController.setUsername(username);
			
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
	
	public void googleMethod(ActionEvent e) throws IOException {
        System.out.println("Pressed the Google button.");
	}
	
	public void facebookMethod(ActionEvent e) throws IOException {
        System.out.println("Pressed the Facebook button.");
	}
	
	public void appleMethod(ActionEvent e) throws IOException {
        System.out.println("Pressed the Apple button.");
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
