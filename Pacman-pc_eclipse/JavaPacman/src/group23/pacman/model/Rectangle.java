package group23.pacman.model;

/** The class that we use to classify hit boxes for all game objects */
public class Rectangle {

    private double x;
    private double y;
    private int width;
    private int height;

    private boolean firstIntersect;

    public Rectangle(){
    	
        firstIntersect = true;
    }
    
    public boolean intersects(Rectangle other){
    	
        return (this.x < (other.x + other.width) && (this.x + this.width) > other.x && this.y < (other.y  + other.height) && (this.y +  this.height) > other.y );
    }

    public boolean firstIntersect(){

        return this.firstIntersect;
    }

    public void setFirstIntersect(boolean firstIntersect){

        this.firstIntersect = firstIntersect;
    }
    
    public void setX(double x) {
    	
    	this.x = x;
    }
    
    public void setY(double y) {
    	
    	this.y = y;
    }
    
    public void setWidth(int width) {
    	
    	this.width = width;
    }
    
    public void setHeight(int height) {
    	
    	this.height = height;
    }
    
    public double getX() {
    	
    	return this.x;
    }
    
    public double getY() {
    	return this.y;
    }
    
    

}
