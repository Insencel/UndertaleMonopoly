package application.gui;

import application.spiel.Spiel;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class SpielfeldController
{
	@FXML
	private GridPane gp;
	@FXML
	private ImageView sp1;
	@FXML
	private ImageView sp2;
	@FXML
	private ImageView sp3;
	@FXML
	private ImageView sp4;
	@FXML
	private ImageView move;
	@FXML
	private ImageView würfelL;
	@FXML
	private ImageView würfelR;
	
	public static Spiel spiel = new Spiel();

	@SuppressWarnings("static-access")
	@FXML
	public void richtigStellen()
	{
		switch(spiel.getSpieler().length)
		{
		case 4:
			gp.setRowIndex(sp4, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[0]);
			gp.setColumnIndex(sp4, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[0]);
			
		case 3:
			gp.setRowIndex(sp3, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[0]);
			gp.setColumnIndex(sp3, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[0]);
			
		case 2:
			gp.setRowIndex(sp2, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[0]);
			gp.setColumnIndex(sp2, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[0]);
			
			gp.setRowIndex(sp1, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[0]);
			gp.setColumnIndex(sp1, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[0]);
		}
		
		
		switch(spiel.getSpieler().length)
		{
		case 2:
			sp3.setVisible(false);
		case 3:
			sp4.setVisible(false);
		}
		
		würfelL.setVisible(true);
		würfelR.setVisible(true);
	}
	
	
	@SuppressWarnings("static-access")
	@FXML
	public void bewegen()
	{
		int[] würfel = spiel.momentanenSpielerBewegen();
		
		würfelL.setImage(new Image("bilder/würfel/Würfel " + (würfel[0] +1) + ".jpg"));
		würfelR.setImage(new Image("bilder/würfel/Würfel " + (würfel[1] +1) + ".jpg"));
		
		int zuBewegenderSpieler = spiel.getAmZug() -1;
		if(zuBewegenderSpieler<0)
		{
			zuBewegenderSpieler = spiel.getSpieler().length;
		}
		
		int spielerPosition = spiel.getSpieler()[zuBewegenderSpieler].getPosition();
		
		int[] position = spiel.getTabellenposition(spielerPosition);
		switch(zuBewegenderSpieler)
		{
		case 0:
			gp.setColumnIndex(sp1, position[0]);
			gp.setRowIndex(sp1, position[1]);
			break;
		case 1:
			gp.setColumnIndex(sp2, position[0]);
			gp.setRowIndex(sp2, position[1]);
			break;
		case 2:
			gp.setColumnIndex(sp3, position[0]);
			gp.setRowIndex(sp3, position[1]);
			break;
		case 3:
			gp.setColumnIndex(sp4, position[0]);
			gp.setRowIndex(sp4, position[1]);
			break;
		}
	}
	
	
	
	
	
	
	
}
