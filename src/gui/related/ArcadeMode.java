package gui.related;

import java.util.ArrayList;
import java.util.Random;

import Controller.IController;
import gameobjects.IGameObject;
import javafx.animation.KeyFrame;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ArcadeMode extends LevelView {

	/*private ArrayList<Sprite> fatalBomb = new ArrayList<Sprite>();
	private ArrayList<Sprite> specialFruit = new ArrayList<Sprite>();
*/	public ArcadeMode(IController controller, Stage stage) {
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
				timeSeconds++;
				timerLabel.setText(Integer.toString(timeSeconds));

			}
		}));
		livesLabel.setVisible(false);
		}
	public void setSpecialFruit() {
		IGameObject x = controller.getSpecialBanana();
		specialFruit.add(new Sprite(x.getImages()[0], x.getNumber()));

		Random r = new Random();

		specialFruit.get(specialFruit.size() - 1).setPositinoX(r.nextInt(700) + 10);
		specialFruit.get(specialFruit.size() - 1).setPositionY(-50);

	}
	
	

}
