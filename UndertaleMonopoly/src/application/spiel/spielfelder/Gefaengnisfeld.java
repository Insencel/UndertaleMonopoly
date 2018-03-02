package application.spiel.spielfelder;

import java.util.Random;

import application.gui.SpielfeldController;
import application.spiel.Spieler;


public class Gefaengnisfeld extends Spielfeld{
	
	public void gefangenNehmen(Spieler s)
	{
		s.setVerbleibendeGefangenenZeit((byte) 2);
	}
	
	public void freilassen(Spieler s)
	{
		s.setVerbleibendeGefangenenZeit((byte) -1);
	}

	@Override
	public void funktion(SpielfeldController sc)
	{
		if(SpielfeldController.spiel.getMomentanenSpieler().isGefangen())
		{
			sc.getSpielfeldEventText().setText("You are a prisoner!");
			sc.spielfeldEventTextAnzeigen();
		}
		else
		{
			sc.getSpielfeldEventText().setText("You are just a visitor.");
			sc.spielfeldEventTextAnzeigen();
		}
	}
	
	public void freiwuerfeln(SpielfeldController sc)
	{
		Random rdm = new Random();
		int[] wuerfel = {rdm.nextInt(6)+1, rdm.nextInt(6)+1};
		
		sc.setWuerfel(wuerfel);
		
		if(wuerfel[0] == wuerfel[1])
		{
			freilassen(SpielfeldController.spiel.getMomentanenSpieler());
			sc.getSpielfeldEventText().setText("You freed yourself by rolling a double!");
			sc.spielfeldEventTextAnzeigen();
		}
		
	}
	
	public void freikaufen(SpielfeldController sc)
	{
		SpielfeldController.spiel.getMomentanenSpieler().minusGold(50);
		
		freilassen(SpielfeldController.spiel.getMomentanenSpieler());
	}
}
