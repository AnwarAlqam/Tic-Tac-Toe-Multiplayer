import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

public class gameLauncherOfflineController implements Initializable {

	@FXML
	private Pane gameLauncherPane;

	@FXML
	private Label nameholderLabel;

	@FXML
	private ChoiceBox<String> gamemodeChoiceBox;

	@FXML
	private Spinner<Integer> playerAmountSpinner;

	private int board[][];
	Image playerImage;

	Image silvergear = new Image("images/silvergear.png");
	Image cosmictriangle = new Image("images/cosmictriangle.png");
	Image heart = new Image("images/heart.png");
	Image O = new Image("images/O.png");
	Image goldenplus = new Image("images/goldenplus.png");
	Image trapezoid = new Image("images/trapezoid.png");
	Image square = new Image("images/square.png");
	Image triangle = new Image("images/triangle.png");
	Image threepointstar = new Image("images/threepointstar.png");
	private Image imageArray[] = { silvergear, playerImage, cosmictriangle, heart, O, goldenplus, trapezoid, square,
			triangle, threepointstar };

	// 1v1 gamemode variables
	private boolean OneOnOneMovesLeft = true;
	private int OneOnOneScore = -1;

	// FreeForAll variable
	private boolean FreeForAllMovesLeft = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Inserting all available gamemodes to the ChoiceBox
		String gamemodes[] = { "1 Versus 1 OFFLINE", "Team Versus Team OFFLINE", "Free For All OFFLINE" };
		gamemodeChoiceBox.getItems().addAll(gamemodes);

