package com.navinf.learntocode;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shooter extends Enemy
{
	private BufferedImage image;

	public Shooter( int x, int y, int health, ArrayList<Element> elements )
	{
		super( x, y, health, elements );
		try {
			image=ImageIO.read(new File("tank.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(){
		super.update();
		if(distanceTo(player) < 50 && ticker % 10 == 0){
			player.damage(10);
		}
	}
	
	public void action(  )
	{
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.drawImage(image, getX()-4, getY()-1, getWidth()+4, getHeight()+4, null);
		//g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}

