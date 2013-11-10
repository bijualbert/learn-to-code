package com.navinf.learntocode;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Obstacle extends Element{
	
	Obstacle(int x, int y, int width, int height, ArrayList<Element> elements){
		super(x, y, width, height, elements);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	public void update(){}

}
