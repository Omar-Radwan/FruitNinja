
public class SpecialMango extends Fruit {
	public SpecialMango() {
		super();
		bufferedImages = bufferedImagesFactory.getSpecialMangoImages();
		this.score *= 2;
	}
}
