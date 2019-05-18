package gameobjects;

public class Banana extends Fruit {

	public Banana() {
		super();
		this.number = 0;
		images = bufferedImagesFactory.getBananaImages();
		radius = 30;
	}
}
