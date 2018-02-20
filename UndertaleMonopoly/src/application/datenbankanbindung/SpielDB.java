package application.datenbankanbindung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import application.spiel.Spiel;
import application.spiel.Spieler;

public class SpielDB {

	private Datenbank db;
	
	public Spiel spielstandLaden(String name)
	{
		try
		{
			ResultSet spielSet = db.auslesen("spiel", " WHERE SPIELNAME='" + name + "'");
			ResultSet spielerSet = db.auslesen("spiel", " WHERE SPIELID='" + spielSet.getString("SPIELID") + "'");
			ResultSet spielfelderSet = db.auslesen("spielfelder", "WHERE SPIELID='" + spielSet.getString("SPIELID") + "'");
			
			
			
			for(spielerSet.next())
			{
				s = {new Spieler(Integer.parseInt(spielerSet.getString("GOLD")), Integer.parseInt(spielerSet.getString("POSITION")))};
			}
			
		}
		catch (SQLException e)
		{
			System.out.println("Laden nicht möglich");
			e.printStackTrace();
		}
		
		Spiel s = new Spiel();
		
		return s;
	}
	
	public void neuesSpiel(String name, int spielerzahl)
	{
		db.einlesen("spiel", "AMZUG, SPIELNAME, ZULETZTGESPEICHERT", "0, " + name + ", " + new Timestamp(System.currentTimeMillis()).toString());

		try
		{
			String spielID = db.auslesen("spielfelder", "WHERE SPIELNAME ='" + name + "'").getString("SPIELID");
			
			for(int i = 0; i<10; i++)
			{
				db.einlesen("spielfelder", "SPIELID", spielID);
			}
			
			
			for(int i = 0; i<spielerzahl; i++)
			{
				db.einlesen("spieler", "GOLD, POSITION, SPIELID", "100, 0, " + spielID);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
