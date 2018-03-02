package application.spiel.spielfelder;

import application.eventkarten.Eventkarte;
import application.gui.SpielfeldController;

public class Aktionsfeld extends Spielfeld{

	@Override
	public void funktion(SpielfeldController sc)
	{
		sc.getSpielfeldEventText().setText("");
		sc.spielfeldEventTextAnzeigen();
		
		Eventkarte ek = SpielfeldController.spiel.getZufälligeEventkarte();
		
		sc.eventkarteAnzeigen(ek.getText());
		ek.funktion(sc);
		
		sc.richtigStellen();
		sc.textupdate();
	}

}
