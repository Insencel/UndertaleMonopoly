package application.spiel.spielfelder;

import application.gui.SpielfeldController;

public class InsGefängnisFeld extends Spielfeld {

	@Override
	public void funktion(SpielfeldController sc)
	{
		sc.getSpielfeldEventText().setText("Go to jail!");
		sc.spielfeldEventTextAnzeigen();
		
		SpielfeldController.spiel.getMomentanenSpieler().setPosition(10);
		
		Gefängnisfeld gf = (Gefängnisfeld) SpielfeldController.spiel.getSpielfelder()[10];
		gf.gefangenNehmen(SpielfeldController.spiel.getMomentanenSpieler());
	}

}
