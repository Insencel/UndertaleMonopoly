package application.spiel;

import javafx.scene.image.Image;

public abstract class Spezialfeld extends Spielfeld
{
	
	public Spezialfeld(Image bild, String name)
	{
		super(bild);
		this.bild = bild;
	}
	
	@Override
	public void funktion(Spieler s)
	{
		// TODO funktionscode einfügen
	}
}
