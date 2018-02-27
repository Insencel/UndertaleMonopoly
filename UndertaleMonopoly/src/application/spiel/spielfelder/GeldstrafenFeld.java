package application.spiel.spielfelder;

import application.gui.SpielfeldController;

public class GeldstrafenFeld extends Spielfeld {

	private int strafe;
	
	public GeldstrafenFeld(int strafe)
	{
		super();
		this.strafe = strafe;
	}
	
	@Override
	public void funktion(SpielfeldController sc)
	{
		sc.getSpielfeldEventText().setText("You have to pay a fee of " + strafe + " G!");
		sc.spielfeldEventTextAnzeigen();
		
		SpielfeldController.spiel.getMomentanenSpieler().minusGold(strafe);
	}

}
