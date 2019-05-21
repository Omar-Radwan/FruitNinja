package gui.related;

import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
	private Image image;
	private double positionX;
	private double positionY;
	public double width;
	public double height;
	public boolean changedImage;

	protected int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	ImageView img = new ImageView();
	Random random = new Random();
//	private Circle circle = new Circle();

	public Sprite() {
		positionX = 0;
		positionY = 0;
	}

	public Sprite(Image image, int number) {
		this.image = image;
		img.setImage(image);
		positionX = 0;
		positionY = 500;
		img.setY(500);
		img.setX(0);
		// circle.setLayoutY(500);
		// circle.setCenterY(500);
		width = image.getWidth();
		height = image.getHeight();
		// maxHeight = random.nextInt(500) * 0.1 + 200;
		// circle.setRadius(radius);
		this.number = number;
		this.changedImage = false;
	}

	private int pic;

	public int getPic() {
		return pic;
	}

	public void setPic(int pic) {
		this.pic = pic;
	}

	public void setPositinoX(double positionX) {
		img.setX(positionX);
	}

	public ImageView getImage() {
		// ImageView img = new ImageView();
		img.setImage(image);
		img.setX(positionX);
		img.setY(positionY);
		return img;
	}

	public void setPositionY(double positionY) {
		// circle.setLayoutY(positionY);
		// circle.setCenterY(positionY);

		img.setY(positionY);
	}

	public void setPosYOfMouse(double positionY) {
		this.positionY = positionY;
	}

	public void setPosXOfMouse(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionXOfMouse() {
		return positionX;
	}

	public double getPositionYOfMouse() {
		return positionY;
	}

	public double getPositionX() {
		return img.getX() + img.getTranslateX();
	}

	public double getPositionY() {
		return img.getY() + img.getTranslateY();
	}

	/*
	 * public void render(GraphicsContext gc) { gc.drawImage(image,
	 * img.getTranslateX()+positionX, img.getTranslateY()+positionY);
	 * 
	 * }
	 */

	private Rectangle2D getBoundary() {
		return new Rectangle2D(img.getTranslateX() + positionX, img.getTranslateY() + positionY, width, height);
	}

	private Rectangle2D getboundaryOfMouse() {
		return new Rectangle2D(positionX, positionY, width, height);

	}

	public boolean intersects(Sprite sprite) {
		return this.getBoundary().intersects(sprite.getboundaryOfMouse());
	}

	public void setImage(Image img) {
		this.image = img;
	}

}
/*
 * 
 * QuadCurveTo quadCurve = new QuadCurveTo(); Path path = new Path(); pathT =
 * new PathTransition(); PathTransition pt2 = new PathTransition(); double x; //
 * System.out.println(objects.get(i).getPositionY());
 * if(objects.get(i).getPositionY() == 500) { x = objects.get(i).getPositionX();
 * path.getElements().add(new MoveTo(x , 550)); /*quadCurve.setX(400 +
 * random.nextInt(300)); quadCurve.setY(600); quadCurve.setControlX(800);
 * quadCurve.setControlY(0);
 */
/*
 * Circle circ = new Circle(random.nextInt(100)+250); circ.setCenterX(400);
 * circ.setCenterY(550); pathT.setPath(circ); pt2.setPath(circ); } else {
 * //System.out.println("FU"); x = objects.get(i).getPositionY();
 * path.getElements().add(new MoveTo(0 , x)); quadCurve.setX(400 +
 * random.nextInt(300)); quadCurve.setY(600); quadCurve.setControlX(600);
 * quadCurve.setControlY(100); path.getElements().add(quadCurve);
 * pathT.setPath(path); pt2.setPath(path);
 * 
 * } // MoveTo move = new MoveTo(x, 400);
 * root.getChildren().add(objects.get(i).getImage());
 * //root.getChildren().add(objects.get(i).getCircle());
 * pathT.setNode(objects.get(i).getImage()); //
 * pathT.setNode(objects.get(i).getCircle()); // pathT.setPath(path);
 * pathT.setAutoReverse(false); pathT.setCycleCount(1);
 * pathT.setDuration(Duration.millis(pathFruitDur)); //path for circle
 * pt2.setNode(objects.get(i).getCircle()); pt2.setAutoReverse(false);
 * pt2.setCycleCount(1); pt2.setDuration(Duration.millis(pathFruitDur));
 * pathT.play(); pt2.play();
 * 
 * 
 * }
 *
 *
 * 
 */
