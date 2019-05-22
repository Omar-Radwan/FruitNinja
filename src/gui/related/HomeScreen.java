package gui.related;

import controller.IController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import levelmodels.ArcadeLevelModel;
import levelmodels.EasyLevelModel;
import levelmodels.HardLevelModel;
import levelmodels.ILevelModel;
import levelmodels.MediumLevelModel;

public class HomeScreen {
	private Stage stage;
	private IController controller;

	public HomeScreen() {

	}

	public IController getController() {
		return controller;
	}

	public void setController(IController controller) {
		this.controller = controller;
	}

	public HomeScreen(Stage stage) {
		this.stage = stage;
	}

	private BackgroundImage bgImg;

	private Button easy = new Button("Easy");
	private Button medium = new Button("Medium");
	private Button hard = new Button("Hard");
	private LevelView level;

	private void setStyle(Button button) {

		// button.setStyle("-fx-font-size: 15pt;");
		// law 3ayez a add kaza style lel buttons heya method wa7da mesh lazem amla el
		// code kolo nfs el setstyle wenabi

		button.setStyle("-fx-background-color: \r\n" + "        #F79704,\r\n"
				+ "        linear-gradient(#F79704 50%, white 100%),\r\n"
				+ "        radial-gradient(center 50% -40%, radius 200%, #F79704 45%, rgba(230,230,230,0) 50%);\r\n"
				+ "    -fx-background-radius: 30;\r\n" + "    -fx-background-insets: 0,1,1;\r\n"
				+ "    -fx-text-fill: black;\r\n"
				+ "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");

		button.setMaxSize(100, 800);

	}

	public void screen2() {
		setStyle(easy);
		setStyle(medium);
		setStyle(hard);
		VBox vb = new VBox(20);
		vb.getChildren().addAll(easy, medium, hard);
		vb.setAlignment(Pos.CENTER);
		// TODO :easy set on action
		easy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				click();
				createNewLevel(new EasyLevelModel());
			}
		});
		// TODO : medium set on action
		medium.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				click();
				createNewLevel(new MediumLevelModel());
			}
		});
		// TODO: hard set on action
		hard.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				click();
				createNewLevel(new HardLevelModel());
			}
		});

		Image img = new Image("file:src/gui/related/fruit-ninjaBackground.jpg");
		bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

		vb.setBackground(new Background(bgImg));

		Scene scene = new Scene(vb, 800, 500);
		stage.setScene(scene);
	}

	public void screen1() {
		Button arcadeMode = new Button("Arcade Mode");
		Button normalMode = new Button("Normal Mode");
		setStyle(arcadeMode);
		setStyle(normalMode);

		arcadeMode.setOnAction(e -> {
			createNewArcadeLevel(new ArcadeLevelModel());
		});
		normalMode.setOnAction(e -> {
			screen2();

		});
		VBox vb = new VBox(20);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(arcadeMode, normalMode);
		vb.setBackground(new Background(bgImg));
		Image img = new Image("file:src/gui/related/fruit-ninjaBackground.jpg");
		bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

		vb.setBackground(new Background(bgImg));

		Scene scene = new Scene(vb, 800, 500);
		stage.setScene(scene);

		stage.setResizable(false);
		stage.setTitle("Fruit Ninja");

		stage.show();
	}

	private void createNewLevel(ILevelModel levelModel) {
		controller.setLevelModel(levelModel);
		level = new NormalMode(controller, stage);
		controller.setLevelView(level);

	}

	private void createNewArcadeLevel(ILevelModel levelModel) {
		controller.setLevelModel(levelModel);
		level = new ArcadeMode(controller, stage);
		controller.setLevelView(level);

	}

	protected void click() {
		AudioClip s = new AudioClip(this.getClass().getResource("click.mp3").toString());
		s.play();

	}

}
