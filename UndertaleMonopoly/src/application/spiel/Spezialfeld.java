package application.spiel;

import javafx.scene.image.Image;

public abstract class Spezialfeld extends Spielfeld
{
	private String name;
	//TODO eventuell um�ndern auf ENUM, Getter f�r name einf�gen
	
	public Spezialfeld(Image bild, String name)
	{
		super(bild);
		this.bild = bild;
	}
	
	@Override
	public void funktion()
	{
		// TODO funktionscode einf�gen
	}
	
	public String getName()
	{
		return name;
	}
}
