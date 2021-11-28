package dad.gesaula;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GesAulaApp extends Application {
	
	private MainController controller;
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new MainController();
		
		GesAulaApp.primaryStage = primaryStage;
		
		Scene scene = new Scene(controller.getView(), 640, 480);
		primaryStage.setTitle("GesAula");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(getClass().getResource("/images/app-icon-64x64.png").toExternalForm()));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
