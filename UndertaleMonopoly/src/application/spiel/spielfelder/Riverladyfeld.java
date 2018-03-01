package application.spiel.spielfelder;

public class Riverladyfeld extends KaufbaresFeld {

	public Riverladyfeld(String name, int preis)
	{
		super(name, preis);
	}

	@Override
	public int aufenthaltBerechnen()
	{
		
		return 0;
	}

}
