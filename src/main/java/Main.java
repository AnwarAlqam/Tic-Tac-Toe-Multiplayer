import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		 try {
			 
			 Menu fileMenu = new Menu("File");
			 fileMenu.getItems().add(new MenuItem("test"));
			 
			 MenuBar menuBar = new MenuBar();
			 menuBar.getMenus().addAll(fileMenu);
			 
			 
			 
			 
			 
	         // Read file fxml and draw interface.
	         Parent root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
	 
	         primaryStage.setTitle("Tic-Tac-Toe Multiplayer");
             Scene s1 = new Scene(root, 904,501);
             s1.getStylesheets().add("/styles/login.css");
	         
 			 primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			     @Override
		         public void handle(WindowEvent t) {
				     Platform.exit();
		             System.exit(0);
		         }
		     });
             
             primaryStage.setScene(s1);
	         primaryStage.show();
	         
	         } catch(Exception e) {
	             e.printStackTrace();
	             System.exit(1);
	         }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
