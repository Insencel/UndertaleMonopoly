package application.eventkarten;

import application.gui.SpielfeldController;

public class GeheInsGef�ngnisKarte extends BewegenZuFeldKarte {

	public GeheInsGef�ngnisKarte(String text) {
		super(text, (byte) 10);
	}
	
	@Override
	public void funktion(SpielfeldController sc)
	{
		SpielfeldController.spiel.getGef�ngnis().gefangenNehmen(SpielfeldController.spiel.getMomentanenSpieler());
		super.funktion(sc);
	}

}
