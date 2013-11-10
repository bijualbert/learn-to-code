import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.navinf.learntocode.LearnToCode;

 
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	private static final int FPS = 1000 / 36;
	private int ticks;
	
	public static GamePanel me;
	
	public Player player;
	
	public static Component codeBox;
	public static JFrame parentWindow;
	
	public static void main(String[] args) throws IOException
    {
    	
		parentWindow = new JFrame("Learn Programming v1");
    	
    	me = new GamePanel();
    	me.addMouseListener(new MouseAdapter() {
    		@Override public void mousePressed(MouseEvent e){
    			me.requestFocusInWindow();
    		}
		});
    	
    	parentWindow.getContentPane().add(me);
    	//parentWindow.getContentPane().addKeyListener(me);
    	
    	parentWindow.setSize(800 , 600);
        parentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        me.setPreferredSize(new Dimension(800, 450));
        
        final LearnToCode ltc = new LearnToCode();
        codeBox = ltc.frame.getComponent(0);
        codeBox.setPreferredSize(new Dimension(800, 150));
        parentWindow.add(codeBox,BorderLayout.SOUTH);
        codeBox.setVisible(false);
        new Thread(){public void run(){
        	try {
				ltc.compileLoop();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }}.start();
        
        parentWindow.setVisible(true);
    }
	
	public ArrayList<Element> elements = new ArrayList<Element>();
	
	private BufferedImage background;
	AffineTransformOp reflect;
    
	//private Ball e1;

	public GamePanel() throws IOException {
		background=ImageIO.read(new File("background.jpg"));
//		AffineTransform scale = new AffineTransform();
//		scale.scale(0.4, 0.4);
//		//scale.
		
		AffineTransform tr = AffineTransform.getScaleInstance(-1, 1);
		tr.translate(-background.getWidth(null), 0);
		reflect = new AffineTransformOp(tr, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		
		//elements.add(e1 = new Ball(10, 10, 32, 32));
		player = new Player(20, 20, 100, elements);
		elements.add(player);
		
		
		
		elements.add(new Obstacle(0, 300, 5000, 30, elements));
		
		elements.add(new Obstacle(200, 250, 50, 50, elements));
		
		
		
		elements.add(new Obstacle(1500, 150, 50, 150, elements));
		
		Obstacle door1 = new Obstacle(400, 150, 50, 150, elements);
		
		elements.add(door1);
		
		Shooter shooter = new Shooter(700, 200, 50, elements);
		shooter.minX = 500;
		shooter.maxX =  1000;
		shooter.restrict = true;
		
		elements.add(shooter);
		
		Talker talker = new Talker(200, 150, 50, elements);
		
		elements.add(talker);

		
		setFocusable(true);
		addKeyListener(this);
		
		new Thread(this).start();
	}
	
    public void run() {
    	// Remember the starting time
    	long tm = System.currentTimeMillis();

        while(true){
            update();
            repaint();
            ticks++;

            try {
                tm += FPS;
                Thread.sleep(Math.max(0, tm - System.currentTimeMillis()));
            }
            catch(InterruptedException e)
            {
            	System.err.println(e);
            }
        }
    }
	
    public void update(){
    	
    	for(int i = 0; i < elements.size(); i++){
    		elements.get(i).update();
    	}
		
    }
    
	public void paint(Graphics f){
		super.paint(f);
		Graphics2D g = (Graphics2D)f;
		g.setColor(Color.WHITE);
		
		
		
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		
		g.translate(-player.getX() + getWidth()/2 -200, 0);
		
		for (int i = 0; i <= 2; i += 2) {
			g.drawImage(background, null, -250 + i * background.getWidth(), 0);
			g.drawImage(background, reflect, -250 + (i + 1) * background.getWidth(), 0);
		}
		
		Iterator<? extends Element> iterator = elements.iterator();
		
		while(iterator.hasNext())
			iterator.next().draw(g);
		
		g.setColor(Color.red);
		g.drawString(""+player.grounded, 100, 100);

		g.drawString(""+ticks, 100, 110);
		g.drawString(""+player.getX(), 100, 120);
		g.drawString(""+player.getY(), 100, 130);
		
		player.draw(g);
		g.translate(player.getX() - getWidth()/2 + 200, 0);
		
		g.setColor(Color.BLACK);
		g.fillRect(30, 30, 100, 15);
		
		g.setColor(Color.RED);
		g.fillRect(30, 30, player.getHealth(), 15);
		
		
		
		
	}
	
	public void keyPressed(KeyEvent e) {

	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	       player.moveLeft(true);
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	        player.moveRight(true);
	    }
	    
	    if (key == KeyEvent.VK_UP) {
	        player.jump();
	    }
	    
	    if (key == KeyEvent.VK_SPACE) {
	    	Talker talker = new Talker(0, 0, 0, elements);
	    	for(Element el: elements){
	    		if(el instanceof Talker){
	    			talker = (Talker)el;
	    		}
	    	}
	    	if(player.distanceTo(talker) < 100){
	    		codeBox.setVisible(true);
	    		parentWindow.pack();
	    		parentWindow.setSize(800 , 600);
	    		codeBox.requestFocusInWindow();
	    	}
	    	
	    }
	}
	
	public void keyReleased(KeyEvent e) {

	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	       player.moveLeft(false);
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	        player.moveRight(false);
	    }
	    
	    
	}
	
	public void keyTyped(KeyEvent e) {

	}
}