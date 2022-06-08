import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class Server {

	int clientAmount = 1;
	int amountOfPlayer = 0;
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	ServerThread server;
	int port = 0;
	int board[][];
	
	Object lock = new Object();
	
	private Consumer<Serializable> connections;
	private Consumer<Serializable> results;
	private Consumer<Serializable> AmountPlayers;
	
	Server(Consumer<Serializable> conn, Consumer<Serializable> res, Consumer<Serializable> ap, int p){
		
		connections = conn;
		results = res;
		server = new ServerThread();
		this.port = p;
		server.start();
		this.AmountPlayers = ap;
	}
	
	public class ServerThread extends Thread {
		
		Socket connection;
		ObjectInputStream in;
		ObjectOutputStream out;
		
		public void run() {
			
//			try {
//				// USE THE FOLLOWING VARIABLES TO CONTACT A SPECIFIC CLIENT
//				in = new ObjectInputStream(connection.getInputStream());
//				out = new ObjectOutputStream(connection.getOutputStream());
//				connection.setTcpNoDelay(true);
//				
//			} catch (Exception e) {
//				System.out.println("Streams not open");
//			}
//			
//			GameInfo gameData = new GameInfo(board, -1, -1, "-1", -1);
//			
//			for (int i = 0; i < clients.size(); i++) {
//				gameData.setPlayerNum(i);
//				clients.get(i).sendToOne(i, gameData);
//			}
			
			try (ServerSocket serverSocket = new ServerSocket(port);) {
				connections.accept("Waiting for players...");
				
				while (true) {
					ClientThread player = new ClientThread(serverSocket.accept(), clientAmount);
					connections.accept("Player number " + clientAmount + " has connected to the server");
					
					synchronized (lock) {
						clients.add(player);	
					}
					
					player.start();
					amountOfPlayer++;
					AmountPlayers.accept(String.valueOf(amountOfPlayer));
					
					clientAmount++;
				}
				
			} catch (IOException e) {
				connections.accept("Server did not launch");
			}
			
		}
		
	}
	
	class ClientThread extends Thread {
		
		Socket connection;
		int playerNum;
		ObjectInputStream in;
		ObjectOutputStream out;
		
		ClientThread(Socket s, int num) {
			this.connection = s;
			this.playerNum = num;
		}
		
		public void run() {
			
			try {
				// USE THE FOLLOWING VARIABLES TO CONTACT A SPECIFIC CLIENT
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);
				
			} catch (Exception e) {
				System.out.println("Streams not open");
			}
			
			while (true) {
				try {
					
					// Reading object
					GameInfo gameData = (GameInfo) in.readObject();
					// Retrieving sent information.
					board = gameData.getGameBoard();
					printBoard(board);
					
					//Image playerImage = gameData.getPlayerImage();
					String whoWon = gameData.getWhoWon();
					
//					// Send info to all clients
//					for (ClientThread Client : clients) {
//						Client.sendInfo(gameData);
//					}
					
					GameInfo gameDataUpdated = new GameInfo(gameData.getGameBoard(), gameData.getRow(), gameData.getCol(), gameData.getWhoWon(), gameData.getPlayerNum(), gameData.getRowChanged(), gameData.getColChanged(), gameData.getImage());
					
					
					for (int i = 0; i < clients.size(); i++) {
						gameDataUpdated.setPlayerNum(i + 1);
						sendToOne(i, gameDataUpdated);
					}
					
					
				} catch (Exception e) {
					connections.accept("Client " + playerNum + " has disconnected from the server");
					amountOfPlayer -= 1;
					AmountPlayers.accept(String.valueOf(amountOfPlayer));
					clients.remove(this);
					break;
				}
			}
			
		}
		
		public int checkWin() {
			// Checking horizontal wins
			if (board[0][0] == board[0][1] && board[0][0] == board[0][2]) {
				return board[0][0];
			} else if (board[1][0] == board[1][1] && board[1][0] == board[1][2]) {
				return board[1][0];
			} else if (board[2][0] == board[2][1] && board[2][0] == board[2][2]) {
				return board[2][0];
			}
			
			// Checking vertical wins
			else if (board[0][0] == board[1][0] && board[0][0] == board[2][0]) {
				return board[0][0];
			} else if(board[0][1] == board[1][1] && board[0][1] == board[2][1]) {
				return board[0][1];
			} else if (board[0][2] == board[1][2] && board[0][2] == board[2][2]) {
				return board[0][2];
			}
			
			// Checking diagonal wins
			else if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
				return  board[0][0];
			} else if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
				return board[0][2];
			}
			
			return -1;
		}
		
		public void write(Object obj) {
            try{
                out.writeObject(obj);
            }
            catch(IOException e){ e.printStackTrace(); }
		}
		
	    public void sendToOne(int index, Object message)throws IndexOutOfBoundsException {
	    	clients.get(index).write(message);
	    }
		
		public void sendInfo(GameInfo gameData) {
			try {
				out.writeObject(gameData);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void printBoard(int board[][]) {
			System.out.println("Server Printing server board");
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(board[i][j] + " ");
				}
				System.out.println("");
			}
			System.out.println("");
		}
		
		
	}

}
