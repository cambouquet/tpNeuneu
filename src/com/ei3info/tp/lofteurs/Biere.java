/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Color;

/**
 * @author Camille
 *
 */
public class Biere extends Nourriture {

	protected Biere(int x, int y) {
		super(x, y);
		System.out.println("Bière placée en " + posX + ":" + posY);
		couleur = Color.YELLOW;
	}

    @Override
    public String getDescriptionConsommation()
    {
        return new String("Une bonne bière et ça repart !");
    }
}
