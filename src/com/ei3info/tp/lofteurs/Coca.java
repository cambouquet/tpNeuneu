package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Le Coca. /n
 * Contient les propriétés spécifiques au Coca.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class Coca extends Nourriture {

    /**
     * Crée une nouvelle bouteille de coca..
     * @param x
     *          Sa position initiale en X.
     * @param y
     *          Sa position initiale en Y.
     */
	protected Coca(int x, int y) {
		super(x, y);
		System.out.println("Coca placé en " + posX + ":" + posY);
		couleur = Color.BLACK;
	}

    @Override
    /**
     * Message de consommation de coca.
     */
    public String getDescriptionConsommation()
    {
        return new String("Hmm du Coca-Cola™ !");
    }
}
