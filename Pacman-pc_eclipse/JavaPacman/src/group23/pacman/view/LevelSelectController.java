package group23.pacman.view;

import java.io.IOException;

import group23.pacman.MainApp;
import group23.pacman.model.Game;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/** 
 * This class handles the level select screen and creates the game to pass to game controller class **/

public class LevelSelectController {
	
	/* Number of available levels subtract 1 */
	private static final int MAX_BACKGROUND_INDEX = 2;
	
	/* FXML elements in LevelSelect.fxml */
	@FXML
	private ImageView background;
	@FXML
	private ImageView levelImage;
	@FXML
	private ImageView leftArrow;
	@FXML
	private ImageView rightArrow;
	
	
	/* Main app copy kept to use when referencing to get its stage, and scene. */
	private MainApp mainApp;
	private Scene scene;

	/* Variables for showing which background/level/map will be set */
	private int index;
	private Image seaBackground;
	private Image desertBackground;
	private Image classicBackground;
	
	private Image[] backgrounds;

	/* Variable to control scroll speed */
	private long lastTime;

	/* This boolean stops the enter key press that was used before from instantly starting a game */
	private boolean firstPress;
	
	/* Boolean to prevent animation to happen to already animated image */
	private boolean animated;
	
	
	/* Constructor */
	public LevelSelectController() {
		
		lastTime = 0;
		firstPress = true;
		animated = false;
	}
	
	/* Sets up images and backgrounds for initial view */
	@FXML
	private void initialize() {
		
		/* Set up background of this view */
		Image backgroundImage = new Image("bg/background-levelSelect.png");
		background.setImage(backgroundImage);
		
		/* Set up level backgrounds to scroll through */
		seaBackground = new Image("bg/background-sea_game.png");
		desertBackground = new Image("bg/background-desert_game.png");
		classicBackground = new Image("bg/background-classic_game.png");
		backgrounds = new Image[3];
		backgrounds[0] = classicBackground;
		backgrounds[1] = seaBackground;
		backgrounds[2] = desertBackground;
		index = 0;
		levelImage.setImage(backgrounds[index]);
		
		/* Load the arrows */
		Image leftArrowImage = new Image("assets/buttons/leftArrow.png",110,110,false,false);
		Image rightArrowImage = new Image("assets/buttons/rightArrow.png",110,110,false,false);		
		leftArrow.setImage(leftArrowImage);
		rightArrow.setImage(rightArrowImage);
		
	}
	
	/* Adds key listener to scene */
	public void listenToKeyEvents() {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		    @Override
		    public void handle(KeyEvent event) {
		    	/* Selects the currently shown map/level */
		    	if (event.getCode() == KeyCode.ENTER) {	
		    		if (!firstPress) {
			    		char level;
			    		switch (index) {
			    			case 0 :
			    				level = 'c';
			    				break;
			    			case 1 :
			   					level = 's';
			   					break;
			    			case 2 :
			    				level = 'd';
			    				break;
		    				default :
			    				level = 'c';
			    				break;
			    		}
			    		
			    		startGame(level);

		    		}
		    		else {
		    			firstPress = false;
		    		}
		    	}
		    	else if (event.getCode() == KeyCode.LEFT) {
		    		
		    		/* Prevents background from scrolling too fast if key is held down
		    		 * Only changes every half second */
					if (System.currentTimeMillis() - lastTime > 500) {
						lastTime = System.currentTimeMillis();
						setLeftBackground();
					}
					if (!animated) {
						animateLeft();
						animated = true;
					}

				}
				
				else if (event.getCode() == KeyCode.RIGHT) {
					
					/* Prevents background from scrolling too fast if key is held down
		    		 * Only changes every half second */
					if (System.currentTimeMillis() - lastTime > 500) {
						lastTime = System.currentTimeMillis();
						setRightBackground();
					}
					if (!animated) {
						animateRight();
						animated = true;
					}
				}
				
		    }	    
		});
		
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		    @Override
		    public void handle(KeyEvent event) {
		    	if (event.getCode() == KeyCode.LEFT) {
		    		resetLArrow();
		    		lastTime = 0;
		    		animated = false;
				}
				
				else if (event.getCode() == KeyCode.RIGHT) {
					resetRArrow();
		    		lastTime = 0;
		    		animated = false;
				}
		    }	    
		});
	}
	
	/* Creates a game view and sets the game */
	private void startGame(char map) {
		
		try {
			
			/* Load/show the game view layout */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GameView.fxml"));
			AnchorPane gameView = (AnchorPane) loader.load();
			mainApp.getPane().setCenter(gameView);
			
			/* Get the controller to manipulate this class */
			GameViewController controller = loader.getController();
			controller.setMainApp(mainApp);
			
			/* Create game and pass to controller */
			Game game = new Game(map);
			controller.setGame(game);
			controller.initialDraw();
			controller.startGame();	
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	/* Public setter for this class to reference the main application */
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
		this.scene = mainApp.getScene();
	}
	
	
	
	
	/** BELOW ARE HELPER FUNCTIONS WHICH HELP WITH THE ANIMATION OF THIS VIEW **/
	
	/* Set background functions - 
	 * Help scroll the background to the left or the right
	 */
	private void setLeftBackground() {
		index--;
		index = (index < 0) ? MAX_BACKGROUND_INDEX : index;
		levelImage.setImage(backgrounds[index]);
	}
	
	private void setRightBackground() {
		index--;
		index = (index < 0) ? MAX_BACKGROUND_INDEX : index;
		levelImage.setImage(backgrounds[index]);
	}
	
	/* Animate functions help "animate" the arrow keys, by
	 * enlarging them as the respective key is held down.
	 * This gives the user feedback on the key press and is a nice little feature for the UI*/
	private void animateLeft() {
		
		leftArrow.setX(- 40);
        leftArrow.setY(- 40);
		leftArrow.setFitHeight(150);
		leftArrow.setFitWidth(150);
        leftArrow.setImage(new Image("assets/buttons/leftArrow.png",150,150,false,false));
        
	}
	
	private void animateRight() {
		
		rightArrow.setX(0);
		rightArrow.setY(-40);
		rightArrow.setFitHeight(150);
		rightArrow.setFitWidth(150);
		rightArrow.setImage(new Image("assets/buttons/rightArrow.png",150,150,false,false));

	}
	
	/* Reset functions help reset the arrows to their original size to let the 
	 * user know when the key is released.
	 */
	private void resetLArrow() {
		
		leftArrow.setX(0);
        leftArrow.setY(0);
		leftArrow.setFitHeight(110);
		leftArrow.setFitWidth(110);
        leftArrow.setImage(new Image("assets/buttons/leftArrow.png",110,110,false,false));
	}
	
	private void resetRArrow() {
		
		rightArrow.setX(0);
		rightArrow.setY(0);
		rightArrow.setFitHeight(110);
		rightArrow.setFitWidth(110);
		rightArrow.setImage(new Image("assets/buttons/rightArrow.png",110,110,false,false));
	}

}
