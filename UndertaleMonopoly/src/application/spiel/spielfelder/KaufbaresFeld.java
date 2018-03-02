package application.spiel.spielfelder;

import application.datenbankanbindung.UpdateEnum;
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
			sc.getSpielfeldEventText().setText("You have to pay " + gold + " G to Player " + (SpielfeldController.spiel.getIndexOfSpieler(besitzer)+1) + "!");
			SpielfeldController.spiel.getMomentanenSpieler().ueberweisen(gold, besitzer);
			sc.spielfeldEventTextAnzeigen();
			sc.textupdate();
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
		SpielfeldController.sdb.aenderungHinzufuegen(UpdateEnum.besitzer);
		SpielfeldController.sdb.setBesitzeraenderung(this);
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
