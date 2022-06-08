import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class GameInfo implements Serializable{

	private int gameBoard[][];
	private String whoWon;
	private String image;
	private int row;
	private int col;
	private int playerNum;
	private int rowChanged;
	private int colChanged;
	
	GameInfo(int GameBoard[][], int r, int c, String won, int n, int rChanged, int cChanged, String img) {
		this.gameBoard = GameBoard;
		//this.playerImage = pImage;
		this.whoWon = won;
		this.row = r;
		this.col = c;
		this.playerNum = n;
		this.rowChanged = rChanged;
		this.colChanged = cChanged;
		this.image = img;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public int getRowChanged() {
		return this.rowChanged;
	}
	
	public int getColChanged() {
		return this.colChanged;
	}
	
	public int[][] getGameBoard(){
		return this.gameBoard;
	}
	
//	public Image getPlayerImage() {
//		return this.playerImage;
//	}
	
	public int getPlayerNum() {
		return this.playerNum;
	}
	
	public void setPlayerNum(int num) {
		this.playerNum = num;
	}
	
	public String getWhoWon() {
		return this.whoWon;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void setBoard(int[][] newBoard) {
		this.gameBoard = newBoard;
	}
	
}