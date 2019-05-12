
public class NonFatalBomb extends Bomb {

	public NonFatalBomb() {
		super();
		isFatal = false;
		bufferedImages = bufferedImagesFactory.getNonFatalBombImages();
	}

}
