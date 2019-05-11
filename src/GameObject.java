import java.awt.image.BufferedImage;

public abstract class GameObject implements iGameObject {
	private int xLocation;
	private int yLocation;
	private int maxHeight;
	private int initialVelocity;
	private int fallingVelocity;
	private boolean isSliced;
	private boolean hasMovedOffScreen;
	private BufferedImage[] bufferedImages;

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
