
public class Enemy extends Character
{
	public enum Facing { LEFT, RIGHT }
	Facing face = Facing.LEFT;
	
	public Enemy( int x, int y, int width, int height, int health, boolean mobile )
	{
		super( x, y, width, height, health, mobile );
	}
	
	public void movement(Player player)
	{
	
		while()
		{
			switch( face )
			{
			case LEFT:
			{
			
				if( player.getX() < getX() && getX() - player.getX() <= 200 )
					shoot();
		 	}
		 		break;
		 		
		 	case RIGHT:
		 	{
		 		if( player.getX() > getX() && player.getX() - getX() <= 200 )
					shoot();
			}
				break;
				
			default:
				break;
			}
		}
	}
	
	public void patrol(  )
	{
		
	}
	
	public void shoot()
	{}
	
}
