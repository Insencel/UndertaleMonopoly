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
		//this.bild = new ImageView(new Image(url, 0, 0, true, false));
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
		this.position += this.würfel[0].würfeln()+this.würfel[1].würfeln();
	}
	
	
	
	
	
	public ImageView getBild() {
		return bild;
	}

	public void setBild(ImageView bild) {
		this.bild = bild;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Würfel[] getWürfel() {
		return würfel;
	}

	public void setWürfel(Würfel[] würfel) {
		this.würfel = würfel;
	}
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

}
