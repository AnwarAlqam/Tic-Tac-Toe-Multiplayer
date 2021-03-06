public class MinMaxTwoPlayerAI 
{
	static class Move
	{
	    int row, col;
	};
	 
	// Player is the AI, Opponent is the human
	static int player = 1, opponent = 2;
	 
	// Checks if there are any moves left on the board
	static Boolean isMovesLeft(int board[][])
	{
	    for (int i = 0; i < 3; i++)
	        for (int j = 0; j < 3; j++)
	            if (board[i][j] == 0)
	                return true;
	    return false;
	}
	 
	// Assigns a value of 10 for Max win
	// Assigns a value of -10 for Min wins
	// Will return 0 for ties or while the game is ongoing
	static int evaluate(int b[][])
	{
	    // Checking for Rows for X or O victory.
	    for (int row = 0; row < 3; row++)
	    {
	        if (b[row][0] == b[row][1] &&
	            b[row][1] == b[row][2])
	        {
	            if (b[row][0] == player)
	                return +10;
	            else if (b[row][0] == opponent)
	                return -10;
	        }
	    }
	 
	    // Checking for Columns for X or O victory.
	    for (int col = 0; col < 3; col++)
	    {
	        if (b[0][col] == b[1][col] &&
	            b[1][col] == b[2][col])
	        {
	            if (b[0][col] == player)
	                return +10;
	 
	            else if (b[0][col] == opponent)
	                return -10;
	        }
	    }
	 
	    // Checking for Diagonals for X or O victory.
	    if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
	    {
	        if (b[0][0] == player)
	            return +10;
	        else if (b[0][0] == opponent)
	            return -10;
	    }
	 
	    if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
	    {
	        if (b[0][2] == player)
	            return +10;
	        else if (b[0][2] == opponent)
	            return -10;
	    }
	 
	    // Else if none of them have won then return 0
	    return 0;
	}
	 
	// Minmax recursively builds a tree of all possible moves from a given state
	// It will expand all of the moves to the end of the game
	// From here it will choose the best path
	static int minimax(int board[][],
	                    int depth, Boolean isMax)
	{
	    int score = evaluate(board);
	 
	    // If Max has won the game
	    // return evaluated score
	    if (score == 10)
	        return score - depth;
	 
	    // If Min has won the game
	    // return evaluated score
	    if (score == -10)
	        return score + depth;
	 
	    // If there are no more moves and
	    // no winner then it is a tie
	    if (isMovesLeft(board) == false)
	        return 0;
	 
	    // If this max move
	    if (isMax)
	    {
	        int best = -1000;
	 
	        // Traverse all cells
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++)
	            {
	                // Check if cell is empty
	                if (board[i][j]== 0)
	                {
	                    // Make the move
	                    board[i][j] = player;
	 
	                    // Call minimax recursively and choose
	                    // the maximum value
	                    best = Math.max(best, minimax(board,
	                                    depth + 1, !isMax));
	 
	                    // Undo the move
	                    board[i][j] = 0;
	                }
	            }
	        }
	        return best;
	    }
	 
	    // If this min move
	    else
	    {
	        int best = 1000;
	 
	        // Traverse all cells
	        for (int i = 0; i < 3; i++)
	        {
	            for (int j = 0; j < 3; j++)
	            {
	                // Check if cell is empty
	                if (board[i][j] == 0)
	                {
	                    // Make the move
	                    board[i][j] = opponent;
	 
	                    // Call minimax recursively and choose
	                    // the minimum value
	                    best = Math.min(best, minimax(board,
	                                    depth + 1, !isMax));
	 
	                    // Undo the move
	                    board[i][j] = 0;
	                }
	            }
	        }
	        return best;
	    }
	}
	 
	// This will return the best possible
	// move for the player
	static Move findBestMove(int board[][])
	{
	    int bestVal = -1000;
	    Move bestMove = new Move();
	    bestMove.row = -1;
	    bestMove.col = -1;
	 
	    // Traverse all cells, evaluate minimax function
	    // for all empty cells. And return the cell
	    // with optimal value.
	    for (int i = 0; i < 3; i++)
	    {
	        for (int j = 0; j < 3; j++)
	        {
	            // Check if cell is empty
	            if (board[i][j] == 0)
	            {
	                // Make the move
	                board[i][j] = player;
	 
	                // compute evaluation function for this
	                // move.
	                int moveVal = minimax(board, 0, true);
	 
	                // Undo the move
	                board[i][j] = 0;
	 
	                // If the value of the current move is
	                // more than the best value, then update
	                // best/
	                if (moveVal > bestVal)
	                {
	                	// Prevent illegal moves
	                	if(board[i][j] == 0)
	                	{
		                    bestMove.row = i;
		                    bestMove.col = j;
		                    bestVal = moveVal;
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


