package application.spiel;

import java.util.ArrayList;
import java.util.Random;

public class Spiel
{
	private Spielfeld[] spielfelder = {new KaufbaresFeld(60, Spielfeldgebiet.RUINS)};
	private final int[][] tabellenposition = {{1, 11}, {1, 10}, {1, 9}, {1, 8}, {1, 7}, {1, 6}, {1, 5}, {1, 4}, {1, 3}, {1, 2}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {9, 1}, {10, 1}, {11,1} , {11, 2}, {11, 3}, {11, 4}, {11, 5}, {11, 6}, {11, 7}, {11, 8}, {11, 9}, {11, 10}, {11, 11}, {10, 11}, {9, 11}, {8, 11},{7, 11}, {6, 11}, {5, 11}, {4, 11}, {3, 11}, {2, 11}};
	//TODO Überlegen wie ein Kartendeck funktioniert
	private Spieler[] spieler;
	private int amZug;
	private int paschcounter;
	
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
	
	public int[] momentanenSpielerBewegen()
	{
		Random rdm = new Random();
		int[] würfel = {rdm.nextInt(), rdm.nextInt()};
		
		for(int i = 0; i<würfel.length; i++)
		{
			spieler[amZug].setPosition(spieler[amZug].getPosition() + würfel[i]);
		}
		
		if(spieler[amZug].getPosition()>tabellenposition.length)
		{
			spieler[amZug].setPosition(spieler[amZug].getPosition()-tabellenposition.length);
		}
		
		if(würfel[0] == würfel[1])
		{
			paschcounter++;
		}
		else
		{
			paschcounter = 0;
			nächsterSpieler();
		}
		
		return würfel;
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
			amZug++;
		}
		
	}

	
	
	public int getPaschcounter() {
		return paschcounter;
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
