import java.util.ArrayList;
import java.awt.*;

public class Shooter extends Enemy
{
	public Shooter( int x, int y, int health, ArrayList<Element> elements )
	{
		super( x, y, health, elements );
	}
	
	public void action(  )
	{
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}

