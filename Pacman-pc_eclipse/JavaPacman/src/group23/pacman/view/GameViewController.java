package group23.pacman.view;

import java.util.ArrayList;

import group23.pacman.MainApp;
import group23.pacman.controller.GameStateController;
import group23.pacman.model.Game;
import group23.pacman.model.GameObject;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;


public class GameViewController {
	
	/* FXML elements in GameView.fxml */
	@FXML
	private ImageView background_map;
	@FXML
	private ImageView digit_ones;
	@FXML
	private ImageView digit_tens;
	@FXML
	private ImageView digit_hunds;
	@FXML
	private ImageView digit_thous;
	@FXML
	private ImageView score_image;
	@FXML
	private ImageView life_1;
	@FXML
	private ImageView life_2;
	@FXML
	private ImageView life_3;
	@FXML
	private ImageView lives_image;

	private MainApp mainApp;
	
	/* Paint to canvas */
	private GraphicsContext graphicsContext;
	
	/* Allows user control */
	private GameStateController gameStateController;
	
	/* Stores game state (paused, running)*/
	private boolean running = true;
	
	public GameViewController() {
		
	}
	
	/* Creates a game based on user selected map */
	public void setGame(Game game) {
		
		/* First, select map based on user input */
		char map = game.getMap();
		String backgroundImage;
		switch (map) {
    	case 'c' :
    		backgroundImage = "bg/background-classic_game.png";
    		break;
	    case 's' :
	    	backgroundImage = "bg/background-sea_game.png";
	    	break;
	    case 'd' :
	    	backgroundImage = "bg/background-desert_game.png";
	    	break;
	    default :
	    	backgroundImage = "bg/background-sea_game.png";
	    	break;
		}
		
		/*Second, set the map as the background */
		background_map.setImage(new Image(backgroundImage));
		
		/* Create a controller through which the user may play the game */
		gameStateController = new GameStateController(this,game);
		gameStateController.listenToKeyEvents();
		
	}
	
	/* Sets up initial view */
	@FXML
	private void initialize() {
		
		/* Score starts off as 0 */
		String digitimage = "assets/numbers/0.png";
		digit_ones.setImage(new Image(digitimage));
		digit_tens.setImage(new Image(digitimage));
		digit_hunds.setImage(new Image(digitimage));
		digit_thous.setImage(new Image(digitimage));

	}
	
	/* Pauses/starts the game */
	public void toggleState() {
		
		this.running = !this.running;
	}
	
	/* Function called once to kick start the game */
	public void startGame() {
		
		/* Add canvas to layout.
		 * Create graphics context to draw game to canvas */
		Canvas canvas = new Canvas(1366,768);
		mainApp.getPane().getChildren().add(canvas);
		graphicsContext = canvas.getGraphicsContext2D();
		
		/* Animation timer to update frames */
		new AnimationTimer() {
			public void handle(long time) {
				
				/* Make sure game isn't in paused state */
				if (running == true) {
					graphicsContext.clearRect(0, 0, 1366, 768);
					gameStateController.update();
					draw(graphicsContext);
				}
			}
		}.start();
	}
	
	/* Draws all objects */
	public void draw(GraphicsContext graphicsContext) {
		
		
		gameStateController.getGame().getPacman().draw(graphicsContext);
		gameStateController.getGame().getGhost().draw(graphicsContext);
		
		/* Draws other objects ( walls ,pellets) */
		ArrayList<GameObject> objects = gameStateController.getGame().getOtherGameObjects();
		for (GameObject object : objects) {
			object.draw(graphicsContext);
		}
	}
	
	
	/* Public getter to pass scene to GameStateController */
	public Scene getScene() {
		
		return this.mainApp.getScene();
	}
	
	/* Public setter to reference main app */
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
	
	/* Updates the images of score digits to reflect user's score */
	public void setDigitImage(String image, int digit) {
		switch (digit) {
			case 0 :
				digit_ones.setImage(new Image(image));
				break;
			case 1 :
				digit_tens.setImage(new Image(image));
				break;
			case 2 :
				digit_hunds.setImage(new Image(image));
				break;
			case 3 :
				digit_thous.setImage(new Image(image));
				break;
		}
	}
	
	/* Updates the images of the lives to reflect remaining lives */
	public void setLivesImage(String image, int number) {
		switch (number) {
		case 0 :
			life_1.setImage(new Image(image));
			break;
		case 1 :
			life_2.setImage(new Image(image));
			break;
		case 2 :
			life_3.setImage(new Image(image));
			break;
		}
	}
}
