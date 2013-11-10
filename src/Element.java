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
	
	public boolean isColliding(Element e){
	    int leftSide = getX();
	    int rightSide = getX() + getWidth();
	    int top = getY();
	    int bottom = getY() + getHeight();
	    
	    int eLeftSide = e.getX();
	    int eRightSide = e.getX() + e.getWidth();
	    int eTop = e.getY();
	    int eBottom = e.getY() + e.getHeight();
	    

	    if(eLeftSide < rightSide)
	        if(eRightSide > leftSide)
	        	if(eTop < bottom)
	        		if(eBottom > top)
	        			return true;
	    
	    return false;
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