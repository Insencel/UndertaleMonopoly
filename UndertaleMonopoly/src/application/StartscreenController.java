package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
			Main.primaryStage.setScene((Scene) loader.load());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
