package application.gui;

import java.io.IOException;

import application.Main;
import application.datenbankanbindung.SpielDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
	private ImageView back2;
	@FXML
	private ImageView newGame;
	@FXML
	private ImageView fortsetzen;
	@FXML
	private TextField tf;
	
	
	SpielDB sdb = new SpielDB();
	
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
	
	@FXML
	public void zurueck()
	{
		gp2.setVisible(false);
		gp2.setDisable(true);
		gp3.setVisible(false);
		gp3.setDisable(true);
		
		gp1.setVisible(true);
		gp1.setDisable(false);
	}
	
	
	
	@FXML
	public void neuesSpielKnopf()
	{
		gp1.setVisible(false);
		gp1.setDisable(true);
		
		gp2.setVisible(true);
		gp2.setDisable(false);
	}
	
	@FXML
	public void fortsetzenKnopf()
	{
		gp1.setVisible(false);
		gp1.setDisable(true);
		
		gp3.setVisible(true);
		gp3.setDisable(false);
	}
	
	
	@FXML
	public void startmoeglich()
	{
		System.out.println("HI");
		if(!sdb.isSpielExistent(tf.getText()))
		{
			System.out.println("Yay");
		}
		else
		{
			System.out.println("WTF?!?!?!");
		}
	}
	
	
	
	
	@FXML
	public void zurueckHoverOn()
	{
		back.setImage(new Image("bilder/startscreen/back_hover.png"));
		back2.setImage(new Image("bilder/startscreen/back_hover.png"));
	}
	@FXML
	public void zurueckHoverOff()
	{
		back.setImage(new Image("bilder/startscreen/back.png"));
		back2.setImage(new Image("bilder/startscreen/back.png"));
	}
	
	@FXML
	public void neuesSpielHoverOn()
	{
		newGame.setImage(new Image("bilder/startscreen/new_game_hover.png"));
	}
	@FXML
	public void neuesSpielHoverOff()
	{
		newGame.setImage(new Image("bilder/startscreen/new_game.png"));
	}
	
	@FXML
	public void fortsetzenHoverOn()
	{
		fortsetzen.setImage(new Image("bilder/startscreen/continue_hover.png"));
	}
	@FXML
	public void fortsetzenHoverOff()
	{
		fortsetzen.setImage(new Image("bilder/startscreen/continue.png"));
	}
	
}
