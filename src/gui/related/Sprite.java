package gui.related;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	private Image image;
	private double positionX;
	private double positionY;
	private double width;
	private double height;
	private double deltaX;
	private double deltaY;
	public Sprite(Image image) {
		this.image = image;
		positionX = 0;
		positionY = 400;
		width = image.getWidth();
		height = image.getHeight();
	}
	public void setPositinoX(double positionX) {
		this.positionX=positionX;
		
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	public double getPositionX() {
		return positionX;
	}
	public double getPositionY() {
		return positionY;
	}
	public void render(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY);
	}
	public void updateLocations() {
		this.positionX += deltaX;
		this.positionY += deltaY;
		//el mafrod hashof law howa fo2 hanazelo w law ta7t hazawedo wait for it
		
	}
	
	private Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}

	public boolean intersects(Sprite sprite) {
		return this.getBoundary().intersects(sprite.getBoundary());
	}
	
}
