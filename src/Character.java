<<<<<<< HEAD
import java.awt.*;
=======
>>>>>>> 5c233b43ff715007683b63a4dd9f295ad1515cf3

public abstract class Character extends Mobile
{
	private int health;
	
	public Character()
	{
<<<<<<< HEAD
		super(0,0,1,1);
=======
		super( 0, 0, 0, 0, false );
>>>>>>> 5c233b43ff715007683b63a4dd9f295ad1515cf3
		setHealth( 100 );
	}
<<<<<<< HEAD
	public Character( int x, int y, int health )
	{
		super(x,y,32,32);
=======
	public Character( int x, int y, int width, int height, int health, boolean moving )
	{
		super( x, y, width, height, moving );
>>>>>>> 5c233b43ff715007683b63a4dd9f295ad1515cf3
		setHealth( health );
	}
	
	public void setHealth( int a )
	{
		health = a;
	}
	
	public int getHealth()
	{
		return health;
	}
<<<<<<< HEAD
	
	
	public abstract void movement();
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
=======

	public abstract void movement();
>>>>>>> 5c233b43ff715007683b63a4dd9f295ad1515cf3
}
