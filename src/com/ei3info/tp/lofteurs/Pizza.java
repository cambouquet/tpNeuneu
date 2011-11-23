/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Color;

/**
 * Les Pizzas. /n
 * Contient les propriétés spécifiques aux pizzas.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class Pizza extends Nourriture 
{
    /**
     * Crée une nouvelle pizza.
     * @param x
     *          Sa position initiale en X.
     * @param y
     *          Sa position initiale en Y.
     */
	public Pizza(int x, int y) {
		super(x, y);
		System.out.println("Pizza placée en " + posX + ":" + posY);
		couleur = Color.RED;
	}

    @Override
    /**
     * Message de consommation de pizza.
     */
    public String getDescriptionConsommation()
    {
        return new String("Une pizza. Miam !");
    }
}
