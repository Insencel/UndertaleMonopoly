package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try
		{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(arg0));
		}
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}