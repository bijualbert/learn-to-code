package com.navinf.learntocode;
import java.util.ArrayList;


public abstract class Mobile extends Element 
{

	private double vx, vy;
	private final double GRAVITY = 0.3;
	public boolean grounded;
	protected final double MAXVX = 5;
	protected double MOVESPEED = 0.05;
	protected final double JUMPSPEED = 10;
	public boolean movingRight;
	public boolean movingLeft;
	protected int ticker;
	
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
		
		ticker++;
		
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
		
		
		if(getVY() < 10 && !grounded ){
			setVY( getVY() + GRAVITY );
		}
		
		
		for(int j = 0; j < elements.size(); j++){
			
			if ((elements.get(j) != this) && !(elements.get(j) instanceof Mobile)){
				
				
				
				
				boolean[] array2 = {false, false, false, false, false};
				
				//setY( getY() + 5 );
				
				boolean[] array = this.isColliding(elements.get(j));
				
				//if(array[0]){
				//	System.out.println("unground");
				//	grounded = false;
				//}
				
				//setY( getY() - 5 );
				
				array = this.isColliding(elements.get(j));
				
				while(array[1] || array[2] || array[3] || array[4]){
					//System.out.println("bleh");
					array = this.isColliding(elements.get(j));
					if(array[1]){
						//System.out.println("1");
						setX(getX() - 1 );
						array2[1] = true;
					}
					
					if(array[2]){
						//System.out.println("2");
						setY(getY() + 1 );
						array2[2] = true;
					}
					
					if(array[3]){
						//System.out.println("3");
						setX(getX() + 1);
						array2[3] = true;
					}
					
					if(array[4]){
						//System.out.println("4");
						setY(getY() - 1);
						array2[4] = true;
					}
					
					if(array[4] && array[2]){
						setY(getY() - 1);
					}
					
					if(array[1] && array[3]){
						
						if(getVX() > 0){
							setX(getX() - 1);
						}else if(getVX() < 0){
							setX(getX() + 1);
						}else{
							
						}
					}
					
					
				}
				
				
				
					if(array2[1]){
						//System.out.println("ONE");
						setVX(-getVX()*0.5);
						setVX(0);
					}
					if(array2[2]){
						//System.out.println("TWO");
						setVY(0);
					}
					if(array2[3]){
						//System.out.println("THREE");
							setVX(0);
					}
					if(array2[4]){
						grounded = true;
						setVY(0);
					}
				
				
			}
		}
		
		setY( getY() + 3 );
		int any = 0;
		for(int j = 0; j < elements.size(); j++){
			if (elements.get(j) != this && !this.isColliding(elements.get(j))[0]){
				
				any++;
			}
		}
		if(any > 0){
			grounded = true;
		}else{
			grounded = false;
		}
		
		setY( getY() - 3 );
		
		
		
		setX( getX() + (int)getVX() );
		setY( getY() + (int)getVY() );
	}
}
