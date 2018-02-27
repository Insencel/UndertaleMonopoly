package application.spiel.spielfelder;

import application.gui.SpielfeldController;

public class InsGef�ngnisFeld extends Spielfeld {

	@Override
	public void funktion(SpielfeldController sc)
	{
		sc.getSpielfeldEventText().setText("Go to jail!");
		sc.spielfeldEventTextAnzeigen();
		
		SpielfeldController.spiel.getMomentanenSpieler().setPosition(10);
		
		Gef�ngnisfeld gf = (Gef�ngnisfeld) SpielfeldController.spiel.getSpielfelder()[10];
		gf.gefangenNehmen(SpielfeldController.spiel.getMomentanenSpieler());
	}

}
