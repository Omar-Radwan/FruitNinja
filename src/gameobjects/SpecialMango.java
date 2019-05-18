package gameobjects;

public class SpecialMango extends Fruit {
	public SpecialMango() {
		super();
		this.number = 6;
		images = bufferedImagesFactory.getSpecialMangoImages();
		this.score *= 2;
	}
}
