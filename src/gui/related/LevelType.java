package gui.related;

import java.util.ArrayList;
import java.util.Random;

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
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.QuadCurveTo;
import javafx.stage.Stage;

public abstract class LevelType extends HomeScreen {
	private Group root = new Group();
	private GraphicsContext gc;
	private Canvas canvas = new Canvas(800 , 500);
	static int bestScore = 0;
	private ArrayList<Sprite> objects = new ArrayList<Sprite>();
	public void level(Stage stage) {
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		Image background = new Image("file:src/gui/related/background.jpg");
		gc.drawImage(background, 0, 0);
		
		
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
	public void setImages() {
		Random random = new Random();
		int i =random.nextInt(3);
		if(i == 1)
			objects.add(new Sprite(new Image("file:src/gui/related/Banana.png")));
		else if(i == 2)
			objects.add(new Sprite(new Image("file:src/gui/related/Mango.png")));
		else if( i == 3)
			objects.add(new Sprite(new Image("file:src/gui/related/Watermelon.png")));

		
		
	}
	public void rendering() {
		for(Sprite e : objects)
			e.render(gc);
	}
	public void setPositionX() {
		Random random = new Random();
		for(Sprite e : objects) {
		int positionX = 200 + random.nextInt(500);
		e.setPositinoX(positionX);
		}
	}
	public void setPath() {
		
		QuadCurveTo quadCurve = new QuadCurveTo();
		
		Path path = new Path(new PathElement());

	}
	
	
}
