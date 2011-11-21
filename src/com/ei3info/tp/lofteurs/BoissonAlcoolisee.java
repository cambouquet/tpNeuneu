/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Camille
 *
 */
public class BoissonAlcoolisee extends Nourriture {

	protected BoissonAlcoolisee(int x, int y) {
		super(x, y);
		couleur = Color.YELLOW;
	}

    @Override
    public String getDescriptionConsommation()
    {
        return new String("Une bonne bière et ça repart !");
    }
}
