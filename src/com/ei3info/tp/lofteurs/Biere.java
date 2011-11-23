/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Color;

/**
 * Les Bières. /n
 * Contient les propriétés spécifiques aux bières.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class Biere extends Nourriture {

    /**
     * Crée une nouvelle bière.
     * @param x
     *          Sa position initiale en X.
     * @param y
     *          Sa position initiale en Y.
     */
	protected Biere(int x, int y) {
		super(x, y);
		System.out.println("Bière placée en " + posX + ":" + posY);
		couleur = Color.YELLOW;
	}

    @Override
    /**
     * Message de consommation de bière.
     */
    public String getDescriptionConsommation()
    {
        return new String("Une bonne bière et ça repart !");
    }
}
