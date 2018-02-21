package application.spiel;


public class Spieler {
	private int gold;
	private int position;
	private W�rfel[] w�rfel = {new W�rfel(), new W�rfel()}; //Die zwei W�rfel
	
	public Spieler()
	{
		this.gold = 1000;
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

	public W�rfel[] getW�rfel() {
		return w�rfel;
	}

	public void setW�rfel(W�rfel[] w�rfel) {
		this.w�rfel = w�rfel;
	}
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

}
