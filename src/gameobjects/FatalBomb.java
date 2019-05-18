package gameobjects;
public class FatalBomb extends Bomb {

	public FatalBomb() {
		super();
		this.number = 3;
		isFatal = true;
		images = bufferedImagesFactory.getFatalBombImages();
	}
}
