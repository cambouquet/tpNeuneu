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
public abstract class Nourriture extends ObjetPositionnable {
	protected Nourriture(int x, int y) {
		super(x, y);
	}

	protected Color couleur = Color.black;

	@Override
	public void dessinerObjet(Graphics g) {
		g.setColor(Color.red);
		g.fillRoundRect(posX * tailleX, posY * tailleY, tailleX - 4,
				tailleY - 4, tailleX, tailleY);
	}
}
