import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class carObject {
	int x;
	int y;
	int width;
	int height;
	int speed;
	Rectangle collisionBox;

	BufferedImage carImage;

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}

	carObject(int x, int y, int width, int height, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;

		collisionBox = new Rectangle(x, y, width, height);

		try {
			carImage = ImageIO.read(this.getClass().getResourceAsStream("carImage.png"));
		} catch (IOException e) {
			System.err.println("Error in loading carImage");
		}
	}

	public void paint(Graphics g) {
		g.drawImage(carImage, x, y, width, height, null);
	}

	public void moveUp() {
		y -= speed;
	}

	public void moveDown() {
		y += speed;
	}

	public void moveLeft() {
		x -= speed;
	}

	public void moveRight() {
		x += speed;
	}

	public void Update() {
		if (x <= 0) {
			x = 0;
		}
		if (x + width >= 500) {
			x = 500 - width;

		}
		if (y + height >= 700) {
			y = 700 - height;

		}
		if (y <= 0) {
			y = 0;

		}

		collisionBox.setBounds(x, y, width, height);

	}

}
