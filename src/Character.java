
import java.awt.*;


public abstract class Character extends Mobile
{
	private int health;
	
	public Character()
	{

		super(0,0,1,1);
		setHealth( 100 );
	}
	
	public Character( int x, int y, int health )
	{
		super(x,y,32,32);
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

	
	
	public abstract void movement();
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
