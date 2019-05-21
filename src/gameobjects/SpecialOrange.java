package gameobjects;

public class SpecialOrange extends Fruit {
	public SpecialOrange() {
		super();
		this.number = 6;
		images = bufferedImagesFactory.getSpecialMangoImages();
		this.score *= 2;
	}
}
