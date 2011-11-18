/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Graphics;

/**
 * interface des objets dessinables ; ils devront simplement implanter une m�thode de dessin
 * � partir d'un contexte graphique pass� par l'application
 * 
 * @author moreau
 *
 */
public interface ObjetDessinable {
	/**
	 * fonction de dessin ; à surcharger
	 * @param g le contexte graphique
	 */
	public void dessinerObjet(Graphics g);

}
