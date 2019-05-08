package gui.related;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreen {
	Stage stage;
	public HomeScreen(Stage stage) {
		this.stage=stage;
	}
	private Button easy = new Button("Easy");
	private Button medium = new Button("Medium");
	private Button hard = new Button("Hard");
	private void setStyle (Button button) {
		button.setStyle("-fx-font-size: 15pt;");
		//law 3ayez a add kaza style lel buttons heya method wa7da mesh lazem amla el code kolo nfs el setstyle wenabi
	}
	public void screen() {
		setStyle(easy);
		setStyle(medium);
		setStyle(hard);
		VBox vb = new VBox(20);
		vb.getChildren().addAll(easy , medium , hard);
		vb.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(vb , 800 , 600);
		stage.setScene(scene);
	}

}
