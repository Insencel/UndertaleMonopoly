package application.spiel;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class W�rfel {
	private ImageView[] w�rfelseiten; //hat nur sechs seiten!
	
	
	public int w�rfeln()
	{
		 Random randomGenerator = new Random();
		    int randomInt = randomGenerator.nextInt(6)+1;
			return randomInt;
	}
	
	
}
