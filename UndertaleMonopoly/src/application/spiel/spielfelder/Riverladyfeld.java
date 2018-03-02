package application.spiel.spielfelder;

public class Riverladyfeld extends KaufbaresFeld {

	public Riverladyfeld(String name, int preis)
	{
		super(name, preis);
	}

	@Override
	public int aufenthaltBerechnen()
	{
		return getPreisliste()[besitzer.getAnzahlAnHäfenImBesitz()-1];
	}
	
	@Override
	public int[] getPreisliste()
	{
		int[] liste = {25, 50, 100, 200};
		return liste;
	}

}
