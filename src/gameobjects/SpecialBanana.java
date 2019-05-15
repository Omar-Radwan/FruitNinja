package gameobjects;

public class SpecialBanana extends Fruit {
	public SpecialBanana() {
		super();
		images = bufferedImagesFactory.getSpecialBananaImages();
		score *= 2;
	}
}
