import java.awt.*;

public class Enemy extends Character
{
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
>>>>>>> d7e0c2ccda138a138df343dea884e40c613c6f69
	
	public Enemy(int x, int y, int health){
		super( x, y, health );
	}
	
	
	public void movement()
>>>>>>> d7e0c2ccda138a138df343dea884e40c613c6f69
	{
		
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
	public void shoot()
	{}
	
=======
=======
>>>>>>> d7e0c2ccda138a138df343dea884e40c613c6f69
	public void draw(Graphics g){
		
	}
	
	public void update(){
		
	}
<<<<<<< HEAD
>>>>>>> d7e0c2ccda138a138df343dea884e40c613c6f69
=======
>>>>>>> d7e0c2ccda138a138df343dea884e40c613c6f69
}
