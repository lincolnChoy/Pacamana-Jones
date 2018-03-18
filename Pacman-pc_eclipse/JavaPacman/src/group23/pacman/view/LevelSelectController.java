package group23.pacman.view;

import group23.pacman.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LevelSelectController {
	
	private MainApp mainApp;
	private static final int MAX_BACKGROUND_INDEX = 1;
	
	@FXML
	private ImageView levelImage;
	

	@FXML
	private ImageView leftArrow;
	
	@FXML
	private ImageView rightArrow;
	
	private int index;
	private Image seaBackground;
	private Image desertBackground;
	private Image[] backgrounds;
	
	
	public LevelSelectController() {
	

	}
	
	
	@FXML
	private void initialize() {
		
		/* Set up level backgrounds to scroll through */
		seaBackground = new Image("bg/background-sea_game.png");
		desertBackground = new Image("bg/background-desert_game.png");
		backgrounds = new Image[2];
		backgrounds[0] = seaBackground;
		backgrounds[1] = desertBackground;
		index = 0;
		levelImage.setImage(backgrounds[index]);
		
		/* Load the arrows */
		Image leftArrowImage = new Image("assets/leftArrow.png");
		Image rightArrowImage = new Image("assets/rightArrow.png");		
		leftArrow.setImage(leftArrowImage);
		rightArrow.setImage(rightArrowImage);
		
		
	}
	

	
	@FXML
	private void handleImageScroll(KeyEvent event) {
		
		if (event.getCode() == KeyCode.RIGHT) {
			index++;
			index = (index > MAX_BACKGROUND_INDEX) ? 0 : index;
		}
		
		else if (event.getCode() == KeyCode.LEFT) {
			index--;
			index = (index < 0) ? MAX_BACKGROUND_INDEX : index;
		}
		
		levelImage.setImage(backgrounds[index]);
	}
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}