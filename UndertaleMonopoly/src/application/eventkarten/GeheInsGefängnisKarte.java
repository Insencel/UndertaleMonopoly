package application.eventkarten;

import application.spiel.Spieler;

public class GeheInsGef�ngnisKarte extends BewegenZuFeldKarte {

	public GeheInsGef�ngnisKarte(String text) {
		super(text, 10);
	}
	
	@Override
	public void funktion(Spieler s)
	{
		super.funktion(s);
		s.setVerbleibendeGefangenenZeit(3);
	}

}
