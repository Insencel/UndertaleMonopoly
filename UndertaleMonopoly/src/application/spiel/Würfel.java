package application.spiel;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class W�rfel {
	private int letzteZahl;
	private ImageView[] w�rfelseiten = {new ImageView(new Image("bilder\\w�rfel\\W�rfel 1.jpg")), new ImageView(new Image("bilder\\w�rfel\\W�rfel 2.jpg")), new ImageView(new Image("bilder\\w�rfel\\W�rfel 3.jpg")), new ImageView(new Image("bilder\\w�rfel\\W�rfel 4.jpg")), new ImageView(new Image("bilder\\w�rfel\\W�rfel 5.jpg")), new ImageView(new Image("bilder\\w�rfel\\W�rfel 6.jpg"))};
	
	
	public int w�rfeln()
	{
		 Random randomGenerator = new Random();
		    int randomInt = randomGenerator.nextInt(6)+1;
		    letzteZahl = randomInt;
			return randomInt;
	}
	
	public ImageView getBild(){
		return w�rfelseiten[letzteZahl-1];
	}
}
