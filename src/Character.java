
public abstract class Character extends Mobile
{
	private int health;
	
	public Character()
	{
		super( 0, 0, 0, 0, false );
		setHealth( 100 );
	}
	public Character( int x, int y, int width, int height, int health, boolean moving )
	{
		super( x, y, width, height, moving );
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

	public abstract void movement();
}
