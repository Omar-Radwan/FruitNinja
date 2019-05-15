package gui.related;

import java.util.ArrayList;
import java.util.Random;

import Controller.Controller;
import gameobjects.GameObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelView {
	private Group root = new Group();
	private GraphicsContext gc;
	private Canvas canvas = new Canvas(800, 500);
	static int bestScore = 0;
	private int StartTime = 0;
	private Label timerLabel = new Label();
	private Integer timeSeconds = StartTime;
	private Timeline timeline;
	private Random random = new Random();
	private PathTransition pathT;

	protected Sprite mouse = new Sprite();
	private double indexX, indexY;
	protected Scene scene;

//	static int fruitSpeedX;// = 4;
//	static int fruitSpeedY;// = 3;

	private Image background = new Image("file:src/gui/related/background.jpg");
	public Controller controller;
	private ArrayList<Sprite> objects = new ArrayList<Sprite>();
	private ArrayList<Sprite> fatalBomb = new ArrayList<Sprite>();
	private ArrayList<Sprite> normalBomb = new ArrayList<Sprite>();
	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public LevelView(Controller controller, Stage stage) {
		this.controller = controller;
		this.stage = stage;

		level();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	int objectsNumb = 0;
	int fatalBombs = 0;
	int normalBombs = 0;

	public void level() {
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(background, 0, 0);

		// label to current score :

		int currentS = 0;
		Label currentScore = new Label("Score: " + currentS);
		Label best = new Label("Best: " + bestScore);

		timerLabel.setText(timeSeconds.toString());
		timerLabel.setTextFill(Color.RED);
		timerLabel.setStyle("-fx-font-size: 4em;");
		timerLabel.setLayoutX(750);
		timerLabel.setLayoutY(0);
		timeSeconds = StartTime;
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {

			@Override
			public void handle(Event event) {

				timeSeconds++;
				// update timerLabel
				timerLabel.setText(timeSeconds.toString());
//		                        if (timeSeconds <= 0) {
//		                            timeline.stop();
//		                        }

			}
			// KeyFrame event handler

		}));
		timeline.playFromStart();
		/*
		 * new AnimationTimer() {
		 * 
		 * @Override public void handle(long arg0) { rendering(); updateGame(); }
		 * }.start();
		 */
		// setImages();
		/*
		 * setPath(); pathT.play();
		 */
		/*
		 * Circle circle = new Circle(50);
		 * circle.setCenterX(objects.get(0).getPositionX()); circle.setCenterY(500);
		 * root.getChildren().add(circle);
		 */
		repeatingImage();
		repeatingfatalBomb();
		repeatingnormalBomb();

		VBox vb = new VBox(10);
		vb.getChildren().addAll(currentScore, best);
		root.getChildren().addAll(vb, timerLabel);
		scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		cut();

	}

	public void setFatalBomb() {
		GameObject x = controller.getLevelModel().getFatalBomb();
		fatalBomb.add(new Sprite(x.getImages()[0], x.radius));

		// Rectangle rectangle = new Rectangle();
		int j = random.nextInt(2);
		if (j == 0) {
			// System.out.println(objects.get(objects.size() -1).getPositionY());
			setPositionX(fatalBomb.get(fatalBomb.size() - 1));
		} else if (j == 1) {
			// System.out.println("FU");
			setPositionY(fatalBomb.get(fatalBomb.size() - 1));
		}

		// setPositionX(fatalBomb.get(fatalBomb.size() - 1));
		fatalBombs++;
		// renderingFatalbomb();

	}

	public void repeatingfatalBomb() {
		Timeline timeline = new Timeline(
				new KeyFrame(new Duration(controller.getLevelModel().getFataldur()), new EventHandler() {
					@Override
					public void handle(Event event) {
						setFatalBomb();
						setPathOfFatalBombs(fatalBombs - 1);
					}
				}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}

	public void setNormalBomb() {
		GameObject x = controller.getLevelModel().getNormalBomb();

		normalBomb.add(new Sprite(x.getImages()[0], x.radius));

		int j = random.nextInt(2);

		if (j == 0) {
			// System.out.println(objects.get(objects.size() -1).getPositionY());
			setPositionX(normalBomb.get(normalBomb.size() - 1));
		} else if (j == 1) {
			// System.out.println("FU");
			setPositionY(normalBomb.get(normalBomb.size() - 1));
		}

		// setPositionX();
		normalBombs++;
		// renderingNormalbomb();

	}

	public void repeatingnormalBomb() {
		Timeline timeline = new Timeline(
				new KeyFrame(new Duration(controller.getLevelModel().getNormaldur()), new EventHandler() {
					@Override
					public void handle(Event event) {
						setNormalBomb();
						setPathOfNormalBombs(normalBombs - 1);
					}
				}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}

	public void repeatingImage() {

		Timeline timeline = new Timeline(
				new KeyFrame(new Duration(controller.getLevelModel().getRepeatDur()), new EventHandler() {
					@Override
					public void handle(Event event) {
						setImages();
						setPathObjects(objectsNumb - 1);

					}
				}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();

	}

	/*
	 * public void renderingFatalbomb() { for (Sprite e : fatalBomb) e.render(gc); }
	 * public void renderingNormalbomb() { for (Sprite e : normalBomb) e.render(gc);
	 * }
	 */

	public void setImages() {

		GameObject x = controller.getLevelModel().getFruit();
		System.out.println(x.getImages()[0]);
		objects.add(new Sprite(x.getImages()[0], x.radius));

		int j = random.nextInt(2);
		if (j == 0) {
			// System.out.println(objects.get(objects.size() -1).getPositionY());
			setPositionX(objects.get(objects.size() - 1));
		} else if (j == 1) {
			// System.out.println("FU");
			setPositionY(objects.get(objects.size() - 1));
		}
		objectsNumb++;
		// rendering();
	}

	/*
	 * public void rendering() { // gc.clearRect(0, 0, 800, 500); for(Sprite e :
	 * objects) e.render(gc); }
	 */
	public void setPositionX(Sprite e) {
		// for(Sprite e : objects) {
		/* int positionX = */// random.nextInt(200);
		e.setPositinoX(random.nextInt(200));
		// e.setPositionY(500);
	}

	public void setPositionY(Sprite e) {
		e.setPositionY(random.nextInt(200));
		e.setPositinoX(0);
	}

	public void updateGame() {
		gc.drawImage(background, 0, 0);
		for (Sprite e : objects) {
			// e.updateLocations();
			e.render(gc);
		}

		for (Sprite e : fatalBomb) {
			// e.updateLocations();
			e.render(gc);
		}
		for (Sprite e : normalBomb) {
			// e.updateLocations();
			e.render(gc);
		}

	}

	public void setPathObjects(int i) {
		QuadCurveTo quadCurve = new QuadCurveTo();
		Path path = new Path();
		double x;
		// System.out.println(objects.get(i).getPositionY());
		if (objects.get(i).getPositionY() == 500) {
			x = objects.get(i).getPositionX();
			path.getElements().add(new MoveTo(x, 550));
			quadCurve.setX(400 + random.nextInt(300));
			quadCurve.setY(600);
			quadCurve.setControlX(800);
			quadCurve.setControlY(50);
		} else {
			// System.out.println("FU");
			x = objects.get(i).getPositionY();
			path.getElements().add(new MoveTo(0, x));
			quadCurve.setX(400 + random.nextInt(300));
			quadCurve.setY(600);
			quadCurve.setControlX(600);
			quadCurve.setControlY(100);

		}
		// MoveTo move = new MoveTo(x, 400);
		path.getElements().add(quadCurve);
		root.getChildren().add(objects.get(i).getImage());
		pathT = new PathTransition();
		// root.getChildren().add(objects.get(i).getCircle());
		pathT.setNode(objects.get(i).getImage());
		// pathT.setNode(objects.get(i).getCircle());
		pathT.setPath(path);
		pathT.setAutoReverse(false);
		pathT.setCycleCount(Timeline.INDEFINITE);
		pathT.setDuration(Duration.millis(controller.getLevelModel().getPathFruitDur()));
		// path for circle
		PathTransition pt2 = new PathTransition();
		pt2.setNode(objects.get(i).getCircle());
		pt2.setPath(path);
		pt2.setAutoReverse(false);
		pt2.setCycleCount(1);
		pt2.setDuration(Duration.millis(controller.getLevelModel().getPathFruitDur()));
		pathT.play();
		pt2.play();

		// System.out.println(objects.get(0).getPositionX());
		// rendering();
		// System.out.println(objects.get(0).getImage());
	}

	public void setPathOfFatalBombs(int i) {
		Path path = new Path();
		QuadCurveTo quadCurve = new QuadCurveTo();
		double x;
		if (fatalBomb.get(i).getPositionY() == 500) {
			x = fatalBomb.get(i).getPositionX();
			path.getElements().add(new MoveTo(x, 550));
			quadCurve.setX(400 + random.nextInt(300));
			quadCurve.setY(600);
			quadCurve.setControlX(500);
			quadCurve.setControlY(200);
		} else {
			// System.out.println("FU");
			x = fatalBomb.get(i).getPositionY();
			path.getElements().add(new MoveTo(0, x));
			quadCurve.setX(400 + random.nextInt(300));
			quadCurve.setY(600);
			quadCurve.setControlX(600);
			quadCurve.setControlY(100);

		}
		// MoveTo move = new MoveTo(x, 400);
		path.getElements().add(quadCurve);
		root.getChildren().add(fatalBomb.get(i).getImage());
		PathTransition pat = new PathTransition();
		// pat = new PathTransition();
		pat.setNode(fatalBomb.get(i).getImage());
		pat.setPath(path);
		pat.setAutoReverse(false);
		pat.setCycleCount(1);
		pat.setDuration(Duration.millis(controller.getLevelModel().getPathFatalDur()));
		PathTransition pat2 = new PathTransition();
		// pat = new PathTransition();
		pat2.setNode(fatalBomb.get(i).getCircle());
		pat2.setPath(path);
		pat2.setAutoReverse(false);
		pat2.setCycleCount(1);
		pat2.setDuration(Duration.millis(controller.getLevelModel().getPathFatalDur()));
		pat.play();
		pat2.play();
	}

	public void setPathOfNormalBombs(int i) {
		Path path = new Path();
		QuadCurveTo quadCurve = new QuadCurveTo();
		double x;
		if (normalBomb.get(i).getPositionY() == 500) {
			x = normalBomb.get(i).getPositionX();
			path.getElements().add(new MoveTo(x, 550));
			quadCurve.setX(400 + random.nextInt(300));
			quadCurve.setY(600);
			quadCurve.setControlX(800);
			quadCurve.setControlY(50);
		} else {
			x = normalBomb.get(i).getPositionY();
			path.getElements().add(new MoveTo(0, x));
			quadCurve.setX(400 + random.nextInt(300));
			quadCurve.setY(600);
			quadCurve.setControlX(600);
			quadCurve.setControlY(100);

		}
		// MoveTo move = new MoveTo(x, 400);
		path.getElements().add(quadCurve);
		root.getChildren().add(normalBomb.get(i).getImage());
		PathTransition pat = new PathTransition();
		pat = new PathTransition();
		pat.setNode(normalBomb.get(i).getImage());
		pat.setPath(path);
		pat.setAutoReverse(false);
		pat.setCycleCount(1);
		pat.setDuration(Duration.millis(controller.getLevelModel().getPathNormalDur()));

		PathTransition pat2 = new PathTransition();
		// pat = new PathTransition();
		pat2.setNode(normalBomb.get(i).getCircle());
		pat2.setPath(path);
		pat2.setAutoReverse(false);
		pat2.setCycleCount(1);
		pat2.setDuration(Duration.millis(controller.getLevelModel().getPathNormalDur()));
		pat.play();
		pat2.play();
	}

	public void cut() {

		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouse.setPosXOfMouse(event.getX());
				mouse.setPosYOfMouse(event.getY());
				/*
				 * System.out.println(mouse.getPositionXOfMouse());
				 * System.out.println(mouse.getPositionYOfMouse());
				 */
				for (int i = 0; i < objects.size(); i++) {
					if (objects.get(i).intersects(mouse)) {/* System.out.println("FU"); */
						indexX = mouse.getPositionX();
						indexY = mouse.getPositionY();
						// System.out.println(objects.size());
						objects.remove(i);
						// System.out.println(objects.size());
						if (mouse.getPic() == 2) {
							Image img = new Image("file:src/gui/related/heya de.jpg");
							// objects.add(new Sprite(img));
						} else if (mouse.getPic() == 0) {
							// banana msh l2ya sora lehom ^_^
							/*
							 * Image img = new Image("file:src/gui/related/anans.jpg"); objects.add(new
							 * Sprite(img));
							 */
						} else if (mouse.getPic() == 1) {
							// mango
						} else if (mouse.getPic() == 3) {

						}

					}

				}

			}

		});

	}
}
