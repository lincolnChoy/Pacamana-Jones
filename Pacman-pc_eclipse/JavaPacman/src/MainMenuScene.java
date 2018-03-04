import javafx.application.Application; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.StackPane; 
import javafx.stage.Stage; 
import javafx.scene.image.*;

public class MainMenuScene extends Application{ 
	
	
	Image image = new Image("background-main.png");
	 
	
	public static void main(String[] args) {
		
		launch(args);
		
		} 
	
	@Override 
	public void start(Stage gameWindow) { 
		
		gameWindow.setTitle("Pacman");
				
		Image playImage = new Image(getClass().getResourceAsStream("button-play.png"),150,100,false,false);
		Button btn = new Button("",new ImageView(playImage));
		
		btn.setOnAction(new EventHandler<ActionEvent>() { 
			
			@Override 
			public void handle(ActionEvent event) { 
				
				GameActivity gs = new GameActivity(gameWindow);
				
				} }); 
		
		StackPane pane = new StackPane();
		ImageView iv = new ImageView(image);
		
		pane.getChildren().add(iv);
		pane.getChildren().add(btn);
		gameWindow.setScene(new Scene(pane, 1440, 900)); 
		gameWindow.show(); 
		
		}
	
	
}