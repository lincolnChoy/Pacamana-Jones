import java.awt.image.BufferedImage;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pacman implements GameObject{
	
	
	private static final int SPRITE_HEIGHT = 50;
	private static final int SPRITE_WIDTH = 50;
	private static final int SPEED = 3;
	private Rectangle hitBox;
	private Image sprite;
	private int vector;
	private AnimationManager animationManager;
	private Animation animation;
	
	public Pacman(int x,int y) {
		
		/* Set up the frame animation for the main character */
		Image pacmanMouthClosed = new Image("pacman-mouthclosed.png",50,50,false,false);
		Image pacmanMouthOpened = new Image("pacman-mouthopen.png",50,50,false,false);
		Image[] regularFrames = new Image[2];
		regularFrames[0] = pacmanMouthClosed;
		regularFrames[1] = pacmanMouthOpened;
		Animation regularAnimation = new Animation(regularFrames,0.2f);
		animationManager = new AnimationManager(regularAnimation);
		
		/* Sets up the main character's hit-box */
		hitBox = new Rectangle();
		hitBox.setHeight(SPRITE_HEIGHT);
		hitBox.setWidth(SPRITE_WIDTH);
		hitBox.setX(x);
		hitBox.setY(y);
		this.vector = 0;
	
	}
	
	public void update() {
		
		
		/* UP */
		if (this.vector == 1) {
			this.hitBox.setY((int)hitBox.getY() - SPEED);
		}
		/* DOWN */
		else if (this.vector == 2) {
			this.hitBox.setY((int)hitBox.getY() + SPEED);
		}
		/* LEFT */
		else if (this.vector == 3) {
			this.hitBox.setX((int)hitBox.getX() - SPEED);
		}
		/* RIGHT */
		else if (this.vector == 4) {
			this.hitBox.setX((int)hitBox.getX() + SPEED);
		}
		
		animationManager.update();
		animationManager.playAction(0);
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		animationManager.draw(graphicsContext,this.hitBox.getX(),this.hitBox.getY());
		
	}
	
    public Rectangle getHitBox(){
    	
    	return this.hitBox;
    }

    public double getX() {
    	
    	return this.hitBox.getX();
    }
    
    public double getY() {
    	
    	return this.hitBox.getY();
    }
    
    public void setDirection(int vector) {

    	this.vector = vector;	
    }
    
    public int getDirection() {
    	
    	return this.vector;
    }
    
    public Image getImage() {
    	
    	return this.sprite;
    }
}
