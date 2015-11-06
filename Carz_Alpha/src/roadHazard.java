import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class roadHazard {
	int x;
	int y;
	int width;
	int height;
	int speed;

	BufferedImage roadHazardImage;

	Rectangle collisionBox;

	roadHazard(int x, int y, int width, int height, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.collisionBox = new Rectangle(x, y, width, height); 
		try {
			roadHazardImage = ImageIO.read(this.getClass().getResourceAsStream("coneImage.png"));
		} catch (IOException e) {
			System.err.println("Error in loading coneImage");
		}
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}

	public void paint(Graphics g) {
		g.drawImage(roadHazardImage, x, y, width, height, null);
	}

	public void Update() {
		y += 20;
		collisionBox.setBounds(x, y, width, height);
	}
}
