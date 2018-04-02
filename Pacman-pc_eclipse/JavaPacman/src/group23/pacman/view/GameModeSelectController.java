package group23.pacman.view;

import group23.pacman.MainApp;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** Controller class for the GameModeSelect screen */

public class GameModeSelectController {
	
	@FXML
	private ImageView background;
	@FXML
	private ImageView singlePlayerImage;
	@FXML
	private ImageView twoPlayerImage;
	@FXML
	private ImageView threePlayerImage;
	
	/* Main app copy kept to use when referencing to get its stage, and scene. */
	private MainApp mainApp;
	
	private Scene scene;

	public GameModeSelectController() {
		
	}
	
		
	@FXML
	private void initialize() {
		
		/* Set up background of this view */
		Image backgroundImage = new Image("bg/game_mode_select_background.png");
		background.setImage(backgroundImage);
		
		Image playImage = new Image("assets/buttons/button-single_player-highlighted.png",200,100,false,false);
		singlePlayerImage.setImage(playImage);
		
		Image optionImage = new Image("assets/buttons/button-two_player.png",200,100,false,false);
		twoPlayerImage.setImage(optionImage);
		
		Image exitImage = new Image("assets/buttons/button-three_player.png",200,100,false,false);
		threePlayerImage.setImage(exitImage);

	}
	
	@FXML
	private void handleSinglePlayer(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			
			mainApp.setPlayers(1);
			mainApp.showLevelSelect();
		}
		
		else if (event.getCode() == KeyCode.LEFT) {
			/* Maybe play a sound effect here */
		}
		else if (event.getCode() == KeyCode.RIGHT) {
			highlightButton(2);
		}
		
	}
	
	@FXML
	private void handleTwoPlayer(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			
			mainApp.setPlayers(2);
			mainApp.showLevelSelect();
		}
		
		else if (event.getCode() == KeyCode.LEFT) {
			highlightButton(1);
		}
		else if (event.getCode() == KeyCode.RIGHT) {
			highlightButton(3);
		}
		
	}
	
	@FXML
	private void handleThreePlayer(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			
			mainApp.setPlayers(3);
			mainApp.showLevelSelect();
		}
		
		else if (event.getCode() == KeyCode.LEFT) {
			highlightButton(2);
		}
		else if (event.getCode() == KeyCode.RIGHT) {

		}
		
	}
	
	
	private void highlightButton(int button) {
		
		if (button == 1) {
			singlePlayerImage.setImage(new Image("assets/buttons/button-single_player-highlighted.png",200,100,false,false));
			twoPlayerImage.setImage(new Image("assets/buttons/button-two_player.png",200,100,false,false));
			threePlayerImage.setImage(new Image("assets/buttons/button-three_player.png",200,100,false,false));
		}
		else if (button == 2) {
			singlePlayerImage.setImage(new Image("assets/buttons/button-single_player.png",200,100,false,false));
			twoPlayerImage.setImage(new Image("assets/buttons/button-two_player-highlighted.png",200,100,false,false));
			threePlayerImage.setImage(new Image("assets/buttons/button-three_player.png",200,100,false,false));
		}
		else if (button == 3) {
			singlePlayerImage.setImage(new Image("assets/buttons/button-single_player.png",200,100,false,false));
			twoPlayerImage.setImage(new Image("assets/buttons/button-two_player.png",200,100,false,false));
			threePlayerImage.setImage(new Image("assets/buttons/button-three_player-highlighted.png",200,100,false,false));
		}
	}
	
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
	
}