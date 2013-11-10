import java.awt.*;

public class Enemy extends Character
{
	public Enemy( int x, int y, int width, int height, int health, boolean moving )
	{
<<<<<<< HEAD
		super(0,0, health );
=======
		super( x, y, width, height, health, moving );
>>>>>>> 5c233b43ff715007683b63a4dd9f295ad1515cf3
	}
	
	public Enemy(int x, int y, int health){
		super( x, y, health );
	}
	
	
	public void movement()
	{
		
	}
	
	public void draw(Graphics g){
		
	}
	
	public void update(){
		
	}
}
