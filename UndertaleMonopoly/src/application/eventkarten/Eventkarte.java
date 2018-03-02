package application.eventkarten;

import application.gui.SpielfeldController;

public abstract class Eventkarte {

	/*
	 * Monsterkid schenkt dir 50G. [+50G]
	 * Sans verkauft dir einen Hotdog. [-20G]
	 * Du bezahlst Temmies College. [-200G]
	 * Du kaufst Nice Cream. [-50G]
	 * 👎︎♓︎♏︎⬧︎ ♓︎⬧︎⧫︎ ♓︎⬧︎⧫︎ ♏︎♓︎■︎ ❄︎♏︎⌧︎⧫︎ ♎︎♏︎■︎ ♎︎◆︎ ■︎♓︎♍︎♒︎⧫︎ ●︎♏︎⬧︎♏︎■︎ &︎♋︎■︎■︎⬧︎⧫︎. ☯︎✋︎■︎ ♎︎♏︎❒︎ ☪︎◆︎&︎◆︎■︎♐︎⧫︎ ♓︎⬧︎⧫︎ ♋︎●︎●︎♏︎⬧︎ ❖︎♏︎❒︎♍︎♒︎❒︎□︎❍︎⧫︎📪︎ 📄︎🗐︎☸︎ [+100G bis -100G Zufaellig] (Zeichen können nicht in einem String gespeichert werden)
	 * Papyrus faengt dich. [Ins Gefaengnis]
	 * Papyrus schuetzt dich vor Undyne. [1x aus dem Gefaengnis] (vllt. Du erfaehrst, dass Papyrus die Tuer manchmal nicht abschließt)
	 * Du triffst das Goner Kid, du bist starr vor Schock. [Du kannst dich eine Runde nicht bewegen]
	 * Sans laedt dich zu Grillby's ein. [Gehe zu Grillby's]
	 * Du triffst den Faehrmann. [Gehe zum naechsten Hafen]
	 * Bugerpants gibt dir einen Lebensrat. [Lebe nicht wie ich, ich bin 19 Jahre alt und habe mein ganzes Leben verschwendet. Du bist verzweifelt und kannst dich nicht fuer eine Rund bewegen.]
	 * Papyrus hat fuer dich gekocht, du lehnst ab es zu essen. [Du fuehlst dich schlecht. Du musst fuer eine Runde aussetzen!]
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
