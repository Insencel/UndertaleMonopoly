package application.spiel;

import java.util.ArrayList;

public class Spiel
{
	private ArrayList<Spielfeld> spielfelder = new ArrayList<Spielfeld>();
	//TODO �berlegen wie ein Kartendeck funktioniert
	private Spieler[] spieler;
	
	public Spiel()
	{
		this.spieler = new Spieler[] {new Spieler(), new Spieler(), new Spieler(), new Spieler()};
		spielen();
	}
	
	
	public void spielen()
	{
		n�chsterSpieler();
	}
	
	public void �berLos()
	{
		
	}
	
	private void n�chsterSpieler()
	{
		spieler = new Spieler[]{spieler[1], spieler[2], spieler[3], spieler[0]};
	}
	
	
}
