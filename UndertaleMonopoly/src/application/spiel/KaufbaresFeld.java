package application.spiel;


public class KaufbaresFeld extends Spielfeld {
	private Spieler besitzer;
	private final int preis;
	private Spielfeldgebiet gebiet;
	private byte h�user;
	private boolean hotel;
	
	public KaufbaresFeld(int preis, Spielfeldgebiet gebiet)
	{
		this.preis = preis;
		this.gebiet = gebiet;
	}
	
	@Override
	public void funktion(Spieler s)
	{
		/* TODO Abfragen ob der Spieler, der das Spielfeld betreten hat (Parameter) der
		 * Besitzer ist. Wenn nicht, �berweist er die h�lfte von preis (feld) an den Besitzer. (+ eventuell Haus und Hotelszeugs)
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
			s.�berweisen(aufenthaltBerechnen(), besitzer);
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
