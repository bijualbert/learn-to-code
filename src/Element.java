import java.awt.*;
import java.util.*;

public abstract class Element{
	private int x, y, width, height;
	
	protected ArrayList<Element> elements;
	
	public Element(int x, int y, int width, int height, ArrayList<Element> elements){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.elements = elements;
	}
	
	public int isColliding(Element e){
	    int leftSide = getX();
	    int rightSide = getX() + getWidth();
	    int top = getY();
	    int bottom = getY() + getHeight();
	    
	    int eLeftSide = e.getX();
	    int eRightSide = e.getX() + e.getWidth();
	    int eTop = e.getY();
	    int eBottom = e.getY() + e.getHeight();
	    
	    int count = 0;
	    
	    if(eTop < bottom && eLeftSide < rightSide && eRightSide > leftSide && eBottom > top){
	    	System.out.println("bottom");
	    	return 4;
	    }
	    
	    if(eBottom > top && eLeftSide < rightSide && eRightSide > leftSide && eTop < bottom){
	    	System.out.println("top");
	    	return 2;
	    }
	    
	    if(eBottom > top && eTop < bottom && eRightSide < leftSide && eLeftSide > rightSide){
	    	System.out.println("left");
	    	return 3;
	    }
	    
	    if(eBottom > top && eTop < bottom && eLeftSide < rightSide && eRightSide > leftSide ){
	    	System.out.println("right");
	    	return 1;
	    }
	    	
	    
	    return 0;
	}

	public void setX(double x) {
		this.x = (int)x;
	}
	
	public void setY(double y) {
		this.y = (int)y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	abstract void draw(Graphics g);
	
	abstract void update();
}