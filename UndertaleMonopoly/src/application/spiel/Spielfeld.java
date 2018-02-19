package application.spiel;

import javafx.scene.image.ImageView;

public abstract class Spielfeld
{
	protected ImageView bild;
	
	public Spielfeld(ImageView bild)
	{
		this.bild = bild;
	}
	
	public abstract void funktion(Spieler s);
}
