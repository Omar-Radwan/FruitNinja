package gameobjects;

public class Apple extends Fruit {
	public Apple() {
		super();
		this.number = 1;
		images = bufferedImagesFactory.getAppleImages();
		radius = 30;
	}
}
