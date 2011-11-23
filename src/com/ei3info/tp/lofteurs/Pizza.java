/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Color;

/**
 * @author Camille
 *
 */
public class Pizza extends Nourriture 
{
	public Pizza(int x, int y) {
		super(x, y);
		System.out.println("Pizza placée en " + posX + ":" + posY);
		couleur = Color.RED;
	}

    @Override
    public String getDescriptionConsommation()
    {
        return new String("Une pizza. Miam !");
    }
}
