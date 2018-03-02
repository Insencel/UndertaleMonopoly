package application.spiel;

import java.util.ArrayList;
import java.util.Random;

import application.datenbankanbindung.UpdateEnum;
import application.eventkarten.*;
import application.gui.SpielfeldController;
import application.spiel.spielfelder.*;

public class Spiel
{
	private Spielfeld[] spielfelder = {new SpielfeldOhneFunktion(), new BebaubaresFeld("Flowerfield", 60, Spielfeldgebiet.RUINS), new Aktionsfeld(), new BebaubaresFeld("Toriel's House", 60, Spielfeldgebiet.RUINS), new GeldstrafenFeld(200), new Riverladyfeld("Riverlady West", 200), new BebaubaresFeld("Conveniently-Shaped Lamp", 100, Spielfeldgebiet.SNOWDIN_FOREST), new Aktionsfeld(),  new BebaubaresFeld("Kennel", 100, Spielfeldgebiet.SNOWDIN_FOREST), new BebaubaresFeld("Long Bridge", 120, Spielfeldgebiet.SNOWDIN_FOREST), new Gefängnisfeld(), new BebaubaresFeld("Library", 140, Spielfeldgebiet.SNOWDIN), new Produktionsfeld("Ice-Fabric", 150), new BebaubaresFeld("Grillby's", 140, Spielfeldgebiet.SNOWDIN), new BebaubaresFeld("Shop & Inn", 160, Spielfeldgebiet.SNOWDIN), new Riverladyfeld("Riverlady North", 200), new BebaubaresFeld("Statue", 180, Spielfeldgebiet.WATERFALL), new Aktionsfeld(),  new BebaubaresFeld("Ghost House", 180, Spielfeldgebiet.WATERFALL), new BebaubaresFeld("Temmie Village", 200, Spielfeldgebiet.WATERFALL), new SpielfeldOhneFunktion(), new BebaubaresFeld("Lab", 220, Spielfeldgebiet.HOTLAND), new Aktionsfeld(), new BebaubaresFeld("Spider", 220, Spielfeldgebiet.HOTLAND), new BebaubaresFeld("Theatre", 240, Spielfeldgebiet.HOTLAND), new Riverladyfeld("Riverlady East", 200), new BebaubaresFeld("MTT-Well", 260, Spielfeldgebiet.HOTEL), new BebaubaresFeld("MTT", 260, Spielfeldgebiet.HOTEL), new Produktionsfeld("Core", 200), new BebaubaresFeld("Hotelroom", 280, Spielfeldgebiet.HOTEL), new InsGefängnisFeld(), new BebaubaresFeld("Bin", 300, Spielfeldgebiet.CORE), new BebaubaresFeld("Core-Bridge", 300, Spielfeldgebiet.CORE), new Aktionsfeld(), new BebaubaresFeld("MTT-Boss Door", 320, Spielfeldgebiet.CORE), new Riverladyfeld("Riverlady South", 200), new Aktionsfeld(),  new BebaubaresFeld("End-House", 350, Spielfeldgebiet.END), new GeldstrafenFeld(100), new BebaubaresFeld("Throne", 400, Spielfeldgebiet.END)};
	private final int[][] tabellenposition = {{1, 11}, {1, 10}, {1, 9}, {1, 8}, {1, 7}, {1, 6}, {1, 5}, {1, 4}, {1, 3}, {1, 2}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {9, 1}, {10, 1}, {11,1} , {11, 2}, {11, 3}, {11, 4}, {11, 5}, {11, 6}, {11, 7}, {11, 8}, {11, 9}, {11, 10}, {11, 11}, {10, 11}, {9, 11}, {8, 11},{7, 11}, {6, 11}, {5, 11}, {4, 11}, {3, 11}, {2, 11}};
	private final Eventkarte[] kartenstapel = {new GoldKarte("Monster Kid gives you a gift!\n\nYou earn 50 G.", 50), new GoldKarte("Sans sells a hotdog to you.\n\nYou pay him 20 G.", -20), new GoldKarte("You pay for Temmies college.\n\nIt costs 200G.", -200), new GoldKarte("You buy Nice Cream.\n\nIt costs 50 G.", -50), new RandomGoldKarte("#?E2&§ |=€:%*R5 #*/R? !\\O<\" #°§R$&"), new GeheInsGefängnisKarte("Papyrus caught you whilst you were busy thinking of what this card could do.\n\nGo to jail!"), new ParalyseKarte("You see Goner Kid and are shocked.\n\nYou lose one turn.", (byte) 1), new ParalyseKarte("Burgerpants gives you and advice.\n\nDon't live your life like I did. I am 19 years old and I wasted my entire life.\n\nYou are so stunned from this intellectual statement that you can't move next turn.", (byte) 1), new ParalyseKarte("Papyrus cooked spaghetti for you but you don't eat them.\n\nYou feel bad and won't move next turn.", (byte) 1), new BewegenZuFeldKarte("Sans invites you to go to Grillby's.\n\nGo to Grillby's.", (byte) 13), new ZumNächstenHafenKarte("The Riverlady takes you to her next harbour.")};
	private Spieler[] spieler;
	private int amZug;
	private int paschcounter;
	private final int überStartGold = 200;
	private int zuletztGewürfelt;
	private String name;
	
	
	
