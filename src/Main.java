
import Controller.Controller;
import factories.ObjectFactory;
import gui.related.HomeScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		HomeScreen homeScreen = new HomeScreen(primaryStage);

		homeScreen.screen();
		homeScreen.setController(new Controller());

		primaryStage.setResizable(false);
		primaryStage.setTitle("Fruit Ninja");
		primaryStage.show();

	}

}