		// Setting the spinner range
		SpinnerValueFactory<Integer> playerAmountFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10);
		playerAmountFactory.setValue(2);
		playerAmountSpinner.setValueFactory(playerAmountFactory);

		playerImage = new Image("images/X.png");
	}

	public void setAvatarY(Image x) {
		System.out.println("Avatar changed successfully.");
		playerImage = new Image("images/X.png");
	}

	public void setAvatarB(Image x) {
		System.out.println("Avatar changed successfully.");
		playerImage = new Image("images/silvergear.png");
	}

	public void setAvatarG(Image x) {
		System.out.println("Avatar changed successfully.");
		playerImage = new Image("images/goldenplus.png");
	}

	public void setAvatarP(Image x) {
		System.out.println("Avatar changed successfully.");
		playerImage = new Image("images/cosmictriangle.png");
	}

	public void setUsername(String username) {
		nameholderLabel.setText(username);
	}
	
	public void setGamemode(String gamemode) {
		gamemodeChoiceBox.setValue(gamemode);
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
			AvatarSelectorController.setGamemode(gamemodeChoiceBox.getValue());

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

	public Scene createScene(int amountOfPlayers, GridPane gameGridPane, String gamemode) {
		// Setting the width and the height for the scene.
		int sceneWidth = 400 + (amountOfPlayers * 75);
		int sceneHeight = 250 + (amountOfPlayers * 75);

		// Creating core components for the scene.
		StackPane gamePane = new StackPane();
		HBox contentBox = new HBox();

		// Menubar creation starts here
		// -----------------------------------------------------------
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
		// Menubar creation end here
		// -----------------------------------------------------------

		// GameGrid creation starts here
		// -------------------------------------------------------
		// Calculating the amount of rows and columns for the game.
		int rows = amountOfPlayers + 1;
		int columns = amountOfPlayers + 1;
		EventHandler<ActionEvent> buttonHandler;
		buttonHandler = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Button gameButton = new Button();
				gameButton = (Button) e.getSource();

				gameButton.setDisable(true);

				Image X = playerImage;
				ImageView xView = new ImageView(X);
				gameButton.setGraphic(xView);

				try {
					int row = 0;
					int column = 0;
					ObservableList<Node> GridChildren = gameGridPane.getChildren();

					// Updating the board with the player picked position.
					for (Node b : GridChildren) {
						if (b == gameButton) {
							row = gameGridPane.getRowIndex(b);
							column = gameGridPane.getColumnIndex(b);

							board[row][column] = 2;
						}
					}
					if (gamemode == "OneOnOne") {
						OneOnOneScore = MinMaxTwoPlayerAI.evaluate(board);

						if (OneOnOneScore == -10) {
							String whoWon = nameholderLabel.getText() + " won!";
							finishGame(gameGridPane, whoWon);
						} else if (OneOnOneScore == 10) {
							String whoWon = "AI won!";
							finishGame(gameGridPane, whoWon);
						} else if (OneOnOneMovesLeft == false && OneOnOneScore == -10) {
							String whoWon = nameholderLabel.getText() + " won!";
							finishGame(gameGridPane, whoWon);
						} else if (OneOnOneMovesLeft == false && OneOnOneScore == 10) {
							String whoWon = "AI won!";
							finishGame(gameGridPane, whoWon);
						} else if (OneOnOneMovesLeft == false && OneOnOneScore != -10 & OneOnOneScore != 10) {
							String whoWon = "The game ended on a tie!";
							finishGame(gameGridPane, whoWon);
						} else {
							OneOnOneAIOfflineMethod(gameGridPane, rows, columns);
						}

					} else if (gamemode == "FreeForAll") {
						FreeForAllOfflineMethod(gameGridPane, rows, columns, amountOfPlayers);
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		// GameGrid creation ends here
		// ---------------------------------------------------------

		return new Scene(gamePane, sceneWidth, sceneHeight);
	}

	public void startGameMethod(ActionEvent e) throws IOException {

		// Checking what gamemode did the user select.
		if (gamemodeChoiceBox.getValue() == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Gamemode Warning");
			alert.setHeaderText("Please select a gamemode!");
			alert.showAndWait();
		} else if (gamemodeChoiceBox.getValue() == "1 Versus 1 OFFLINE") {

			// Checking if the correct amount of players have been inputed.
			if (playerAmountSpinner.getValue() > 2) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Player amount Warning");
				alert.setHeaderText("Please set only two players!");
				alert.showAndWait();
			} else {
				GridPane gameGridPane = new GridPane();

				Stage gameLauncherStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
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

		} else if (gamemodeChoiceBox.getValue() == "Team Versus Team OFFLINE") {
			System.out.println("Team Versus Team OFFLINE not implemented yet.");
		} else if (gamemodeChoiceBox.getValue() == "Free For All OFFLINE") {
			GridPane gameGridPane = new GridPane();

			Stage gameLauncherStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			Scene FreeForAllScene = createScene(playerAmountSpinner.getValue(), gameGridPane, "FreeForAll");
			gameLauncherStage.setScene(FreeForAllScene);

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
	}

	public void backMethod(ActionEvent e) throws IOException {
		try {
			// Switch scenes
			// Loading the FXML file.
			FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/FXML/mainMenu.fxml"));
			Parent mainMenuRoot = mainMenuLoader.load();
			Stage mainMenuStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

			mainMenuController MainMenuController = mainMenuLoader.getController();
			MainMenuController.setUsername(nameholderLabel.getText());

			// Creating the scene with the loaded FXML file.
			Scene mainMenuScene = new Scene(mainMenuRoot);

			// Setting the scene
			mainMenuStage.setScene(mainMenuScene);
			mainMenuStage.show();

		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(1);
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

	public void FreeForAllOfflineMethod(GridPane gameGridPane, int rows, int columns, int amountOfPlayers) {
		FreeForAllMovesLeft = isMoveLeft(rows, columns);
		ArrayList<Integer> FreeForAllScore = new ArrayList<Integer>();
		
		// We have to check if the player has won before entering the AI loop.
		FreeForAllScore = MultiplayerAI_V2.evaluate(board, rows, amountOfPlayers, 2);
		
		/**************************
		 * Made revisions to this function along with the AI
		 * Mostly operational
		 * Current Issue:
		 * 
		 * The way the winner is checked for just picks the first one from the loop
		 * Which is player 1
		 * So, even if player 2 makes the winning move, player one gets the win
		 * 
		 * Checking here outside the loop if player 2 is the winner seems to cause major issues
		 * I do not know the solution
		 */
	
		if (FreeForAllScore.get(2) == 10) {
			String whoWon = "Player " + 2 + " won!";
			finishGame(gameGridPane, whoWon);
			return;
		} else if (FreeForAllMovesLeft == false) {
			String whoWon = "The game ended on a tie!";
			finishGame(gameGridPane, whoWon);
			return;
		}
		
		for (int i = 1; i < amountOfPlayers + 1; i++)
		{
			//FreeForAllScore = MultiplayerAI_V2.evaluate(board, rows, amountOfPlayers, i);
			
			
			if (FreeForAllMovesLeft == true) 
			{
				if (i != 2) 
				{
					MultiplayerAI_V2.Move BestMove = MultiplayerAI_V2.findBestMove(board, rows, amountOfPlayers, i);
					board[BestMove.row][BestMove.col] = i;
				

					int row = 0;
					int column = 0;
					ObservableList<Node> GridChildren = gameGridPane.getChildren();

					// Updating the board with the player picked position.
					for (Node b : GridChildren) 
					{

						// Reason to why I am checking if the getRowIndex is null is because gridlines
						// are a child node that do not have a column/row.
						if (gameGridPane.getRowIndex(b) != null && gameGridPane.getRowIndex(b) == BestMove.row
								&& gameGridPane.getColumnIndex(b) != null
								&& gameGridPane.getColumnIndex(b) == BestMove.col) 
						{

							Button gameButton = (Button) b;
							gameButton.setDisable(true);

							ImageView aiView = new ImageView(imageArray[i - 1]);
							gameButton.setGraphic(aiView);
						}
					}
				}
				
				FreeForAllScore = MultiplayerAI_V2.evaluate(board, rows, amountOfPlayers, i);
				
				
				if (FreeForAllScore.get(i) == 10) {
					String whoWon = "Player " + i + " won!";
					finishGame(gameGridPane, whoWon);
				} else if (FreeForAllMovesLeft == false) {
					String whoWon = "The game ended on a tie!";
					finishGame(gameGridPane, whoWon);
				}
				
				/******************************************************************
				 * Printing this won't work, it only holds values internally for the AI to make decisions
				 * It will always return 0 unless there is a winner when called like this
				 * Currently the main function of find best move will show you the calculated value for each call with a print to console
				 */
				
				//for (int j = 0; j < FreeForAllScore.size(); j++) {
				//	System.out.println(j + ": " + FreeForAllScore.get(j));
				//}
				//System.out.println("");
				

				 
			}


			//System.out.println("State of board:");
			//printBoard(rows, columns);

		}
		

		
		// Moved outside loop to avoid double printing 
		
		System.out.println("State of board:");
		printBoard(rows, columns);
	}

	public void finishGame(GridPane gameGridPane, String whoWon) {
		disableEntireBoard(gameGridPane);
		// Pausing the game
		PauseTransition pauseGame = new PauseTransition(Duration.seconds(2));
		pauseGame.setOnFinished(event -> {
			try {
				// Switch scenes
				// Loading the FXML file.
				FXMLLoader winningLoader = new FXMLLoader(getClass().getResource("/FXML/winning.fxml"));
				Parent winningRoot = winningLoader.load();
				Stage winningStage = (Stage) gameGridPane.getScene().getWindow();
				// Stage winningStage = new Stage();

				winningController WinningController = winningLoader.getController();
				WinningController.setUsername(nameholderLabel.getText());
				WinningController.setPlayerWonID(whoWon);

				// Creating the scene with the loaded FXML file.
				Scene winningScene = new Scene(winningRoot);

				// Setting the scene
				winningStage.setScene(winningScene);
				winningStage.show();

			} catch (Exception e1) {
				e1.printStackTrace();
				System.exit(1);
			}
		});
		pauseGame.play();
	}

	public void OneOnOneAIOfflineMethod(GridPane gameGridPane, int rows, int columns) throws IOException {
		OneOnOneMovesLeft = isMoveLeft(rows, columns);
		OneOnOneScore = MinMaxTwoPlayerAI.evaluate(board);
		// Now it is AI turn
		if (OneOnOneMovesLeft == true) {
			MinMaxTwoPlayerAI.Move bestMove = MinMaxTwoPlayerAI.findBestMove(board);

			// Play the move
			board[bestMove.row][bestMove.col] = 1;

			int row = 0;
			int column = 0;
			ObservableList<Node> GridChildren = gameGridPane.getChildren();

			// Updating the board with the player picked position.
			for (Node b : GridChildren) {

				// Reason to why I am checking if the getRowIndex is null is because gridlines
				// are a child node that do not have a column/row.
				if (gameGridPane.getRowIndex(b) != null && gameGridPane.getRowIndex(b) == bestMove.row
						&& gameGridPane.getColumnIndex(b) != null && gameGridPane.getColumnIndex(b) == bestMove.col) {

					Button gameButton = (Button) b;
					gameButton.setDisable(true);

					ImageView oView = new ImageView(O);
					gameButton.setGraphic(oView);
				}
			}

			// OneOnOneMovesLeft = MinMaxTwoPlayerAI.isMovesLeft(board);
			OneOnOneScore = MinMaxTwoPlayerAI.evaluate(board);
		}
		// Show winner
		if (OneOnOneScore == -10) {
			String whoWon = nameholderLabel.getText() + " won!";
			finishGame(gameGridPane, whoWon);
		} else if (OneOnOneScore == 10) {
			String whoWon = "AI won!";
			finishGame(gameGridPane, whoWon);
		} else if (OneOnOneMovesLeft == false && OneOnOneScore == -10) {
			String whoWon = nameholderLabel.getText() + " won!";
			finishGame(gameGridPane, whoWon);
		} else if (OneOnOneMovesLeft == false && OneOnOneScore == 10) {
			String whoWon = "AI won!";
			finishGame(gameGridPane, whoWon);
		} else if (OneOnOneMovesLeft == false && OneOnOneScore != -10 & OneOnOneScore != 10) {
			String whoWon = "The game ended on a tie!";
			finishGame(gameGridPane, whoWon);
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
