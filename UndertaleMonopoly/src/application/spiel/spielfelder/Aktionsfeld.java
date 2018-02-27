package application.spiel.spielfelder;

import application.gui.SpielfeldController;

public class Aktionsfeld extends Spielfeld{

	@Override
	public void funktion(SpielfeldController sc)
	{
		sc.getSpielfeldEventText().setText("");
		sc.spielfeldEventTextAnzeigen();
	}

}
