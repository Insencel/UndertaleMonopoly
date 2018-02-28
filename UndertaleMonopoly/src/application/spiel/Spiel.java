package application.spiel;

import java.util.ArrayList;
import java.util.Random;

import application.eventkarten.*;
import application.spiel.spielfelder.*;

public class Spiel
{
	private Spielfeld[] spielfelder = {new SpielfeldOhneFunktion(), new KaufbaresFeld("Flowerfield", 60, Spielfeldgebiet.RUINS), new Aktionsfeld(), new KaufbaresFeld("Toriel's House", 60, Spielfeldgebiet.RUINS), new GeldstrafenFeld(200), new KaufbaresFeld("Riverlady West", 200, Spielfeldgebiet.RIVERLADY), new KaufbaresFeld("Conveniently-Shaped Lamp", 100, Spielfeldgebiet.SNOWDIN_FOREST), new Aktionsfeld(),  new KaufbaresFeld("Kennel", 100, Spielfeldgebiet.SNOWDIN_FOREST), new KaufbaresFeld("Long Bridge", 120, Spielfeldgebiet.SNOWDIN_FOREST), new Gefängnisfeld(), new KaufbaresFeld("Library", 140, Spielfeldgebiet.SNOWDIN), new KaufbaresFeld("Ice-Fabric", 150, Spielfeldgebiet.PRODCTION), new KaufbaresFeld("Grillby's", 140, Spielfeldgebiet.SNOWDIN), new KaufbaresFeld("Shop & Inn", 160, Spielfeldgebiet.SNOWDIN), new KaufbaresFeld("Riverlady North", 200, Spielfeldgebiet.RIVERLADY), new KaufbaresFeld("Statue", 180, Spielfeldgebiet.WATERFALL), new Aktionsfeld(),  new KaufbaresFeld("Ghost House", 180, Spielfeldgebiet.WATERFALL), new KaufbaresFeld("Temmie Village", 200, Spielfeldgebiet.WATERFALL), new SpielfeldOhneFunktion(), new KaufbaresFeld("Lab", 220, Spielfeldgebiet.HOTLAND), new Aktionsfeld(), new KaufbaresFeld("Spider", 220, Spielfeldgebiet.HOTLAND), new KaufbaresFeld("Theatre", 240, Spielfeldgebiet.HOTLAND), new KaufbaresFeld("Riverlady East", 200, Spielfeldgebiet.RIVERLADY), new KaufbaresFeld("MTT-Well", 260, Spielfeldgebiet.HOTEL), new KaufbaresFeld("MTT", 260, Spielfeldgebiet.HOTEL), new KaufbaresFeld("Core", 200, Spielfeldgebiet.PRODCTION), new KaufbaresFeld("Hotelroom", 280, Spielfeldgebiet.HOTEL), new InsGefängnisFeld(), new KaufbaresFeld("Bin", 300, Spielfeldgebiet.CORE), new KaufbaresFeld("Core-Bridge", 300, Spielfeldgebiet.CORE), new Aktionsfeld(), new KaufbaresFeld("MTT-Boss Door", 320, Spielfeldgebiet.CORE), new KaufbaresFeld("Riverlady South", 200, Spielfeldgebiet.RIVERLADY), new Aktionsfeld(),  new KaufbaresFeld("End-House", 350, Spielfeldgebiet.END), new GeldstrafenFeld(100), new KaufbaresFeld("Throne", 400, Spielfeldgebiet.END)};
	private final int[][] tabellenposition = {{1, 11}, {1, 10}, {1, 9}, {1, 8}, {1, 7}, {1, 6}, {1, 5}, {1, 4}, {1, 3}, {1, 2}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {9, 1}, {10, 1}, {11,1} , {11, 2}, {11, 3}, {11, 4}, {11, 5}, {11, 6}, {11, 7}, {11, 8}, {11, 9}, {11, 10}, {11, 11}, {10, 11}, {9, 11}, {8, 11},{7, 11}, {6, 11}, {5, 11}, {4, 11}, {3, 11}, {2, 11}};
	private final Eventkarte[] kartenstapel = {new GoldKarte("Monster Kid gives you a gift!\n\nYou earn 50 G.", 50), new GoldKarte("Sans sells you a hotdog.\n\nYou pay him 20 G.", -20), new GoldKarte("You pay for Temmies college.\n\nIt costs 200G.", -200), new GoldKarte("You buy Nice Cream.\n\nIt costs 50 G.", -50), new RandomGoldKarte("#?E2&§ |=€:%*R5 #*/R? !\\O<\" #°§R$&"), new GeheInsGefängnisKarte("Papyrus caught you whilst you were busy thinking of what this card could do.\n\nGo to jail!"), new ParalyseKarte("You see Goner Kid and are shocked.\n\nYou lose one turn."), new ParalyseKarte("Burgerpants gives you and advice.\n\nDon't live your life like I did. I am 19 years old and I wasted my entire life.\n\nYou are so stunned from this intellectual statement that you can't move next turn."), new ParalyseKarte("Papyrus cooked spaghetti for you but you don't eat them.\n\n You feel bad and won't move next turn."), new BewegenZuFeldKarte("Sans invites you to go to Grillby's.\n\nGo to Grillby's.", 13), new ZumNächstenHafenKarte("The Riverlady takes you to her next harbour.")};
	private Spieler[] spieler;
	private int amZug;
	private int paschcounter;
	private final int überStartGold = 200;
	
	
	
