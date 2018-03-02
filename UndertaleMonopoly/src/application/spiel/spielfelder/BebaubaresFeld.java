package application.spiel.spielfelder;

import application.gui.SpielfeldController;

public class BebaubaresFeld extends KaufbaresFeld {
	private Spielfeldgebiet gebiet;
	private byte häuser;
	
	public BebaubaresFeld(String name, int preis, Spielfeldgebiet gebiet)
	{
		super(name, preis);
		this.gebiet = gebiet;
	}
	
	
	
	public void zubau()
	{
		häuser++;
		
		besitzer.minusGold(getZubauKosten());
		SpielfeldController.sdb.häuserÄnderungHinzufügen(this);
	}
	
	@Override
	public int aufenthaltBerechnen()
	{
		int miete = getPreisliste()[häuser];
		
		return miete;
	}
	
	
	
	public int getZubauKosten()
	{
		int zubaukosten = 0;
		
		switch(gebiet)
		{
		case RUINS:
		case SNOWDIN_FOREST:
			zubaukosten = 50;
			break;
		case SNOWDIN:
		case WATERFALL:
			zubaukosten = 100;
			break;
		case HOTLAND:
		case HOTEL:
			zubaukosten = 150;
			break;
		case CORE:
		case END:
			zubaukosten = 200;
			break;
		default:
			
		}
		
		return zubaukosten;
	}
	
	public int[] getPreisliste()
	{
		int[] i = {preis/10, preis/10 *5, preis/10 *15, (int)(preis/10 * 37.5), (int) (preis/10 *46.25), preis/10 *55};
		return i;
	}
	
	public Spielfeldgebiet getGebiet()
	{
		return gebiet;
	}
	
	public int getHäuser()
	{
		return häuser;
	}
	
	public void setHäuser(int häuser)
	{
		this.häuser = (byte) häuser;
	}
}
