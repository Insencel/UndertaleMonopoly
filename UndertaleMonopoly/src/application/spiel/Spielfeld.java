package application.spiel;

import javafx.scene.image.Image;

public abstract class Spielfeld
{
	protected Image bild;
	
	public Spielfeld(Image bild)
	{
		this.bild = bild;
	}
	
	public abstract void funktion(Spieler s);
}
