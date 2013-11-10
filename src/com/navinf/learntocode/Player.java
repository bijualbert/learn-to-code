package com.navinf.learntocode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Player extends Character
{
	private BufferedImage player_image;
	boolean dead;
	public Player( int x, int y, int health, ArrayList<Element> elements ) throws IOException
	{
		super( x, y, health, elements );
		MOVESPEED = 0.2;

		player_image=ImageIO.read(new File("player.png"));
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
		g.drawImage(player_image, getX(), getY(), getWidth(), getHeight(), null);
		if(dead)
			g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
}
