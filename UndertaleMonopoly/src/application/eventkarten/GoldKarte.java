package application.eventkarten;

import application.gui.SpielfeldController;

public class GoldKarte extends Eventkarte {

	private int balance;
	
	public GoldKarte(String text, int balance) {
		super(text);
		this.balance = balance;
	}
	
	@Override
	public void funktion(SpielfeldController sc) {
		SpielfeldController.spiel.getMomentanenSpieler().plusGold(balance);
		
		super.funktion(sc);
	}

}
