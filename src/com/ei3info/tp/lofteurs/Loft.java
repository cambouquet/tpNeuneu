/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Dimension;
import java.util.LinkedList;

/**
 * @author Camille
 * 
 */
public class Loft {

	private int tailleLoft;
	private ZoneGraphique zone;
	private LinkedList<ObjetDessinable> listeObjets;
	private LoftPanel loftPanel;
	
	public Loft(int tailleLoft, ZoneGraphique zone) {
		this.tailleLoft = tailleLoft;
		this.zone = zone;
		this.listeObjets = new LinkedList<ObjetDessinable>();
		this.loftPanel = new LoftPanel(listeObjets);
		loftPanel.setPreferredSize(new Dimension(tailleLoft * ObjetPositionnable.tailleX, tailleLoft * ObjetPositionnable.tailleY));
		loftPanel.setSize(tailleLoft * ObjetPositionnable.tailleX, tailleLoft * ObjetPositionnable.tailleY);
		this.zone.setLoftPanel(loftPanel);
	}

	public void remplissageAleatoire(float f) {
		int nbrCaseARemplir = (int) (f * tailleLoft);
		for (int i = 0 ; i < nbrCaseARemplir ; i ++)
		{
			this.add(new Pizza((int)(Math.random()*(tailleLoft - 1)),
						(int)(Math.random()*(tailleLoft - 1))));
		}
	}

	public void add(ObjetDessinable objet) {
		listeObjets.add(objet);
		loftPanel.updateListeObjets(listeObjets);
	}

	public void go() {
		while (!listeObjets.isEmpty())
		{
			for (ObjetDessinable objet : listeObjets)
			{
				if (objet instanceof Neuneu)
				{
					Neuneu neuneu = (Neuneu) objet;
					neuneu.seDeplacer();
					neuneu.manger();
					neuneu.seReproduire();
					neuneu.mourrir();
				}
			}
		}
	}
	
	public int getTailleLoft()
	{
		return this.tailleLoft;
	}

}
