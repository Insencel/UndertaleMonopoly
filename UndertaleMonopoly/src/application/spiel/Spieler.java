package application.spiel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spieler {
	private int gold;
	private ImageView bild;
	private int position;
	private Würfel[] würfel = {new Würfel(), new Würfel()}; //Die zwei Würfel
	
	public Spieler(){
		this.gold = 1000;
		this.position = 0;
		String url = getClass().getResource("").toString();
		this.bild = new ImageView(new Image(url, 0, 0, true, false));
	}
	
	public void überweisen(int gold, Spieler s)
	{
		//TODO überweisen von Gold an einen anderen Spieler
		this.gold -= gold;
		s.setGold(gold+s.getGold());
	}
	
	public void kaufen(Spielfeld sf)
	{
		
	}
	
	public void bewegen()
	{
		
	}
	
	
	
	
	
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

}
