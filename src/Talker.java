import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Talker extends Character
{
	public Talker( int x, int y, int health, ArrayList<Element> elements )
	{
		super( x, y, health, elements );
	}
	
	public void update(){
		super.update();
		double dist = distanceTo(player);
		System.out.println(dist);
		
	}
	
	public void action(  )
	{
		
	}
	
	public void draw(Graphics g){
		g.setColor(new Color(139, 69, 19));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
