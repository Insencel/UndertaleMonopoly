package application.datenbankanbindung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import application.gui.SpielfeldController;
import application.gui.Spielstand;
import application.spiel.Spiel;
import application.spiel.Spieler;
import application.spiel.Spielfeld;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SpielDB {

	private Datenbank db = new Datenbank();
	
	public Spiel spielstandLaden(String name)
	{
		Spiel s;
		
		try
		{
			ResultSet spielSet = db.auslesen("spiel", " WHERE Spielname='" + name + "'");
			
			if(spielSet.next())
			{
				ResultSet spielerSet = db.auslesen("spieler", " WHERE SpielID='" + spielSet.getString("SpielID") + "'");
				ResultSet spielfelderSet = db.auslesen("spielfelder", "WHERE SpielID='" + spielSet.getString("SpielID") + "'");
			
				ArrayList<Spieler> sp = new ArrayList<Spieler>();
			
				while(spielerSet.next())
				{
					sp.add(new Spieler(Integer.parseInt(spielerSet.getString("Gold")), Integer.parseInt(spielerSet.getString("Position"))));
				}
			
			
				ArrayList<Integer> sf = new ArrayList<Integer>();
			
				while(spielfelderSet.next())
				{
					sf.add( spielfelderSet.getInt("SpielerID"));
				}
			
				s = new Spiel(spielSet.getInt("AmZug"), sp, sf);
				
				SpielfeldController.spiel = s;
			}
			else
			{
				throw new SQLException();
			}
			
		}
		catch (SQLException e)
		{
			System.out.println("Laden nicht möglich");
			e.printStackTrace();
			s = new Spiel();
		}
		
		return s;
	}
	
	public void neuesSpiel(String name, int spielerzahl)
	{
		db.einlesen("spiel", "AmZug, Spielname", "0, '" + name + "'");

		try
		{
			ResultSet rs =  db.auslesen("spiel", "WHERE Spielname ='" + name + "'");
			rs.next();
			String spielID = rs.getString("SpielID");
			
			for(int i = 0; i<10; i++)
			{
				db.einlesen("spielfelder", "SpielID", spielID);
			}
			
			
			for(int i = 0; i<spielerzahl; i++)
			{
				db.einlesen("spieler", "Gold, Position, SpielID", Spieler.startkapital + ", 0, " + spielID);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean isSpielExistent(String name)
	{
		boolean b = false;
		
		ResultSet rs = db.auslesen("spiel", "WHERE Spielname = '" + name + "'");
		
		try {
			if(rs.next())
			{
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		return b;
		
	}
	
	public ObservableList<Spielstand> alleSpielständeLaden()
	{
		ArrayList<Spielstand> spielständeAl = new ArrayList<Spielstand>();
		
		ResultSet rs = db.auslesen("spiel", "");
		
		try
		{
			while(rs.next())
			{
				spielständeAl.add(new Spielstand(rs.getString("Spielname"), rs.getString("ZuletztGespielt")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		ObservableList<Spielstand> spielstände = FXCollections.observableList(spielständeAl);
		return spielstände;
	}
}
