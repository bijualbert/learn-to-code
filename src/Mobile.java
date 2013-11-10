import java.util.ArrayList;


public abstract class Mobile extends Element 
{

	private double vx, vy;
	private final double GRAVITY = 0.3;
	protected boolean grounded;
	protected final double MAXVX = 5;
	protected final double MOVESPEED = 0.2;
	protected final double JUMPSPEED = 5;
	public boolean movingRight;
	public boolean movingLeft;
	
	public Mobile( int x, int y, int width, int height, ArrayList<Element> elements )
	{
		super( x, y, width, height, elements);
	}
	
	public double getVX(){
		return vx;
	}
	
	public void setVX(double vx){
		this.vx = vx;
	}
	
	public double getVY(){
		return vy;
	}
	
	public void setVY(double vy){
		this.vy = vy;
	}
	
	public void moveLeft(boolean val){
		movingLeft = val;
	}
	
	public void moveRight(boolean val){
		movingRight = val;

	}
	
	public void moveRight()
	{
		setX( getX() + getVX() );
	}
	
	public void moveLeft()
	{
		setX( getX() - getVX() );
	}
	
	public void jump(){
		if(grounded)
			setVY(getVY() - JUMPSPEED);
		
		grounded = false;
	}
	
	public void update(){
		
		if(movingLeft){
			if(getVX() > -MAXVX){
				setVX(getVX() - MOVESPEED);
			}
		}
		
		if(movingRight){
			if(getVX() < MAXVX){
				setVX(getVX() + MOVESPEED);
			}
		}
		
		if(!movingLeft && !movingRight){
			int dir = getVX() > 0 ? 1 : -1;
			setVX(getVX() - dir * 0.2);
		}
		
		
		
		
		
		for(int j = 0; j < elements.size(); j++){
			if ((elements.get(j) != this)){
				switch (this.isColliding(elements.get(j))) {
					
					case 0: 
						//System.out.println("S'all good");
						break;
					
					case 2:
						setY(getY() - (getVY() > 0 ? -1 : 1));
						setVY(0);
						break;
					case 1:
						setX(getX() + (getVX()) > 0 ? -1 : 1);
						setVX(-getVX()*0.5);
						break;
					case 3:
						setX(getX() + (getVX()) > 0 ? -1 : 1);
						setVX(-getVX()*0.5);
						break;
					case 4:
						grounded = true;
						setY(getY() - (getVY() > 0 ? -1 : 1));
						setVY(0);
						break;
				
				}
				
				
			}
		}
		
		setY( getY() + 1 );
		
		/*for(int j = 0; j < elements.size(); j++){
			if (elements.get(j) != this && this.isColliding(elements.get(j)) != 0){
				
				grounded = true;
			}else{
				grounded = false;
			}
		}*/
		
		setY( getY() - 1 );
		
		if(getVY() < 10 && !grounded ){
			setVY( getVY() + GRAVITY );
		}
		
		setX( getX() + (int)getVX() );
		setY( getY() + (int)getVY() );
	}
}
