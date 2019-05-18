package gameobjects;


public abstract class Bomb extends GameObject {
	protected boolean isFatal;

	public Bomb() {
		super();
		radius = 10;
	}

	public boolean isFatal() {
		return isFatal;
	}

}
