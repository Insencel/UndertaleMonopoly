package application.spiel;


public class Spieler {
	private int gold;
	private int position;
	private W�rfel[] w�rfel = {new W�rfel(), new W�rfel()}; //Die zwei W�rfel
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
	
	public void �berweisen(int gold, Spieler s)
	{
		//TODO �berweisen von Gold an einen anderen Spieler
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
		this.position += this.w�rfel[0].w�rfeln() + this.w�rfel[1].w�rfeln();
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
