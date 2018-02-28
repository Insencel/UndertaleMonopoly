package application.eventkarten;

import java.util.Random;

import application.spiel.Spieler;

public class RandomGoldKarte extends Eventkarte{

	public RandomGoldKarte(String text) {
		super(text);
	}

	@Override
	public void funktion(Spieler s) {
		Random rdm = new Random();
		
		int balance = rdm.nextInt(201)-100;
		s.plusGold(balance);
	}
	
	

	
}
