package gui.related;

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
import javafx.stage.Stage;

public class HomeScreen {
	protected Stage stage;
	public HomeScreen() {
		
	}
	public HomeScreen(Stage stage) {
		this.stage = stage;
	}

	private Button easy = new Button("Easy");
	private Button medium = new Button("Medium");
	private Button hard = new Button("Hard");
	private LevelType level;

	private void setStyle(Button button) {
		button.setStyle("-fx-font-size: 15pt;");
		// law 3ayez a add kaza style lel buttons heya method wa7da mesh lazem amla el
		// code kolo nfs el setstyle wenabi
	}

	public void screen() {
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
				level = new EasyLevel();

			}
		});
		// TODO : medium set on action
		medium.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				level = new MediumLevel();

			}
		});
		// TODO: hard set on action
		hard.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				level = new HardLevel();

			}
		});

		Image img = new Image("file:src/gui/related/fruit-ninjaBackground.jpg");

		BackgroundImage bgImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

		vb.setBackground(new Background(bgImg));

		Scene scene = new Scene(vb, 800, 500);
		stage.setScene(scene);
	}

}
