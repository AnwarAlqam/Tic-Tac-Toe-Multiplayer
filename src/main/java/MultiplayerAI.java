import java.util.ArrayList;

public class MultiplayerAI
{
	static class Move
	{
	    int row, col;
	};
	 
// This function requires that the number representing the player be given to it
// The player count must start at 1 up to the max of 10
// The highest number player value will always be stored in position 1 of scores array
// For example Player 2 in a 2 player game will be stored at position 1 while player 1 will be at position 2
	
	 
	// Checks if there are any moves left on the board
	static Boolean isMovesLeft(int board[][], int boardSize)
	{
	    for (int i = 0; i < boardSize; i++)
	        for (int j = 0; j < boardSize; j++)
	            if (board[i][j] == 0)
	                return true;
	    return false;
	}
	 
	// Assigns a value of 10 for Max win
	// Assigns a value of -10 for Min wins
	// Will return 0 for ties or while the game is ongoing
	static int evaluate(int b[][], int boardSize, int numPlayers, int playerNum)
	{
	    // Checking for Rows for X or O victory.
		ArrayList<Integer> scores = new ArrayList<Integer>(numPlayers + 1);
		
		// Initialize the score list
		for(int i = 0; i < numPlayers + 1; i++)
		{
			scores.add(i,0);
		}
		
		// Check rows
	    for (int row = 0; row < boardSize; row++)
	    {
	    	for(int i = 0; i < boardSize; i++)
	    	{
	    		// Make sure it is 3 in a row
	    		if(scores.get(b[row][i]) == 2)
	    		{
	    			if(b[row][i - 1] == b[row][i] && b[row][i - 2] == b[row][i])
	    			{
	    		    	scores.add(b[row][i],scores.get(b[row][i])+1);
	    			}
	    			else
	    			{
	    				// Not 3 in a row, reset count
	    				scores.add(b[row][i],1);
	    			}
	    		}
	    		else
	    		{
			    	scores.add(b[row][i],scores.get(b[row][i])+1);
	    		}
	    		// Next row, reset counter
	    		for(int j = 0; j < numPlayers + 1; j++)
	    		{
	    			scores.add(j,0);
	    		}
	    	}
	    	
	    	// Who scored?
	        for(int i = 1; i <= numPlayers; i++)
	        {
	        	if(scores.get(i) == 3)
	        	{
	        		if(i == (playerNum % numPlayers) + 1)
	        		{
	        			return +100;
	        		}
	        		else if(i == 0)
	        		{
	        			return -10;
	        		}
	        		else if(i == 1)
	        		{
	        			return -20;
	        		}
	        		else if(i == 2)
	        		{
	        			return -30;
	        		}
	        		else if(i == 3)
	        		{
	        			return -40;
	        		}
	        		else if(i == 4)
	        		{
	        			return -50;
	        		}
	        		else if(i == 5)
	        		{
	        			return -60;
	        		}
	        		else if(i == 6)
	        		{
	        			return -70;
	        		}
	        		else if(i == 7)
	        		{
	        			return -80;
	        		}
	        		else if(i == 8)
	        		{
	        			return -90;
	        		}
	        		else if(i == 9)
	        		{
	        			return -100;
	        		}
	        	}
	        }
	    }
	    
	    // Clear the array
	    scores.clear();
	    // Reset the array
		for(int i = 0; i < numPlayers + 1; i++)
		{
			scores.add(i,0);
		}
	 
	    // Check columns
	    for (int col = 0; col < boardSize; col++)
	    {
	    	for(int i = 0; i < boardSize; i++)
	    	{
	    		if(scores.get(b[i][col]) == 2)
	    		{
	    			// Check for 3 in a row
	    			if(b[i - 1][col] == b[i][col] && b[i - 2][col] == b[i][col])
	    			{
	    		    	scores.add(b[i][col],scores.get(b[i][col])+1);
	    			}
	    			else
	    			{
	    				// Not 3 in a row, reset
	    				scores.add(b[i][col],1);
	    			}
	    		}
	    		else
	    		{
			    	scores.add(b[i][col],scores.get(b[i][col])+1);
	    		}
	    	}
	    	// New col, reset counter
			for(int j = 0; j < numPlayers + 1; j++)
			{
				scores.add(j,0);
			}
	    }
	    	// Who scored?
	        for(int i = 1; i <= numPlayers; i++)
	        {
	        	if(scores.get(i) == 3)
	        	{
	        		if(i == (playerNum % numPlayers) + 1)
	        		{
	        			return +100;
	        		}
	        		else if(i == 0)
	        		{
	        			return -10;
	        		}
	        		else if(i == 1)
	        		{
	        			return -20;
	        		}
	        		else if(i == 2)
	        		{
	        			return -30;
	        		}
	        		else if(i == 3)
	        		{
	        			return -40;
	        		}
	        		else if(i == 4)
	        		{
	        			return -50;
	        		}
	        		else if(i == 5)
	        		{
	        			return -60;
	        		}
	        		else if(i == 6)
	        		{
	        			return -70;
	        		}
	        		else if(i == 7)
	        		{
	        			return -80;
	        		}
	        		else if(i == 8)
	        		{
	        			return -90;
	        		}
	        		else if(i == 9)
	        		{
	        			return -100;
	        		}
	        	}
	        }
	    
	    
	    // Clear the array
	    scores.clear();
	    // Reset the array
		for(int i = 0; i < numPlayers + 1; i++)
		{
			scores.add(i,0);
		}
		
		// TESTING>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	    
		// Try to find diagonal, needs work
	    for(int row = 0; row < boardSize - 1; row++)
	    {
	    	for(int col = 0; col < boardSize - 1; col++)
	    	{
	            int startRow = row - row % 3, startCol = col - col % 3;
	            for (int i = 0; i < 3; i++)
	            {
	            	for (int j = 0; j < 3; j++)
	            	{
	              		if((i == 0 && j == 0) || (i == 0 && j == 2))
	            		{
	            			scores.add(b[i + startRow][j + startCol],scores.get(b[i + startRow][j + startCol])+1);
	            		}
	            		else if(i == 1 && j == 1)
	            		{
	            			scores.add(b[i + startRow][j + startCol],scores.get(b[i + startRow][j + startCol])+1);
	            		}
	            		else if((i == 2 && j == 2) || ((i == 2 && j == 0)))
	            		{
	            			// Check if 3 in a row
	            			if(scores.get(b[i + startRow][j + startCol]) == 2)
	            			{
	            				if((i == 2 && j == 0))
	            				{
	            					if(b[i - 2 + startRow][j + 2 + startCol] == b[i + startRow][j + startCol])
	            					{
	        	            			scores.add(b[i + startRow][j + startCol],scores.get(b[i + startRow][j + startCol])+1);
	            					}
	            				}
	            				else if(i == 2 && j == 2)
	            				{
	            					if(b[i - 2 + startRow][j - 2 + startCol] == b[i + startRow][j + startCol])
	            					{
	        	            			scores.add(b[i + startRow][j + startCol],scores.get(b[i + startRow][j + startCol])+1);
	            					}
	            				}
	            				else
	            				{
	            					scores.add(b[i + startRow][j + startCol],1);
	            				}
	            			}
	            		}
	     
	            	}
	            }
	            // New 3x3 cell check
	            if(col % 3 == 0)
	            {
	        		for(int j = 0; j < numPlayers + 1; j++)
	        		{
	        			scores.add(j,0);
	        		}
	            }
	            	
	    	}
	    	// New 3x3 cell check
	    	if(row % 3 == 0)
	    	{
	    		for(int j = 0; j < numPlayers + 1; j++)
	    		{
	    			scores.add(j,0);
	    		}
	    	}
	    }
	    
    	// Who scored?
	      for(int i = 1; i <= numPlayers; i++)
	        {
	        	if(scores.get(i) == 3)
	        	{
	        		if(i == (playerNum % numPlayers) + 1)
	        		{
	        			return +100;
	        		}
	        		else if(i == 0)
	        		{
	        			return -10;
	        		}
	        		else if(i == 1)
	        		{
	        			return -20;
	        		}
	        		else if(i == 2)
	        		{
	        			return -30;
	        		}
	        		else if(i == 3)
	        		{
	        			return -40;
	        		}
	        		else if(i == 4)
	        		{
	        			return -50;
	        		}
	        		else if(i == 5)
	        		{
	        			return -60;
	        		}
	        		else if(i == 6)
	        		{
	        			return -70;
	        		}
	        		else if(i == 7)
	        		{
	        			return -80;
	        		}
	        		else if(i == 8)
	        		{
	        			return -90;
	        		}
	        		else if(i == 9)
	        		{
	        			return -100;
	        		}
	        	}
	        }
	 
	 
	    // Else if none of them have won then return 0
	    return 0;
	}
	 
