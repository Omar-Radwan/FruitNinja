package gui.related;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class LevelType extends HomeScreen {
	private Group root = new Group();
	private GraphicsContext gc;
	private Canvas canvas = new Canvas(800 , 500);
	static int bestScore = 0;
	public void level(Stage stage) {
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		Image background = new Image("file:src/gui/related/background.jpg");
		BackgroundImage bgImg = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
		//HBox hb = new HBox();
		gc.drawImage(background, 0, 0);
		//hb.setBackground(new Background(bgImg));
		//root.getChildren().add(hb);
		
		//label to current score : 
		int currentS = 0;
		Label currentScore = new Label("Score: " + currentS);
		Label best = new Label("Best: " + bestScore);
		VBox vb = new VBox(10);
		vb.getChildren().addAll(currentScore , best);
		root.getChildren().add(vb);
				Scene scene = new Scene(root , 800 , 500);
		stage.setScene(scene);
		
		
	}
	
	
}
