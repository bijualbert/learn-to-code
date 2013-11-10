
public class Player extends Character
{
	public Player( int x, int y, int width, int height, int health, boolean moving )
	{
<<<<<<< HEAD
		super( 0, 0, health );
	}
	
	public Player( int x, int y, int health )
	{
		super( x, y, health );
=======
		super( x, y, width, height, health, moving );
>>>>>>> 5c233b43ff715007683b63a4dd9f295ad1515cf3
	}
	
	public void movement()
	{
		
	}
	
}
