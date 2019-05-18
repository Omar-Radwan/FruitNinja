
import Controller.Controller;
import gui.related.HomeScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// System.out.println(new Banana().getImages()[0]);
		// System.out.println(new Mango().getImages()[0]);

		HomeScreen homeScreen = new HomeScreen(primaryStage);
		homeScreen.screen();
		homeScreen.setController(new Controller());
		primaryStage.setResizable(false);
		primaryStage.setTitle("Fruit Ninja");
		primaryStage.show();

	}

}
