package group23.pacman.controller;

import group23.pacman.model.Game;
import group23.pacman.view.GameViewController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameStateController {
	
	/* Scene used to add key listener */
	private Scene scene;
	
	private Game game;
	
	private GameViewController gameViewController;
	
	public GameStateController(GameViewController gameViewController,Game game) {
		
		this.gameViewController = gameViewController;
		this.scene = gameViewController.getScene();
		this.game = game;
		
	}
	
	public void listenToKeyEvents() {
		
		 scene.setOnKeyPressed(new EventHandler<KeyEvent> (){
		    	@Override
		    	public void handle(KeyEvent e) {
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
			    	else if (e.getCode() == KeyCode.SPACE) {
			    		game.getPacman().whip();
			    	}
			    	/* Pause button */
			    	else if (e.getCode() == KeyCode.P) {
			    		gameViewController.toggleState();
			    	}
		    	}
		    });
	}
	
	/* Update the game state and score */
	public void update() {
		
		game.update();
		updateScore();
		updateLives();
	}
	
	
	private void updateScore() {
		
		/* Updates each digit */
		for (int i = 0; i < 4 ; i++) {
			gameViewController.setDigitImage(getDigit(game.getScore().charAt(i)), i);
		}
	}
	
	private void updateLives() {
		
		/* Updates lives image only if lives have been lost */
		for (int i = game.getPacman().getLives(); i < 3; i++) {
			gameViewController.setLivesImage("assets/misc/empty.png", i);
		}
	}
	
	/* Helper function to break score digits down (to more easily show in UI) */
	private String getDigit(char digit){

        switch (digit){
            case '0' :
                return "assets/numbers/0.png";
            case '1' :
                return "assets/numbers/1.png";
            case '2' :
                return "assets/numbers/2.png";
            case '3' :
                return "assets/numbers/3.png";
            case '4' :
                return "assets/numbers/4.png";
            case '5' :
                return "assets/numbers/5.png";
            case '6' :
                return "assets/numbers/6.png";
            case '7' :
                return "assets/numbers/7.png";
            case '8' :
                return "assets/numbers/8.png";
            case '9' :
                return "assets/numbers/9.png";
            default :
                return "assets/numbers/0.png";
        }
    }
	
	/* Public getter to allow GameViewController(the view class) to reference objects(to draw) */
	public Game getGame() {
		
		return this.game;
	}

}
