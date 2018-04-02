package group23.pacman.model;

import java.util.Random;

/** The class which deals with the AI movement of moving character objects.
*/

public class AI {
	
	/* Board object to help determine whether a move is valid */
	private Board board;
	
	/* There are currently 3 types of AI
	 * 1) Type == 0, is not an AI, it is a player controlled object
	 * 2) Type == 1, is an AI with completely random movements
	 * 3) Type == 2, is an AI which chases the main character */
	private int type;
	
	/* Random number generator for picking directions */
	private Random rand;
	
	/* Count value to prevent excessive bouncing between 2 nodes */
	private int count;
	
	
	public AI(Board board, int type) {
		
		this.board = board;
		this.type = type;
		rand = new Random();
		count = 0;
	}
	
	
	/* Chooses a direction using the private move generator method,while checking if the direction is a valid move on the board */
	public char chooseMovement(boolean hasLeftSpawn, char currentDirection, int ghostX, int ghostY, int pacmanX, int pacmanY) {
		char direction;
		if (type == 1) {
			direction = randomMove(hasLeftSpawn, currentDirection);
			while (!board.isValidDestination(hasLeftSpawn, direction, ghostX, ghostY)) {
				direction = randomMove(hasLeftSpawn, currentDirection);
			}
			return direction;
		}
		else if (type == 2) {
			direction = posCompMove(ghostX, ghostY, pacmanX, pacmanY);
			while (!board.isValidDestination(hasLeftSpawn, direction, ghostX, ghostY)) {
				direction = randomMove(hasLeftSpawn, currentDirection);
			}
			return direction;
		}
		return 'S';
	}
	
	
	/* Computes a move which depends on how close pacman is in a certain x/y direction */
	private char posCompMove(int ghostX, int ghostY, int pacmanX, int pacmanY) {
		if (Math.abs(ghostX - pacmanX) >= Math.abs(ghostY - pacmanY)){
			if (ghostX >= pacmanX) {
				return 'L';
			}
			else {
				return 'R';
			}
		}
		else {
			
			if(ghostY >= pacmanY) {
				return 'U';
			}
			else {
				return 'D';
			}
		}
	}
	
	
	/* Generates a random direction for the AI to move in */
	private char randomMove(boolean hasLeftSpawn, char currentDirection) {
		
		int nextDir = rand.nextInt(4);
		
		if (!hasLeftSpawn) {
			count = 1;
		}
		switch (nextDir) {
			case 0 :
				if (currentDirection == 'D') {
					if (count%2 == 0) {
						return currentDirection;
					}
					count++;
				}
				return 'U';
			case 1 :
				if (currentDirection == 'U') {
					if (count%2 == 0) {
						return currentDirection;
					}
					count++;
				}
				return 'D';
			case 2 :
				if (currentDirection == 'R') {
					if (count%2 == 0) {
						return currentDirection;
					}
					count++;
				}
				return 'L';
			case 3 :
				if (currentDirection == 'L') {
					if (count%2 == 0) {
						return currentDirection;
					}
					count++;
				}
				return 'R';
			default : 
				return currentDirection;
		}
	}
	
	
	/* Checks the board object if we are able to turn at a certain (x,y) position */
	public boolean canTurn(int x, int y) {
		
		return board.atNode(x, y);
	}
}
