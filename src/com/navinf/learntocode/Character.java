package com.navinf.learntocode;

import java.awt.*;
import java.util.ArrayList;


public abstract class Character extends Mobile
{
	private int health;
	Player player;
	
	public Character( int x, int y, int health, ArrayList<Element> elements )
	{
		super(x,y,32,32, elements);
		setHealth(health);
		
		for( int i = 0; i < elements.size() - 1; i++ )
		{
			if( elements.get( i ) instanceof Player )
				player = (Player) elements.get( i );
		}
	}
	
	
	public void setHealth( int a )
	{
		health = a;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void damage(int damage){
		if(damage >= 0){
			setHealth(getHealth() - damage);
		}
	}
	
	public abstract void draw(Graphics g);

}
