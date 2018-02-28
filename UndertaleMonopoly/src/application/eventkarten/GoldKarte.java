package application.eventkarten;

import application.spiel.Spieler;

public class GoldKarte extends Eventkarte {

	private int balance;
	
	public GoldKarte(String text, int balance) {
		super(text);
		this.balance = balance;
	}
	
	@Override
	public void funktion(Spieler s) {
		s.plusGold(balance);

	}

}
