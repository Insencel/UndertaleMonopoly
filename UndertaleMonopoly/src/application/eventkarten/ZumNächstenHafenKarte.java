package application.eventkarten;

import application.gui.SpielfeldController;
import application.spiel.Spieler;

public class ZumN�chstenHafenKarte extends Eventkarte {

	public ZumN�chstenHafenKarte(String text) {
		super(text);
	}

	@Override
	public void funktion(SpielfeldController sc)
	{
		super.funktion(sc);
		Spieler s = SpielfeldController.spiel.getMomentanenSpieler();
		
		int position = s.getPosition();
		
		if(position < 5 || position > 35)
		{
			s.setPosition((byte) 5);
		}
		else if(position < 15)
		{
			s.setPosition((byte) 15);
		}
		else if(position < 25)
		{
			s.setPosition((byte) 25);
		}
		else
		{
			s.setPosition((byte) 35);
		}

	}

}
