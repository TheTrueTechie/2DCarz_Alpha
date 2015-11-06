
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class startScreen extends JPanel implements ActionListener, KeyListener {
	boolean Up;
	boolean Down;
	boolean Right;
	boolean Left;
	JFrame mainFrame;
	Timer time;

	int bkg1y;
	int bkg2y;

	carObject car;
	//roadHazard cone;
	ArrayList<roadHazard> HazardList;
	
	
	BufferedImage backgroundImage;
	BufferedImage backgroundImage2;
	
	public static void main(String[] args) {
		startScreen a = new startScreen();
	}

	startScreen() {
		car = new carObject(250, 250, 30, 60, 10);
	//	cone = new roadHazard(250, 0, 50, 50, 20);
		HazardList = new ArrayList<roadHazard>(); 
		
		bkg1y = 0;
		bkg1y = -700;

		try {
			backgroundImage = ImageIO.read(this.getClass().getResourceAsStream("roadImage.jpg"));
			backgroundImage2 = ImageIO.read(this.getClass().getResourceAsStream("roadImage.jpg"));
		} catch (IOException e) {
			System.err.println("Error in loading roadImage");

		}

		JFrame mainFrame = new JFrame();

		mainFrame.add(this);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("Carz Alpha");
		mainFrame.setSize(500, 700);
		mainFrame.setVisible(true);

		mainFrame.addKeyListener(this);

		time = new Timer(1000 / 60, this);
		time.start();
	}

	public void paint(Graphics g) {
		g.drawImage(backgroundImage, 0, bkg1y, 500, 700, null);
		g.drawImage(backgroundImage2, 0, bkg2y, 500, 700, null);
		
		for (int i = 0; i < HazardList.size(); i++) {
			roadHazard rH = HazardList.get(i);
			rH.paint(g);
		}
		
		if (bkg1y >= 700) {
			bkg1y = -700;
		}
		
		if (bkg2y >= 700) {
			bkg2y = -700;
		}

		bkg1y += 20;
		bkg2y += 20;
		
		car.paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		keyController();

		Update();
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Up = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Down = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Right = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Up = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Down = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Left = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Right = false;
		}

	}

	public void keyController() {
		if (Up) {
			car.moveUp();
		//	System.out.println("Up");
		}
		if (Down) {
			car.moveDown();
			//System.out.println("Down");
		}
		if (Left) {
			car.moveLeft();
		//	System.out.println("Left");
		}
		if (Right) {
			car.moveRight();
		//	System.out.println("Right");
		}

	}

	public void coneGenerator() {
		Random r = new Random(); 
		
		if (r.nextInt(50)==0) {
			roadHazard cone = new roadHazard(r.nextInt(500), 0, 50, 50, 20); 
			HazardList.add(cone);
		}
		
		
	}
	
	public void Update() {
		for (int i = 0; i < HazardList.size(); i++) {
			roadHazard rH = HazardList.get(i);
			rH.Update();
			if (rH.getCollisionBox().intersects(car.getCollisionBox())) {
				System.out.println("Hit!");
			}
		}
		
		car.Update();
		coneGenerator(); 
	}

}
