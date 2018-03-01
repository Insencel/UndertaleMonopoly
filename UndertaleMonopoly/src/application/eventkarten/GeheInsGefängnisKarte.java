package application.eventkarten;

import application.gui.SpielfeldController;

public class GeheInsGefängnisKarte extends BewegenZuFeldKarte {

	public GeheInsGefängnisKarte(String text) {
		super(text, (byte) 10);
	}
	
	@Override
	public void funktion(SpielfeldController sc)
	{
		super.funktion(sc);
		SpielfeldController.spiel.getMomentanenSpieler().setVerbleibendeGefangenenZeit((byte) 3);
	}

}
