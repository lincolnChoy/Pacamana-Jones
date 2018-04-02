package group23.pacman.model;

public class Whip extends GameObject implements MovingCharacter {
	
	/* Pixels moved per update */
	private static final int SPEED = 2;
	
	/* Direction Pacman is facing */
	private char vector;
	
	public Whip() {
		
		hitBox = new Rectangle();
		
	}

	@Override
	public boolean checkforQueuedAction() {
		return false;
	}

	@Override
	public void setDirection(char qDirection) {
		
	}

	@Override
	public char getQDirection() {
		return 0;
	}

	@Override
	public void updateDestination() {
		
	}

	@Override
	public char getDirection() {
		return 0;
	}

	@Override
	public void reset(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHasLeftSpawn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getHasLeftSpawn() {
		// TODO Auto-generated method stub
		return false;
	}
}
