package application.spiel;

import javafx.scene.image.Image;

public class Spieler {
	private int gold;
	private Image bild;
	private int position;
	private Würfel[] würfel = {new Würfel(), new Würfel()}; //Die zwei Würfel
	
	public void überweisen(int gold, Spieler s)
	{
		//TODO überweisen von Gold an einen anderen Spieler
	}
	
	public void kaufen(Spielfeld sf)
	{
		
	}
	
	public void bewegen()
	{
		
	}
}
