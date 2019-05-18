package gameobjects;


import factories.ImagesFactory;
import javafx.scene.image.Image;

public abstract class GameObject {

	protected boolean hasMovedOffScreen;
	protected boolean isSliced;
	protected Image[] images;
	protected int number;

	public int radius;

	protected ImagesFactory bufferedImagesFactory;

	public GameObject() {
		super();
		this.bufferedImagesFactory = ImagesFactory.getInstance();
		isSliced = false;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public ImagesFactory getBufferedImagesFactory() {
		return bufferedImagesFactory;
	}

	public void setBufferedImagesFactory(ImagesFactory bufferedImagesFactory) {
		this.bufferedImagesFactory = bufferedImagesFactory;
	}

	public boolean isSliced() {
		return isSliced;
	}

	public boolean isHasMovedOffScreen() {
		return hasMovedOffScreen;
	}

	public void setHasMovedOffScreen(boolean hasMovedOffScreen) {
		this.hasMovedOffScreen = hasMovedOffScreen;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setSliced(boolean isSliced) {
		this.isSliced = isSliced;
	}

	public void setImages(Image[] images) {
		this.images = images;
	}

	public boolean hasMovedOffScreen() {
		return hasMovedOffScreen;
	}

	public void slice() {
		isSliced = true;
	}

	public void move(double time) {
		// xLocation=haga*time; i think leeha 3elaqa bel projectiles
		// yLocation=haga*time;
	}

	public Image[] getImages() {
		return images;
	}

}
