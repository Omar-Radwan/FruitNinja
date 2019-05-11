package gui.related;

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
		

		
		
		VBox vb = new VBox(10);
		
		vb.getChildren().addAll(currentScore , best);
		root.getChildren().addAll(vb,timerLabel);
				Scene scene = new Scene(root , 800 , 500);
		stage.setScene(scene);
		
		
	}
	
	
}
