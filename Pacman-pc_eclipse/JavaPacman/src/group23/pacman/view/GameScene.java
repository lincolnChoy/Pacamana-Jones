package group23.pacman.view;
import group23.pacman.controller.Game;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage; 
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** 
	The view class that handles user input and drawing to the canvas.
 */
public class GameScene {
	
	Stage mainStage;
	
	private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Game game;
    private boolean running;
    private char map;

	public GameScene(Stage mainStage,char map) {
		
		this.mainStage = mainStage;
        this.running = true;
        this.map = map;
		root = new Group();
		scene = new Scene(root);
	    canvas = new Canvas(1366, 768);
	    
	    
	    String backgroundImage;
	    
	    switch (map) {
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

	    ImageView iv = new ImageView(new Image(backgroundImage));
	    
	    
	    root.getChildren().add(iv);
	    root.getChildren().add(canvas);
	    graphicsContext = canvas.getGraphicsContext2D();
		

	    scene.setOnKeyPressed(new EventHandler<KeyEvent> (){
	    	@Override
	    	public void handle(KeyEvent e) {
	    	/* switch to switch statements later */
	    	
		    	if (e.getCode() == KeyCode.UP) {
		    		game.getPacman().queueMovement('U');
		    	}
		    	else if (e.getCode() == KeyCode.DOWN) {
		    		game.getPacman().queueMovement('D');
		    	}
		    	else if (e.getCode() == KeyCode.LEFT) {
		    		game.getPacman().queueMovement('L');
		    	}
		    	else if (e.getCode() == KeyCode.RIGHT) {
		    		game.getPacman().queueMovement('R');
		    	}
		    	else if (e.getCode() == KeyCode.P) {
		    		changeState();
		    	}
	    	}
	    });
	    
	    mainStage.setScene(scene);
	    mainStage.show();

	}
	
	
	
	/* Pauses/starts the game */
	protected void changeState() {
		
		this.running = !this.running;
	}
	
	public void start() {

		
		 new AnimationTimer() {
			 	

				public void handle(long time) {
						
					if (running == true) {
			        	graphicsContext.clearRect(0, 0, 1366, 768);
			        	game.update();
			        	draw(graphicsContext);
					}

		        }
		    }.start();
		
	}
	
	public void setMap(char map) {
		
		this.map = map;
	}
	public void setGameMode(int gameType) {
		
		/* Game has single player, multi-player game modes */
		if (gameType == 1) {
			game = new Game(map);

		}
		
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		game.getPacman().draw(graphicsContext);
		//game.getGhost().draw(graphicsContext);
		game.drawObjects(graphicsContext);
	}
	


	
	
	
}
