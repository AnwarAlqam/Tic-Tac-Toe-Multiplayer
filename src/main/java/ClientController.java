import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ClientController implements Initializable {
	
	@FXML
	private Label nameholderLabel;
	
	@FXML
	private ChoiceBox<String> gamemodeChoiceBox;
	
	@FXML
	private Spinner<Integer> playerAmountSpinner;
	
	@FXML
	private TextField ipAddressTextField;
	
	@FXML
	private TextField portTextField;

	static int board[][];
	Image playerImage;
	static Client clientGame;
	static GridPane gameGridPane = new GridPane();
	
	private static Image xImage = new Image("images/X.png");
	private static Image silverGearImage = new Image("images/silvergear.png");
	private static Image goldenPlusImage = new Image("images/goldenplus.png");
	private static Image cosmicTriangleImage = new Image("images/cosmictriangle.png");
	private static String pImage = "X";
	
	int playerNumber;
	
	// 1v1 gamemode variables
	private boolean OneOnOneMovesLeft = true;
	private int OneOnOneScore = -1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Inserting all available gamemodes to the ChoiceBox
		String gamemodes[] = {"1 Versus 1 ONLINE", "Team Versus Team ONLINE", "Free For All ONLINE"};
		gamemodeChoiceBox.getItems().addAll(gamemodes);
		
		// Setting the spinner range
		SpinnerValueFactory<Integer> playerAmountFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10);
		playerAmountFactory.setValue(2);
		playerAmountSpinner.setValueFactory(playerAmountFactory);
		int playerNumber = 300;
	}
	
	public void initiateClient() {
		// Replace the bottom with clientGame = new Client(ipAddressTextField.ipAddressTextField.getText();, Integer.parseInt(portTextField.getText()), board, playerImage);
		clientGame = new Client("127.0.0.1", 5555, board, playerImage);
		clientGame.start();
	}
	
	public void setAvatarY(Image x) {
		System.out.println("Avatar changed successfully.");
		playerImage = new Image("images/X.png");
		this.pImage = "x";
		
	}

	public void setAvatarB(Image x) {
		System.out.println("Avatar changed successfully.");
		playerImage = new Image("images/silvergear.png");
		this.pImage = "silvergear";
	}

	public void setAvatarG(Image x) {
		System.out.println("Avatar changed successfully.");
		playerImage = new Image("images/goldenplus.png");
		this.pImage = "goldenplus";
	}

	public void setAvatarP(Image x) {
		System.out.println("Avatar changed successfully.");
		playerImage = new Image("images/cosmictriangle.png");
		this.pImage = "cosmictriangle";
	}
	
	public void setUsername(String username) {
		nameholderLabel.setText(username);
	}
	
	public void setGamemode(String gamemode) {
		gamemodeChoiceBox.setValue(gamemode);
	}
	
	public void setIP(String IP) {
		ipAddressTextField.setText(IP);
	}
	
	public void setPort(String PORT) {
		portTextField.setText(PORT);
	}
	
	public void selectAvatarMethod(ActionEvent e) throws IOException {
		try {
			// Switch scenes
			// Loading the FXML file.
			FXMLLoader selectAvatarLoader = new FXMLLoader(getClass().getResource("/FXML/avatarSelector.fxml"));
			Parent selectAvatarRoot = selectAvatarLoader.load();
			Stage selectAvatarStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

			avatarSelectorController AvatarSelectorController = selectAvatarLoader.getController();
			AvatarSelectorController.setUsername(nameholderLabel.getText());
			AvatarSelectorController.setPrevScene("online");
			AvatarSelectorController.setGamemode(gamemodeChoiceBox.getValue());
			AvatarSelectorController.setIP(ipAddressTextField.getText());
			AvatarSelectorController.setPort(portTextField.getText());

			// Creating the scene with the loaded FXML file.
			Scene selectAvatarScene = new Scene(selectAvatarRoot);

			// Setting the scene
			selectAvatarStage.setScene(selectAvatarScene);
			selectAvatarStage.show();

		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(1);
		}
	}
	
	public Scene createScene(int amountOfPlayers, GridPane GameGridPane, String gamemode) {
		
		// Setting the width and the height for the scene.
		int sceneWidth = 400 + (amountOfPlayers * 75);
		int sceneHeight = 250 + (amountOfPlayers * 75);
		
		// Creating core components for the scene.
		StackPane gamePane = new StackPane();
		HBox contentBox = new HBox();
		
		// Menubar creation starts here -----------------------------------------------------------
		// Creating the menubar and their menus
		MenuBar menuBar = new MenuBar();
		
		Menu optionMenu = new Menu("Options");
		optionMenu.setMnemonicParsing(false);
		
		Menu helpMenu = new Menu("Help");
		helpMenu.setMnemonicParsing(false);
		
		MenuItem settingsMenuItem = new MenuItem("Settings");
		settingsMenuItem.setMnemonicParsing(false);
		settingsMenuItem.setOnAction(e -> {
			System.out.println("Pressed the Settings button.");
		});
		
		// Using a lambda expression to give the exit menuitem an action event.
		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setMnemonicParsing(false);
		exitMenuItem.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
		});
		
		SeparatorMenuItem seperator = new SeparatorMenuItem();
		seperator.setMnemonicParsing(false);
		
		optionMenu.getItems().add(settingsMenuItem);
		optionMenu.getItems().add(seperator);
		optionMenu.getItems().add(exitMenuItem);
		
		MenuItem aboutMenuItem = new MenuItem("About");
		aboutMenuItem.setMnemonicParsing(false);
		aboutMenuItem.setOnAction(e -> {
			System.out.println("Pressend the About button.");
		});
		
		helpMenu.getItems().add(aboutMenuItem);
		
		menuBar.getMenus().add(optionMenu);
		menuBar.getMenus().add(helpMenu);
		
		contentBox.getChildren().add(menuBar);
		contentBox.setAlignment(Pos.TOP_LEFT);
		// Menubar creation end here -----------------------------------------------------------
		
		// GameGrid creation starts here -------------------------------------------------------
		// Calculating the amount of rows and columns for the game.
		int rows = amountOfPlayers + 1;
		int columns = amountOfPlayers + 1;
		
		EventHandler<ActionEvent> buttonHandler;
		buttonHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Button gameButton = new Button();
				gameButton = (Button)e.getSource();
				
				gameButton.setDisable(true);
				//gameButton.setText("WOOOWWW");
				
				Image X = playerImage;
				ImageView xView = new ImageView(X);
				gameButton.setGraphic(xView);
				
				int row = 0;
				int column = 0;
				ObservableList<Node> GridChildren = GameGridPane.getChildren();
					
				// Updating the board with the player picked position.
				for (Node b : GridChildren) {
					if (b == gameButton) {
						row = GameGridPane.getRowIndex(b);
						column = GameGridPane.getColumnIndex(b);

						board[row][column] = clientGame.getPlayerNum();
					}
				}
				
				if (gamemode == "OneOnOne") {
					String whoWon = "NoOne";
			        if(OneOnOneScore == -10) {
			        	whoWon = nameholderLabel.getText() + " won!";
			        	finishGame(GameGridPane, whoWon);
			        } else if(OneOnOneScore == 10) {
			        	whoWon = "AI won!";
			        	finishGame(GameGridPane, whoWon);
			        } else if (OneOnOneMovesLeft == false && OneOnOneScore == -10) {
			        	whoWon = nameholderLabel.getText() + " won!";
			        	finishGame(GameGridPane, whoWon);
			        } else if (OneOnOneMovesLeft == false && OneOnOneScore == 10) {
			        	whoWon = "AI won!";
			        	finishGame(GameGridPane, whoWon);
			        } else if (OneOnOneMovesLeft == false && OneOnOneScore != -10 & OneOnOneScore != 10){
			        	whoWon = "The game ended on a tie!";
			        	finishGame(GameGridPane, whoWon);
			        } else {
			        	//clientGame.send(board, row, column, playerImage, whoWon);
			        	clientGame.send(board, rows, columns, whoWon, row, column, pImage);
			        }
						
				}
				
			}
			
		};
		
		
		
		// Creating and inserting the buttons in the GridPane.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				double buttonWidth = 75;
				double buttonHeight = 75;
				
				Button gameButton = new Button();
				gameButton.setOnAction(buttonHandler);
				gameButton.setMinHeight(buttonHeight);
				gameButton.setMinWidth(buttonWidth);
				
				gameGridPane.add(gameButton, j, i);
			}
		}
		
		// Adding the grid to the pane.
		contentBox.getChildren().add(gameGridPane);
		
		// Adjusting the position and enabling gridlines.
		gameGridPane.setAlignment(Pos.BOTTOM_CENTER);
		gameGridPane.setPadding(new Insets(0.0, 0.0, 30.0, 45.0));
		gameGridPane.setGridLinesVisible(true);
		
		gamePane.getChildren().add(contentBox);
		// GameGrid creation ends here ---------------------------------------------------------

		
		return new Scene(gamePane, sceneWidth, sceneHeight);
	}
	
	public void searchGameMethod(ActionEvent e) throws IOException {
		
		// Checking what gamemode did the user select.
		if (gamemodeChoiceBox.getValue() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Gamemode Warning");
			alert.setHeaderText("Please select a gamemode!");
			alert.showAndWait();
		} else if (ipAddressTextField.getText() == "" || portTextField.getText() == "") {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("IP Warning");
			alert.setHeaderText("Please enter a(n) IP/Port!");
			alert.showAndWait();
		} else if (gamemodeChoiceBox.getValue() == "1 Versus 1 ONLINE") {
			
			// Checking if the correct amount of players have been inputed.
			if (playerAmountSpinner.getValue() > 2) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Player amount Warning");
				alert.setHeaderText("Please set only two players!");
				alert.showAndWait();
			} else {
				//GridPane gameGridPane = new GridPane();

				
				Stage gameLauncherStage = (Stage)((Node)e.getSource()).getScene().getWindow();
				Scene OneOnOneScene = createScene(playerAmountSpinner.getValue(), gameGridPane, "OneOnOne");
				gameLauncherStage.setScene(OneOnOneScene);
				
				int rows = playerAmountSpinner.getValue() + 1;
				int columns = playerAmountSpinner.getValue() + 1;
				
				board = new int[rows][columns];
				
				// Initializing board
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < columns; j++) {
						board[i][j] = 0;
					}
				}
			}
			
			initiateClient();
