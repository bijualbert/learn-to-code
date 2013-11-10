package com.navinf.learntocode;
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

 
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	private static final int FPS = 1000 / 36;
	private int ticks;
	
	public static GamePanel me;
	
	public Player player;
	
	public static PrintLogger pl;
	public static PrintLogger el;
	public static Component codeBox;
	public static JFrame parentWindow;
	public static Talker computer;
	
	public static void main(String[] args) throws IOException
    {
    	System.setOut(pl=new PrintLogger(System.out));
    	System.setErr(el=new PrintLogger(System.err));
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
	private BufferedImage trophy;
	AffineTransformOp reflect;
    
	//private Ball e1;

	public GamePanel() throws IOException {
		background=ImageIO.read(new File("background.jpg"));
		trophy=ImageIO.read(new File("trophy.png"));
//		AffineTransform scale = new AffineTransform();
//		scale.scale(0.4, 0.4);
//		//scale.
		
		AffineTransform tr = AffineTransform.getScaleInstance(-1, 1);
		tr.translate(-background.getWidth(null), 0);
		reflect = new AffineTransformOp(tr, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		
		//elements.add(e1 = new Ball(10, 10, 32, 32));
		player = new Player(-300, 20, 100, elements);
		elements.add(player);
		
		
		
		elements.add(new Obstacle(-1000, 300, 6000, 30, elements));
		
		elements.add(new Obstacle(200, 250, 50, 50, elements));
		
		
		
		//elements.add(new Obstacle(1500, 150, 50, 150, elements));
		
		Obstacle door1 = new Obstacle(400, 0, 50, 300, elements);
		
		elements.add(door1);
		
		Shooter shooter = new Shooter(600, 200, 50, elements);
		shooter.minX = 500;
		shooter.maxX =  1000;
		shooter.restrict = true;
		
		Shooter shooter2 = new Shooter(700, 200, 50, elements);
		shooter2.minX = 500;
		shooter2.maxX =  1000;
		shooter2.restrict = true;
		
		Shooter shooter3 = new Shooter(900, 200, 50, elements);
		shooter3.minX = 500;
		shooter3.maxX =  1000;
		shooter3.restrict = true;
		
		elements.add(shooter);
		elements.add(shooter2);
		elements.add(shooter3);
		
		Talker talker = new Talker(200, 150, 50, "I am a voice-activated computer -- Identify yourself.", elements);
		
		elements.add(talker);
		
		
		Obstacle door2 = new Obstacle(1500, 0, 50, 300, elements);
		
		elements.add(door2);
		
		Talker talker2 = new Talker(1350, 150, 50, "I have a 4-digit passcode.", elements);
		talker2.setAttached(door2);
		
		elements.add(talker2);
		
		computer = talker2;
		
		elements.add(new Obstacle(2000, 0, 50, 300, elements));
		elements.add(new Obstacle(2050, 0, 400, 50, elements));
		elements.add(new Obstacle(2450, 0, 50, 300, elements));
		
		//Dummy talkers
		elements.add(new Talker(2055, 200, 50, "How can you get into the box using code?", elements));
		elements.add(new Talker(1800, 150, 50, "My current coordinates are: 1800, 250", elements));
		elements.add(new Talker( 1200, 150, 50, "You need to enter the passcode into \"computer.unlock(i)\"", elements));
		elements.add(new Talker( 735, 150, 50, "Use a for loop: \"for(int i = 0; i < 10000; i++ )\"", elements));

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
    	int index = 0;
    	for(int i = 0; i < elements.size(); i++){
    		if(elements.get(i) instanceof Obstacle && ((Obstacle)elements.get(i)).getX() == 400){
    			index = i;
    		}
    	}
    	if(pl.last != null && index > 0){
    		elements.remove(index);
    	}
    	
    	
		
    }
    
	public void paint(Graphics f){
		super.paint(f);
		Graphics2D g = (Graphics2D)f;
		g.setColor(Color.WHITE);
		
		
		
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		
		g.translate(-player.getX() + getWidth()/2 -200, 0);
		
		for (int i = 0; i <= 2; i += 8) {
			g.drawImage(background, null, -1000 + i * background.getWidth(), 0);
			g.drawImage(background, reflect, -1000 + (i + 1) * background.getWidth(), 0);
		}
		
		Iterator<? extends Element> iterator = elements.iterator();
		
		while(iterator.hasNext())
			iterator.next().draw(g);
		
		g.setColor(Color.red);
		g.drawString(""+player.grounded, 100, 100);

		g.drawString(""+ticks, 100, 110);
		g.drawString(""+player.getX(), 100, 120);
		g.drawString(""+player.getY(), 100, 130);
		
		g.drawImage(trophy, 2200, 100, 100, 100, null);
		
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
	    	Talker talker1 = new Talker(0, 0, 0, "I am a voice-activated computer -- Identify yourself.", elements);
	    	for(Element el: elements){
	    		if(el instanceof Talker && ((Talker)el).str == "I am a voice-activated computer -- Identify yourself."){
	    			talker1 = (Talker)el;
	    		}
	    	}
	    	if(player.distanceTo(talker1) < 100){
	    		codeBox.setVisible(true);
	    		parentWindow.pack();
	    		parentWindow.setSize(800 , 600);
	    		me.requestFocusInWindow();
	    		elements.add(new Talker(-20, 150, 50, "Fill in the quotes: System.out.println(\" \");", elements));

	    	}
	    	
	    	
	    	Talker talker2 = new Talker(0, 0, 0, "I am a computer with a 4 digit passcode.", elements);
	    	for(Element el: elements){
	    		if(el instanceof Talker && ((Talker)el).str == "I am a computer with a 4 digit passcode."){
	    			talker1 = (Talker)el;
	    		}
	    	}
	    	if(player.distanceTo(talker1) < 100){
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