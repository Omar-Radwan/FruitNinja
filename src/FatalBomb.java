
public class FatalBomb extends Bomb {

	public FatalBomb() {
		super();
		isFatal = true;
		bufferedImages = bufferedImagesFactory.getFatalBombImages();
	}
}
