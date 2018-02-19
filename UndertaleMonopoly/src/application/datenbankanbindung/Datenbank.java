package application.datenbankanbindung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Datenbank {

	private Connection verbindung;
	
	public Datenbank()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			verbindung = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
			
			Statement anweisung = verbindung.createStatement();
			anweisung.executeUpdate(
				"CREATE TABLE IF NOT EXISTS UndertaleMonopoly.spieler(" +
					"SPIELERID INT NOT NULL Auto_Increment, " +
					"GOLD INT NOT NULL, " +
					"POSITION INT NOT NULL," +
					"SPIELID INT NOT NULL " +
				")");
			
			anweisung.executeUpdate(
					"CREATE TABLE IF NOT EXISTS UndertaleMonopoly.spiel(" +
						"SPIELID INT NOT NULL Auto_Increment, " +
						"AMZUG INT, " +
						"SPIELNAME VARCHAR(32), " +
						"ZULETZTGESPEICHERT TIMESTAMP " +
					")");
			
			anweisung.executeUpdate(
					"CREATE TABLE IF NOT EXISTS UndertaleMonopoly.spielfelder(" +
						"SPIELFELDERID INT NOT NULL Auto_Increment, " +
						"SPIELID INT NOT NULL" +
						"SPIELERID INT " +
					")");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Klasse nicht gefunden");
		}
		catch(SQLException e)
		{
			System.out.println("SQL Fehler");
			e.printStackTrace();
		}
	}
	
	public void einlesen(String tabelle, String spalten, String values)
	{
		try
		{
			
			Statement anweisung = verbindung.createStatement();
			anweisung.executeUpdate("INSERT INTO UndertaleMonopoly." + tabelle + "(" + spalten + ") VALUES(" + values + ")");
			
		}
		catch (SQLException e)
		{
			
			System.out.println("Einlesen funktioniert nicht");
		}
	}
	
	public ResultSet auslesen(String tabelle, String query)
	{
		ResultSet rs = null;
		try	
		{
			Statement anweisung = verbindung.createStatement();
			rs = anweisung.executeQuery("SELECT * FROM UndertaleMonopoly." + tabelle + "KONTAKTE" + query);
		}
		catch (SQLException e)
		{
			System.out.println("Auslesen funktioniert nicht");
			e.printStackTrace();
		}
		return rs;
	}
	
	public void löschen(String tabelle, String query)
	{
		try
		{
			Statement anweisung = verbindung.createStatement();
			anweisung.executeUpdate("DELETE FROM UndertaleMonopoly." + tabelle + "kontakte WHERE " + query);
		}
		catch (SQLException e)
		{
			
			System.out.println("Löschen funktioniert nicht");
			e.printStackTrace();
		}
	}
	
	public ResultSet eigeneSeclectAbfrage(String query)
	{
		ResultSet rs = null;
		try	
		{
			Statement anweisung = verbindung.createStatement();
			rs = anweisung.executeQuery("query");
		}
		catch (SQLException e)
		{
			System.out.println("Auslesen funktioniert nicht");
			e.printStackTrace();
		}
		return rs;
	}
}
