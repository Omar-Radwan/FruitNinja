

import java.awt.image.BufferedImage;

public abstract class GameObject implements iGameObject {

	protected int xLocation;
	protected int yLocation;
	protected int maxHeight;
	protected int initialVelocity;
	protected int fallingVelocity;
	protected boolean hasMovedOffScreen;
	
	protected boolean isSliced;
	protected BufferedImage[] bufferedImages;

	protected BufferedImagesFactory bufferedImagesFactory;

	public GameObject() {
		super();
		this.bufferedImagesFactory = BufferedImagesFactory.getInstance();
		isSliced = false;
	}

	@Override
	public int getXlocation() {

		return xLocation;
	}

	@Override
	public int getYlocation() {

		return yLocation;
	}

	@Override
	public int getMaxHeight() {
		return maxHeight;
	}

	@Override
	public int getInitialVelocity() {
		return initialVelocity;
	}

	@Override
	public int getFallingVelocity() {
		return fallingVelocity;
	}

	@Override
	public boolean isSliced() {
		return isSliced;
	}

	@Override
	public boolean hasMovedOffScreen() {
		return hasMovedOffScreen;
	}

	@Override
	public void slice() {
		isSliced = true;
	}

	@Override
	public void move(double time) {
		// xLocation=haga*time; i think leeha 3elaqa bel projectiles
		// yLocation=haga*time;
	}

	@Override
	public BufferedImage[] getBufferedImages() {
		return bufferedImages;
	}

}
