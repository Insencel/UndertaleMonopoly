package application.gui;

import application.spiel.Spiel;
import javafx.fxml.FXML;
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
	
	public static Spiel spiel = new Spiel();

	public void richtigStellen()
	{
		
		gp.setRowIndex(sp1, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[0]);
		
	}
	
	
	
	
	
}
