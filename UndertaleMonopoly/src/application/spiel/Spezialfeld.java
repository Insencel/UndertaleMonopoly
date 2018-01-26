package application.spiel;

import javafx.scene.image.Image;

public abstract class Spezialfeld extends Spielfeld
{
	private String name;
	//TODO eventuell umändern auf ENUM, Getter für name einfügen
	
	public Spezialfeld(Image bild, String name)
	{
		super(bild);
		this.bild = bild;
	}
	
	@Override
	public void funktion()
	{
		// TODO funktionscode einfügen
	}
	
	public String getName()
	{
		return name;
	}
}
