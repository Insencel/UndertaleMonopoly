package application.eventkarten;

import application.spiel.Spieler;

public class BewegenZuFeldKarte extends Eventkarte {

	private int position;
	
	public BewegenZuFeldKarte(String text, int position) {
		super(text);
		this.position = position;
	}

	@Override
	public void funktion(Spieler s) {
		s.setPosition(position);
	}

}