	public Spiel(int spieleranzahl, String name)
	{
		spieler = new Spieler[spieleranzahl];
		for(int i = 0; i<spieler.length; i++)
		{
			this.spieler[i] = new Spieler();
		}
		this.amZug = 0;
		this.name = name;
	}
	
	public Spiel(int amZug, int paschcounter, ArrayList<Spieler> spieler, ArrayList<Integer[]> spielfelddaten, String name)
	{
		this.amZug = amZug;
		this.paschcounter = paschcounter;
		this.spieler = spieler.toArray(new Spieler[spieler.size()]);
		this.name = name;
		setzeBesitzer(spielfelddaten);
	}
	
	public int[] momentanenSpielerBewegen() throws SpielerGefangenException
	{
		Random rdm = new Random();
		int[] würfel = {rdm.nextInt(6)+1, rdm.nextInt(6)+1};
		zuletztGewürfelt = würfel[0] + würfel[1];
		
		for(int i = 0; i<würfel.length; i++)
		{
			spieler[amZug].setPosition((byte) (spieler[amZug].getPosition() + würfel[i]));
		}
		
		
		if(spieler[amZug].getPosition()>=tabellenposition.length)
		{
			spieler[amZug].setPosition((byte) (spieler[amZug].getPosition()-tabellenposition.length));
		}
		
		if(würfel[0] == würfel[1])
		{
			if(paschcounter==3)
			{
				setPaschcounter(0);
				getGefängnis().gefangenNehmen(getMomentanenSpieler());
				SpielfeldController.sdb.änderungHinzufügen(UpdateEnum.paschcounter);
				
				throw new SpielerGefangenException();
			}
			else
			{
				setPaschcounter(paschcounter+1);
			}
		}
		else
		{
			setPaschcounter(0);
		}
		SpielfeldController.sdb.änderungHinzufügen(UpdateEnum.paschcounter);
		
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
	
	private void setzeBesitzer(ArrayList<Integer[]> spielfelddaten)
	{
		int besitzerCounter = 0;
		
		for(int i = 0; i<this.spielfelder.length; i++)
		{
			if(this.spielfelder[i] instanceof KaufbaresFeld)
			{
				if(spielfelddaten.get(besitzerCounter)[1]!=-1)
				{
					BebaubaresFeld bf = (BebaubaresFeld) this.spielfelder[i];
					
					bf.setHäuser(spielfelddaten.get(besitzerCounter)[1]);
				}
				
				if(spielfelddaten.get(besitzerCounter)[0]!=-1)
				{
					KaufbaresFeld k = (KaufbaresFeld) this.spielfelder[i];
					k.setBesitzer(spieler[spielfelddaten.get(besitzerCounter)[0]]);
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
		if(paschcounter==0)
		{
			if(amZug>=spieler.length-1)
			{
				amZug = 0;
			}
			else
			{
				amZug++;
			}
			
			SpielfeldController.sdb.änderungHinzufügen(UpdateEnum.amZug);
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
	
	public int getIndexOfKaufbaresFeld(KaufbaresFeld gesucht)
	{
		int rückgabe = -1;
		KaufbaresFeld[] felder = getKaufbareFelder();
		
		for(int i = 0; i < felder.length; i++)
		{
			if(felder[i].equals(gesucht))
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
	
	public KaufbaresFeld[] getKaufbareFelder()
	{
		ArrayList<KaufbaresFeld> kf = new ArrayList<KaufbaresFeld>();
		
		for(int i = 0; i<spielfelder.length; i++)
		{
			if(spielfelder[i] instanceof KaufbaresFeld)
			{
				kf.add((KaufbaresFeld) spielfelder[i]);
			}
		}
		
		return kf.toArray(new KaufbaresFeld[kf.size()]);
	}
	
	public BebaubaresFeld[] getBebaubareFelder()
	{
		ArrayList<BebaubaresFeld> felder = new ArrayList<BebaubaresFeld>();
		
		for(int i = 0; i<spielfelder.length; i++)
		{
			if(spielfelder[i] instanceof BebaubaresFeld)
			{
				felder.add((BebaubaresFeld) spielfelder[i]);
			}
		}
		
		return felder.toArray(new BebaubaresFeld[felder.size()]);
	}
	
	public Spieler getMomentanenSpieler()
	{
		return spieler[amZug];
	}
	
	private void setPaschcounter(int paschcounter)
	{
		this.paschcounter = paschcounter;
		SpielfeldController.sdb.änderungHinzufügen(UpdateEnum.paschcounter);
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
	
	public Eventkarte getZufälligeEventkarte() {
		Random rdm = new Random();
		return kartenstapel[rdm.nextInt(kartenstapel.length)];
	}
	
	public int getZuletztGewürfelt()
	{
		return zuletztGewürfelt;
	}
	
	public String getName()
	{
		return name;
	}
}
