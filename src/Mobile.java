
public abstract class Mobile extends Element 
{
	private double vx, vy;
	private final double GRAVITY = 0.1;
	
	public Mobile( int x, int y, int width, int height )
	{
		super( x, y, width, height );
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
	
	public void update(){
		setX( getX() + (int)getVX() );
		setY( getY() + (int)getVY() );
		if(getVY() < 10){
			setVY( getVY() + GRAVITY );
		}
	}
}
