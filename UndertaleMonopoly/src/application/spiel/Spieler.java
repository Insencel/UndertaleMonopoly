package application.spiel;

import application.spiel.spielfelder.KaufbaresFeld;

public class Spieler {
	private int gold;
	private int position;
	public final static int startkapital = 1500;
	
	public Spieler()
	{
		this.gold = startkapital;
		this.position = 0;
	}
		
	public Spieler(int gold, int position)
	{
		this.gold = gold;
		this.position = position;
	}
	
	public void überweisen(int gold, Spieler s)
	{
		//TODO überweisen von Gold an einen anderen Spieler
		this.gold -= gold;
		
		//Bei s==null heißt es, dass der Spieler Gold an die Bank gibt (Bank hat unendlich Geld, deswegen verliert der Spieler das Geld hier einfach)
		if(s!=null)
		{
			s.plusGold(gold);
		}
	}
	
	
	public void kaufen(KaufbaresFeld kf)
	{
		kf.setBesitzer(this);
		this.gold -= kf.getPreis();
	}
	
	
	public void plusGold(int gold)
	{
		this.gold+=gold;
	}
	
	public void minusGold(int gold)
	{
		this.gold+=gold;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getGold() {
		return gold;
	}
}
