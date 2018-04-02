package group23.pacman.model;

import java.util.Random;

public class AI {
	
	private Board board;
	
	private int type;
	
	private Random rand;
	
	/* Count value to prevent excessive bouncing between 2 nodes*/
	private int count;
	
	public AI(Board board, int type) {
		
		this.board = board;
		this.type = type;
		rand = new Random();
		count = 0;
	}
	
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
	
	public boolean canTurn(int x, int y) {
		return board.atNode(x, y);
	}
}
