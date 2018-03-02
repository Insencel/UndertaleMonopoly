package application.eventkarten;

import application.gui.SpielfeldController;

public class GeheInsGefaengnisKarte extends BewegenZuFeldKarte {

	public GeheInsGefaengnisKarte(String text) {
		super(text, (byte) 10);
	}
	
	@Override
	public void funktion(SpielfeldController sc)
	{
		SpielfeldController.spiel.getGefaengnis().gefangenNehmen(SpielfeldController.spiel.getMomentanenSpieler());
		super.funktion(sc);
	}

}
