package main;

import javax.swing.JPanel;

import creature.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{

	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize*scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize*maxScreenCol;
	final int screenHeight = tileSize* maxScreenRow;
	int FPS = 60;
	
	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	
	Player player = new Player(this, keyHandler);
	
	
	
	int playerX = 100;
	int playerY= 100;
	int speed = 4;
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		
	}
	
	public void startGame() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
		
	}



	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta --;
				
			}
			
		}
		
		
	}
	
	public void update() {
		
		player.update();
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		;player.draw(g2d);
		g2d.dispose();
		
	}
	
}
