package application.eventkarten;

import java.util.Random;

import application.gui.SpielfeldController;

public class RandomGoldKarte extends Eventkarte{

	public RandomGoldKarte(String text) {
		super(text);
	}

	@Override
	public void funktion(SpielfeldController sc) {
		Random rdm = new Random();
		
		int balance = rdm.nextInt(201)-100;
		SpielfeldController.spiel.getMomentanenSpieler().plusGold(balance);
		
		super.funktion(sc);
	}
	
	

	
}
