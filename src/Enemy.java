import java.awt.*;

import java.util.ArrayList;

import java.util.*;

public class Enemy extends Character
{
	public enum Facing { LEFT, RIGHT }
	Facing face = Facing.LEFT;
	Random random = new Random();
	Player player;
	
	public Enemy( int x, int y, int health, ArrayList<Element> elements )
	{
		super( x, y, health, elements );
		
		for( int i = 0; i < elements.size() - 1; i++ )
		{
			if( elements.get( i ) instanceof Player )
				player = (Player) elements.get( i );
		}
	}
	
	public void movement()
	{
		int choice = random.nextInt( 3 ) + 1;
		something( player );
		setVX( 25 );
		
		switch( choice )
		{
		case 1:
			if( face == Facing.LEFT )
				moveLeft( true );
			else
				moveRight( true );
			break;
			
		case 2:
			if( face == Facing.LEFT )
				face = Facing.RIGHT;
			else
				face = Facing.LEFT;
			break;
			
		case 3:
			setVX( 0 );
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
	
}
