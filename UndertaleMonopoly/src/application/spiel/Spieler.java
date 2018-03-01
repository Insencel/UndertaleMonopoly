package application.spiel;

import application.gui.SpielfeldController;
import application.spiel.spielfelder.BebaubaresFeld;
import application.spiel.spielfelder.Spielfeldgebiet;

public class Spieler {
	private int gold;
	private byte position;
	private byte verbleibendeGefangenenZeit;
	private byte rundenStehenBleiben;
	public final static int startkapital = 1500;
	
	public Spieler()
	{
		this.gold = startkapital;
		this.position = 0;
		this.verbleibendeGefangenenZeit = -1;
		this.rundenStehenBleiben = 0;
	}
		
	public Spieler(int gold, byte position, byte verbleibendeGefangenenZeit, byte rundenStehenBleiben)
	{
		this.gold = gold;
		this.position = position;
		this.verbleibendeGefangenenZeit = verbleibendeGefangenenZeit;
		this.rundenStehenBleiben = rundenStehenBleiben;
	}
	
	public void überweisen(int gold, Spieler s)
	{
		this.gold -= gold;
		
		//Bei s==null heißt es, dass der Spieler Gold an die Bank gibt (Bank hat unendlich Geld, deswegen verliert der Spieler das Geld hier einfach)
		if(s!=null)
		{
			s.plusGold(gold);
		}
	}
	
	
	public void kaufen(BebaubaresFeld kf)
	{
		kf.setBesitzer(this);
		this.gold -= kf.getPreis();
	}
	
	
	public void gefangenenZeitVergeht()
	{
		verbleibendeGefangenenZeit--;
	}
	
	public void paralyseZeitVergeht()
	{
		rundenStehenBleiben--;
	}
	
	public void plusGold(int gold)
	{
		this.gold+=gold;
	}
	
	public void minusGold(int gold)
	{
		this.gold-=gold;
	}
	
	public byte getPosition() {
		return position;
	}

	public void setPosition(byte position) {
		this.position = position;
	}

	public int getGold() {
		return gold;
	}

	public boolean isGefangen() {
		return verbleibendeGefangenenZeit>=0;
	}

	public byte getVerbleibendeGefangenenZeit() {
		return verbleibendeGefangenenZeit;
	}

	public void setVerbleibendeGefangenenZeit(byte verbleibendeGefangenenZeit) {
		this.verbleibendeGefangenenZeit = verbleibendeGefangenenZeit;
	}
	
	public boolean isBewegungsunfähig()
	{
		return rundenStehenBleiben>0;
	}

	public void setRundenStehenBleiben(byte rundenStehenBleiben) {
		this.rundenStehenBleiben = rundenStehenBleiben;
	}
	
	
	public boolean isBesitzerVonAllenFeldernImGebiet(Spielfeldgebiet gebiet)
	{
		boolean b = true;
		Object[] felderObject = SpielfeldController.spiel.getBebaubareFelder();
		
		if(felderObject instanceof BebaubaresFeld[])
		{
			BebaubaresFeld[] felder = (BebaubaresFeld[]) felderObject;
			
			for(int i = 0; i<felder.length; i++)
			{
				if(felder[i].getGebiet().equals(gebiet))
				{
					if(!felder[i].getBesitzer().equals(this))
					{
						b = false;
						
						break;
					}
				}
			}
		}
		
		
		
		return b;
	}
	
	public int besitztWieVieleHäfen()
	{
		return 0;
	}
	

}
