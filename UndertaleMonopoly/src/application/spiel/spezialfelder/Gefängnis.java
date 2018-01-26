package application.spiel.spezialfelder;

import java.util.ArrayList;
import application.spiel.Spieler;


public class Gefängnis {
	private ArrayList<Spieler> gefangene;
	
	public void gefangenNehmen(Spieler s)
	{
		gefangene.add(s);
	}
	
	public void freilassen(Spieler s)
	{
		gefangene.remove(s);
	}
}
