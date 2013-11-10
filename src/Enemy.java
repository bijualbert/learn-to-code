import java.awt.*;
import java.util.*;

public class Enemy extends Character
{
	public enum Facing { LEFT, RIGHT }
	Facing face = Facing.LEFT;
	Random random = new Random();
	
	public Enemy( int x, int y, int health )
	{
		super( x, y, health );
	}
	
	public void movement()
	{
		int choice = random.nextInt( 3 ) + 1;
		something( player );
		
		switch( choice )
		{
		case 1:
			if( face == Facing.LEFT )
				moveLeft();
			else
				moveRight();
			break;
			
		case 2:
			if( face == Facing.LEFT )
				face = Facing.RIGHT;
			else
				face = Facing.LEFT;
			break;
			
		case 3:
			break;
			
		default:
			break;
		}
	}
	
	private void something( Player player )
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

	private void shoot()
	{}
	
	public void update(){
		
	}
}
