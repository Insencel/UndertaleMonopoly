package application.gui;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class StartscreenController
{
	
	@FXML
	private GridPane gp1;
	@FXML
	private GridPane gp2;
	@FXML
	private GridPane gp3;
	@FXML
	private ImageView back;
	
	@FXML
	private void start()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(StartscreenController.class.getResource("Spielfeld.fxml"));
			Scene scene = new Scene((Parent) loader.load());
			Main.primaryStage.setScene(scene);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void zurueck()
	{
		gp2.setVisible(false);
		gp2.setDisable(true);
		gp3.setVisible(false);
		gp3.setDisable(true);
		
		gp1.setVisible(true);
		gp1.setDisable(false);
	}
	
	public void zurueckHoverOn()
	{
		back.setImage(new Image("..\\..\\bilder\\knöpfe\\back_hover.png"));
	}
	public void zurueckHoverOff()
	{
		back.setImage(new Image("..\\..\\bilder\\knöpfe\\back.png"));
	}
	
	
	public void neuesSpiel()
	{
		gp1.setVisible(false);
		gp1.setDisable(true);
		gp3.setVisible(false);
		gp3.setDisable(true);
		
		gp2.setVisible(true);
		gp2.setDisable(false);
	}
	
}
