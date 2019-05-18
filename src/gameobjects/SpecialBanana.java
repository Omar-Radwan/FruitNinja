package gameobjects;

public class SpecialBanana extends Fruit {
	public SpecialBanana() {
		super();
		this.number = 5; 
		images = bufferedImagesFactory.getSpecialBananaImages();
		score *= 2;
	}
}
