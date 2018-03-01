package application.eventkarten;

import application.gui.SpielfeldController;

public class BewegenZuFeldKarte extends Eventkarte {

	private byte position;
	
	public BewegenZuFeldKarte(String text, byte position) {
		super(text);
		this.position = position;
	}

	@Override
	public void funktion(SpielfeldController sc) {
		super.funktion(sc);
		SpielfeldController.spiel.getMomentanenSpieler().setPosition(position);
	}

}
