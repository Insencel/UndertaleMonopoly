package application.spiel;

import javafx.scene.image.Image;

public class KaufbaresFeld extends Spielfeld {
	private Spieler besitzer;
	private final int preis;
	private String typ;
	//TODO eventuell typ auf ENUM umändern
	private byte häuser;
	private boolean hotel;
	
	public KaufbaresFeld(Image bild, int preis, String typ)
	{
		super(bild);
		this.preis = preis;
		this.typ = typ;
	}
	
	@Override
	public void funktion(Spieler s)
	{
		/* TODO Abfragen ob der Spieler, der das Spielfeld betreten hat (Parameter) der
		 * Besitzer ist. Wenn nicht, überweist er die hälfte von preis (feld) an den Besitzer. (+ eventuell Haus und Hotelszeugs)
		 * Ansonsten kann er es kaufen
		 */
		if(besitzer==null)
		{
			if(s.getGold() >= preis)
			{
				//Kaufen?
				if(true)
				{
					s.setGold(s.getGold()-preis);
				}
			}
		}
		else if(besitzer != s)
		{
			s.überweisen(aufenthaltBerechnen(), besitzer);
		}
		else
		{
			//nichts passiert
		}
	}
	
	public int aufenthaltBerechnen()
	{
		return preis/2;
	}
	
	public void setBesitzer(Spieler besitzer)
	{
		this.besitzer = besitzer;
	}

	
}
