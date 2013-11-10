
public abstract class Character extends Mobile
{
	private int health;
	
	public Character()
	{
		super( 0, 0, 0, 0, false );
		setHealth( 100 );
	}
	public Character( int a, int b, int c, int d, int health, boolean moving )
	{
		super( a, b, c, d, moving );
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
