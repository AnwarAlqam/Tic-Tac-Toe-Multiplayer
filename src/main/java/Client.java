import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Client extends Thread {
	
	// Default ip : 127.0.0.1
	// Default port: 5555

	private String ipAddress;
	private int port;
	
	private int gameBoard[][];
	Image playerImage;
	private int playerNum;
	
	Client(String IP_Address,
		   int Port,
		   int GameBoard[][],
		   Image PlayerImage) {
		this.ipAddress = IP_Address;
		this.port = Port;
		this.gameBoard = GameBoard;
		this.playerImage = PlayerImage;
		this.playerNum = 1;
	}
	
	Socket socketClient;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	
	
	// Login screen
	// Database
	// All AI gamemode working
	// All multiplayer gamemode working
	
	
	// Show 2 AI gamemodes
	// Atleast 1 multiplayer gamemode 
	// Store 
	
	
	
	public void run() {
		try {
			socketClient = new Socket(ipAddress, port);
		    out = new ObjectOutputStream(socketClient.getOutputStream());
		    in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
		} catch (Exception e) {}
		
		while (true) {
			try {
				GameInfo gameData = (GameInfo) in.readObject();
				this.playerNum = gameData.getPlayerNum();
				
				
				if (this.playerNum == 1) {
					this.playerNum = 2;
				} else {
					this.playerNum = 1;
				}
				
				System.out.println("playerNum: " + this.playerNum);
				
				gameBoard = gameData.getGameBoard();
				ClientController.board = gameData.getGameBoard();

				ObservableList<Node> GridChildren = ClientController.gameGridPane.getChildren();
				for (Node b : GridChildren) {
					if ((ClientController.gameGridPane.getRowIndex(b) != null && ClientController.gameGridPane.getColumnIndex(b) != null) &&(gameData.getRowChanged() == ClientController.gameGridPane.getRowIndex(b) && gameData.getColChanged() == ClientController.gameGridPane.getColumnIndex(b))) {
						Button gameButton = (Button) b;
						
						System.out.println("pImage: " + gameData.getImage());
						
						Platform.runLater(new Runnable(){

							@Override
							public void run() {
								if (gameData.getImage().equals("x")) {
									System.out.println("triggered1");
									
									Image xImage = new Image("images/X.png");
									ImageView xView = new ImageView(xImage);
									gameButton.setGraphic(xView);
								} else if (gameData.getImage().equals("silvergear")) {
									System.out.println("triggered2");
									
									Image silverImage = new Image("images/silvergear.png");
									ImageView silverView = new ImageView(silverImage);
									gameButton.setGraphic(silverView);
								} else if (gameData.getImage().equals("goldenplus")) {
									System.out.println("triggered3");
									
									Image goldenImage = new Image("images/goldenplus.png");
									ImageView goldenView = new ImageView(goldenImage);
									gameButton.setGraphic(goldenView);
								} else if (gameData.getImage().equals("cosmictriangle")) {
									System.out.println("triggered4");
									
									Image cosmicImage = new Image("images/cosmictriangle.png");
									ImageView cosmicView = new ImageView(cosmicImage);
									gameButton.setGraphic(cosmicView);
								}
								
							}
							// do your GUI stuff here
							});
						
						gameButton.setDisable(true);
					}
				}
				
				int winner = checkWin();
				boolean empty = false;
				
				if (winner == 1) {
					String whoWon = "Player 2 won!";
					finishGame(ClientController.gameGridPane, whoWon);
				} else if (winner == 2){
					String whoWon = "Player 1 won!";
					finishGame(ClientController.gameGridPane, whoWon);
				}
				//else if (winner == -1 &&   check board is all disabled){
					
				//}

				
				printBoard(gameBoard);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
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
				WinningController.setUsername(whoWon);
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
	
	public int checkWin() {
		// Checking horizontal wins
		if (gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][0] == gameBoard[0][2]) {
			return gameBoard[0][0];
		} else if (gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][0] == gameBoard[1][2]) {
			return gameBoard[1][0];
		} else if (gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][0] == gameBoard[2][2]) {
			return gameBoard[2][0];
		}
		
		// Checking vertical wins
		else if (gameBoard[0][0] == gameBoard[1][0] && gameBoard[0][0] == gameBoard[2][0]) {
			return gameBoard[0][0];
		} else if(gameBoard[0][1] == gameBoard[1][1] && gameBoard[0][1] == gameBoard[2][1]) {
			return gameBoard[0][1];
		} else if (gameBoard[0][2] == gameBoard[1][2] && gameBoard[0][2] == gameBoard[2][2]) {
			return gameBoard[0][2];
		}
		
		// Checking diagonal wins
		else if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2]) {
			return  gameBoard[0][0];
		} else if (gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0]) {
			return gameBoard[0][2];
		}
		
		return -1;
	}
	
	public void send(int board[][], int row, int col, String whoWon, int rowChanged, int colChanged, String image) {
		
		try {
			GameInfo info = new GameInfo(gameBoard, row, col, whoWon, playerNum, rowChanged, colChanged, image);
			out.writeObject(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getPlayerNum() {
		return this.playerNum;
	}
	
	//public updateGridPane()
	
	public void printBoard(int board[][]) {
		System.out.println("Client Printing client board");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
