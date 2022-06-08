import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServerController implements Initializable {
	
	@FXML
	private Button turnOffButton;
	
	@FXML
	private TextField portTextField;
	
    @FXML
    private ListView<String> connectionsListView;
    
    @FXML
    private ListView<String> resultsListView;
    
    @FXML
    private Label amountOfPlayersLabel;
	
	Server serverConnection;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void turnOnMethod() {
		int port = Integer.parseInt(portTextField.getText());
		
		serverConnection = new Server(data -> {
			Platform.runLater(() -> {
				connectionsListView.getItems().add(data.toString());
			});
		},data -> {
			Platform.runLater(() -> {
				resultsListView.getItems().add(data.toString());
			});
		}, data -> {
			Platform.runLater(() -> {
				amountOfPlayersLabel.setText((String) data);
			});
		}, port);
		
		
	}
	
	public void turnOffMethod(ActionEvent e) throws IOException {
	    Stage hostGameStage = (Stage) turnOffButton.getScene().getWindow();
	    hostGameStage.close();
	}

}
