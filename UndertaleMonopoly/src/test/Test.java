package test;

import application.spiel.Würfel;

public class Test {

	public static void main(String[] args) {
		Würfel w = new Würfel();
		for(int i = 0; i<2; i++)
		{
			System.out.println(w.würfeln());
		}
	}
	

	
}
