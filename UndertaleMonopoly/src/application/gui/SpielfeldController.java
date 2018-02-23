package application.gui;


import java.awt.Label;

import application.spiel.Spiel;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

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
	
	public static Spiel spiel;
	private final Image[] bilder = {new Image("bilder/spielfelder/Start.png"), new Image("bilder/spielfelder/Feld1.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld2.png"), new Image("bilder/spielfelder/Flowey.png"), new Image("bilder/spielfelder/Riverlady.png"), new Image("bilder/spielfelder/Feld3.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld4.png"), new Image("bilder/spielfelder/Feld5.png"), new Image("bilder/spielfelder/Gefängnis.png"), new Image("bilder/spielfelder/Feld6.png"), new Image("bilder/spielfelder/Ice-Fabric.png"), new Image("bilder/spielfelder/Feld7.png"), new Image("bilder/spielfelder/Feld8.png"), new Image("bilder/spielfelder/Riverlady.png"), new Image("bilder/spielfelder/Feld9.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld10.png"), new Image("bilder/spielfelder/Feld11.png"),  new Image("bilder/spielfelder/FreeParking.png"), new Image("bilder/spielfelder/Feld12.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld13.png"), new Image("bilder/spielfelder/Feld14.png"), new Image("bilder/spielfelder/Riverlady.png"), new Image("bilder/spielfelder/Feld15.png"), new Image("bilder/spielfelder/Feld16.png"), new Image("bilder/spielfelder/Core.png"), new Image("bilder/spielfelder/Feld17.png"), new Image("bilder/spielfelder/Gefängnis.png"), new Image("bilder/spielfelder/Feld18.png"), new Image("bilder/spielfelder/Feld19.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld20.png"), new Image("bilder/spielfelder/Riverlady.png"), new Image("bilder/spielfelder/kartenfeld.png"), new Image("bilder/spielfelder/Feld21.png"), new Image("bilder/spielfelder/strafe_judgment_hall.png") , new Image("bilder/spielfelder/Feld22.png")};

	@SuppressWarnings("static-access")
	@FXML
	public void richtigStellen()
	{
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
		int[] würfel = spiel.momentanenSpielerBewegen();
		
		wuerfelL.setImage(new Image("bilder/würfel/Würfel " + (würfel[0]) + ".jpg"));
		wuerfelR.setImage(new Image("bilder/würfel/Würfel " + (würfel[1]) + ".jpg"));
		int zuBewegenderSpieler = spiel.getAmZug();
		
		if(spiel.getPaschcounter()==0)
		{
			zuBewegenderSpieler -= 1;
			
			if(zuBewegenderSpieler<0)
			{
				zuBewegenderSpieler = spiel.getSpieler().length-1;
			}
		}
		
		int spielerPosition = spiel.getSpieler()[zuBewegenderSpieler].getPosition();
		
		int[] position = spiel.getTabellenposition(spielerPosition);
		switch(zuBewegenderSpieler)
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
		
		textupdate();
	}
	
	private void textupdate()
	{
		spieler.setText("Spieler " + (spiel.getAmZug() + 1));
		
		goldmenge.setText(spiel.getSpieler()[spiel.getAmZug()].getGold() + " G");
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
		
		vergroesserung.setImage(bilder[index]);
	}
	
	@FXML
	public void umdrehen()
	{
		
	}
	
	
	
	
	
}
