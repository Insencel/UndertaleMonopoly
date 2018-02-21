package application.spiel;


public class Spiel
{
	private Spielfeld[] spielfelder = {new KaufbaresFeld(60, Spielfeldgebiet.RUINS)};
	private final int[][] tabellenposition = {{1, 10}, {1, 9}, {1, 8}, {1, 7}, {1, 6}, {1, 5}, {1, 4}, {1, 3}, {1, 2}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {9, 1}, {10, 1}, {10, 2}, {10, 3}, {10, 4}, {10, 5}, {10, 6}, {10, 7}, {10, 8}, {10, 9}, {10, 10}, {9, 10}, {8, 10},{7, 10}, {6, 10}, {5, 10}, {4, 10}, {3, 10}, {2, 10}};
	//TODO Überlegen wie ein Kartendeck funktioniert
	private Spieler[] spieler;
	private int amZug;
	
	public Spiel()
	{
		this.spieler = new Spieler[] {new Spieler(), new Spieler(), new Spieler(), new Spieler()};
		spielen();
	}
	
	public Spiel(int amZug, Spieler[] spieler, Spielfeld[] spielfelder)
	{
		this.amZug = amZug;
		this.spieler = spieler;
		ersetzeKaufbareFelder(spielfelder);
	}
	
	
	
	
	

	public void spielen()
	{
		nächsterSpieler();
	}
	
	public void überLos()
	{
		
	}
	
	private void ersetzeKaufbareFelder(Spielfeld[] kaufbareFelder)
	{
		int kaufbaresFeldCounter = 0;
		
		for(int i = 0; i<this.spielfelder.length; i++, kaufbaresFeldCounter++)
		{
			if(this.spielfelder[i] instanceof KaufbaresFeld)
			{
				this.spielfelder[i] = kaufbareFelder[kaufbaresFeldCounter];
			}
		}
	}
	
	private void nächsterSpieler()
	{
		if(amZug>=spieler.length-1)
		{
			amZug = 0;
		}
		else
		{
			amZug = amZug+1;
		}
		
	}
	
	public void setSpieler(Spieler[] s)
	{
		this.spieler = s;
	}
}
