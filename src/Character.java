
import java.awt.*;
import java.util.ArrayList;


public abstract class Character extends Mobile
{
	private int health;
	
	
	public Character( int x, int y, int health, ArrayList<Element> elements )
	{
		super(x,y,32,32, elements);
		setHealth(health);
	}
	
	
	public void setHealth( int a )
	{
		health = a;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public abstract void draw(Graphics g);

}
