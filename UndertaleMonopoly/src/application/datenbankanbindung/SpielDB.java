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
	private ArrayList<UpdateEnum> �nderungen = new ArrayList<UpdateEnum>();
	private KaufbaresFeld besitzer�nderung;
	private ArrayList<BebaubaresFeld> haus�nderung = new ArrayList<BebaubaresFeld>();
	
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
					Integer[] hinzuf�gen = {-1, -1};
					
					if(spielfelderSet.getString("Besitzer")!=null)
					{
						hinzuf�gen[0] = spielfelderSet.getInt("Besitzer");
					}
					
					if(spielfelderSet.getString("Haeuser")!=null)
					{
						hinzuf�gen[1] = spielfelderSet.getInt("Haeuser");
					}
					
					sf.add(hinzuf�gen);
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
			System.out.println("Laden nicht m�glich");
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
	
	public ObservableList<Spielstand> alleSpielst�ndeLaden()
	{
		ArrayList<Spielstand> spielst�ndeAl = new ArrayList<Spielstand>();
		
		ResultSet rs = db.auslesen("spiel", "");
		
		try
		{
			while(rs.next())
			{
				spielst�ndeAl.add(new Spielstand(rs.getString("Spielname"), rs.getString("ZuletztGespielt")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		ObservableList<Spielstand> spielst�nde = FXCollections.observableList(spielst�ndeAl);
		return spielst�nde;
	}
	
	
	public void alleMitNamenL�schen(String name)
	{
		try
		{
			
			ResultSet rs = db.auslesen("spiel", "WHERE Spielname = '" + name + "'");
			rs.next();
			int spielID = rs.getInt("SpielID");
			
			db.l�schen("spiel", "SpielID=" + spielID);
			db.l�schen("spieler", "SpielID=" + spielID);
			db.l�schen("spielfelder", "SpielID=" + spielID);
		}
		catch (SQLException e)
		{
			
		}
	}
	
	
	
	public void updateGame()
	{
		if(�nderungen.contains(UpdateEnum.besitzer))
		{
			updateSpielfeldBesitzer(besitzer�nderung);
			
			�nderungen.remove(UpdateEnum.besitzer);
		}
		if(�nderungen.contains(UpdateEnum.h�user))
		{
			for(int i = 0; i<haus�nderung.size(); i++)
			{
				updateSpielfeldH�user(haus�nderung.get(i));
			}
			�nderungen.remove(UpdateEnum.h�user);
		}
		if(�nderungen.contains(UpdateEnum.amZug))
		{
			int amZug = SpielfeldController.spiel.getAmZug();
			
			updateSpielAmZug(amZug);
			
			
			�nderungen.remove(UpdateEnum.amZug);
		}
		if(�nderungen.contains(UpdateEnum.paschcounter))
		{
			updateSpielPaschcounter();
			�nderungen.remove(UpdateEnum.paschcounter);
		}
		if(�nderungen.contains(UpdateEnum.gold))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerGold(spieler[i]);
			}
			
			�nderungen.remove(UpdateEnum.gold);
		}
		if(�nderungen.contains(UpdateEnum.position))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerPosition(spieler[i]);
			}
			
			�nderungen.remove(UpdateEnum.position);
		}
		if(�nderungen.contains(UpdateEnum.gef�ngnis))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerGef�ngnisZeit(spieler[i]);
			}
			
			�nderungen.remove(UpdateEnum.gef�ngnis);
		}
		if(�nderungen.contains(UpdateEnum.paralyse))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerRundenStehenBleiben(spieler[i]);
			}
			
			�nderungen.remove(UpdateEnum.paralyse);
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
	
	private void updateSpielfeldH�user(BebaubaresFeld bf)
	{
		int spielfeldID = getSpielfeldID(bf);
		
		db.update("spielfelder", "Haeuser=" + bf.getH�user(), "SpielID=" + spielID + " AND SpielfelderID=" + spielfeldID);
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
	
	private void updateSpielerGef�ngnisZeit(Spieler s)
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
		int r�ckgabe = 0;
		try
		{
			rs.next();
			r�ckgabe = rs.getInt("SpielerID") + SpielfeldController.spiel.getIndexOfSpieler(s);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return r�ckgabe;
	}
	
	private int getSpielfeldID(KaufbaresFeld kf)
	{
		ResultSet rs = db.auslesen("spielfelder", "WHERE SpielID=" + spielID);
		int r�ckgabe = 0;
		try
		{
			rs.next();
			r�ckgabe = rs.getInt("SpielfelderID") + SpielfeldController.spiel.getIndexOfKaufbaresFeld(kf);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return r�ckgabe;
	}
	
	
	public void �nderungHinzuf�gen(UpdateEnum �nderung)
	{
		if(!�nderungen.contains(�nderung))
		{
			�nderungen.add(�nderung);
		}
	}
	
	public void setBesitzer�nderung(KaufbaresFeld k)
	{
		this.besitzer�nderung = k;
	}
	
	public void h�user�nderungHinzuf�gen(BebaubaresFeld bf)
	{
		if(!haus�nderung.contains(bf))
		{
			haus�nderung.add(bf);
		}
	}
	
}
