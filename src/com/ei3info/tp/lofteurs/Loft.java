/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.util.LinkedList;

/**
 * @author Camille
 * 
 */
public class Loft {

	private int tailleLoft;
	private ZoneGraphique zone;
	private LinkedList<ObjetDessinable> listeObjets;
	
	public Loft(int tailleLoft, ZoneGraphique zone) {
		this.tailleLoft = tailleLoft;
		this.zone = zone;
	}

	public void remplissageAleatoire(float f) {
		int nbrCaseARemplir = (int) f * tailleLoft;
		for (int i = 0 ; i < nbrCaseARemplir ; i ++)
		{
			this.add(new Pizza((int)(Math.random()*(tailleLoft - 1)),
						(int)(Math.random()*(tailleLoft - 1))));
		}
	}

	public void add(ObjetDessinable objet) {

	}

	public void go() {
		// TODO Auto-generated method stub

	}

}
