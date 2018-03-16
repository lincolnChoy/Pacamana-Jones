import java.io.File;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Game {
	
	
	private Pacman pacman;
	private Ghost ghost;
	private Media chompNoise;
	private MediaPlayer mediaPlayer;
	private ArrayList<GameObject> objects;
	private Board board;
	
	
	public Game() {
		
		board = new Board();
		objects = board.getObjects();
		pacman = new Pacman(43,44);
		ghost = new Ghost(300,334);
		chompNoise = new Media(new File("bin\\assets\\sfx\\chompNoise.mp3").toURI().toString());
	
	}
	
	public void update() {
		
		checkCollisions();
		pacman.update();
		ghost.update();
		
	}
	
	private void checkCollisions() {
		
		
		if (pacman.checkforQueuedAction() && board.canTurn((int) pacman.getX(), (int) pacman.getY())) {
			if (board.testNextMove(pacman.getQDirection(), (int) pacman.getX(), (int) pacman.getY())){
				pacman.setDirection(pacman.getQDirection());
				pacman.changeMovement();
				return;
			}
		}
		if (board.testNextMove(pacman.getDirection(), (int) pacman.getX(), (int) pacman.getY())) {
			pacman.changeMovement();
		}
		
		
	
		
		for (GameObject object : objects) {

			if (pacman.collidedWith(object)) {
				if (object.getType() == GameObject.TYPE.PELLET) {
					playSfx(chompNoise);
					objects.remove(object);
					break;
				}
		
			}
		}
	}
	
	

	public Pacman getPacman() {
		
		return this.pacman;
	}
	
	public Ghost getGhost() {
		
		return this.ghost;
	}
	
	public void drawObjects(GraphicsContext graphicsContext) {
		
		for (GameObject object : objects) {
			object.draw(graphicsContext);
		}
	}
	
	public void playSfx(Media sfx) {
		mediaPlayer = new MediaPlayer(sfx);
		mediaPlayer.setVolume(0.3);
		mediaPlayer.play();
	}


}
