package application.spiel;

import java.util.ArrayList;

public class Spiel
{
	private Spielfeld[] spielfelder = {new KaufbaresFeld(60, Spielfeldgebiet.RUINS)};
	private final int[][] tabellenposition = {{1, 11}, {1, 10}, {1, 9}, {1, 8}, {1, 7}, {1, 6}, {1, 5}, {1, 4}, {1, 3}, {1, 2}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {9, 1}, {10, 1}, {11,1} , {11, 2}, {11, 3}, {11, 4}, {11, 5}, {11, 6}, {11, 7}, {11, 8}, {11, 9}, {11, 10}, {11, 11}, {10, 11}, {9, 11}, {8, 11},{7, 11}, {6, 11}, {5, 11}, {4, 11}, {3, 11}, {2, 11}};
	//TODO Überlegen wie ein Kartendeck funktioniert
	private Spieler[] spieler;
	private int amZug;
	
	public Spiel()
	{
		this.spieler = new Spieler[] {new Spieler(), new Spieler(), new Spieler(), new Spieler()};
		this.amZug = 0;
	}
	
	public Spiel(int amZug, ArrayList<Spieler> spieler, ArrayList<Integer> besitzer)
	{
		this.amZug = amZug;
		this.spieler = spieler.toArray(new Spieler[spieler.size()]);
		setzeBesitzer(besitzer);
	}
	
	
	
	
	

	public void spielen()
	{
		nächsterSpieler();
	}
	
	public void überLos()
	{
		
	}
	
	private void setzeBesitzer(ArrayList<Integer> besitzer)
	{
		int besitzerCounter = 0;
		
		for(int i = 0; i<this.spielfelder.length; i++, besitzerCounter++)
		{
			if(this.spielfelder[i] instanceof KaufbaresFeld)
			{
				KaufbaresFeld k = (KaufbaresFeld) this.spielfelder[i];
				k.setBesitzer(spieler[besitzer.get(besitzerCounter)]);
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

	
	
	
	
	public int getAmZug() {
		return amZug;
	}

	public void setAmZug(int amZug) {
		this.amZug = amZug;
	}

	public Spielfeld[] getSpielfelder() {
		return spielfelder;
	}

	public int[] getTabellenposition(int i) {
		return tabellenposition[i];
	}

	public Spieler[] getSpieler() {
		return spieler;
	}
	
	
}
