package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage primaryStage;
	
	
	@Override
	public void start(Stage primaryStage)
	{
		Main.primaryStage = primaryStage;
		try
		{
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Startscreen.fxml"));
			Scene scene = new Scene((Parent) loader.load());
			primaryStage.setScene(scene);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