//			String test = "";
//			int r = -1;
//			int c = -1;
//			clientGame.send(board, r, c, test);
			
			
			//System.out.println("1 Versus 1 ONLINE not implemented yet.");
		} else if (gamemodeChoiceBox.getValue() == "Team Versus Team ONLINE") {
			System.out.println("Team Versus Team ONLINE not implemented yet.");
		} else if (gamemodeChoiceBox.getValue() == "Free For All ONLINE") {
			System.out.println("Free For All ONLINE not implemented yet.");
		}
	}
	
	public void disableEntireBoard(GridPane gameGridpane) {
		ObservableList<Node> GridChildren = gameGridpane.getChildren();
		
		for (Node b : GridChildren) {
			if (gameGridpane.getRowIndex(b) != null && gameGridpane.getColumnIndex(b) != null) {
				Button gameButton = (Button) b;
				gameButton.setDisable(true);
			}
		}
	}
	
	public boolean isMoveLeft(int rows, int columns) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (board[i][j] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void printBoard(int rows, int columns) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public void finishGame(GridPane gameGridPane, String whoWon) {
    	disableEntireBoard(gameGridPane);
		// Pausing the game
		PauseTransition pauseGame = new PauseTransition(Duration.seconds(2));
		pauseGame.setOnFinished(event ->  {
			try {
				// Switch scenes
				// Loading the FXML file.
				FXMLLoader winningLoader = new FXMLLoader(getClass().getResource("/FXML/winning.fxml"));
				Parent winningRoot = winningLoader.load();
				Stage winningStage = (Stage)gameGridPane.getScene().getWindow();
				//Stage winningStage = new Stage();
				
				winningController WinningController = winningLoader.getController();
				WinningController.setUsername(nameholderLabel.getText());
				WinningController.setPlayerWonID(whoWon);
				
				// Creating the scene with the loaded FXML file.
				Scene winningScene = new Scene(winningRoot);
				
				// Setting the scene
				winningStage.setScene(winningScene);
				winningStage.show();

		    } catch(Exception e1) {
		    	e1.printStackTrace();
		        System.exit(1);
		    }
		});	
		pauseGame.play();
	}
	
	public void hostGameMethod(ActionEvent e) throws IOException {
      
        try {
        	FXMLLoader serverLoader = new FXMLLoader(getClass().getResource("/FXML/server.fxml"));
        	Parent serverRoot = serverLoader.load();
        	
        	Stage serverStage = new Stage();
        	Scene serverScene = new Scene(serverRoot);
        	
        	serverStage.setScene(serverScene);
        	serverStage.show();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        
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
