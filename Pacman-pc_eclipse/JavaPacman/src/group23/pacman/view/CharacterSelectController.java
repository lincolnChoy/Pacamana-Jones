package group23.pacman.view;

import group23.pacman.MainApp;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class CharacterSelectController {

	@FXML
	private ImageView ghost1;
	@FXML
	private ImageView ghost2;
	@FXML
	private ImageView ghost3;
	@FXML
	private ImageView ghost4;
	
	private MainApp mainApp;
	
	private static final int SPRITE_HEIGHT = 150;
	private static final int SPRITE_WIDTH = 150;
	
	
	public CharacterSelectController() {
		
	}
	
	@FXML
	private void initialize() {
		
		ghost1.setImage(new Image("assets/Ghost/ghost1Right1.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		ghost2.setImage(new Image("assets/Ghost/ghost2Right1.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		ghost3.setImage(new Image("assets/Ghost/ghost3Right1.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		ghost4.setImage(new Image("assets/Ghost/ghost4Right1.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
	}
	
	
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
}
