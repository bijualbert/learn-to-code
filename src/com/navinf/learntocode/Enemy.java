package com.navinf.learntocode;
import java.awt.*;

import java.util.ArrayList;

import java.util.*;

public abstract class Enemy extends Character
{
	public enum Facing { LEFT, RIGHT }
	Facing face = Facing.LEFT;
	Random random = new Random();
	public int minX;
	public int maxX;
	public boolean restrict;
	
	public Enemy( int x, int y, int health, ArrayList<Element> elements )
	{
		super( x, y, health, elements );
		
		
		
		if(random.nextInt() == 1){
			face = Facing.RIGHT;
		}
	}
	
	public void update(){
		super.update();
		if(restrict && (getX() < minX)){
			face = Facing.RIGHT;
			setX(minX);
		}
		if(restrict && (getX() > maxX)){
			face = Facing.LEFT;
			setX(maxX);
		}
		if(ticker % 100 == 1){
			movement();
		}
		
	}
	
	public void movement()
	{
		
		something( player );
		//setVX( 0.05 );
		
		if( face == Facing.LEFT ){
			moveLeft( true );
			moveRight( false );
		}else{
			moveRight( true );
			moveLeft( false );}
		
		
	}
	
	public void changeDirection(){
		System.out.println("sdfsd");
		if( face == Facing.LEFT )
			face = Facing.RIGHT;
		else
			face = Facing.LEFT;
	}
	
	private void something( Player player )
	{
		switch( face )
		{
		case LEFT:
		{	
			if( player.getX() < getX() && getX() - player.getX() <= 200 )
				action();
		}
			break;
		 		
		case RIGHT:
		{
		 	if( player.getX() > getX() && player.getX() - getX() <= 200 )
				action();
		}
			break;
				
		default:
			break;
		}
	}

	public abstract void action();
}
