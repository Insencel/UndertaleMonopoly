package application.eventkarten;

import application.spiel.Spieler;

public class ZumNächstenHafenKarte extends Eventkarte {

	public ZumNächstenHafenKarte(String text) {
		super(text);
	}

	@Override
	public void funktion(Spieler s) {
		int position = s.getPosition();
		
		if(position < 5 || position > 35)
		{
			s.setPosition(5);
		}
		else if(position < 15)
		{
			s.setPosition(15);
		}
		else if(position < 25)
		{
			s.setPosition(25);
		}
		else
		{
			s.setPosition(35);
		}

	}

}
