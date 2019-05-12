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
import javafx.animation.Timeline;
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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;



public abstract class LevelType extends HomeScreen {
	private Group root = new Group();
	private GraphicsContext gc;
	private Canvas canvas = new Canvas(800 , 500);
	static int bestScore = 0;
	private int StartTime =0;
	private Label timerLabel = new Label();
	private Integer timeSeconds =StartTime;
	private Timeline timeline;
	private Random random = new Random();
	
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
		
		timerLabel.setText(timeSeconds.toString());
		timerLabel.setTextFill(Color.RED);
		timerLabel.setStyle("-fx-font-size: 4em;");
		timerLabel.setLayoutX(750);
		timerLabel.setLayoutY(0);
		timeSeconds = StartTime;
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(
		        new KeyFrame(Duration.seconds(1),
		          new EventHandler() {

					@Override
					public void handle(Event event) {
						   
		                        timeSeconds++;
		                        // update timerLabel
		                        timerLabel.setText(
		                              timeSeconds.toString());
//		                        if (timeSeconds <= 0) {
//		                            timeline.stop();
//		                        }
		                      
						
					}
		            // KeyFrame event handler
		         
		        }));
		timeline.playFromStart();
		
		setImages();
		VBox vb = new VBox(10);
		vb.getChildren().addAll(currentScore , best);
		root.getChildren().addAll(vb , timerLabel);
				Scene scene = new Scene(root , 800 , 500);
		stage.setScene(scene);
		
		
	}
	public void setImages() {
		int i =random.nextInt(3);
		if(i == 0) 
			objects.add(new Sprite(new Image("file:src/gui/related/Banana.png")));
		else if(i == 1) 
			objects.add(new Sprite(new Image("file:src/gui/related/Mango.png")));
		
		else if( i == 2) 
			objects.add(new Sprite(new Image("file:src/gui/related/Watermelon.png")));
			
		setPositionX(objects.get(objects.size() -1));
		rendering();
		
		
	}
	public void rendering() {
		for(Sprite e : objects)
			e.render(gc);
	}
	public void setPositionX(Sprite e) {
	//	for(Sprite e : objects) {
		/*int positionX = *///random.nextInt(200);
		e.setPositinoX(random.nextInt(200));
	}
	public void setPath() {
		
		QuadCurveTo quadCurve = new QuadCurveTo();
		quadCurve.setX(200 + random.nextInt(500));
		quadCurve.setY(500);
		quadCurve.setControlX(objects.get(0).getPositionX());
		quadCurve.setControlY(500);
		
		
		Path path = new Path( new PathElement[] {quadCurve});
	/*	PathTransition pathT = new PathTransition();
		pathT.setNode(objects.get(0));
		*/

	}
	
	
	
	
}
