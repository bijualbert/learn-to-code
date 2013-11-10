package com.navinf.learntocode;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Player extends Character
{
	boolean dead;
	public Player( int x, int y, int health, ArrayList<Element> elements )
	{
		super( x, y, health, elements );
		MOVESPEED = 0.2;

	}
	
	public void update(){
		super.update();
		if(getHealth() <= 0)
			dead = true;
		if(dead){
			setVX(0);
		}
	}
	
	public void movement()
	{
		
	}
	
	
	public void draw(Graphics g){
		g.setColor(dead ? Color.RED : Color.GREEN);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
}
