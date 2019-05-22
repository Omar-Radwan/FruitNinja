package gui.related;

import java.util.ArrayList;
import java.util.Random;

import Controller.IController;
import gameobjects.IGameObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ArcadeMode extends LevelView {

	public ArcadeMode(IController controller, Stage stage) {
		super(controller, stage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void level() {
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		gc.drawImage(background, 0, 0);

		// label to current score :

		currentScore = new Label();
		currentScore.setLayoutX(50);
		currentScore.setLayoutY(0);
		currentScore.setTextFill(Color.GOLDENROD);
		currentScore.setFont(Font.font("Vineta BT", FontWeight.EXTRA_BOLD, 20));

		bestScoreLabel = new Label();
		bestScoreLabel.setTextFill(Color.GREEN);
		bestScoreLabel.setFont(Font.font("Broadway", FontWeight.EXTRA_BOLD, 20));

		timerLabel.setText(Integer.toString(timeSeconds));
		timerLabel.setTextFill(Color.RED);
		timerLabel.setStyle("-fx-font-size: 4em;");
		timerLabel.setLayoutX(750);
		timerLabel.setLayoutY(0);

		//timeSeconds = 0;
		timeline = new Timeline();

		timeline.setCycleCount(Timeline.INDEFINITE);

		timeSeconds = 60;
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {

			@Override
			public void handle(Event event) {
				timeSeconds--;
				timerLabel.setText(Integer.toString(timeSeconds));
				if (timeSeconds == 0)
					GameOverScene();
			}
		}));

		timeline.playFromStart();

		Image image2 = new Image("file:src/gui/related/animated-heart-image-0503.gif", 40, 40, false, false);

		setLabel(livesLabel);
		livesLabel.setGraphic(new ImageView(image2));
		livesLabel.setLayoutX(720);
		livesLabel.setLayoutY(70);

		Image image3 = new Image("file:src/gui/related/score.png", 60, 60, false, false);

		scoreImg.setGraphic(new ImageView(image3));
		scoreImg.setLayoutX(10);
		scoreImg.setLayoutY(0);

	//	root.getChildren().add(scoreImg);
		root.getChildren().add(livesLabel);

		repeatingImage();
		repeatingnormalBomb();
		repeatSpecialFruit();

		VBox vb = new VBox(10);
		vb.getChildren().addAll(currentScore, bestScoreLabel);
		root.getChildren().addAll(vb, timerLabel);
		scene = new Scene(root, 800, 500);

		Image mouse = new Image("file:src/gui/related/knife.png");
		scene.setCursor(new ImageCursor(mouse));
		stage.setScene(scene);
		cut();
		
		livesLabel.setVisible(false);
	}

	@Override
	public void setSpecialFruit() {
		IGameObject x = controller.getSpecialBanana();
		specialFruit.add(new Sprite(x.getImages()[0], x.getNumber()));

		Random r = new Random();

		specialFruit.get(specialFruit.size() - 1).setPositinoX(r.nextInt(700) + 10);
		specialFruit.get(specialFruit.size() - 1).setPositionY(-50);

	}

	@Override
	public void cut() {
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// gameMusic();

				mouse.setPosXOfMouse(event.getX());
				mouse.setPosYOfMouse(event.getY());

				for (int i = 0; i < objects.size(); i++) {
					Sprite x = objects.get(i);
					if (x.intersects(mouse) && !x.isChangedImage()) {
						int number = x.getNumber();
						soundSlicing();
						Image img2 = new Image("file:src/gui/related/" + types[number] + "sliced.png");
						x.getImage().setDisable(true);
						x.setImage(img2);
						controller.sliceFruit(i);
						x.setChangedImage(true);
						x.getImage().setDisable(false);

					}

				}

				for (int i = 0; i < specialFruit.size(); i++) {
					Sprite x = specialFruit.get(i);

					if (x.intersects(mouse) && !x.isChangedImage()) {
						int number = x.getNumber();
						soundSlicing();
						Image img2 = new Image("file:src/gui/related/sliced" + types[number] + ".png");
						x.getImage().setDisable(true);
						x.setImage(img2);
						x.setChangedImage(true);
						x.getImage().setDisable(false);
						controller.sliceSpecialFruit(i);
						time = timeSeconds - 4;

					}

				}
				for (int i = 0; i < normalBomb.size(); i++) {
					Sprite x = normalBomb.get(i);

					if (x.intersects(mouse) && !x.isChangedImage()) {
						controller.arcadeSliceBomb(i);;
						bombSlicing();
						Image img2 = new Image("file:src/gui/related/boooomb.png", 80, 80, false, false);
						x.getImage().setDisable(true);
						x.setImage(img2);
						x.setChangedImage(true);
						x.getImage().setDisable(false);

					}
				}

				int x = timeSeconds;
				if (x <= time) {
					controller.endDoubleScore();
				}

			}

		});

	}

}
