package com.navinf.learntocode;
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
	
	public double distanceTo(Element e){
		int centerx1 = this.getX() + getWidth()/2;
		int centery1 = this.getY() + getHeight()/2;
		
		int centerx2 = e.getX() + e.getWidth()/2;
		int centery2 = e.getY() + e.getHeight()/2;
		
		return Math.sqrt( (centerx2 - centerx1) * (centerx2 - centerx1) + (centery2 - centery1)*(centery2 - centery1) );
	}
	
	public boolean[] isColliding(Element e){
	    int leftSide = getX();
	    int rightSide = getX() + getWidth();
	    int top = getY();
	    int bottom = getY() + getHeight();
	    
	    int eLeftSide = e.getX();
	    int eRightSide = e.getX() + e.getWidth();
	    int eTop = e.getY();
	    int eBottom = e.getY() + e.getHeight();
	    
	    boolean[] array = {false, false, false, false, false};
	    
	    if(eBottom > top && eTop < bottom && eRightSide > leftSide && eLeftSide < rightSide){
	    	//System.out.println("left");
	    	array[3] = true;
	    	//return 3;
	    }
	    
	    if(eBottom > top && eTop < bottom && eLeftSide < rightSide && eRightSide > leftSide ){
	    	//System.out.println("right");
	    	array[1] = true;
	    	//return 1;
	    }
	    
	    if(eTop < bottom && eLeftSide < rightSide && eRightSide > leftSide && eBottom > top){
	    	//System.out.println("bottom");
	    	array[4] = true;
	    	//return 4;
	    }
	    
	    if(eBottom > top && eLeftSide < rightSide && eRightSide > leftSide && eTop < bottom){
	    	//System.out.println("top");
	    	array[2] = true;
	    	//return 2;
	    }
	    
	    int count = 0;
	    for(boolean b : array){
	    	if(b){
	    		count++;
	    	}
	    }
	    
	    if(count == 0){
	    	array[0] = true;
	    }
	    
	    return array;
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