package gameobjects;


public class NonFatalBomb extends Bomb {

	public NonFatalBomb() {
		super();
		this.number = 4;
		isFatal = false;
		images = bufferedImagesFactory.getNonFatalBombImages();
	}

}
