package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;

public class StartscreenController {
	@FXML
	private RadioButton btn2spieler;
	@FXML
	private RadioButton btn3spieler;
	@FXML
	private RadioButton btn4spieler;
	
	@FXML
	private void start()
	{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("Spielfeld.fxml"));
		try
		{
			if(btn2spieler.)
			
			Scene scene = new Scene((Parent) loader.load());
			Main.primaryStage.setScene(scene);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
