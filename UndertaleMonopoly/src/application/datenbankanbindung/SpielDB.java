package application.datenbankanbindung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.gui.SpielfeldController;
import application.gui.Spielstand;
import application.spiel.Spiel;
import application.spiel.Spieler;
import application.spiel.spielfelder.BebaubaresFeld;
import application.spiel.spielfelder.KaufbaresFeld;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SpielDB {

	private Datenbank db = new Datenbank();
	private static int spielID;
	private ArrayList<UpdateEnum> aenderungen = new ArrayList<UpdateEnum>();
	private KaufbaresFeld besitzerAenderung;
	private ArrayList<BebaubaresFeld> hausaenderung = new ArrayList<BebaubaresFeld>();
	
	public void spielstandLaden(String name)
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
					sp.add(new Spieler(spielerSet.getInt("Gold"), spielerSet.getByte("Position"), spielerSet.getByte("VerbleibendeGefaengnisZeit"), spielerSet.getByte("RundenStehenBleiben")));
				}
			
			
				ArrayList<Integer[]> sf = new ArrayList<Integer[]>();
			
				while(spielfelderSet.next())
				{
					Integer[] hinzufuegen = {-1, -1};
					
					if(spielfelderSet.getString("Besitzer")!=null)
					{
						hinzufuegen[0] = spielfelderSet.getInt("Besitzer");
					}
					
					if(spielfelderSet.getString("Haeuser")!=null)
					{
						hinzufuegen[1] = spielfelderSet.getInt("Haeuser");
					}
					
					sf.add(hinzufuegen);
				}
			
				s = new Spiel(spielSet.getInt("AmZug"), spielSet.getInt("Paschcounter"), sp, sf, name);
				spielID = spielSet.getInt("SpielID");
				
				SpielfeldController.spiel = s;
			}
			else
			{
				throw new SQLException();
			}
			
		}
		catch (SQLException e)
		{
			System.out.println("Laden nicht moeglich");
			e.printStackTrace();
			s = new Spiel(4, name);
		}
	}
	
	
	
	
	public void neuesSpiel(String name, int spielerzahl)
	{
		db.einlesen("spiel", "AmZug, Spielname", "0, '" + name + "'");

		try
		{
			ResultSet rs =  db.auslesen("spiel", "WHERE Spielname ='" + name + "'");
			rs.next();
			spielID = rs.getInt("SpielID");
			
			KaufbaresFeld[] felder = SpielfeldController.spiel.getKaufbareFelder();
			
			for(int i = 0; i<felder.length; i++)
			{
				if(felder[i] instanceof BebaubaresFeld)
				{
					db.einlesen("spielfelder", "SpielID, Haeuser", spielID + ", 0");
				}
				else
				{
					db.einlesen("spielfelder", "SpielID, Haeuser", spielID + ", NULL");
				}
				
			}
			
			
			for(int i = 0; i<spielerzahl; i++)
			{
				db.einlesen("spieler", "Gold, Position, SpielID, VerbleibendeGefaengnisZeit, RundenStehenBleiben", Spieler.startkapital + ", 0, " + spielID + ", -1, 0");
			}
			
			
			SpielfeldController.spiel = new Spiel(spielerzahl, name);
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
	
	public ObservableList<Spielstand> alleSpielstaendeLaden()
	{
		ArrayList<Spielstand> spielstaendeAl = new ArrayList<Spielstand>();
		
		ResultSet rs = db.auslesen("spiel", "");
		
		try
		{
			while(rs.next())
			{
				spielstaendeAl.add(new Spielstand(rs.getString("Spielname"), rs.getString("ZuletztGespielt")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		ObservableList<Spielstand> spielstaende = FXCollections.observableList(spielstaendeAl);
		return spielstaende;
	}
	
	
	public void alleMitNamenLoeschen(String name)
	{
		try
		{
			
			ResultSet rs = db.auslesen("spiel", "WHERE Spielname = '" + name + "'");
			rs.next();
			int spielID = rs.getInt("SpielID");
			
			db.loeschen("spiel", "SpielID=" + spielID);
			db.loeschen("spieler", "SpielID=" + spielID);
			db.loeschen("spielfelder", "SpielID=" + spielID);
		}
		catch (SQLException e)
		{
			
		}
	}
	
	
	
	public void updateGame()
	{
		if(aenderungen.contains(UpdateEnum.besitzer))
		{
			updateSpielfeldBesitzer(besitzerAenderung);
			
			aenderungen.remove(UpdateEnum.besitzer);
		}
		if(aenderungen.contains(UpdateEnum.haeuser))
		{
			for(int i = 0; i<hausaenderung.size(); i++)
			{
				updateSpielfeldHaeuser(hausaenderung.get(i));
			}
			aenderungen.remove(UpdateEnum.haeuser);
		}
		if(aenderungen.contains(UpdateEnum.amZug))
		{
			int amZug = SpielfeldController.spiel.getAmZug();
			
			updateSpielAmZug(amZug);
			
			
			aenderungen.remove(UpdateEnum.amZug);
		}
		if(aenderungen.contains(UpdateEnum.paschcounter))
		{
			updateSpielPaschcounter();
			aenderungen.remove(UpdateEnum.paschcounter);
		}
		if(aenderungen.contains(UpdateEnum.gold))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerGold(spieler[i]);
			}
			
			aenderungen.remove(UpdateEnum.gold);
		}
		if(aenderungen.contains(UpdateEnum.position))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerPosition(spieler[i]);
			}
			
			aenderungen.remove(UpdateEnum.position);
		}
		if(aenderungen.contains(UpdateEnum.gefaengnis))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerGefaengnisZeit(spieler[i]);
			}
			
			aenderungen.remove(UpdateEnum.gefaengnis);
		}
		if(aenderungen.contains(UpdateEnum.paralyse))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerRundenStehenBleiben(spieler[i]);
			}
			
			aenderungen.remove(UpdateEnum.paralyse);
		}
		
			
				
				
		aktualisiereZuletztGespielt();
	}
	
	private void aktualisiereZuletztGespielt()
	{
		db.update("spiel", "ZuletztGespielt=CURRENT_TIMESTAMP", "SpielID=" + spielID);
	}
	
	private void updateSpielfeldBesitzer(KaufbaresFeld kf)
	{
		int spielfeldID = getSpielfeldID(kf);
		
		db.update("spielfelder", "Besitzer=" + SpielfeldController.spiel.getIndexOfSpieler(kf.getBesitzer()), "SpielID=" + spielID + " AND SpielfelderID=" + spielfeldID);
		
	}
	
	private void updateSpielfeldHaeuser(BebaubaresFeld bf)
	{
		int spielfeldID = getSpielfeldID(bf);
		
		db.update("spielfelder", "Haeuser=" + bf.getHaeuser(), "SpielID=" + spielID + " AND SpielfelderID=" + spielfeldID);
	}
	
	
	
	private void updateSpielAmZug(int amZug)
	{
		db.update("spiel", "AmZug=" + amZug, "SpielID=" + spielID);
	}
	
	private void updateSpielPaschcounter()
	{
		db.update("spiel", "Paschcounter=" + SpielfeldController.spiel.getPaschcounter(), "SpielID=" + spielID);
	}
	
	
	
	
	private void updateSpielerGold(Spieler s)
	{
		int gold = s.getGold();
		
		int spielerID = 0;
		
		ResultSet rs = db.auslesen("spieler", "WHERE SpielID=" + spielID);
		try
		{
			rs.next();
			spielerID = rs.getInt("SpielerID") + SpielfeldController.spiel.getIndexOfSpieler(s);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		db.update("spieler", "Gold=" + gold, "SpielID=" + spielID + " AND SpielerID=" + spielerID);
	}
	
	private void updateSpielerPosition(Spieler s)
	{
		db.update("spieler", "position=" + s.getPosition(), "SpielID=" + spielID + " AND SpielerID=" + getSpielerID(s));
	}
	
	private void updateSpielerGefaengnisZeit(Spieler s)
	{
		db.update("spieler", "VerbleibendeGefaengnisZeit=" + s.getVerbleibendeGefangenenZeit(), "SpielID=" + spielID + " AND SpielerID=" + getSpielerID(s));
	}
	
	private void updateSpielerRundenStehenBleiben(Spieler s)
	{
		db.update("spieler", "RundenStehenBleiben=" + s.getRundenStehenBleiben() , "SpielID=" + spielID + " AND SpielerID=" + getSpielerID(s));
	}
	
	
	
	
	private int getSpielerID(Spieler s)
	{
		ResultSet rs = db.auslesen("spieler", "WHERE SpielID=" + spielID);
		int rueckgabe = 0;
		try
		{
			rs.next();
			rueckgabe = rs.getInt("SpielerID") + SpielfeldController.spiel.getIndexOfSpieler(s);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return rueckgabe;
	}
	
	private int getSpielfeldID(KaufbaresFeld kf)
	{
		ResultSet rs = db.auslesen("spielfelder", "WHERE SpielID=" + spielID);
		int rueckgabe = 0;
		try
		{
			rs.next();
			rueckgabe = rs.getInt("SpielfelderID") + SpielfeldController.spiel.getIndexOfKaufbaresFeld(kf);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return rueckgabe;
	}
	
	
	public void aenderungHinzufuegen(UpdateEnum aenderung)
	{
		if(!aenderungen.contains(aenderung))
		{
			aenderungen.add(aenderung);
		}
	}
	
	public void setBesitzeraenderung(KaufbaresFeld k)
	{
		this.besitzerAenderung = k;
	}
	
	public void haeuseraenderungHinzufuegen(BebaubaresFeld bf)
	{
		if(!hausaenderung.contains(bf))
		{
			hausaenderung.add(bf);
		}
	}
	
}
