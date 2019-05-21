package gui.related;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import Controller.Controller;
import Controller.IController;
import gameobjects.GameObject;
import gameobjects.IGameObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
import levelmodels.ILevelModel;

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
	private static int CurrScore = 0;
	protected Sprite mouse = new Sprite();
	private double indexX, indexY;
	protected Scene scene;
	static Integer live = 3;
	Label currentScore;
	Label bestScoreLabel;
	Label scoreImg = new Label();
//	static int fruitSpeedX;// = 4;
//	static int fruitSpeedY;// = 3;

	private Image background = new Image("file:src/gui/related/background.jpg");

	public IController controller;

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
		types[5] = "SpecialBanana";
		types[6] = "SpecialOrange";

	}

	public Stage getStage() {
		return stage;
	}

	public LevelView(IController controller, Stage stage) {
		this.controller = controller;
		this.stage = stage;
		fillTypes();
		level();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public IController getController() {
		return controller;
	}

	public void setController(IController controller) {
		this.controller = controller;
	}

	int objectsNumb = 0;
	int fatalBombs = 0;
	int normalBombs = 0;
	int specialFruits = 0;
	static int fataBombDur;

	static int normalBombDur;

	static int pathFruitDur;
	Label livesLabel = new Label();

	public void level() {

		fataBombDur = controller.getPathFatalDur();

		normalBombDur = controller.getPathNormalDur();

		pathFruitDur = controller.getPathFruitDur();

		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(background, 0, 0);

		// label to current score :

		currentScore = new Label("" + CurrScore);
		currentScore.setLayoutX(50);
		currentScore.setLayoutY(0);
		currentScore.setTextFill(Color.GOLDENROD);
		currentScore.setFont(Font.font("Vineta BT", FontWeight.EXTRA_BOLD, 20));
		
		bestScoreLabel = new Label("Best: " + bestScore);
		bestScoreLabel.setTextFill(Color.GREEN);
		bestScoreLabel.setFont(Font.font("Broadway", FontWeight.EXTRA_BOLD, 20));

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
		livesLabel.setText(live.toString());
		/*
		 * Color c1 = Color.RED; label2.setTextFill(c1);
		 * label2.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		 */
		setLabel(livesLabel);
		livesLabel.setGraphic(new ImageView(image2));
		/*
		 * grid.add(label2, 1, 0); GridPane.setHalignment(label2,HPos.RIGHT);
		 */
		livesLabel.setLayoutX(720);
		livesLabel.setLayoutY(70);
		
		////////////
		Image image3 = new Image("file:src/gui/related/score.png", 60, 60, false, false);
		scoreImg.setGraphic(new ImageView(image3));
		
		scoreImg.setLayoutX(10);
		scoreImg.setLayoutY(0);
		root.getChildren().add(scoreImg);
		///////////////////
		root.getChildren().add(livesLabel);
		repeatingImage();
		repeatingfatalBomb();
		repeatingnormalBomb();
		repeatSpecialFruit();
		VBox vb = new VBox(10);
		vb.getChildren().addAll(currentScore, bestScoreLabel);
		root.getChildren().addAll(vb, timerLabel);
		scene = new Scene(root, 800, 500);
		Image mouse = new Image("file:src/gui/related/knife.png");
		scene.setCursor(new ImageCursor(mouse));
		stage.setScene(scene);
		cut(stage);

	}

	private void setLabel(Label label) {
		Color c1 = Color.RED;
		label.setTextFill(c1);
		label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

	}

	public void repeatSpecialFruit() {
		Timeline timeline = new Timeline(new KeyFrame(new Duration(4000), new EventHandler() {

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
		IGameObject x = controller.getSpecialFruit();
		specialFruit.add(new Sprite(x.getImages()[0], x.getNumber()));
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
		pathT.setDuration(Duration.millis(2000));
		pathT.play();
	}

	public void setFatalBomb() {
		IGameObject x = controller.getFatalBomb();
		fatalBomb.add(new Sprite(x.getImages()[0], x.getNumber()));
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
		Timeline timeline = new Timeline(new KeyFrame(new Duration(controller.getFataldur()), new EventHandler() {
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
		IGameObject x = controller.getNonFatalBomb();
		mouse.setPic(4);
		normalBomb.add(new Sprite(x.getImages()[0], x.getNumber()));

		int j = random.nextInt(2);

		if (j == 0) {
			setPositionX(normalBomb.get(normalBomb.size() - 1));
		} else if (j == 1) {
			setPositionY(normalBomb.get(normalBomb.size() - 1));
		}

		normalBombs++;

	}

	public void repeatingnormalBomb() {
		Timeline timeline = new Timeline(new KeyFrame(new Duration(controller.getNormaldur()), new EventHandler() {
			@Override
			public void handle(Event event) {
				setNormalBomb();
				setPath(normalBomb.get(normalBombs - 1), normalBombDur);
			}
		}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}

	public void repeatingImage() {

		Timeline timeline = new Timeline(new KeyFrame(new Duration(controller.getRepeatDur()), new EventHandler() {
			@Override
			public void handle(Event event) {
				setFruits();
				setPath(objects.get(objectsNumb - 1), pathFruitDur);
			}
		}));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();

	}

	public void setFruits() {

		IGameObject x = controller.getFruit();
		objects.add(new Sprite(x.getImages()[0], x.getNumber()));

		int j = random.nextInt(2);

		if (j == 0) {
			setPositionX(objects.get(objects.size() - 1));
		} else if (j == 1) {
			setPositionY(objects.get(objects.size() - 1));
		}

		objectsNumb++;
	}

	public void setPositionX(Sprite e) {
		e.setPositinoX(random.nextInt(200));
	}

	public void setPositionY(Sprite e) {
		e.setPositionY(random.nextInt(200));
		Random r = new Random();
		int y = r.nextInt(2);
		if (y == 0)
			e.setPositinoX(0);
		else
			e.setPositinoX(870);
	}

	public void setPath(Sprite e, int dur) {
		Path path = new Path();
		QuadCurveTo quadCurve = new QuadCurveTo();
		double x;
		if (e.getPositionY() == 500) {
			x = e.getPositionX();
			path.getElements().add(new MoveTo(x, 550));

			Random r = new Random();
			CubicCurveTo cb = new CubicCurveTo(0, 400 + r.nextInt(100), 300 + r.nextInt(50), -500, 600 + r.nextInt(100),
					600);
			path.getElements().add(cb);
		} else if (e.getPositionX() == 0) {
			x = e.getPositionY();
			path.getElements().add(new MoveTo(0, x));
			quadCurve.setX(400 + random.nextInt(300));
			quadCurve.setY(600);
			quadCurve.setControlX(500 + random.nextInt(100));
			quadCurve.setControlY(50 + random.nextInt(50));
			path.getElements().add(quadCurve);

		} else {
			x = e.getPositionY();
			path.getElements().add(new MoveTo(870, x));
			quadCurve.setX(random.nextInt(200));
			quadCurve.setY(700);
			quadCurve.setControlX(500 + random.nextInt(100));
			quadCurve.setControlY(50 + random.nextInt(50));
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
		pat.play();
		pat.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(e.getImage().isDisabled() == false && e.getNumber()!=3 && e.getNumber()!=4)
				controller.checkIfIsSliced(objectsNumb-1);
			}
		});
	}

	int time;

	public void cut(Stage stage) {
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// gameMusic();

				mouse.setPosXOfMouse(event.getX());
				mouse.setPosYOfMouse(event.getY());

				for (int i = 0; i < objects.size(); i++) {
					Sprite x = objects.get(i);
				//	System.out.println(objects.get(i).getPositionY());
					if (x.intersects(mouse)) {
						int number = x.getNumber();
						soundSlicing();
						Image img2 = new Image("file:src/gui/related/" + types[number] + "sliced.png");
						x.getImage().setDisable(true);
						x.setImage(img2);
						controller.sliceFruit(i);
						
					}
				/*	else if(x.getPositionY() >= 600) {
						System.out.println("FU");
						controller.checkIfIsSliced(i);
					}*/

					
				}

				for (int i = 0; i < specialFruit.size(); i++) {
					Sprite x = specialFruit.get(i);
					if (x.intersects(mouse)) {
						int number = x.getNumber();
						soundSlicing();
						Image img2 = new Image("file:src/gui/related/sliced" + types[number] + ".png");
						x.getImage().setDisable(true);
						x.setImage(img2);
						controller.sliceSpecialFruit(i);
						time = timeSeconds + 4;
						/*
						 * System.out.println(); System.out.println(time);
						 */

					}

				}
				for (int j = 0; j < normalBomb.size(); j++) {
					if (normalBomb.get(j).intersects(mouse)) {
						controller.sliceNonFatalBomb(j);
						bombSlicing();
						Image img2 = new Image("file:src/gui/related/boooomb.png", 80, 80, false, false);
						normalBomb.get(j).getImage().setDisable(true);
						normalBomb.get(j).setImage(img2);

					}
				}

				for (int k = 0; k < fatalBomb.size(); k++) {
					if (fatalBomb.get(k).intersects(mouse)) {

						controller.sliceFatalBomb();

					}
				}

				// }
				int x = timeSeconds;
				if (x >= time) {
					controller.endDoubleScore();
				}

			}

		});

	}

	private void setStyle(Button button) {
		button.setStyle("-fx-background-color: \r\n" + "        #F79704,\r\n"
				+ "        linear-gradient(#F79704 50%, white 100%),\r\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #F79704 45%, rgba(230,230,230,0) 50%);\r\n"
				+ "    -fx-background-radius: 30;\r\n" + "    -fx-background-insets: 0,1,1;\r\n"
				+ "    -fx-text-fill: black;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");

		button.setTextFill(Color.WHEAT);
	}
	public void GameOverScene() {
		
		Text GO = new Text("GAME OVER");
		Font f = Font.font("Castellar", FontWeight.BOLD, FontPosture.REGULAR, 100);
		GO.setFill(Color.RED);
		GO.setFont(f);
		HBox hb = new HBox(30);
		hb.getChildren().add(GO);

		hb.setAlignment(Pos.CENTER);
		Image img = new Image("file:src/gui/related/background.jpg");
		BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

		hb.setBackground(new Background(bgImg));

		VBox vb = new VBox(10);

		Button newGame = new Button("New game");
		setStyle(newGame);
		Button exit = new Button("Exit");
		setStyle(exit);

		
		vb.getChildren().add(exit);
		vb.getChildren().add(newGame);
		vb.setAlignment(Pos.CENTER_RIGHT);
		hb.getChildren().add(vb);
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// TODO Auto-generated method stub
				click();
				System.exit(0);

			}

		});

		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				click();
				// TODO Auto-generated method stub
				HomeScreen hm = new HomeScreen(stage);
				hm.screen();
			//	createNewLevel(levelModel);
				//hm.createNewLevel(levelModel);
				

			}
		});
		Scene gameOver = new Scene(hb, 800, 500);
		stage.setScene(gameOver);
		stage.show();
		GameOverSound();
	}
	//////////////////////////////
	
	/////////////////////////////////
	

	// --------------------------------Controller Related
	// Functions-------------------------------------
	public void updateScore(int value) {
		currentScore.setText("" + value);
		currentScore.setLayoutX(10);
		currentScore.setLayoutY(0);
	}

	public void updateBestScore(int value) {
		bestScoreLabel.setText("best :" + value);
	}

	public void updateLives(int value) {
		livesLabel.setText(Integer.toString(value));
	}

	// -------------------------------SoundFunctions---------------------------------------------

	protected void GameOverSound() {
		AudioClip ov = new AudioClip(this.getClass()
				.getResource("gameOver.mp3").toString());
		ov.play();
	}

	protected void soundSlicing() {
		AudioClip s = new AudioClip(this.getClass()
				.getResource("Slice.mp3").toString());
		s.play();

	}

	protected void bombSlicing() {
		AudioClip s = new AudioClip(this.getClass()
				.getResource("bombSound.mp3").toString());
		s.play();

	}
	protected void click() {
		AudioClip s = new AudioClip(this.getClass()
				.getResource("click.mp3").toString());
		s.play();

	}
//	private void gameMusic() {
//		String musicFile = "audio_hero_Laughs-And-Giggles_SIPML_K-04-01-01.mp3"; // For example
//		Media sound = new Media(new File(musicFile).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(sound);
//		mediaPlayer.play();
//	}
}