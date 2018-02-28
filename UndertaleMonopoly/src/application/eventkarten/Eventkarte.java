package application.eventkarten;

import application.gui.SpielfeldController;
import application.spiel.Spieler;

public abstract class Eventkarte {

	/*
	 * Monsterkid schenkt dir 50G. [+50G]
	 * Sans verkauft dir einen Hotdog. [-20G]
	 * Du bezahlst Temmies College. [-200G]
	 * Du kaufst Nice Cream. [-50G]
	 * 👎︎♓︎♏︎⬧︎ ♓︎⬧︎⧫︎ ♓︎⬧︎⧫︎ ♏︎♓︎■︎ ❄︎♏︎⌧︎⧫︎ ♎︎♏︎■︎ ♎︎◆︎ ■︎♓︎♍︎♒︎⧫︎ ●︎♏︎⬧︎♏︎■︎ &︎♋︎■︎■︎⬧︎⧫︎. ☯︎✋︎■︎ ♎︎♏︎❒︎ ☪︎◆︎&︎◆︎■︎♐︎⧫︎ ♓︎⬧︎⧫︎ ♋︎●︎●︎♏︎⬧︎ ❖︎♏︎❒︎♍︎♒︎❒︎□︎❍︎⧫︎📪︎ 📄︎🗐︎☸︎ [+100G bis -100G Zufällig] (Zeichen können nicht in einem String gespeichert werden)
	 * Papyrus fängt dich. [Ins Gefängnis]
	 * Papyrus schützt dich vor Undyne. [1x aus dem Gefängnis] (vllt. Du erfährst, dass Papyrus die Tür manchmal nicht abschließt)
	 * Du triffst das Goner Kid, du bist starr vor Schock. [Du kannst dich eine Runde nicht bewegen]
	 * Sans lädt dich zu Grillby's ein. [Gehe zu Grillby's]
	 * Du triffst den Fährmann. [Gehe zum nächsten Hafen]
	 * Bugerpants gibt dir einen Lebensrat. [Lebe nicht wie ich, ich bin 19 Jahre alt und habe mein ganzes Leben verschwendet. Du bist verzweifelt und kannst dich nicht für eine Rund bewegen.]
	 * Papyrus hat für dich gekocht, du lehnst ab es zu essen. [Du fühlst dich schlecht. Du musst für eine Runde aussetzen!]
	 */
	
	private String text;
	
	public Eventkarte(String text)
	{
		this.text = text;
	}
	
	public void funktion(SpielfeldController sc)
	{
		sc.eventkarteAnzeigen(text);
	}
	
	public String getText()
	{
		return text;
	}
}
