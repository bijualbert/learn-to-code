import java.awt.Graphics;

public abstract class Element{
	private int x, y, width, height;
	private boolean mobile;
	
	public Element(int x, int y, int width, int height, boolean mobile){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.mobile = mobile;
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

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
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