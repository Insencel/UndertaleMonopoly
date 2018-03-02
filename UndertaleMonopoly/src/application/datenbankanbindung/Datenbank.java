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
					"CREATE TABLE IF NOT EXISTS UndertaleMonopoly.spiel(" +
						"SpielID INT NOT NULL Auto_Increment, " +
						"AmZug INT NOT NULL DEFAULT 0, " +
						"Paschcounter INT NOT NULL DEFAULT 0, " +
						"Spielname VARCHAR(32), " +
						"ZuletztGespielt TIMESTAMP, " +
						"PRIMARY KEY (SpielID)" +
					")");
			
			anweisung.executeUpdate(
				"CREATE TABLE IF NOT EXISTS UndertaleMonopoly.spieler(" +
					"SpielerID INT NOT NULL Auto_Increment, " +
					"SpielID INT NOT NULL, " +
					"Gold INT NOT NULL, " +
					"Position INT NOT NULL," +
					"VerbleibendeGefaengnisZeit INT NOT NULL," +
					"RundenStehenBleiben INT NOT NULL," +
					"PRIMARY KEY (SPIELERID), " +
					"INDEX (SpielID)" +
				")");
			
			anweisung.executeUpdate(
					"CREATE TABLE IF NOT EXISTS UndertaleMonopoly.spielfelder(" +
						"SpielfelderID INT NOT NULL Auto_Increment, " +
						"SpielID INT NOT NULL, " +
						"Besitzer INT, " +
						"Haeuser INT," +
						"PRIMARY KEY (SpielfelderID), " +
						"INDEX (SpielID)" +
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
			//anweisung.executeUpdate("INSERT INTO UndertaleMonopoly.spiel (AmZug, Spielname) VALUES (0, Test)");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Einlesen funktioniert nicht");
		}
	}
	
	public ResultSet auslesen(String tabelle, String query)
	{
		ResultSet rs = null;
		try	
		{
			Statement anweisung = verbindung.createStatement();
			rs = anweisung.executeQuery("SELECT * FROM UndertaleMonopoly." + tabelle + " " + query);
			
		}
		catch (SQLException e)
		{
			System.out.println("Fehler beim Auslesen");
			e.printStackTrace();
		}
		return rs;
	}
	
	public void loeschen(String tabelle, String query)
	{
		try
		{
			Statement anweisung = verbindung.createStatement();
			anweisung.executeUpdate("DELETE FROM UndertaleMonopoly." + tabelle + " WHERE " + query);
		}
		catch (SQLException e)
		{
			
			System.out.println("Loeschen funktioniert nicht");
			e.printStackTrace();
		}
	}
	
	public void update(String tabelle, String set, String query)
	{
		try
		{
			Statement anweisung = verbindung.createStatement();
			anweisung.executeUpdate("UPDATE UndertaleMonopoly." + tabelle + " SET " + set + " WHERE " + query);
		}
		catch (SQLException e)
		{
			
			System.out.println("Update funktioniert nicht");
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
