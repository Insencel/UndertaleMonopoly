package application.gui;

import java.io.IOException;

import application.Main;
import application.spiel.Spiel;
import application.spiel.Spieler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EndscreenController {

	@FXML
	private Label place1;
	@FXML
	private Label place2;
	@FXML
	private Label place3;
	@FXML
	private Label place4;
	@FXML
	private Label place3text;
	@FXML
	private Label place4text;
	@FXML
	private Label back;
	@FXML
	private GridPane screen;
	@FXML
	private Rectangle oneTimeExecute;
	
	
	@FXML
	public void ladeScreen()
	{
		oneTimeExecute.setVisible(false);
		Spiel s = SpielfeldController.spiel;
		
		Spieler[] spieler = s.getSpieler();
		
		
		for(int i = 0; i<spieler.length-1; i++)
		{
			int maxIndex = i;
			
			for(int j = i; j<spieler.length; j++)
			{
				if(spieler[j].getScore() > spieler[maxIndex].getScore())
				{
					maxIndex = j;
				}
			}
			Spieler zwischenspeicher = spieler[i];
			spieler[i] = spieler[maxIndex];
			spieler[maxIndex] = zwischenspeicher;
		}
		
		
		place1.setText("Player " + (SpielfeldController.spiel.getIndexOfSpieler(spieler[0])+1) + " (Goldscore: " + spieler[0].getScore() + " G)");
		place2.setText("Player " + (SpielfeldController.spiel.getIndexOfSpieler(spieler[1])+1) + " (Goldscore: " + spieler[1].getScore() + " G)");
		
		switch(spieler.length)
		{
		case 4:
			place4.setText("Player " + (SpielfeldController.spiel.getIndexOfSpieler(spieler[3])+1) + " (Goldscore: " + spieler[3].getScore() + " G)");
			place4.setVisible(true);
			place4text.setVisible(true);
		case 3:
			place3.setText("Player " + (SpielfeldController.spiel.getIndexOfSpieler(spieler[2])+1) + " (Goldscore: " + spieler[2].getScore() + " G)");
			place3.setVisible(true);
			place3text.setVisible(true);
		}
		
		
		screen.setVisible(true);
		
		SpielfeldController.sdb.alleMitNamenLoeschen(SpielfeldController.spiel.getName());
	}
	
	
	@FXML
	public void back()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(StartscreenController.class.getResource("Startscreen.fxml"));
			Scene scene = new Scene((Parent) loader.load());
			Main.primaryStage.setScene(scene);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	public void backHoverOn()
	{
		back.setTextFill(Color.YELLOW);
	}
	
	@FXML
	public void backHoverOff()
	{
		back.setTextFill(Color.WHITE);
	}
	
	
}