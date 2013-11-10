import java.awt.*;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.*;
>>>>>>> a3fda7705383422f5dcdf2e3d9d587ab4f747e9e

public class Enemy extends Character
{
	public enum Facing { LEFT, RIGHT }
	Facing face = Facing.LEFT;
	Random random = new Random();
	
	public Enemy( int x, int y, int health, ArrayList<Element> elements )
	{
		super( x, y, health, elements );
	}
	
	public void movement()
	{
<<<<<<< HEAD
	
		/*while()
=======
		int choice = random.nextInt( 3 ) + 1;
		something( player );
		
		switch( choice )
>>>>>>> a3fda7705383422f5dcdf2e3d9d587ab4f747e9e
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
				
<<<<<<< HEAD
			default:
				break;
			}
		}*/
=======
		default:
			break;
		}
>>>>>>> a3fda7705383422f5dcdf2e3d9d587ab4f747e9e
	}

	private void shoot()
	{}
	
	public void update(){
		
	}
	
	public void movement(){}
}
