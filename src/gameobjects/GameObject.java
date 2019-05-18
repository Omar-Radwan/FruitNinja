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
