package gui.related;

import java.io.File;
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
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
	static Integer live = 3;
//	static int fruitSpeedX;// = 4;
//	static int fruitSpeedY;// = 3;

	private Image background = new Image("file:src/gui/related/background.jpg");
	public Controller controller;
	private ArrayList<Sprite> objects = new ArrayList<Sprite>();
	private ArrayList<Sprite> fatalBomb = new ArrayList<Sprite>();
	private ArrayList<Sprite> normalBomb = new ArrayList<Sprite>();
	private ArrayList<Sprite> specialFruit = new ArrayList<Sprite>();
	private Stage stage;
	private String[] types;

	private void fillTypes() {
		types = new String[10];

		types[0] = "banana";
		types[1] = "apple";
		types[2] = "watermelon";

	}

	public Stage getStage() {
		return stage;
	}

	public LevelView(Controller controller, Stage stage) {
		this.controller = controller;
		this.stage = stage;
		fillTypes();
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
	int specialFruits = 0;
	static int fataBombDur;

	static int normalBombDur;

	static int pathFruitDur;
	Label label2 = new Label();

	public void level() {

		fataBombDur = controller.getLevelModel().getPathFatalDur();

		normalBombDur = controller.getLevelModel().getPathNormalDur();

		pathFruitDur = controller.getLevelModel().getPathFruitDur();

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

		Image image2 = new Image("file:src/gui/related/animated-heart-image-0503.gif", 40, 40, false, false);
		label2.setText(live.toString());
		Color c1 = Color.RED;
		label2.setTextFill(c1);
		label2.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

		label2.setGraphic(new ImageView(image2));
		/*
		 * grid.add(label2, 1, 0); GridPane.setHalignment(label2,HPos.RIGHT);
		 */
		label2.setLayoutX(720);
		label2.setLayoutY(70);
		root.getChildren().add(label2);
		repeatingImage();
		repeatingfatalBomb();
		repeatingnormalBomb();
		repeatSpecialFruit();
		VBox vb = new VBox(10);
		vb.getChildren().addAll(currentScore, best);
		root.getChildren().addAll(vb, timerLabel);
		scene = new Scene(root, 800, 500);
		Image mouse = new Image("file:src/gui/related/knife.png");
		scene.setCursor(new ImageCursor(mouse));
		stage.setScene(scene);
		cut(stage);

	}

	public void repeatSpecialFruit() {
		Timeline timeline = new Timeline(new KeyFrame(new Duration(7000), new EventHandler() {

			@Override
			public void handle(Event event) {
				setSpecialFruit();
				setPathOfSpecialFruit(specialFruits - 1);

			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}

	public void setSpecialFruit() {
		GameObject x = controller.getLevelModel().getSpecialFruit();
		specialFruit.add(new Sprite(x.getImages()[0], x.radius, x.getNumber()));
		Random r = new Random();
		specialFruit.get(specialFruit.size() - 1).setPositinoX(r.nextInt(700) + 10);
		specialFruit.get(specialFruit.size() - 1).setPositionY(-50);
		specialFruits++;

	}

	public void setPathOfSpecialFruit(int i) {
		Path path = new Path();
		double x = specialFruit.get(i).getPositionX();
		MoveTo move = new MoveTo(x, 0);
		LineTo line = new LineTo(x, 600);
		path.getElements().add(move);
		path.getElements().add(line);
		PathTransition pathT = new PathTransition();
		root.getChildren().add(specialFruit.get(i).getImage());
		pathT.setNode(specialFruit.get(i).getImage());
		pathT.setPath(path);
		pathT.setAutoReverse(false);
		pathT.setCycleCount(1);
		pathT.setDelay(Duration.millis(1000));

		PathTransition pat2 = new PathTransition();
		pat2.setNode(specialFruit.get(i).getCircle());
		pat2.setPath(path);
		pat2.setAutoReverse(false);
		pat2.setCycleCount(1);
		pat2.setDuration(Duration.millis(1000));
		pathT.play();
		pat2.play();

	}

	public void setFatalBomb() {
		GameObject x = controller.getLevelModel().getFatalBomb();
		fatalBomb.add(new Sprite(x.getImages()[0], x.radius, x.getNumber()));
		mouse.setPic(3);
		int j = random.nextInt(2);
		if (j == 0) {
			setPositionX(fatalBomb.get(fatalBomb.size() - 1));
		} else if (j == 1) {
			setPositionY(fatalBomb.get(fatalBomb.size() - 1));
		}

		fatalBombs++;

	}

	public void repeatingfatalBomb() {
		Timeline timeline = new Timeline(
				new KeyFrame(new Duration(controller.getLevelModel().getFataldur()), new EventHandler() {
					@Override
					public void handle(Event event) {
						setFatalBomb();
						// setPathOfFatalBombs(fatalBombs - 1);
						setPath(fatalBomb.get(fatalBombs - 1), fataBombDur);
					}
				}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}

	public void setNormalBomb() {
		GameObject x = controller.getLevelModel().getNormalBomb();
		mouse.setPic(4);
		normalBomb.add(new Sprite(x.getImages()[0], x.radius, x.getNumber()));

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
						// setPathOfNormalBombs(normalBombs - 1);
						setPath(normalBomb.get(normalBombs - 1), normalBombDur);
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
						// setPathObjects(objectsNumb - 1);
						setPath(objects.get(objectsNumb - 1), pathFruitDur);
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

		GameObject x = controller.getFruit();
		objects.add(new Sprite(x.getImages()[0], x.radius, x.getNumber()));
		// mouse.setPic(x.getNumber());

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

	/*
	 * public void setPathObjects(int i) { QuadCurveTo quadCurve = new
	 * QuadCurveTo(); Path path = new Path(); double x; //
	 * System.out.println(objects.get(i).getPositionY()); if
	 * (objects.get(i).getPositionY() == 500) { x = objects.get(i).getPositionX();
	 * path.getElements().add(new MoveTo(x, 550)); quadCurve.setX(400 +
	 * random.nextInt(300)); quadCurve.setY(600); quadCurve.setControlX(800);
	 * quadCurve.setControlY(50); } else { // System.out.println("FU"); x =
	 * objects.get(i).getPositionY(); path.getElements().add(new MoveTo(0, x));
	 * quadCurve.setX(400 + random.nextInt(300)); quadCurve.setY(600);
	 * quadCurve.setControlX(600); quadCurve.setControlY(100);
	 * 
	 * } // MoveTo move = new MoveTo(x, 400); path.getElements().add(quadCurve);
	 * root.getChildren().add(objects.get(i).getImage()); pathT = new
	 * PathTransition(); // root.getChildren().add(objects.get(i).getCircle());
	 * pathT.setNode(objects.get(i).getImage()); //
	 * pathT.setNode(objects.get(i).getCircle()); pathT.setPath(path);
	 * pathT.setAutoReverse(false); pathT.setCycleCount(Timeline.INDEFINITE);
	 * pathT.setDuration(Duration.millis(controller.getLevelModel().getPathFruitDur(
	 * ))); // path for circle PathTransition pt2 = new PathTransition();
	 * pt2.setNode(objects.get(i).getCircle()); pt2.setPath(path);
	 * pt2.setAutoReverse(false); pt2.setCycleCount(1);
	 * pt2.setDuration(Duration.millis(controller.getLevelModel().getPathFruitDur())
	 * ); pathT.play(); pt2.play();
	 * 
	 * // System.out.println(objects.get(0).getPositionX()); // rendering(); //
	 * System.out.println(objects.get(0).getImage()); }
	 */
	/*
	 * public void setPathOfFatalBombs(int i) { Path path = new Path(); QuadCurveTo
	 * quadCurve = new QuadCurveTo(); double x; if (fatalBomb.get(i).getPositionY()
	 * == 500) { x = fatalBomb.get(i).getPositionX(); path.getElements().add(new
	 * MoveTo(x, 550)); quadCurve.setX(400 + random.nextInt(300));
	 * quadCurve.setY(600); quadCurve.setControlX(500); quadCurve.setControlY(200);
	 * } else { // System.out.println("FU"); x = fatalBomb.get(i).getPositionY();
	 * path.getElements().add(new MoveTo(0, x)); quadCurve.setX(400 +
	 * random.nextInt(300)); quadCurve.setY(600); quadCurve.setControlX(600);
	 * quadCurve.setControlY(100);
	 * 
	 * } // MoveTo move = new MoveTo(x, 400); path.getElements().add(quadCurve);
	 * root.getChildren().add(fatalBomb.get(i).getImage()); PathTransition pat = new
	 * PathTransition(); // pat = new PathTransition();
	 * pat.setNode(fatalBomb.get(i).getImage()); pat.setPath(path);
	 * pat.setAutoReverse(false); pat.setCycleCount(1);
	 * pat.setDuration(Duration.millis(controller.getLevelModel().getPathFatalDur())
	 * ); PathTransition pat2 = new PathTransition(); // pat = new PathTransition();
	 * pat2.setNode(fatalBomb.get(i).getCircle()); pat2.setPath(path);
	 * pat2.setAutoReverse(false); pat2.setCycleCount(1);
	 * pat2.setDuration(Duration.millis(controller.getLevelModel().getPathFatalDur()
	 * )); pat.play(); pat2.play(); }
	 */

	/*
	 * public void setPathOfNormalBombs(int i) { Path path = new Path(); QuadCurveTo
	 * quadCurve = new QuadCurveTo(); double x; if (normalBomb.get(i).getPositionY()
	 * == 500) { x = normalBomb.get(i).getPositionX(); path.getElements().add(new
	 * MoveTo(x, 550)); quadCurve.setX(400 + random.nextInt(300));
	 * quadCurve.setY(600); quadCurve.setControlX(800); quadCurve.setControlY(50); }
	 * else { x = normalBomb.get(i).getPositionY(); path.getElements().add(new
	 * MoveTo(0, x)); quadCurve.setX(400 + random.nextInt(300));
	 * quadCurve.setY(600); quadCurve.setControlX(600); quadCurve.setControlY(100);
	 * 
	 * } // MoveTo move = new MoveTo(x, 400); path.getElements().add(quadCurve);
	 * root.getChildren().add(normalBomb.get(i).getImage()); PathTransition pat =
	 * new PathTransition(); pat = new PathTransition();
	 * pat.setNode(normalBomb.get(i).getImage()); pat.setPath(path);
	 * pat.setAutoReverse(false); pat.setCycleCount(1);
	 * pat.setDuration(Duration.millis(controller.getLevelModel().getPathNormalDur()
	 * ));
	 * 
	 * PathTransition pat2 = new PathTransition(); // pat = new PathTransition();
	 * pat2.setNode(normalBomb.get(i).getCircle()); pat2.setPath(path);
	 * pat2.setAutoReverse(false); pat2.setCycleCount(1);
	 * pat2.setDuration(Duration.millis(controller.getLevelModel().getPathNormalDur(
	 * ))); pat.play(); pat2.play(); }
	 */

	public void setPath(Sprite e, int dur) {
		Path path = new Path();
		QuadCurveTo quadCurve = new QuadCurveTo();
		double x;
		if (e.getPositionY() == 500) {
			x = e.getPositionX();
			path.getElements().add(new MoveTo(x, 550));
			/*
			 * quadCurve.setX(400 + random.nextInt(300)); quadCurve.setY(600);
			 * quadCurve.setControlX(800); quadCurve.setControlY(50);
			 */
			Random r = new Random();
			CubicCurveTo cb = new CubicCurveTo(0, 400 + r.nextInt(100), 300 + r.nextInt(50), -500, 600 + r.nextInt(100),
					600);
			path.getElements().add(cb);
		} else {
			x = e.getPositionY();
			path.getElements().add(new MoveTo(0, x));
			quadCurve.setX(400 + random.nextInt(300));
			quadCurve.setY(600);
			quadCurve.setControlX(600);
			quadCurve.setControlY(100);
			path.getElements().add(quadCurve);

		}
		root.getChildren().add(e.getImage());
		PathTransition pat = new PathTransition();
		pat = new PathTransition();
		pat.setNode(e.getImage());
		pat.setPath(path);
		pat.setAutoReverse(false);
		pat.setCycleCount(1);
		pat.setDuration(Duration.millis(dur));

		PathTransition pat2 = new PathTransition();
		pat2.setNode(e.getCircle());
		pat2.setPath(path);
		pat2.setAutoReverse(false);
		pat2.setCycleCount(1);
		pat2.setDuration(Duration.millis(dur));
		pat.play();
		pat2.play();

	}

	public void cut(Stage stage) {

		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// gameMusic();

				mouse.setPosXOfMouse(event.getX());
				mouse.setPosYOfMouse(event.getY());

				// System.out.println(mouse.getPositionXOfMouse());
				// System.out.println(mouse.getPositionYOfMouse());

				for (int i = 0; i < objects.size(); i++) {
					Sprite x = objects.get(i);

					if (x.intersects(mouse)) {
						int number = x.getNumber();
						Image img2 = new Image("file:src/gui/related/" + types[number] + "sliced.png");
						x.getImage().setDisable(true);
						x.setImage(img2);
						controller.sliceFruit(i);

					}
				}

				for (int i = 0; i < objects.size(); i++) {

					for (int j = 0; j < normalBomb.size(); j++)
						if (normalBomb.get(j).intersects(mouse)) {
							mouse.getPositionX();
							mouse.getPositionY();
							GameOverScene(stage);
						}

					for (int k = 0; k < fatalBomb.size(); k++)
						if (fatalBomb.get(k).intersects(mouse)) {
							mouse.getPositionX();
							mouse.getPositionY();
							System.out.println(live);
//								if(live == 0)
//								GameOverScene(stage);
//							
//								else {
							// soundAlert();
							System.out.println("fatal bomb" + mouse.getPic());

							live = live - 1;
							label2.setText(live.toString());

							// }

						}
				}

			}

		});

	}

	private void GameOverScene(Stage stage) {
		Text GO = new Text("GAME OVER");
		Font f = Font.font("Castellar", FontWeight.BOLD, FontPosture.REGULAR, 100);
		GO.setFill(Color.RED);
		GO.setFont(f);

		HBox hb = new HBox(GO);
		hb.setAlignment(Pos.CENTER);
		Image img = new Image("file:src/gui/related/background.jpg");
		BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

		hb.setBackground(new Background(bgImg));

		Scene gameOver = new Scene(hb, 800, 500);
		stage.setScene(gameOver);
		stage.show();
		GameOverSound();
	}

	private void alert() {
		Text warning = new Text("YOU HAVE LOST A LIFE");
		Font f = Font.font("Swis721 BlkOul BT", FontWeight.BOLD, FontPosture.REGULAR, 20);
		warning.setFill(Color.RED);
		warning.setFont(f);

		Image imgW = new Image("file:src/gui/related/1219012_thumb.png");
		ImageView iv = new ImageView(imgW);
		iv.setFitHeight(40);
		iv.setFitWidth(40);

		Image img = new Image("file:src/gui/related/background.jpg");
		BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

		HBox hb = new HBox(10);
		hb.getChildren().addAll(warning, iv);
		hb.setAlignment(Pos.CENTER);
		hb.setBackground(new Background(bgImg));
		Scene scene = new Scene(hb, 400, 250);
		Stage stg = new Stage();
		stg.setScene(scene);
		stg.show();
		soundAlert();
	}

	// -------------------------------SoundFunctions---------------------------------------------

	protected void GameOverSound() {
		AudioClip ov = new AudioClip(
				this.getClass().getResource("zapsplat_human_male_laugh_snigger_closed_mouth_002_15992.mp3").toString());
		ov.play();
	}

	protected void soundSlicing() {
		AudioClip s = new AudioClip(this.getClass()
				.getResource("zapsplat_food_tomato_slice_knife_wooden_chopping_board_001_29262.mp3").toString());
		s.play();

	}

	protected void soundAlert() {
		AudioClip sA = new AudioClip(this.getClass()
				.getResource("zapsplat_multimedia_alert_short_2_notes_mallet_synth_exclamation_25550.mp3").toString());
		sA.play();

	}

	private void gameMusic() {
		String musicFile = "audio_hero_Laughs-And-Giggles_SIPML_K-04-01-01.mp3"; // For example
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
}
