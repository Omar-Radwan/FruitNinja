package gui.related;

import java.util.ArrayList;
import java.util.Random;

import Controller.IController;
import gameobjects.IGameObject;
import javafx.animation.KeyFrame;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ArcadeMode extends LevelView {

	public ArcadeMode(IController controller, Stage stage) {
		super(controller, stage);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void level() {
		level();
		timeSeconds= 60;
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {

			@Override
			public void handle(Event event) {
				timeSeconds--;
				timerLabel.setText(Integer.toString(timeSeconds));
				if(timeSeconds == 0)
					GameOverScene();
			}
		}));
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
	public void cut() 
		{
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
							time = timeSeconds -4;

						}

					}
					for (int i = 0; i < normalBomb.size(); i++) {
						Sprite x = normalBomb.get(i);

						if (x.intersects(mouse) && !x.isChangedImage()) {
							controller.sliceNonFatalBomb(i);
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

