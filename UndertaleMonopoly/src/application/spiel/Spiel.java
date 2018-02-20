package application.spiel;

import java.util.ArrayList;

public class Spiel
{
	private Spielfeld[] spielfelder;
	private final int[][] tabellenposition = {{1, 10}, {1, 9}, {1, 8}, {1, 7}, {1, 6}, {1, 5}, {1, 4}, {1, 3}, {1, 2}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {9, 1}, {10, 1}, {10, 2}, {10, 3}, {10, 4}, {10, 5}, {10, 6}, {10, 7}, {10, 8}, {10, 9}, {10, 10}, {9, 10}, {8, 10},{7, 10}, {6, 10}, {5, 10}, {4, 10}, {3, 10}, {2, 10}};
	//TODO Überlegen wie ein Kartendeck funktioniert
	private Spieler[] spieler;
	private int amZug;
	
	public Spiel()
	{
		this.spieler = new Spieler[] {new Spieler(), new Spieler(), new Spieler(), new Spieler()};
		spielen();
	}
	
	public Spiel(int amZug, Spieler[] spieler)
	{
		this.amZug = amZug;
		this.spieler = spieler;
	}
	
	
	public void spielen()
	{
		nächsterSpieler();
	}
	
	public void überLos()
	{
		
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
