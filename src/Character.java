public abstract class Character extends Element
{
	private int health, x, y;
	
	public Character()
	{
		setHealth( 100 );
		setX( 0 );
		setY( 0 );
	}
	public Character( int a, int b, int c )
	{
		setX( a );
		setY( b );
		setHealth( c );
	}
	
	public void setHealth( int a )
	{
		health = a;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setY( int a )
	{
		y = a;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX( int a )
	{
		x = a;
	}
	
	public int getX()
	{
		return x;
	}
	
	public abstract void movement();
}
