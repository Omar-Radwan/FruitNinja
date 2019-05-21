package gui.related;

import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {

	private Image image;
	private double positionX;
	private double positionY;
	private double width;
	private double height;
	private boolean changedImage;

	private ImageView img = new ImageView();
	private Random random = new Random();


	public Sprite() {
		positionX = 0;
		positionY = 0;
	}

	public Sprite(Image image, int number) {
		this.image = image;
		
		img.setImage(image);
		
		positionX = 0;
		positionY = 500;
		
		img.setX(0);
		img.setY(500);


		width = image.getWidth();
		height = image.getHeight();
		
		this.number = number;
		
		this.changedImage = false;
	}



	public void setPositinoX(double positionX) {
		img.setX(positionX);
	}

	public ImageView getImage() {
		img.setImage(image);
		img.setX(positionX);
		img.setY(positionY);
		return img;
	}

	public void setPositionY(double positionY) {

		img.setY(positionY);
	}

	public void setPosYOfMouse(double positionY) {
		this.positionY = positionY;
	}

	public void setPosXOfMouse(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionXOfMouse() {
		return positionX;
	}

	public double getPositionYOfMouse() {
		return positionY;
	}

	public double getPositionX() {
		return img.getX() + img.getTranslateX();
	}

	public double getPositionY() {
		return img.getY() + img.getTranslateY();
	}

	private Rectangle2D getBoundary() {
		return new Rectangle2D(img.getTranslateX() + positionX, img.getTranslateY() + positionY, width, height);
	}

	private Rectangle2D getboundaryOfMouse() {
		return new Rectangle2D(positionX, positionY, width, height);

	}

	public boolean intersects(Sprite sprite) {
		return this.getBoundary().intersects(sprite.getboundaryOfMouse());
	}

	public void setImage(Image img) {
		this.image = img;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isChangedImage() {
		return changedImage;
	}

	public void setChangedImage(boolean changedImage) {
		this.changedImage = changedImage;
	}

	public ImageView getImg() {
		return img;
	}

	public void setImg(ImageView img) {
		this.img = img;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	protected int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
