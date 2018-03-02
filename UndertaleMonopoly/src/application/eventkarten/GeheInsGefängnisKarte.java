package application.eventkarten;

import application.gui.SpielfeldController;

public class GeheInsGefängnisKarte extends BewegenZuFeldKarte {

	public GeheInsGefängnisKarte(String text) {
		super(text, (byte) 10);
	}
	
	@Override
	public void funktion(SpielfeldController sc)
	{
		SpielfeldController.spiel.getGefängnis().gefangenNehmen(SpielfeldController.spiel.getMomentanenSpieler());
		super.funktion(sc);
	}

}
