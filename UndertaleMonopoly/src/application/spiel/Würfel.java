package application.spiel;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Würfel {
	private ImageView[] würfelseiten; //hat nur sechs seiten!
	
	
	public int würfeln()
	{
		 Random randomGenerator = new Random();
		    int randomInt = randomGenerator.nextInt(6)+1;
			return randomInt;
	}
	
	
}
