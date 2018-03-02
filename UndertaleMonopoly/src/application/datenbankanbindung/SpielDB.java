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
	private ArrayList<UpdateEnum> änderungen = new ArrayList<UpdateEnum>();
	private KaufbaresFeld besitzerÄnderung;
	private ArrayList<BebaubaresFeld> hausänderung = new ArrayList<BebaubaresFeld>();
	
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
					Integer[] hinzufügen = {-1, -1};
					
					if(spielfelderSet.getString("Besitzer")!=null)
					{
						hinzufügen[0] = spielfelderSet.getInt("Besitzer");
					}
					
					if(spielfelderSet.getString("Haeuser")!=null)
					{
						hinzufügen[1] = spielfelderSet.getInt("Haeuser");
					}
					
					sf.add(hinzufügen);
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
			System.out.println("Laden nicht möglich");
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
	
	
	public void alleMitNamenLöschen(String name)
	{
		try
		{
			
			ResultSet rs = db.auslesen("spiel", "WHERE Spielname = '" + name + "'");
			rs.next();
			int spielID = rs.getInt("SpielID");
			
			db.löschen("spiel", "SpielID=" + spielID);
			db.löschen("spieler", "SpielID=" + spielID);
			db.löschen("spielfelder", "SpielID=" + spielID);
		}
		catch (SQLException e)
		{
			
		}
	}
	
	
	
	public void updateGame()
	{
		if(änderungen.contains(UpdateEnum.besitzer))
		{
			updateSpielfeldBesitzer(besitzerÄnderung);
			
			änderungen.remove(UpdateEnum.besitzer);
		}
		if(änderungen.contains(UpdateEnum.häuser))
		{
			for(int i = 0; i<hausänderung.size(); i++)
			{
				updateSpielfeldHäuser(hausänderung.get(i));
			}
			änderungen.remove(UpdateEnum.häuser);
		}
		if(änderungen.contains(UpdateEnum.amZug))
		{
			int amZug = SpielfeldController.spiel.getAmZug();
			
			updateSpielAmZug(amZug);
			
			
			änderungen.remove(UpdateEnum.amZug);
		}
		if(änderungen.contains(UpdateEnum.paschcounter))
		{
			updateSpielPaschcounter();
			änderungen.remove(UpdateEnum.paschcounter);
		}
		if(änderungen.contains(UpdateEnum.gold))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerGold(spieler[i]);
			}
			
			änderungen.remove(UpdateEnum.gold);
		}
		if(änderungen.contains(UpdateEnum.position))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerPosition(spieler[i]);
			}
			
			änderungen.remove(UpdateEnum.position);
		}
		if(änderungen.contains(UpdateEnum.gefängnis))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerGefängnisZeit(spieler[i]);
			}
			
			änderungen.remove(UpdateEnum.gefängnis);
		}
		if(änderungen.contains(UpdateEnum.paralyse))
		{
			Spieler[] spieler = SpielfeldController.spiel.getSpieler();
			for(int i = 0; i<spieler.length; i++)
			{
				updateSpielerRundenStehenBleiben(spieler[i]);
			}
			
			änderungen.remove(UpdateEnum.paralyse);
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
	
	private void updateSpielfeldHäuser(BebaubaresFeld bf)
	{
		int spielfeldID = getSpielfeldID(bf);
		
		db.update("spielfelder", "Haeuser=" + bf.getHäuser(), "SpielID=" + spielID + " AND SpielfelderID=" + spielfeldID);
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
	
	private void updateSpielerGefängnisZeit(Spieler s)
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
		int rückgabe = 0;
		try
		{
			rs.next();
			rückgabe = rs.getInt("SpielerID") + SpielfeldController.spiel.getIndexOfSpieler(s);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return rückgabe;
	}
	
	private int getSpielfeldID(KaufbaresFeld kf)
	{
		ResultSet rs = db.auslesen("spielfelder", "WHERE SpielID=" + spielID);
		int rückgabe = 0;
		try
		{
			rs.next();
			rückgabe = rs.getInt("SpielfelderID") + SpielfeldController.spiel.getIndexOfKaufbaresFeld(kf);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return rückgabe;
	}
	
	
	public void änderungHinzufügen(UpdateEnum änderung)
	{
		if(!änderungen.contains(änderung))
		{
			änderungen.add(änderung);
		}
	}
	
	public void setBesitzerÄnderung(KaufbaresFeld k)
	{
		this.besitzerÄnderung = k;
	}
	
	public void häuserÄnderungHinzufügen(BebaubaresFeld bf)
	{
		if(!hausänderung.contains(bf))
		{
			hausänderung.add(bf);
		}
	}
	
}
