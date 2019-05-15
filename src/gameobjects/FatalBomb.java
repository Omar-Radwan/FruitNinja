package gameobjects;

public class FatalBomb extends Bomb {

	public FatalBomb() {
		super();
		isFatal = true;
		images = bufferedImagesFactory.getFatalBombImages();
	}
}
