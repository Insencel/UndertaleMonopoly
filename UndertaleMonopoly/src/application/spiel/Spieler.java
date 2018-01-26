package application.spiel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spieler {
	private int gold;
	private ImageView bild;
	private int position;
	private W�rfel[] w�rfel = {new W�rfel(), new W�rfel()}; //Die zwei W�rfel
	
	public Spieler(){
		this.gold = 1000;
		this.position = 0;
		String url = getClass().getResource("").toString();
		this.bild = new ImageView(new Image(url, 0, 0, true, false));
	}
	
	public void �berweisen(int gold, Spieler s)
	{
		//TODO �berweisen von Gold an einen anderen Spieler
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
