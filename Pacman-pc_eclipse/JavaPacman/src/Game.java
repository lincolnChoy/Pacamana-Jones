import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Game {
	
	
	private Pacman pacman;
	private ArrayList<Wall> walls;
	
	public Game() {
		
		pacman = new Pacman(30,334);
		walls = new ArrayList<Wall>();
		FileReader fileReader;
		try {
			
			/* Check what the directory is */
			/*File folder = new File(".");
			File[] files = folder.listFiles();
			for (File file : files) {
				System.out.println(file.getName());
			}*/
			fileReader = new FileReader("map.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			String[] args;
			int x,y,direction,length;
			//while ((line=bufferedReader.readLine())!=null) {
			for (int i =0;i<2;i++) {
				line = bufferedReader.readLine();
				args = line.split(",");
				x = Integer.parseInt(args[0]);
				y = Integer.parseInt(args[1]);
				direction = Integer.parseInt(args[2]);
				length = Integer.parseInt(args[3]);
				System.out.println(x+","+y+","+direction+","+length);
				walls.add(new Wall(x,y,direction,length));
				
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		catch (IOException e) {
			
		}


		
		
	}
	
	public void update() {

		pacman.update();
	}
	
	public ArrayList<Wall> getWalls(){
		
		return this.walls;
	}
	

	public Pacman getPacman() {
		
		return this.pacman;
	}
}
