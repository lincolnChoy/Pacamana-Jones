package group23.pacman.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import group23.pacman.controller.Game;


public class Board {
	
	private static final int TILE_SIZE = 10;
	private static final int HELP = 1;
	
	
	private Game game;
	private ArrayList<GameObject> objects;
	
	private boolean[][] status;
	private boolean[][] turn;
	
	private char map;

	public Board() {
		
		/* Create the list and arrays of objects/states to be placed on the map */
		status = new boolean[101][71];
		turn = new boolean[101][71];
		objects = new ArrayList<GameObject>();
	}
	
	public void createBoard() {
		
		map = game.getMap();
		String line,mapTxt;
		
		/* Parse the map.txt file, loads the map into the game */
		try {
			switch (map) {
				case 's' :
					mapTxt = "mapOne.txt";
					break;
				case 'd' :
					mapTxt = "mapOne.txt";
					break;
				default :
					mapTxt = "mapOne.txt";
					break;
				
			}
			
			FileReader fileReader = new FileReader(mapTxt);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int row = 0;
			int position;
			/* Creates objects on the map based on their value in the text file
			 * 0 creates a wall
			 * P creates a pellet
			 * 1 is an empty position
			 * R is a position that the character can be in but cannot turn
			 * T is a position that the character can be in and can also turn */
			while ((line = bufferedReader.readLine()) != null ) {
				position = 0;
				for (int i =0;i< line.length();i++) {
					if (line.charAt(i)==('0')) {
						Rectangle rect = new Rectangle();
						rect.setX(position*TILE_SIZE + 33);
						rect.setY(row*TILE_SIZE + 34);
						rect.setWidth(TILE_SIZE);
						rect.setHeight(TILE_SIZE);
						Wall wall = new Wall(rect,map);
						status[position][row] = false;
						turn[position][row] = false;
						objects.add(wall);
						position++;
					}
					else if (line.charAt(i) == 'P') {
						Pellet pellet = new Pellet(position*TILE_SIZE + 33,row*TILE_SIZE + 34);
						objects.add(pellet);
						status[position][row] = false;
						turn[position][row] = false;
						position++;
					}
					else if (line.charAt(i) == '1' ) {
						status[position][row] = false;
						turn[position][row] = false;
						position++;
					}
					else if (line.charAt(i) == 'R' ) {
						status[position][row] = true;
						turn[position][row] = false;
						position++;
					}
					else if (line.charAt(i) == 'T' ) {
						status[position][row] = true;
						turn[position][row] = true;
						position++;
					}
					else {
						
					}
				}
				row++;
			}
			bufferedReader.close();
		} 
		
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file ");
		}

		catch (IOException ex) {
			System.out.println("Error reading file ");
		}
	}
	
	/* Public setter to reference the game object */
	public void setGame(Game game) {
		
		this.game = game;
	}
	
	/* Passes objects back to the game class - to check for collisions */
	public ArrayList<GameObject> getObjects() {
		
		return this.objects;
	}
	
	/* Checks if the resulting location of a turn will be a valid destination, i.e on a square that is valid(not inside a wall) */
	public boolean validTurningPoint(int x, int y) {
		
		if (((x - 33)%10 == 0) && ((y - 34)%10 == 0)){
			return this.status[(x - 33)/10][(y - 34)/10 ];
		}
		else {
			return false;
		}
	}
	
	/* Checks if a location is valid when the character is moving in a certain direction */
	public boolean isValidDestination(char direction, int x, int y) {
		
    	switch (direction) {
			case 'U':
				return this.status[(x - 33)/10][(y - HELP - 34)/10];
			case 'D':
				return this.status[(x - 33)/10][(y + TILE_SIZE - 34)/10];
			case 'L':
				return this.status[(x - HELP - 33)/10][(y - 34)/10];
			case 'R':
				return this.status[(x + TILE_SIZE - 33)/10][(y - 34)/10];
    	}
    	return false;
    }
	
}
