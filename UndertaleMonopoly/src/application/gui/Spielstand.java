package application.gui;

public class Spielstand {

	public final String spielname;
	public final String zuletztGespielt;
	
	public Spielstand(String spielname, String zuletztGespielt)
	{
		this.spielname = spielname;
		this.zuletztGespielt = zuletztGespielt;
	}

	
	
	public String getSpielname() {
		return spielname;
	}

	public String getZuletztGespielt() {
		return zuletztGespielt;
	}
	
	
}
