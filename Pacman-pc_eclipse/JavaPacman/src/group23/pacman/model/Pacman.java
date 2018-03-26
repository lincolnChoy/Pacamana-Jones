package group23.pacman.model;
import group23.pacman.view.Animation;
import group23.pacman.view.AnimationManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pacman extends GameObject implements MovingCharacter {
	
	public enum STATE {
		
		POWER_UP,
		DEAD,
		ALIVE
	
	}
	
	/* Pacman's size */
	private static final int SPRITE_HEIGHT = 30;
	private static final int SPRITE_WIDTH = 30;
	
	/* Pixels moved per update */
	private static final int SPEED = 2;
	
	/* Direction to move and planned direction */
	private char vector;
	private char queuedDirection;
	
	
	/* Handles the animations */
	private AnimationManager animationManager;
	
	/* Pacman's state */
	private STATE state;
	
	/* Pacman's Whip */
	private Whip whip;
	
	private boolean whipping;
	
	private int lives;
	
	
	
	public Pacman(int x,int y) {

		setUpAnimations();

		/* Sets up the main character's hit-box */
		hitBox = new Rectangle();
		hitBox.setHeight(SPRITE_HEIGHT);
		hitBox.setWidth(SPRITE_WIDTH);
		hitBox.setX(x);
		hitBox.setY(y);
		
		type = GameObject.TYPE.PACMAN;
		state = STATE.ALIVE;
		lives = 3;
		
		/* Character does not initially move or whip*/
		this.whipping = false;
		this.vector = 'S';
		this.queuedDirection = 'S';
	
	}

	public void whip() {
		
		this.whipping = true;
	}

	public void update() {	
		
		if (whipping == true) {
			whip.getHitBox().setX(this.hitBox.getX());
			whip.getHitBox().setY(this.hitBox.getY());
			
		}
		animationManager.update();
		playAnimation();
	}

    public boolean collidedWith(GameObject object) {
    	
    	
    	Rectangle hitBox = object.getHitBox();
    	
    	return this.hitBox.intersects(hitBox);
    }
    
    public void died() {
    	lives--;
    }
    
	public void queueMovement(char queuedDirection) {
		
		this.queuedDirection = queuedDirection;
	}

    
    public void setDirection(char vector) {
    	
    	this.vector = vector;	
    }
    
    /* Public setter for state */
    public void setState(STATE state) {
    	
    	this.state = state;
    }
    
    public boolean checkforQueuedAction() {
    		
	    return (queuedDirection != vector);
    }
    
    public boolean oppositeDirection() {
    	
    	switch (vector) {
    		case 'S':
    			return true;
			case 'U':
				if (queuedDirection == 'D') {
					return true;
				}
			case 'D':
				if (queuedDirection == 'U') {
					return true;
				}
			case 'L':
				if (queuedDirection == 'R') {
					return true;
				}
			case 'R':
				if (queuedDirection == 'L') {
					return true;
				}
    	}
    	return false;
    }
        
    public void updateDestination() {
    
    		
    	if (this.vector == 'U') {
			this.hitBox.setY((int)hitBox.getY() - SPEED);
		}
		else if (this.vector == 'D') {
			this.hitBox.setY((int)hitBox.getY() + SPEED);
		}
		else if (this.vector == 'L') {
			this.hitBox.setX((int)hitBox.getX() - SPEED);
		}
		else if (this.vector == 'R') {
			this.hitBox.setX((int)hitBox.getX() + SPEED);
		}
    }

    public char getDirection() {
    	
    	return this.vector;
    }
    
    public char getQDirection() {
    	
    	return this.queuedDirection;
    }
    
    public STATE getState() {
    	return this.state;
    }
    
    public int getLives() {
    	return this.lives;
    }
    
    /* Resets Pacman's position when Pacman dies and still has lives left. */
	public void reset(int x, int y) {
		
		this.hitBox.setX(x);
		this.hitBox.setY(y);
		setDirection('S');
		setState(Pacman.STATE.ALIVE);
	}

	public void draw(GraphicsContext graphicsContext) {
		
		animationManager.draw(graphicsContext,this.hitBox.getX(),this.hitBox.getY());
	}
	
    /* Changes character animation depending on the direction it's currently facing */
    public void playAnimation() {
    	
    	if (this.vector == 'S') {
			animationManager.playAction(1);
		}
		if (this.vector == 'U') {
			animationManager.playAction(2);
		}
		else if (this.vector == 'D') {
			animationManager.playAction(3);
		}
		else if (this.vector == 'L') {
			animationManager.playAction(0);
		}
		else if (this.vector == 'R') {
			animationManager.playAction(1);
		}
    }
    
	/* Set up the frame animation for the main character */
	private void setUpAnimations() {

		Image leftC = new Image("assets/Pacman/leftClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image leftO = new Image("assets/Pacman/leftOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image rightC = new Image("assets/Pacman/rightClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image rightO = new Image("assets/Pacman/rightOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image upC = new Image("assets/Pacman/upClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image upO = new Image("assets/Pacman/upOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image downC = new Image("assets/Pacman/downClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image downO = new Image("assets/Pacman/downOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		
		Image[] leftMove = new Image[2];
		leftMove[0] = leftC;
		leftMove[1] = leftO;
		
		Image[] rightMove = new Image[2];
		rightMove[0] = rightC;
		rightMove[1] = rightO;
		
		Image[] upMove = new Image[2];
		upMove[0] = upC;
		upMove[1] = upO;
		
		Image[] downMove = new Image[2];
		downMove[0] = downC;
		downMove[1] = downO;
		
		Animation leftAnimation = new Animation(leftMove,0.3f);
		Animation rightAnimation = new Animation(rightMove,0.3f);
		Animation upAnimation = new Animation(upMove,0.3f);
		Animation downAnimation = new Animation(downMove,0.3f);
		
		Animation[] movementAnimations = new Animation[4];
		movementAnimations[0] = leftAnimation;
		movementAnimations[1] = rightAnimation;
		movementAnimations[2] = upAnimation;
		movementAnimations[3] = downAnimation;
		
		animationManager = new AnimationManager(movementAnimations);
		
	}

}
