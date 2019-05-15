package gameobjects;

public class SpecialMango extends Fruit {
	public SpecialMango() {
		super();
		images = bufferedImagesFactory.getSpecialMangoImages();
		this.score *= 2;
	}
}
