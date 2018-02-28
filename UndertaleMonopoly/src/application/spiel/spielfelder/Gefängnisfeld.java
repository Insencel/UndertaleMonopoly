package application.spiel.spielfelder;

import java.util.Random;

import application.gui.SpielfeldController;
import application.spiel.Spieler;


public class Gefängnisfeld extends Spielfeld{
	
	public void gefangenNehmen(Spieler s)
	{
		s.setVerbleibendeGefangenenZeit(3);
	}
	
	public void freilassen(Spieler s)
	{
		s.setVerbleibendeGefangenenZeit(-1);
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
	
	public void freiwürfeln(SpielfeldController sc)
	{
		Random rdm = new Random();
		int[] würfel = {rdm.nextInt(6)+1, rdm.nextInt(6)+1};
		
		sc.setWürfel(würfel);
		
		if(würfel[0] == würfel[1])
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
	
	public boolean darfWürfeln(Spieler s)
	{
		return s.getVerbleibendeGefangenenZeit()>0;
	}
}
