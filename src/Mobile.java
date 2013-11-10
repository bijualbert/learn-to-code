import java.util.ArrayList;


public abstract class Mobile extends Element 
{

	private double vx, vy;
	private final double GRAVITY = 0.1;
	private double lastCollided;
	protected boolean grounded;
	protected final double MAXVX = 5;
	protected final double MOVESPEED = 0.2;
	protected boolean movingRight;
	protected boolean movingLeft;
	
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
	
<<<<<<< HEAD
	public void moveLeft(boolean val){
		movingLeft = val;
	}
	
	public void moveRight(boolean val){
		movingRight = val;
=======
	public void moveRight()
	{
		setX( getX() + getVX() );
	}
	
	public void moveLeft()
	{
		setX( getX() - getVX() );
>>>>>>> afd7062cff46659b97ad1ec62b002da173cb34b9
	}
	
	public void update(){
		
		if(movingLeft){
			if(getVX() > -MAXVX){
				setVX(getVX() - MOVESPEED);
			}
		}else{
			if(Math.abs(getVX()) < 0.1){
				setVX(getVX() + MOVESPEED);
			}
		}
		
		if(movingRight){
			if(getVX() < MAXVX){
				setVX(getVX() + MOVESPEED);
			}
		}else{
			if(Math.abs(getVX()) < 0.1){
				setVX(getVX() - MOVESPEED);
			}
		}
		
		setX( getX() + (int)getVX() );
		setY( getY() + (int)getVY() );
		
		
		
		for(int j = 0; j < elements.size(); j++){
			if (this.isColliding(elements.get(j)) && elements.get(j) != this){
				setX(getX() - getVX());
				setY(getY() - getVY());
				setVY(0);
				
				if(System.currentTimeMillis() - lastCollided < 50){
					setVY(0);
				}
				
				grounded = true;
			}
		}
		
		if(getVY() < 10 && !grounded ){
			setVY( getVY() + GRAVITY );
		}
	}
}
