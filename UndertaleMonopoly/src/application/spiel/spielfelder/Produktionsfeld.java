package application.spiel.spielfelder;

import application.gui.SpielfeldController;

public class Produktionsfeld extends KaufbaresFeld {

	public Produktionsfeld(String name, int preis)
	{
		super(name, preis);
	}

	@Override
	public int aufenthaltBerechnen()
	{
		return getPreisliste()[besitzer.getAnzahlAnProduktionImBesitz() - 1] * SpielfeldController.spiel.getZuletztGewuerfelt();
	}
	
	@Override
	public int[] getPreisliste()
	{
		int[] liste = {4, 10};
		return liste;
	}

}
