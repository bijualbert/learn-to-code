import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Character
{
	public enum Facing { LEFT, RIGHT }
	Facing face = Facing.LEFT;
	
	public Enemy( int x, int y, int health, ArrayList<Element> elements )
	{
		super( x, y, health, elements );
	}
	
	public void movement(Player player)
	{
	
		/*while()
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
		}*/
	}

	public void shoot()
	{}

	public void draw(Graphics g){
		
	}
	
	public void update(){
		
	}
	
	public void movement(){}
}
