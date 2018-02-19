package application.datenbankanbindung;

import java.sql.SQLException;
import java.sql.Timestamp;

public class SpielDB {

	private Datenbank db;
	
	public void spielstandLaden()
	{
		db.auslesen(tabelle, query)
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
