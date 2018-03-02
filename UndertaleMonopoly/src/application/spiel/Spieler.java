package application.spiel;

import application.datenbankanbindung.UpdateEnum;
import application.gui.SpielfeldController;
import application.spiel.spielfelder.BebaubaresFeld;
import application.spiel.spielfelder.KaufbaresFeld;
import application.spiel.spielfelder.Produktionsfeld;
import application.spiel.spielfelder.Riverladyfeld;
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
	
	public void ueberweisen(int gold, Spieler s)
	{
		this.gold -= gold;
		
		//Bei s==null heißt es, dass der Spieler Gold an die Bank gibt (Bank hat unendlich Geld, deswegen verliert der Spieler das Geld hier einfach)
		if(s!=null)
		{
			s.plusGold(gold);
		}
		
	}
	
	
	public void kaufen(KaufbaresFeld kf)
	{
		kf.setBesitzer(this);
		this.minusGold(kf.getPreis());
	}
	
	
	public void gefangenenZeitVergeht()
	{
		verbleibendeGefangenenZeit--;
		SpielfeldController.sdb.aenderungHinzufuegen(UpdateEnum.gefaengnis);
		
		if(verbleibendeGefangenenZeit == -1)
		{
			minusGold(50);
		}
	}
	
	public void paralyseZeitVergeht()
	{
		rundenStehenBleiben--;
		SpielfeldController.sdb.aenderungHinzufuegen(UpdateEnum.paralyse);
	}
	
	public void plusGold(int gold)
	{
		this.gold+=gold;
		SpielfeldController.sdb.aenderungHinzufuegen(UpdateEnum.gold);
	}
	
	public void minusGold(int gold)
	{
		this.gold-=gold;
		SpielfeldController.sdb.aenderungHinzufuegen(UpdateEnum.gold);
	}
	
	public byte getPosition() {
		return position;
	}

	public void setPosition(byte position) {
		SpielfeldController.sdb.aenderungHinzufuegen(UpdateEnum.position);
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
		SpielfeldController.sdb.aenderungHinzufuegen(UpdateEnum.gefaengnis);
		this.verbleibendeGefangenenZeit = verbleibendeGefangenenZeit;
	}
	
	public boolean isBewegungsunfaehig()
	{
		return rundenStehenBleiben>0;
	}

	public void setRundenStehenBleiben(byte rundenStehenBleiben) {
		SpielfeldController.sdb.aenderungHinzufuegen(UpdateEnum.paralyse);
		this.rundenStehenBleiben = rundenStehenBleiben;
	}
	
	public byte getRundenStehenBleiben()
	{
		return rundenStehenBleiben;
	}
	
	
	public boolean isBesitzerVonAllenFeldernImGebiet(Spielfeldgebiet gebiet)
	{
		boolean b = true;
		
		BebaubaresFeld[] felder = SpielfeldController.spiel.getBebaubareFelder();
		
		try
		{
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
		catch(NullPointerException e)
		{
			b = false;
		}
		
		
		return b;
	}
	
	public int getAnzahlAnHaefenImBesitz()
	{
		int zaehler = 0;
		
		KaufbaresFeld[] felder = SpielfeldController.spiel.getKaufbareFelder();
		
		for(int i = 0; i<felder.length; i++)
		{
			if(felder[i] instanceof Riverladyfeld && felder[i].getBesitzer().equals(this))
			{
				zaehler++;
			}
		}
		
		
		
		return zaehler;
	}
	
	public int getAnzahlAnProduktionImBesitz()
	{
		int zaehler = 0;
		
		KaufbaresFeld[] felder = SpielfeldController.spiel.getKaufbareFelder();
		
		for(int i = 0; i<felder.length; i++)
		{
			if(felder[i] instanceof Produktionsfeld && felder[i].getBesitzer().equals(this))
			{
				zaehler++;
			}
		}
		
		return zaehler;
	}
	
	public int getScore()
	{
		int score = this.gold;
		
		KaufbaresFeld[] felder = SpielfeldController.spiel.getKaufbareFelder();
		
		for(int i = 0; i<felder.length; i++)
		{
			if(felder[i].getBesitzer()!=null && felder[i].getBesitzer().equals(this))
			{
				score += felder[i].getPreis();
			}
		}
		
		return score;
	}
	

}
