package application.eventkarten;

import application.spiel.Spieler;

public class GeheInsGefängnisKarte extends BewegenZuFeldKarte {

	public GeheInsGefängnisKarte(String text) {
		super(text, 10);
	}
	
	@Override
	public void funktion(Spieler s)
	{
		super.funktion(s);
		s.setVerbleibendeGefangenenZeit(3);
	}

}
