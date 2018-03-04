import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage; 
import javafx.scene.image.*;

public class GameActivity {
	
	
	Stage mainStage;
	Image pacman = new Image("pacman.png");
	
    Group root;
    Scene scene;
    Canvas canvas;
    GraphicsContext gc;
    int value = 50;
    
    
	public GameActivity(Stage mainStage) {
		
		this.mainStage = mainStage;
        
	    root = new Group();
	    scene = new Scene(root);
	         
	    canvas = new Canvas( 1440, 900 );
	    root.getChildren().add(canvas);

	         
	    gc = canvas.getGraphicsContext2D();
	    new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {

	        	value+=5;
	        	if (value>=900) {
	        		value = -50;
	        	}
	        	gc.clearRect(0, 0, 1440, 900);
	            // background image clears canvas
	            gc.drawImage( pacman, value, value );

	        }
	    }.start();
	    mainStage.setScene(scene);
	    mainStage.show();

	   /* GameThread gameThread = new GameThread(this,mainStage,gc,scene,pacman);
	    gameThread.setRunning(true);
	    gameThread.start();
	    
	    
		gameThread.interrupt();*/
		

	}

	
	
	
}
