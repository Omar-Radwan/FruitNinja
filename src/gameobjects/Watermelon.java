package gameobjects;

public class Watermelon extends Fruit {
	public Watermelon() {
		super();
		this.number = 2;

		images = bufferedImagesFactory.getWatermelonImages();
		radius = 35;
	}

}
