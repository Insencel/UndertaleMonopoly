package application.spiel.spielfelder;

import java.util.ArrayList;
import java.util.Random;

import application.gui.SpielfeldController;
import application.spiel.Spieler;


public class Gefängnisfeld extends Spielfeld{
	private ArrayList<Spieler> gefangene = new ArrayList<Spieler>();
	private ArrayList<Integer> gefangenenZeit = new ArrayList<Integer>();
	
	public void gefangenNehmen(Spieler s)
	{
		gefangene.add(s);
		gefangenenZeit.add(3);
	}
	
	public void freilassen(Spieler s)
	{
		gefangenenZeit.remove(gefangene.indexOf(s));
		gefangene.remove(s);
		
	}

	@Override
	public void funktion(SpielfeldController sc)
	{
		if(gefangene.contains(SpielfeldController.spiel.getMomentanenSpieler()))
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
	
	public boolean isGefangen(Spieler s)
	{
		return gefangene.contains(s);
	}
	
	public boolean darfWürfeln(Spieler s)
	{
		if(isGefangen(s))
		{
			return gefangenenZeit.get(gefangene.indexOf(s))>0;
		}
		else
		{
			return false;
		}
		
	}
}
