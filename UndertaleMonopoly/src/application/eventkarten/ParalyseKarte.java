package application.eventkarten;

import application.gui.SpielfeldController;

public class ParalyseKarte extends Eventkarte {

	private byte dauer;
	
	public ParalyseKarte(String text, byte dauer) {
		super(text);
		this.dauer = dauer;
	}

	@Override
	public void funktion(SpielfeldController sc) {
		super.funktion(sc);
		
		SpielfeldController.spiel.getMomentanenSpieler().setRundenStehenBleiben(dauer);
	}

}
