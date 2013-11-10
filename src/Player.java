import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Player extends Character
{
	
	public Player( int x, int y, int health, ArrayList<Element> elements )
	{
		super( x, y, health, elements );
		MOVESPEED = 0.2;

	}
	
	public void movement()
	{
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
}
