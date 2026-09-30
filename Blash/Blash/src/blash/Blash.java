package blash;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import blash.entity.Tank;
import blash.inputs.KeyInput;
import blash.inputs.MouseMotions;
import blash.level.Map;

public class Blash extends JPanel implements Runnable{
	public static int WIDTH = 800;
	public static int HEIGHT = 500;
	private boolean running = false;
	private int studs = 3;
	
	private BufferedImage tank, Map1;
	
	private static MouseMotions mml = new MouseMotions();
	private static KeyInput keyIn = new KeyInput();
	
	public void init(String Maptxt, String tanktxt) {
		try {
			tank = ImageIO.read(getClass().getResourceAsStream(tanktxt));
			Map1 = ImageIO.read(getClass().getResourceAsStream(Maptxt));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		new Thread(this).start();
		running = true;
		init("../Map.png", "../TankTxtu.png");
	}
	
	public void run() {
		double interval = 1000000000/60;
		double delta = 0;
		long last = System.nanoTime();
		long now;
		
		while(running) {
			now = System.nanoTime();
			
			delta += (now - last) / interval;
			
			last = now;
			
			if (delta >= 1) {
				
				update();
				repaint();
				
				delta--;
			}
		}
	}
	
	public void update() {
		
		if (keyIn.dl == true) {
			Map.mx += studs;
			init("../Map.png", "../TankTxtuL.png");
		} else if (keyIn.dr == true) {
			Map.mx -= studs;
		} else if (keyIn.du == true) {
			Map.my += studs;
			init("../Map.png", "../TankTxtu.png");
		} else if (keyIn.dd == true) {
			Map.my -= studs;
		}
	}
	
	public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D)g;
		 
		 g2.drawImage(Map1, Map.mx, Map.my, WIDTH * 2, HEIGHT * 2, null);
		 g2.drawImage(tank, Tank.tx, Tank.ty, 50, 50, null);
		 g2.dispose();
	}
	
	public static void main(String[] args) {
		Blash game = new Blash();
		game.setBackground(Color.black);
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setFocusable(true);
		game.addKeyListener(keyIn);
		
		JFrame frame = new JFrame("Blash");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
}
