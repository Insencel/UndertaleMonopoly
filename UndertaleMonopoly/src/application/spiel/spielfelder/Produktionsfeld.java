package application.spiel.spielfelder;

public class Produktionsfeld extends KaufbaresFeld {

	public Produktionsfeld(String name, int preis)
	{
		super(name, preis);
	}

	@Override
	public int aufenthaltBerechnen()
	{
		if(besitzer)
		return 0;
	}
	
	@Override
	public int[] getPreisliste()
	{
		int[] liste = {};
		return liste;
	}

}
