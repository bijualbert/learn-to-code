import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import com.navinf.learntocode.LearnToCode;

 
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	private static final int FPS = 1000 / 36;
	public static GamePanel me;
	
	Player player;
	
	public static void main(String[] args)
    {
    	JFrame parentWindow = new JFrame("Learn Programming v0");
    	
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
        
        final LearnToCode ltc = new LearnToCode();
        Component codeBox = ltc.frame.getComponent(0);
        codeBox.setPreferredSize(new Dimension(800, 150));
        parentWindow.add(codeBox,BorderLayout.SOUTH);
        new Thread(){public void run(){
        	try {
				ltc.compileLoop();
			} catch (ClassNotFoundException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }}.start();
        
        parentWindow.setVisible(true);
    }
	
	public ArrayList<Element> elements = new ArrayList<Element>();
    
	//private Ball e1;

	public GamePanel() {
		//elements.add(e1 = new Ball(10, 10, 32, 32));
		player = new Player(20, 20, 100, elements);
		elements.add(player);
		
		elements.add(new Obstacle(0, 300, 500, 30, elements));
		
		elements.add(new Obstacle(200, 250, 50, 50, elements));

		
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
    	//Iterator iterator = elements.iterator();
    	
    	for(int i = 0; i < elements.size(); i++){
    		elements.get(i).update();
    	}
		
    }
    
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		Iterator iterator = elements.iterator();
		
		while(iterator.hasNext())
			((Element) iterator.next()).draw(g);
		
		g.setColor(Color.red);
		g.drawString(""+player.movingLeft, 100, 100);

		g.drawString(""+player.movingRight, 100, 110);
		g.drawString(""+player.getX(), 100, 120);
		g.drawString(""+player.getY(), 100, 130);
	}
	
	public void keyPressed(KeyEvent e) {

	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	       player.moveLeft(true);
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	        player.moveRight(true);
	    }
	    
	    if (key == KeyEvent.VK_SPACE) {
	        player.jump();
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