	// Minmax recursively builds a tree of all possible moves from a given state
	// It will expand all of the moves to the end of the game
	// From here it will choose the best path
	static ArrayList<Integer> minimax(int board[][], int depth, Boolean isMax, int boardSize, int numPlayers, int playerNum)
	{
	    int score = evaluate(board, boardSize, numPlayers, playerNum);
	    ArrayList<Integer> bestList = new ArrayList<Integer>(numPlayers + 1);
	    
		for(int i = 0; i < numPlayers + 1; i++)
		{
			bestList.add(i,0);
		}
	
	 
	    // If Max has won the game
	    // return evaluated score
	    if (score == 100)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score - depth);
	    	return bestList;
	    }
	 
	    // If Min has won the game
	    // return evaluated score
	    if (score == -10)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score + depth);
	    	return bestList;
	    }
	    if (score == -20)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score + depth);
	    	return bestList;
	    }
	    if (score == -30)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score + depth);
	    	return bestList;
	    }
	    if (score == -40)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score + depth);
	    	return bestList;
	    }
	    if (score == -50)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score + depth);
	    	return bestList;
	    }
	    if (score == -60)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score + depth);
	    	return bestList;
	    }
	    if (score == -70)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score + depth);
	    	return bestList;
	    }
	    if (score == -80)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score + depth);
	    	return bestList;
	    }
	    if (score == -90)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score + depth);
	    	return bestList;
	    }
	    if (score == -100)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,score + depth);
	    	return bestList;
	    }
	  
	    // If there are no more moves and
	    // no winner then it is a tie
	    if (isMovesLeft(board,boardSize) == false)
	    {
	    	bestList.add((playerNum % numPlayers) + 1,0);
	    	return bestList;
	    }
	    
	 
	    
 
	    // If this max move
	    if (isMax)
	    {
	        int best = -1000;
	 
	        // Traverse all cells
	        for (int i = 0; i < boardSize; i++)
	        {
	            for (int j = 0; j < boardSize; j++)
	            {
	                // Check if cell is empty
	                if (board[i][j]== 0)
	                {
	                    // Make the move

		                    board[i][j] = (playerNum % numPlayers) + 1;
		               	 
		                    // Call minimax recursively and choose
		                    // the maximum value
		                    best = Math.max(best, minimax(board, depth + 1, !isMax, boardSize, numPlayers, playerNum + 1).get((playerNum % numPlayers) + 1));
		                    
		                    // Store the value in the array
		                    bestList.add((playerNum % numPlayers) + 1,best);
		 
		                    // Undo the move
		                    board[i][j] = 0;
	                	
	                }
	            }
	        }
	        return bestList;
	    }
	    // If this min move
	    else
	    {
	         int best = 1000;
	 
	        // Traverse all cells
	        for (int i = 0; i < boardSize; i++)
	        {
	            for (int j = 0; j < boardSize; j++)
	            {
	                // Check if cell is empty
	                if (board[i][j] == 0)
	                {
	                    // Make the move
		    
		                board[i][j] = (playerNum % numPlayers) + 1;
		            
	 
	                    // Call minimax recursively and choose
	                     //the minimum value
		                if(playerNum % numPlayers != 0)
		                {
		                	best = Math.min(best, minimax(board, depth + 1, false, boardSize, numPlayers, playerNum + 1).get((playerNum % numPlayers) + 1));
		                }
		                else if(playerNum % numPlayers == 0)
		                {
		                	best = Math.min(best, minimax(board, depth + 1, true, boardSize, numPlayers, playerNum + 1).get((playerNum % numPlayers) + 1));
		                }
	                    
	                    
	                    bestList.add((playerNum % numPlayers) + 1,best);
	 
	                    // Undo the move
	                    board[i][j] = 0;
	                }
	            }
	        }
	        return bestList;
	    }
	    
	}
	 
	// This will return the best possible
	// move for the player
	
	// CONVERT TO MAXN OR ALPHA BETA
	static Move findBestMove(int board[][], int boardSize, int numPlayers, int playerNum)
	{
	    int bestVal = -1000;
	    Move bestMove = new Move();
	    bestMove.row = -1;
	    bestMove.col = -1;
	    
	    ArrayList<Integer> moveVal = new ArrayList<Integer>(numPlayers + 1);
	 
	    // Traverse all cells, evaluate minimax function
	    // for all empty cells. And return the cell
	    // with optimal value.
	    for (int i = 0; i < boardSize; i++)
	    {
	        for (int j = 0; j < boardSize; j++)
	        {
	            // Check if cell is empty
	            if (board[i][j] == 0)
	            {
	                // Make the move
	                board[i][j] = playerNum;
	 
	                // compute evaluation function for this
	                // move.
	                moveVal = minimax(board, 0, true, boardSize, numPlayers, playerNum - 1);
	 
	                // Undo the move
	                board[i][j] = 0;
	 
	                // If the value of the current move is
	                // more than the best value, then update
	                // best/
	                // The values are stored starting at position 1 to numPlayers
	                if (moveVal.get(playerNum) > bestVal)
	                {
	                	// Prevent illegal moves
	                	if(board[i][j] == 0)
	                	{
		                    bestMove.row = i;
		                    bestMove.col = j;
		                    bestVal = moveVal.get(playerNum);
	                	}
	                }
	            }
	        }
	    }
	 
	    System.out.printf("The value of the best Move " +
	                             "is : %d\n\n", bestVal);
	 
	    return bestMove;
	}
	
}
