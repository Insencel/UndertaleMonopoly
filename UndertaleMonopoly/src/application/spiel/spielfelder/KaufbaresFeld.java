package application.spiel.spielfelder;

import application.gui.SpielfeldController;
import application.spiel.Spieler;

public class KaufbaresFeld extends Spielfeld {
	private String name;
	private Spieler besitzer;
	private final int preis;
	private Spielfeldgebiet gebiet;
	private byte häuser;
	private boolean hotel;
	
	public KaufbaresFeld(String name, int preis, Spielfeldgebiet gebiet)
	{
		this.name = name;
		this.preis = preis;
		this.gebiet = gebiet;
	}
	
	
	@Override
	public void funktion(SpielfeldController sc)
	{
		if(besitzer==null)
		{
			sc.kaufenAnzeigen(SpielfeldController.spiel.getIndexOfSpielfeld(this));
			sc.getSpielfeldEventText().setText("You can buy " + name + " if you want to!");
		}
		else if(!SpielfeldController.spiel.getMomentanenSpieler().equals(besitzer))
		{
			int gold = aufenthaltBerechnen();
			sc.getSpielfeldEventText().setText("You have to pay " + gold + " G to Player " + (SpielfeldController.spiel.getIndexOfSpieler(besitzer)+1) + "!\n(Afterwards you will own " + (SpielfeldController.spiel.getMomentanenSpieler().getGold()-gold) + " G)");
			sc.spielfeldEventTextAnzeigen();
		}
		else
		{
			sc.getSpielfeldEventText().setText("You own this place.\nStay as long as you want");
			sc.spielfeldEventTextAnzeigen();
		}
	}
	
	
	public int aufenthaltBerechnen()
	{
		return preis/10;
	}
	
	public void setBesitzer(Spieler besitzer)
	{
		this.besitzer = besitzer;
	}

	public int getPreis() {
		return preis;
	}
	
	public Spieler getBesitzer()
	{
		return besitzer;
	}


	public String getName() {
		return name;
	}
	
}
