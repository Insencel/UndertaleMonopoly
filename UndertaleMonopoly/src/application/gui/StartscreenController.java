package application.gui;

import java.io.IOException;

import application.Main;
import application.datenbankanbindung.SpielDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class StartscreenController
{
	
	@FXML
	private GridPane gp1;
	@FXML
	private GridPane gp2;
	@FXML
	private GridPane gp3;
	@FXML
	private ImageView back;
	@FXML
	private ImageView back2;
	@FXML
	private ImageView newGame;
	@FXML
	private ImageView fortsetzen;
	@FXML
	private TextField tf;
	@FXML
	private ImageView start;
	@FXML
	private Label spielExistent;
	@FXML
	private ImageView zwei;
	@FXML
	private ImageView drei;
	@FXML
	private ImageView vier;
	@FXML
	private TableColumn<Spielstand, String> spielname;
	@FXML
	private TableColumn<Spielstand, String> zuletztGespielt;
	@FXML
	private TableView<Spielstand> tv;
	
	
	private SpielDB sdb = new SpielDB();
	private byte spieleranzahl = 4;
	
	@FXML
	public void startNeuesSpiel()
	{
		try
		{
			sdb.neuesSpiel(tf.getText(), this.spieleranzahl);
			FXMLLoader loader = new FXMLLoader(StartscreenController.class.getResource("Spielfeld.fxml"));
			Scene scene = new Scene((Parent) loader.load());
			Main.primaryStage.setScene(scene);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			
		}
	}
	
	@FXML
	public void spielLaden()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(StartscreenController.class.getResource("Spielfeld.fxml"));
			Scene scene = new Scene((Parent) loader.load());
			Main.primaryStage.setScene(scene);
			sdb.spielstandLaden(tv.getSelectionModel().getSelectedItem().spielname);;
			
			
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
			
		}
	}
	
	@FXML
	public void zurueck()
	{
		gp2.setVisible(false);
		gp3.setVisible(false);
		
		gp1.setVisible(true);
	}
	
	
	
	@FXML
	public void neuesSpielKnopf()
	{
		gp1.setVisible(false);
		
		gp2.setVisible(true);
	}
	
	@FXML
	public void fortsetzenKnopf()
	{
		gp1.setVisible(false);
		
		gp3.setVisible(true);
		
		
		spielständeAnzeigen();
	}
	
	
	@FXML
	public void startmoeglich()
	{
		if(tf.getText().equals(""))
		{
			spielExistent.setVisible(false);
			start.setVisible(false);
		}
		else if(sdb.isSpielExistent(tf.getText()))
		{
			spielExistent.setVisible(true);
			start.setVisible(false);
		}
		else
		{
			spielExistent.setVisible(false);
			start.setVisible(true);
			
		}
	}
	
	
	
	@FXML
	public void spielständeAnzeigen()
	{
		ObservableList<Spielstand> spielstände = sdb.alleSpielständeLaden();
		
		spielname.setCellValueFactory(new PropertyValueFactory<Spielstand, String>("spielname"));
		zuletztGespielt.setCellValueFactory(new PropertyValueFactory<Spielstand, String>("zuletztGespielt"));
		
		
		tv.setItems(spielstände);
	}
	
	
	
	
	
	
	@FXML
	public void select2()
	{
		drei.setImage(new Image("bilder/startscreen/3.png"));
		vier.setImage(new Image("bilder/startscreen/4.png"));
		
		zwei.setImage(new Image("bilder/startscreen/2_selected.png"));
		this.spieleranzahl=2;
	}
	@FXML
	public void select3()
	{
		zwei.setImage(new Image("bilder/startscreen/2.png"));
		vier.setImage(new Image("bilder/startscreen/4.png"));
		
		drei.setImage(new Image("bilder/startscreen/3_selected.png"));
		this.spieleranzahl=3;
	}
	@FXML
	public void select4()
	{
		zwei.setImage(new Image("bilder/startscreen/2.png"));
		drei.setImage(new Image("bilder/startscreen/3.png"));
		
		vier.setImage(new Image("bilder/startscreen/4_selected.png"));
		this.spieleranzahl=4;
	}
	
	
	
	
	
	@FXML
	public void zurueckHoverOn()
	{
		back.setImage(new Image("bilder/startscreen/back_hover.png"));
		back2.setImage(new Image("bilder/startscreen/back_hover.png"));
	}
	@FXML
	public void zurueckHoverOff()
	{
		back.setImage(new Image("bilder/startscreen/back.png"));
		back2.setImage(new Image("bilder/startscreen/back.png"));
	}
	
	@FXML
	public void neuesSpielHoverOn()
	{
		newGame.setImage(new Image("bilder/startscreen/new_game_hover.png"));
	}
	@FXML
	public void neuesSpielHoverOff()
	{
		newGame.setImage(new Image("bilder/startscreen/new_game.png"));
	}
	
	@FXML
	public void fortsetzenHoverOn()
	{
		fortsetzen.setImage(new Image("bilder/startscreen/continue_hover.png"));
	}
	@FXML
	public void fortsetzenHoverOff()
	{
		fortsetzen.setImage(new Image("bilder/startscreen/continue.png"));
	}
	
	@FXML
	public void startHoverOn()
	{
		fortsetzen.setImage(new Image("bilder/startscreen/start_hover.png"));
	}
	@FXML
	public void startHoverOff()
	{
		fortsetzen.setImage(new Image("bilder/startscreen/start.png"));
	}
	
}
