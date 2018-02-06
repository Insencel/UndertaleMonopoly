package application.spiel;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Würfel {
	private int letzteZahl;
	private ImageView[] würfelseiten = {new ImageView(new Image("bilder\\würfel\\Würfel 1.jpg")), new ImageView(new Image("bilder\\würfel\\Würfel 2.jpg")), new ImageView(new Image("bilder\\würfel\\Würfel 3.jpg")), new ImageView(new Image("bilder\\würfel\\Würfel 4.jpg")), new ImageView(new Image("bilder\\würfel\\Würfel 5.jpg")), new ImageView(new Image("bilder\\würfel\\Würfel 6.jpg"))};
	
	
	public int würfeln()
	{
		 Random randomGenerator = new Random();
		    int randomInt = randomGenerator.nextInt(6)+1;
		    letzteZahl = randomInt;
			return randomInt;
	}
	
	public ImageView getBild(){
		return würfelseiten[letzteZahl-1];
	}
}