	public Spiel(int spieleranzahl)
	{
		spieler = new Spieler[spieleranzahl];
		for(int i = 0; i<spieler.length; i++)
		{
			this.spieler[i] = new Spieler();
		}
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
		int[] würfel = {rdm.nextInt(6)+1, rdm.nextInt(6)+1};
		
		
		for(int i = 0; i<würfel.length; i++)
		{
			spieler[amZug].setPosition(spieler[amZug].getPosition() + würfel[i]);
		}
		
		if(spieler[amZug].getPosition()>=tabellenposition.length)
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
		}
	
		
		
		return würfel;
	}
	
	
	
	
	public void überLos()
	{
		Spieler s = spieler[amZug];

		if(s.getPosition()==0)
		{
			s.plusGold(überStartGold*2);
		}
		else
		{
			s.plusGold(überStartGold);
		}
		
		
	}
	
	private void setzeBesitzer(ArrayList<Integer> besitzer)
	{
		int besitzerCounter = 0;
		
		for(int i = 0; i<this.spielfelder.length; i++)
		{
			if(this.spielfelder[i] instanceof KaufbaresFeld)
			{
				if(besitzer.get(besitzerCounter)!=-1)
				{
					KaufbaresFeld k = (KaufbaresFeld) this.spielfelder[i];
					k.setBesitzer(spieler[besitzer.get(besitzerCounter)]);
					besitzerCounter++;
				}
				else
				{
					besitzerCounter++;
				}
			}
		}
		
		
		
	}

	public void nächsterSpieler()
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

	
	public int getIndexOfTabellenposition(int[] gesucht)
	{
		int rückgabe = -1;
		for(int i = 0; i < tabellenposition.length; i++)
		{
			if(tabellenposition[i][0] == gesucht[0]   &&   tabellenposition[i][1] == gesucht[1])
			{
				rückgabe = i;
				break;
			}
		}
		
		return rückgabe;
	}
	
	public int getIndexOfSpielfeld(Spielfeld gesucht)
	{
		int rückgabe = -1;
		for(int i = 0; i < spielfelder.length; i++)
		{
			if(spielfelder[i].equals(gesucht))
			{
				rückgabe = i;
				break;
			}
		}
		
		return rückgabe;
	}
	
	public int getIndexOfSpieler(Spieler gesucht)
	{
		int rückgabe = -1;
		for(int i = 0; i < spieler.length; i++)
		{
			if(spieler[i].equals(gesucht))
			{
				rückgabe = i;
				break;
			}
		}
		
		return rückgabe;
	}
	
	public Spieler getMomentanenSpieler()
	{
		return spieler[amZug];
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
	
	public int[][] getTabellenposition() {
		return tabellenposition;
	}

	public Spieler[] getSpieler() {
		return spieler;
	}
	
	public Gefängnisfeld getGefängnis() {
		return (Gefängnisfeld) spielfelder[10];
	}
}
