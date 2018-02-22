package application.spiel;


public class Spieler {
	private int gold;
	private int position;
	private Würfel[] würfel = {new Würfel(), new Würfel()}; //Die zwei Würfel
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
		s.setGold(gold+s.getGold());
	}
	
	public void kaufen(KaufbaresFeld kf)
	{
		kf.setBesitzer(this);
		this.gold -= gold;
	}
	
	public void bewegen()
	{
		this.position += this.würfel[0].würfeln() + this.würfel[1].würfeln();
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

	public void setGold(int gold) {
		this.gold = gold;
	}

}
