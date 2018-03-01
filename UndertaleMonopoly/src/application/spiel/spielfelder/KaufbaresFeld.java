package application.spiel.spielfelder;

import application.gui.SpielfeldController;
import application.spiel.Spieler;

public abstract class KaufbaresFeld extends Spielfeld {

	protected String name;
	protected Spieler besitzer;
	protected final int preis;
	
	public KaufbaresFeld(String name, int preis)
	{
		this.name = name;
		this.preis = preis;
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
	
	public abstract int aufenthaltBerechnen();
	
	public abstract int[] getPreisliste();
	
	
	
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
