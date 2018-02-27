package application.spiel.spielfelder;

import java.util.ArrayList;

import application.gui.SpielfeldController;
import application.spiel.Spieler;


public class Gefängnisfeld extends Spielfeld{
	private ArrayList<Spieler> gefangene = new ArrayList<Spieler>();
	
	public void gefangenNehmen(Spieler s)
	{
		gefangene.add(s);
	}
	
	public void freilassen(Spieler s)
	{
		gefangene.remove(s);
	}

	@Override
	public void funktion(SpielfeldController sc)
	{
		if(gefangene.contains(SpielfeldController.spiel.getMomentanenSpieler()))
		{
			sc.getSpielfeldEventText().setText("You are a prisoner!");
		}
		else
		{
			sc.getSpielfeldEventText().setText("You are just a visitor.");
		}
		
		sc.spielfeldEventTextAnzeigen();
		
	}
}
