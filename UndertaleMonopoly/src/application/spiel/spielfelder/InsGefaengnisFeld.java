package application.spiel.spielfelder;

import application.gui.SpielfeldController;

public class InsGefaengnisFeld extends Spielfeld {

	@Override
	public void funktion(SpielfeldController sc)
	{
		sc.getSpielfeldEventText().setText("Go to jail!");
		sc.spielfeldEventTextAnzeigen();
		
		SpielfeldController.spiel.getMomentanenSpieler().setPosition((byte) 10);
		
		Gefaengnisfeld gf = (Gefaengnisfeld) SpielfeldController.spiel.getSpielfelder()[10];
		gf.gefangenNehmen(SpielfeldController.spiel.getMomentanenSpieler());
	}

}
