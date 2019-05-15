package gameobjects;

public class NonFatalBomb extends Bomb {

	public NonFatalBomb() {
		super();
		isFatal = false;
		images = bufferedImagesFactory.getNonFatalBombImages();
	}

}
