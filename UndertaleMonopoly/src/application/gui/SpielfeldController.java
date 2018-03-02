package application.gui;


import java.io.IOException;

import application.Main;
import application.datenbankanbindung.SpielDB;
import application.spiel.Spiel;
import application.spiel.SpielerBewegungsunfähigException;
import application.spiel.SpielerGefangenException;
import application.spiel.spielfelder.Gefängnisfeld;
import application.spiel.spielfelder.KaufbaresFeld;
import application.spiel.spielfelder.Produktionsfeld;
import application.spiel.spielfelder.Riverladyfeld;
import application.spiel.spielfelder.BebaubaresFeld;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class SpielfeldController
{
	@FXML
	private GridPane gp;
	@FXML
	private ImageView sp1;
	@FXML
	private ImageView sp2;
	@FXML
	private ImageView sp3;
	@FXML
	private ImageView sp4;
	@FXML
	private ImageView move;
	@FXML
	private ImageView wuerfelL;
	@FXML
	private ImageView wuerfelR;
	@FXML
	private Rectangle ladescreen;
	@FXML
	private ImageView vergroesserung;
	@FXML
	private Label spieler;
	@FXML
	private Label goldmenge;
	@FXML
	private GridPane feldkarte;
	@FXML
	private GridPane feldGP;
	@FXML
	private ImageView umblaetterpfeil;
	
	@FXML
	private Label feldname;
	@FXML
	private Label owner;
	@FXML
	private Label preis;
	
	@FXML
	private GridPane plBebaubar;
	@FXML
	private Label aufenthaltsgebuehr;
	@FXML
	private Label aufenthaltsgebuehr1house;
	@FXML
	private Label aufenthaltsgebuehr2house;
	@FXML
	private Label aufenthaltsgebuehr3house;
	@FXML
	private Label aufenthaltsgebuehr4house;
	@FXML
	private Label aufenthaltsgebuehrHotel;
	
	@FXML
	private GridPane plRiverlady;
	@FXML
	private Label miete1Hafen;
	@FXML
	private Label miete2Haefen;
	@FXML
	private Label miete3Haefen;
	@FXML
	private Label miete4Haefen;
	
	@FXML
	private GridPane plProduktion;
	@FXML
	private Label miete1Produktion;
	@FXML
	private Label miete2Produktion;
	
	@FXML
	private GridPane buyMenu;
	@FXML
	private Label buy;
	@FXML
	private Label dontBuy;
	@FXML
	private GridPane spielfeldTextGP;
	@FXML
	private Text spielfeldEventText;
	@FXML
	private Label okKnopf;
	@FXML
	private GridPane gefangenerEntscheidung;
	@FXML
	private Label t2rd;
	@FXML
	private Label pay50g;
	@FXML
	private GridPane eventkarte;
	@FXML
	private Text eventkartenText;
	@FXML
	private Label hausbau;
	
	
	
	
	@FXML
	private ImageView f1;
	@FXML
	private ImageView f2;
	@FXML
	private ImageView f3;
	@FXML
	private ImageView f4;
	@FXML
	private ImageView f5;
	@FXML
	private ImageView f6;
	@FXML
	private ImageView f7;
	@FXML
	private ImageView f8;
	@FXML
	private ImageView f9;
	@FXML
	private ImageView f10;
	@FXML
	private ImageView f11;
	@FXML
	private ImageView f12;
	@FXML
	private ImageView f13;
	@FXML
	private ImageView f14;
	@FXML
	private ImageView f15;
	@FXML
	private ImageView f16;
	@FXML
	private ImageView f17;
	@FXML
	private ImageView f18;
	@FXML
	private ImageView f19;
	@FXML
	private ImageView f20;
	@FXML
	private ImageView f21;
	@FXML
	private ImageView f22;
	@FXML
	private ImageView f23;
	@FXML
	private ImageView f24;
	@FXML
	private ImageView f25;
	@FXML
	private ImageView f26;
	@FXML
	private ImageView f27;
	@FXML
	private ImageView f28;
	@FXML
	private ImageView f29;
	@FXML
	private ImageView f30;
	@FXML
	private ImageView f31;
	@FXML
	private ImageView f32;
	@FXML
	private ImageView f33;
	@FXML
	private ImageView f34;
	@FXML
	private ImageView f35;
	@FXML
	private ImageView f36;
	@FXML
	private ImageView f37;
	@FXML
	private ImageView f38;
	@FXML
	private ImageView f39;
	@FXML
	private ImageView f40;
	
	public static Spiel spiel = new Spiel(0, null);
	public static SpielDB sdb = new SpielDB();
	private ImageView[] bilder;
	//private final Image[] bilder = {new Image("bilder/spielfelder/Start.png"), new Image("bilder/spielfelder/Feld1.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld2.png"), new Image("bilder/spielfelder/Flowey.png"), new Image("bilder/spielfelder/Riverlady.png"), new Image("bilder/spielfelder/Feld3.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld4.png"), new Image("bilder/spielfelder/Feld5.png"), new Image("bilder/spielfelder/Gefängnis.png"), new Image("bilder/spielfelder/Feld6.png"), new Image("bilder/spielfelder/Ice-Fabric.png"), new Image("bilder/spielfelder/Feld7.png"), new Image("bilder/spielfelder/Feld8.png"), new Image("bilder/spielfelder/Riverlady.png"), new Image("bilder/spielfelder/Feld9.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld10.png"), new Image("bilder/spielfelder/Feld11.png"),  new Image("bilder/spielfelder/FreeParking.png"), new Image("bilder/spielfelder/Feld12.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld13.png"), new Image("bilder/spielfelder/Feld14.png"), new Image("bilder/spielfelder/Riverlady.png"), new Image("bilder/spielfelder/Feld15.png"), new Image("bilder/spielfelder/Feld16.png"), new Image("bilder/spielfelder/Core.png"), new Image("bilder/spielfelder/Feld17.png"), new Image("bilder/spielfelder/Gefängnis.png"), new Image("bilder/spielfelder/Feld18.png"), new Image("bilder/spielfelder/Feld19.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld20.png"), new Image("bilder/spielfelder/Riverlady.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld21.png"), new Image("bilder/spielfelder/strafe_judgment_hall.png") , new Image("bilder/spielfelder/Feld22.png")};
	private int zumKaufenMarkiertesFeld = -1;
	private int anzeigeIndex;
	
	@SuppressWarnings("static-access")
	@FXML
	public void richtigStellen()
	{
		this.bilder = new ImageView[]{f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, f21, f22, f23, f24, f25, f26, f27, f28, f29, f30, f31, f32, f33, f34, f35, f36, f37, f38, f39, f40};
		switch(spiel.getSpieler().length)
		{
		case 4:
			gp.setColumnIndex(sp4, spiel.getTabellenposition(spiel.getSpieler()[3].getPosition())[0]);
			gp.setRowIndex(sp4, spiel.getTabellenposition(spiel.getSpieler()[3].getPosition())[1]);
			
		case 3:
			gp.setColumnIndex(sp3, spiel.getTabellenposition(spiel.getSpieler()[2].getPosition())[0]);
			gp.setRowIndex(sp3, spiel.getTabellenposition(spiel.getSpieler()[2].getPosition())[1]);
			
		case 2:
			gp.setColumnIndex(sp2, spiel.getTabellenposition(spiel.getSpieler()[1].getPosition())[0]);
			gp.setRowIndex(sp2, spiel.getTabellenposition(spiel.getSpieler()[1].getPosition())[1]);
			
			gp.setColumnIndex(sp1, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[0]);
			gp.setRowIndex(sp1, spiel.getTabellenposition(spiel.getSpieler()[0].getPosition())[1]);
			
		}
		
		textupdate();
		spieler.setVisible(true);
		goldmenge.setVisible(true);
		
		switch(spiel.getSpieler().length)
		{
		case 2:
			sp3.setVisible(false);
		case 3:
			sp4.setVisible(false);
		}
		
		ladescreen.setVisible(false);
		
	}
	
	
	@SuppressWarnings("static-access")
	@FXML
	public void bewegen()
	{
		try
		{
			int[] würfel = spiel.momentanenSpielerBewegen();
			
			move.setVisible(false);
			wuerfelL.setVisible(true);
			wuerfelR.setVisible(true);
			feldkarte.setVisible(false);
			vergroesserung.setVisible(true);
				
			setWürfel(würfel);
		
			int spielerPosition = spiel.getMomentanenSpieler().getPosition();
			
			int[] position = spiel.getTabellenposition(spielerPosition);
			switch(spiel.getAmZug())
			{
			case 0:
				gp.setColumnIndex(sp1, position[0]);
				gp.setRowIndex(sp1, position[1]);
				break;
				
			case 1:
				gp.setColumnIndex(sp2, position[0]);
				gp.setRowIndex(sp2, position[1]);
				break;
					
			case 2:
				gp.setColumnIndex(sp3, position[0]);
				gp.setRowIndex(sp3, position[1]);
				break;
					
			case 3:
				gp.setColumnIndex(sp4, position[0]);
				gp.setRowIndex(sp4, position[1]);
				break;
			}
				
			spiel.getSpielfelder()[spielerPosition].funktion(this);
				
				
			anzeigen(bilder[spielerPosition]);
		}
		catch (SpielerGefangenException e)
		{
			spielfeldEventText.setText("You rolled a double 3 times in a row.\nGo to jail!");
			spielfeldEventTextAnzeigen();
			richtigStellen();
		}
	}
	
	public void textupdate()
	{
		spieler.setText("Player " + (spiel.getAmZug() + 1));
		
		goldmenge.setText(spiel.getMomentanenSpieler().getGold() + " G");
	}
	
	
	
	
	@FXML
	public void anzeigen1()
	{
		anzeigen(f1);
	}
	@FXML
	public void anzeigen2()
	{
		anzeigen(f2);
	}
	@FXML
	public void anzeigen3()
	{
		anzeigen(f3);
	}
	@FXML
	public void anzeigen4()
	{
		anzeigen(f4);
	}
	@FXML
	public void anzeigen5()
	{
		anzeigen(f5);
	}
	@FXML
	public void anzeigen6()
	{
		anzeigen(f6);
	}
	@FXML
	public void anzeigen7()
	{
		anzeigen(f7);
	}
	@FXML
	public void anzeigen8()
	{
		anzeigen(f8);
	}
	@FXML
	public void anzeigen9()
	{
		anzeigen(f9);
	}
	@FXML
	public void anzeigen10()
	{
		anzeigen(f10);
	}
	@FXML
	public void anzeigen11()
	{
		anzeigen(f11);
	}
	@FXML
	public void anzeigen12()
	{
		anzeigen(f12);
	}
	@FXML
	public void anzeigen13()
	{
		anzeigen(f13);
	}
	@FXML
	public void anzeigen14()
	{
		anzeigen(f14);
	}
	@FXML
	public void anzeigen15()
	{
		anzeigen(f15);
	}
	@FXML
	public void anzeigen16()
	{
		anzeigen(f16);
	}
	@FXML
	public void anzeigen17()
	{
		anzeigen(f17);
	}
	@FXML
	public void anzeigen18()
	{
		anzeigen(f18);
	}
	@FXML
	public void anzeigen19()
	{
		anzeigen(f19);
	}
	@FXML
	public void anzeigen20()
	{
		anzeigen(f20);
	}
	public void anzeigen21()
	{
		anzeigen(f21);
	}
	@FXML
	public void anzeigen22()
	{
		anzeigen(f22);
	}
	@FXML
	public void anzeigen23()
	{
		anzeigen(f23);
	}
	@FXML
	public void anzeigen24()
	{
		anzeigen(f24);
	}
	@FXML
	public void anzeigen25()
	{
		anzeigen(f25);
	}
	@FXML
	public void anzeigen26()
	{
		anzeigen(f26);
	}
	@FXML
	public void anzeigen27()
	{
		anzeigen(f27);
	}
	@FXML
	public void anzeigen28()
	{
		anzeigen(f28);
	}
	@FXML
	public void anzeigen29()
	{
		anzeigen(f29);
	}
	@FXML
	public void anzeigen30()
	{
		anzeigen(f30);
	}
	@FXML
	public void anzeigen31()
	{
		anzeigen(f31);
	}
	@FXML
	public void anzeigen32()
	{
		anzeigen(f32);
	}
	@FXML
	public void anzeigen33()
	{
		anzeigen(f33);
	}
	@FXML
	public void anzeigen34()
	{
		anzeigen(f34);
	}
	@FXML
	public void anzeigen35()
	{
		anzeigen(f35);
	}
	@FXML
	public void anzeigen36()
	{
		anzeigen(f36);
	}
	@FXML
	public void anzeigen37()
	{
		anzeigen(f37);
	}
	@FXML
	public void anzeigen38()
	{
		anzeigen(f38);
	}
	@FXML
	public void anzeigen39()
	{
		anzeigen(f39);
	}
	@FXML
	public void anzeigen40()
	{
		anzeigen(f40);
	}
	
	
	
	@SuppressWarnings("static-access")
	public void anzeigen(ImageView iv)
	{
		int[] position = {gp.getColumnIndex(iv), gp.getRowIndex(iv)};
		
		int index = spiel.getIndexOfTabellenposition(position);
		
		vergroesserung.setImage(bilder[index].getImage());
		
		
		anzeigeIndex = index;
		
		
		if(spiel.getSpielfelder()[index] instanceof KaufbaresFeld)
		{
			umblaetterpfeil.setVisible(true);
			KaufbaresFeld kf = (KaufbaresFeld) spiel.getSpielfelder()[index];
			feldname.setText(kf.getName());
			preis.setText(kf.getPreis() + " G");
			
			plBebaubar.setVisible(false);
			plProduktion.setVisible(false);
			plRiverlady.setVisible(false);
			
			if(kf.getBesitzer()==null)
			{
				preis.setStyle("-fx-font-weight: bold");
				owner.setVisible(false);
			}
			else
			{
				preis.setStyle("-fx-font-weight: normal");
				
				owner.setText("Owner: Player " + (spiel.getIndexOfSpieler(kf.getBesitzer())+1));
				owner.setVisible(true);
			}
			
			
			if(kf instanceof BebaubaresFeld)
			{
				plBebaubar.setVisible(true);
				
				BebaubaresFeld bf = (BebaubaresFeld) kf;
				
				int[] preisliste = bf.getPreisliste();
				aufenthaltsgebuehr.setText(preisliste[0] + " G");
				aufenthaltsgebuehr1house.setText(preisliste[1] + " G");
				aufenthaltsgebuehr2house.setText(preisliste[2] + " G");
				aufenthaltsgebuehr3house.setText(preisliste[3] + " G");
				aufenthaltsgebuehr4house.setText(preisliste[4] + " G");
				aufenthaltsgebuehrHotel.setText(preisliste[5] + " G");
				
				aufenthaltsgebuehr.setStyle("-fx-font-weight: normal");
				aufenthaltsgebuehr1house.setStyle("-fx-font-weight: normal");
				aufenthaltsgebuehr2house.setStyle("-fx-font-weight: normal");
				aufenthaltsgebuehr3house.setStyle("-fx-font-weight: normal");
				aufenthaltsgebuehr4house.setStyle("-fx-font-weight: normal");
				aufenthaltsgebuehrHotel.setStyle("-fx-font-weight: normal");
				
				
				switch(bf.getHäuser())
				{
				case 5:
					aufenthaltsgebuehrHotel.setStyle("-fx-font-weight: bold");
					break;
				case 4:
					aufenthaltsgebuehr4house.setStyle("-fx-font-weight: bold");
					break;
				case 3:
					aufenthaltsgebuehr3house.setStyle("-fx-font-weight: bold");
					break;
				case 2:
					aufenthaltsgebuehr2house.setStyle("-fx-font-weight: bold");
					break;
				case 1:
					aufenthaltsgebuehr1house.setStyle("-fx-font-weight: bold");
					break;
				case 0:
					if(bf.getBesitzer()!=null)
					{
						aufenthaltsgebuehr.setStyle("-fx-font-weight: bold");
					}
				}
				
				
				
				if(spiel.getMomentanenSpieler().isBesitzerVonAllenFeldernImGebiet(bf.getGebiet()) && bf.getHäuser()<5)
				{
					hausbau.setText("Buy a " + ((bf.getHäuser()==4) ? "hotel" : "house") + " for " + bf.getZubauKosten() + " G");
					hausbau.setVisible(true);
				}
				else
				{
					hausbau.setVisible(false);
				}
			}
			else if(kf instanceof Produktionsfeld)
			{
				plProduktion.setVisible(true);
				
				Produktionsfeld pf = (Produktionsfeld) kf;
				
				int[] preisliste = pf.getPreisliste();
				
				miete1Produktion.setText(preisliste[0] + " G per eye");
				miete2Produktion.setText(preisliste[1] + " G per eye");
				
				miete1Produktion.setStyle("-fx-font-weight: normal");
				miete2Produktion.setStyle("-fx-font-weight: normal");
				
				if(pf.getBesitzer()!=null)
				{
					switch(pf.getBesitzer().getAnzahlAnProduktionImBesitz())
					{
					case 2:
						miete2Produktion.setStyle("-fx-font-weight: bold");
						break;
					case 1:
						miete1Produktion.setStyle("-fx-font-weight: bold");
						break;
					}
				}
				
			}
			else if(kf instanceof Riverladyfeld)
			{
				plRiverlady.setVisible(true);
				
				Riverladyfeld rf = (Riverladyfeld) kf;
				
				int[] preisliste = rf.getPreisliste();
				
				miete1Hafen.setText(preisliste[0] + " G");
				miete2Haefen.setText(preisliste[1] + " G");
				miete3Haefen.setText(preisliste[2] + " G");
				miete4Haefen.setText(preisliste[3] + " G");
				
				miete1Hafen.setStyle("-fx-font-weight: normal");
				miete2Haefen.setStyle("-fx-font-weight: normal");
				miete3Haefen.setStyle("-fx-font-weight: normal");
				miete4Haefen.setStyle("-fx-font-weight: normal");
				
				
				if(rf.getBesitzer()!=null)
				{
					switch(rf.getBesitzer().getAnzahlAnHäfenImBesitz())
					{
					case 4:
						miete4Haefen.setStyle("-fx-font-weight: bold");
						break;
					case 3:
						miete3Haefen.setStyle("-fx-font-weight: bold");
						break;
					case 2:
						miete2Haefen.setStyle("-fx-font-weight: bold");
						break;
					case 1:
						miete1Hafen.setStyle("-fx-font-weight: bold");
						break;
					}
				}
				
			}
			
			
			if(index==zumKaufenMarkiertesFeld)
			{
				kaufenAnzeigen(zumKaufenMarkiertesFeld);
			}
			else
			{
				buyMenu.setVisible(false);
			}
			
			
		}
		else
		{
			umblaetterpfeil.setVisible(false);
			feldkarte.setVisible(false);
			vergroesserung.setVisible(true);
		}
	}
	
	@FXML
	public void umdrehen()
	{
		if(feldkarte.isVisible())
		{
			feldkarte.setVisible(false);
			vergroesserung.setVisible(true);
		}
		else
		{
			feldkarte.setVisible(true);
			vergroesserung.setVisible(false);
		}
		
	}
	
	
	public void kaufenAnzeigen(int feld)
	{
		feldkarte.setVisible(true);
		buyMenu.setVisible(true);
		spielfeldTextGP.setVisible(true);
		vergroesserung.setVisible(false);
		okKnopf.setVisible(false);
	
		
		
		this.zumKaufenMarkiertesFeld = feld;
		
		if(spiel.getSpielfelder()[feld] instanceof BebaubaresFeld)
		{
			BebaubaresFeld kf = (BebaubaresFeld) spiel.getSpielfelder()[feld];
			preis.setText(kf.getPreis() + " G");
			
			int[] preisliste = kf.getPreisliste();
			aufenthaltsgebuehr.setText(preisliste[0] + " G");
		}
		
		vergroesserung.setImage(bilder[zumKaufenMarkiertesFeld].getImage());
	}
	
	
	
	public void spielfeldEventTextAnzeigen()
	{
		spielfeldTextGP.setVisible(true);
		move.setVisible(false);
	}
	
	private void aktiviereGefangenenAuswahl()
	{
		gefangenerEntscheidung.setVisible(true);
		okKnopf.setVisible(false);
	}
	private void deaktiviereGefangenenAuswahl()
	{
		gefangenerEntscheidung.setVisible(false);
		okKnopf.setVisible(true);
	}
	
	@FXML
	public void t2rd()
	{
		//try to roll doubles
		spiel.getGefängnis().freiwürfeln(this);
		deaktiviereGefangenenAuswahl();
	}
	
	@FXML
	public void pay50g()
	{
		spiel.getGefängnis().freikaufen(this);
		deaktiviereGefangenenAuswahl();
		textupdate();
	}
	
	public void eventkarteAnzeigen(String text)
	{
		eventkartenText.setText(text);
		eventkarte.setVisible(true);
	}
	
	
	@FXML
	public void kaufen()
	{
		//kann nur kaufbar sein!
		if(spiel.getSpielfelder()[zumKaufenMarkiertesFeld] instanceof KaufbaresFeld)
		{
			
			KaufbaresFeld kf = (KaufbaresFeld) spiel.getSpielfelder()[zumKaufenMarkiertesFeld];
			
			if(spiel.getMomentanenSpieler().getGold() >= kf.getPreis())
			{
				spiel.getMomentanenSpieler().kaufen(kf);
				
				nichtKaufen();
			}
			else
			{
				spielfeldEventText.setText("You don't have enough money to afford that.");
			}
			
			textupdate();
		}
		
		
	}
	
	@FXML
	public void nichtKaufen()
	{
		buyMenu.setVisible(false);
		zumKaufenMarkiertesFeld = -1;
		
		if(spiel.getPaschcounter()==0)
		{
			spielfeldEventText.setText("Next player!");
		}
		else
		{
			spielfeldEventText.setText("You rolled a double so roll again!");
		}
		
		okKnopf.setVisible(true);
		spielfeldEventTextAnzeigen();
	}
	
	@FXML
	public void aufruesten()
	{
		BebaubaresFeld kf = (BebaubaresFeld) spiel.getSpielfelder()[anzeigeIndex];
		kf.zubau();
		
		textupdate();
		anzeigen(bilder[spiel.getIndexOfSpielfeld(kf)]);
	}
	
	
	@FXML
	public void zugAbschliessen()
	{
		if(spiel.getMomentanenSpieler().getGold()>=0)
		{
			spiel.getMomentanenSpieler().paralyseZeitVergeht();
			spiel.nächsterSpieler();
			textupdate();
			sdb.updateGame();
			
			eventkartenText.setText("");
			
			
			feldkarte.setVisible(false);
			eventkarte.setVisible(false);
			spielfeldTextGP.setVisible(false);
			spielfeldTextGP.setVisible(false);
			vergroesserung.setVisible(true);
			okKnopf.setVisible(true);
			
			
			try
			{
				if(spiel.getMomentanenSpieler().isGefangen())
				{
					throw new SpielerGefangenException();
				}
				else if(spiel.getMomentanenSpieler().isBewegungsunfähig())
				{
					throw new SpielerBewegungsunfähigException();
				}
				move.setVisible(true);
			}
			catch(SpielerBewegungsunfähigException e)
			{
				spielfeldEventText.setText("You can't move.");
				spielfeldEventTextAnzeigen();
				
			}
			catch (SpielerGefangenException e)
			{
				if(spiel.getSpielfelder()[spiel.getMomentanenSpieler().getPosition()] instanceof Gefängnisfeld)
				{
					spiel.getSpielfelder()[spiel.getMomentanenSpieler().getPosition()].funktion(this);
				}
				
				aktiviereGefangenenAuswahl();
			}
		}
		else
		{
			
			try
			{
				FXMLLoader loader = new FXMLLoader(StartscreenController.class.getResource("Endscreen.fxml"));
				Scene scene = new Scene((Parent) loader.load());
				Main.primaryStage.setScene(scene);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	@FXML
	public void buyHoverOn()
	{
		buy.setTextFill(Color.GREEN);
	}
	@FXML
	public void buyHoverOff()
	{
		buy.setTextFill(Color.BLACK);
	}
	
	@FXML
	public void dontBuyHoverOn()
	{
		dontBuy.setTextFill(Color.RED);
	}
	@FXML
	public void dontBuyHoverOff()
	{
		dontBuy.setTextFill(Color.BLACK);
	}
	
	@FXML
	public void okHoverOn()
	{
		okKnopf.setTextFill(Color.YELLOW);
		
		if(spiel.getPaschcounter()==0)
		{
			okKnopf.setText("Next players turn!");
		}
		else
		{
			okKnopf.setText("Roll again!");
		}
	}
	@FXML
	public void okHoverOff()
	{
		okKnopf.setTextFill(Color.WHITE);
		okKnopf.setText("Ok!");
	}
	
	@FXML
	public void pay50gHoverOn()
	{
		pay50g.setTextFill(Color.YELLOW);
	}
	@FXML
	public void pay50gHoverOff()
	{
		pay50g.setTextFill(Color.WHITE);
	}
	
	@FXML
	public void t2rdHoverOn()
	{
		t2rd.setTextFill(Color.YELLOW);
	}
	@FXML
	public void t2rdHoverOff()
	{
		t2rd.setTextFill(Color.WHITE);
	}
	
	@FXML
	public void hausbauHoverOn()
	{
		hausbau.setTextFill(Color.GREEN);
	}
	@FXML
	public void hausbauHoverOff()
	{
		hausbau.setTextFill(Color.BLACK);
	}
	
	public void setWürfel(int[] würfel)
	{
		wuerfelL.setImage(new Image("bilder/würfel/Würfel " + (würfel[0]) + ".jpg"));
		wuerfelR.setImage(new Image("bilder/würfel/Würfel " + (würfel[1]) + ".jpg"));
	}


	public Text getSpielfeldEventText() {
		return spielfeldEventText;
	}
}
