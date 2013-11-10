package com.navinf.learntocode;
import java.util.ArrayList;
import java.awt.*;

public class Shooter extends Enemy
{
	public Shooter( int x, int y, int health, ArrayList<Element> elements )
	{
		super( x, y, health, elements );
	}
	
	public void update(){
		super.update();
		if(distanceTo(player) < 50 && ticker % 30 == 0){
			player.damage(10);
		}
	}
	
	public void action(  )
	{
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}

